package com.tsinjo.exam.endpoint.rest.controller.health;


import com.tsinjo.exam.model.Donor;
import com.tsinjo.exam.model.Payment;
import com.tsinjo.exam.service.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Instant;

@Controller
public class TsinjoController {
    private final TransactionService transactionService;

    public TsinjoController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/")
    public String showTransactions(Model model) {
        model.addAttribute("donations", transactionService.getAllDonations());
        model.addAttribute("helps", transactionService.getAllHelps());
        return "transactions";
    }

    @PostMapping("/donate")
    public String createDonation(
            @RequestParam String fullName,
            @RequestParam String email,
            @RequestParam Double amount,
            @RequestParam String paymentMethod,
            @RequestParam String paymentReference) {

        Donor donor = new Donor();
        donor.setFullName(fullName);
        donor.setEmail(email);

        Payment payment = new Payment();
        payment.setAmount(amount);
        payment.setPaymentMethod(paymentMethod);
        payment.setPaymentReference(paymentReference);

        transactionService.createDonation(donor, payment);

        return "redirect:/";
    }
}
