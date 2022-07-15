package com.example.cryptotrading.service.implementation;

import com.example.cryptotrading.dto.jsonparser.APIResponseCryptocurrencies;
import com.example.cryptotrading.exceptions.*;
import com.example.cryptotrading.model.AvailableAppCrypto;
import com.example.cryptotrading.model.CryptoInWallet;
import com.example.cryptotrading.model.User;
import com.example.cryptotrading.model.enumeration.Role;
import com.example.cryptotrading.repository.AvailableAppCryptoRepository;
import com.example.cryptotrading.repository.CryptoInWalletRepository;
import com.example.cryptotrading.repository.UserRepository;
import com.example.cryptotrading.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CryptoInWalletRepository cryptoInWalletRepository;
    private final AvailableAppCryptoRepository availableAppCryptoRepository;

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    public UserServiceImplementation(UserRepository userRepository, PasswordEncoder passwordEncoder,
                                     CryptoInWalletRepository cryptoInWalletRepository,
                                     AvailableAppCryptoRepository availableAppCryptoRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.cryptoInWalletRepository = cryptoInWalletRepository;
        this.availableAppCryptoRepository = availableAppCryptoRepository;
    }

    @Override
    public User register(String username, String password, String repeatPassword, String firstName, String lastName, Role role)
            throws InvalidUserCredentialsException, InvalidUserPasswordsException, UserAlreadyExsistsException {

        if(username == null || username.isEmpty() || password == null || password.isEmpty()){
            throw new InvalidUserCredentialsException("Please provide correct user details!");
        }
        if(!password.equals(repeatPassword)){
            throw new InvalidUserPasswordsException("Please provide same passwords!");
        }
        if(this.userRepository.findByUsername(username) != null ){
            throw new UserAlreadyExsistsException("User already exists!");
        }
        String encodedPassword = passwordEncoder.encode(password);

        User user = new User(username, encodedPassword, firstName, lastName, role);
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    @Override
    public void addUSDMoneyInWallet(Double deposit) {
        String loggedUserUsername = auth.getPrincipal().toString();

        User user = userRepository.findByUsername(loggedUserUsername);

        Double availableResourcesUpdate = user.getAvailableResourcesInUSD() + deposit;

        user.setAvailableResourcesInUSD(availableResourcesUpdate);

        userRepository.save(user);
    }

    public void buyCrypto(String currencyName, Double amountToBuy) throws NotEnoughAppResourcesException, NotEnoughUserResourcesException {

        //proveri dali aplikaciajta ima pari vo taa valuta
        AvailableAppCrypto availableAppCrypto = availableAppCryptoRepository.findByAppCurrencyHeldName(currencyName);
        if(availableAppCrypto.getAppCurrencyHeldAmount() < amountToBuy){
            throw new NotEnoughAppResourcesException("Sorry, we don't have enough from this currency!");
        }

        //proveri dali korisnikot ima dovolno pari
        String loggedUserUsername = auth.getPrincipal().toString();
        User user = userRepository.findByUsername(loggedUserUsername);
        if(user.getAvailableResourcesInUSD()<amountToBuy){
            throw new NotEnoughUserResourcesException("Sorry, you don't have enough from this currency!");
        }


        //proveri po kolku pari se prodava taa valuta
        APIResponseCryptocurrencies apiResponseCryptocurrencies = new APIResponseCryptocurrencies();
        Double currentCryptocurrencyPrice = 0.0;
        for(int i=0;i<apiResponseCryptocurrencies.getCryptocurrencyList().size(); i++){
            if(apiResponseCryptocurrencies.getCryptocurrencyList().get(i).getName().equals(currencyName)){
                currentCryptocurrencyPrice = apiResponseCryptocurrencies.getCryptocurrencyList()
                                                        .get(i).getQuote().getUsd().getPrice();
            }
        }

        //proveri dali korisnikot ja ima poseduvano taa valuta
        int ownedCheck = 0;
        for(int i=0; i<user.getCryptoInWallet().size(); i++){
            if(user.getCryptoInWallet().get(i).getCurrencyHeldName().equals(currencyName)){
                Double currentCryptoOwnedAmount = user.getCryptoInWallet().get(i).getCurrencyHeldAmount();

                currentCryptoOwnedAmount += amountToBuy/currentCryptocurrencyPrice;

                user.getCryptoInWallet().get(i).setCurrencyHeldAmount(currentCryptoOwnedAmount);
                ownedCheck = 1;
            }
        }
        if(ownedCheck == 0){ // ako korisnikot ne ja poseduval taa valuta
            CryptoInWallet cryptoInWallet = new CryptoInWallet(currencyName, amountToBuy/currentCryptocurrencyPrice);
            user.getCryptoInWallet().add(cryptoInWallet);
        }


        //namali valuta na aplikacijata
        Double currentAppAmount = availableAppCrypto.getAppCurrencyHeldAmount();
        Double newAppAmount = currentAppAmount - (amountToBuy/currentCryptocurrencyPrice);
        availableAppCrypto.setAppCurrencyHeldAmount(newAppAmount);

        //zachuvaj se vo baza
        availableAppCryptoRepository.save(availableAppCrypto);
        userRepository.save(user);
    }

    public void sellCrypto(String currencyName, Double amountToSell) throws NotEnoughUserResourcesException {
        // zemi ja cenata po koja shto valutata se prodava
        APIResponseCryptocurrencies apiResponseCryptocurrencies = new APIResponseCryptocurrencies();
        Double currentCryptocurrencyPrice = 0.0;
        for(int i=0;i<apiResponseCryptocurrencies.getCryptocurrencyList().size(); i++){
            if(apiResponseCryptocurrencies.getCryptocurrencyList().get(i).getName().equals(currencyName)){
                currentCryptocurrencyPrice = apiResponseCryptocurrencies.getCryptocurrencyList()
                        .get(i).getQuote().getUsd().getPrice();
            }
        }

        // zemi pari od korisnikot po cena na prodazhba
        String loggedUserUsername = auth.getPrincipal().toString();
        User user = userRepository.findByUsername(loggedUserUsername);
        for(int i=0;i<user.getCryptoInWallet().size(); i++){
            if(user.getCryptoInWallet().get(i).getCurrencyHeldName().equals(currencyName)){
                Double currentAvailableAmountCrypto = user.getCryptoInWallet().get(i).getCurrencyHeldAmount();

                if(currentAvailableAmountCrypto<amountToSell){
                    throw new NotEnoughUserResourcesException("You don't have that much crypto to sell!");
                }

                currentAvailableAmountCrypto = currentAvailableAmountCrypto - (amountToSell/currentCryptocurrencyPrice);
                user.getCryptoInWallet().get(i).setCurrencyHeldAmount(currentAvailableAmountCrypto);

                // dodaj mu pari vo cena na dostapni resursi vo USD
                user.setAvailableResourcesInUSD(user.getAvailableResourcesInUSD() + amountToSell*currentCryptocurrencyPrice);

                // dodaj ja valutata na aplikacijata
                AvailableAppCrypto availableAppCrypto = availableAppCryptoRepository.findByAppCurrencyHeldName(currencyName);
                availableAppCrypto.setAppCurrencyHeldAmount(availableAppCrypto.getAppCurrencyHeldAmount() + amountToSell);

                //zachuvaj u baza
                userRepository.save(user);
                availableAppCryptoRepository.save(availableAppCrypto);
                break;
            }
        }





    }

}
