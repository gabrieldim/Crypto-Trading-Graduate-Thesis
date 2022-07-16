package com.example.cryptotrading.model.dto;

import com.example.cryptotrading.model.User;
import com.example.cryptotrading.model.enumeration.Role;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
public class UserDetailsDto {
    private String username;
    private Role role;

    public static UserDetailsDto of(User user){
        UserDetailsDto detailsDto = new UserDetailsDto();
        detailsDto.username = user.getUsername();
        detailsDto.role = user.getRole();
        return detailsDto;
    }
}
