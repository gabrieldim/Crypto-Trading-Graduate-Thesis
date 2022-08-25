package com.example.cryptotrading.service.implementation;

import com.example.cryptotrading.exceptions.NotEnoughUserResourcesException;
import com.example.cryptotrading.model.User;
import com.example.cryptotrading.repository.UserRepository;
import com.example.cryptotrading.service.UserService;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@Service
public class PDFGeneratorServiceImpl {

    private final UserRepository userRepository;

    private final UserService userService;

    public PDFGeneratorServiceImpl(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public void export(HttpServletResponse response, Integer amountToWithdraw)
            throws IOException, NotEnoughUserResourcesException {

        //ako nema tolku pari na available resources, odma frli exception
        userService.withdrawAmount(amountToWithdraw);

        Document document = new Document(PageSize.A4);

        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(16);

        Paragraph paragraph = new Paragraph("INVOICE PAYMENT CONFIRMATION", fontTitle);
        paragraph.setAlignment(0);


        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(12);
        Paragraph space = new Paragraph("   ",fontParagraph);
        Paragraph paragraph1 = new Paragraph("BLOCK TRADER \n 59 West 46th Street \n New York City, NY 10036. \n"
                + "+585 992 019932 \n payments@blocktrader.com", fontParagraph);

        paragraph1.setAlignment(Paragraph.ALIGN_LEFT);

        //paid to
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedUserUsername = auth.getPrincipal().toString();
        User user = userRepository.findByUsername(loggedUserUsername);
        Font fontPaidTo = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontPaidTo.setSize(13);
        Paragraph paidTo = new Paragraph("Paid to:",fontPaidTo);
        Paragraph paragraph6 = new Paragraph(user.getFirstName() + " " + user.getLastName() +
                "\n Known as: \"" + user.getUsername() + "\"\n +389 78 783 294", fontParagraph);

        paidTo.setAlignment(Paragraph.ALIGN_RIGHT);
        paragraph6.setAlignment(Paragraph.ALIGN_RIGHT);

        space.setAlignment(Paragraph.ALIGN_LEFT);

        //text
        Paragraph text = new Paragraph("By submitting this invoice, we confirm that requested amount of "  + amountToWithdraw + " USD has been paid to the user's account.\n" +
                "\n" +
                "According to our policy for the use of this service, if there are any irregularities, they will be accepted only in the next three working days, otherwise it is considered that the funds have successfully reached the user's account.\n" +
                "\n" +
                "Thank you for using our services. We hope you had a great experience and an even better trade.");

        LocalDate currentTime = LocalDate.now();
        Paragraph time = new Paragraph("Date this message was sent:  " + currentTime.toString());

        document.add(paragraph);
        document.add(space);
        document.add(paragraph1);
        document.add(paidTo);
        document.add(paragraph6);
        document.add(space);
        document.add(space);
        document.add(text);
        document.add(space);
        document.add(time);

        document.close();
    }

}
