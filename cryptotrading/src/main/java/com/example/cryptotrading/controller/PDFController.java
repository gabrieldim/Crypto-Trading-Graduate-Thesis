package com.example.cryptotrading.controller;


import com.example.cryptotrading.exceptions.NotEnoughUserResourcesException;
import com.example.cryptotrading.model.dto.WithdrawCashDto;
import com.example.cryptotrading.service.implementation.PDFGeneratorServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api")
@CrossOrigin(value = "*")
public class PDFController {

    private final PDFGeneratorServiceImpl pdfGeneratorService;

    public PDFController(PDFGeneratorServiceImpl pdfGeneratorService) {
        this.pdfGeneratorService = pdfGeneratorService;
    }

    @PostMapping("/generatePDF")
    public void generatePDF(HttpServletResponse response, @RequestBody WithdrawCashDto withdrawCashDto)
            throws IOException, NotEnoughUserResourcesException {
        response.setContentType("application/pdf");
        //response.setContentType("application/octet-stream;charset=UTF-8");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        System.out.println("Amount to withdraw: " + withdrawCashDto.getAmount());

        this.pdfGeneratorService.export(response, Integer.parseInt(withdrawCashDto.getAmount()));
    }


}
