package com.tsinjo.exam.service;


import com.tsinjo.exam.model.Payment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;

@Service
public class VolaService {
    private final RestTemplate restTemplate;
    private final String volaApiKey;
    private final String volaApiUrl = "https://42cwka3n4ifcp7ufheyrpmph240iuaxo.lambda-url.eu-west-3.on.aws/v3/verify";

    public VolaService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.volaApiKey = System.getenv("VOLA_API_KEY"); //
    }

    @Async
    public CompletableFuture<Void> verifyPaymentAsync(Payment payment) {

        try {

            Thread.sleep(5000);
            payment.setStatus(Math.random() > 0.2 ?
                    Payment.PaymentStatus.SUCCEEDED :
                    Payment.PaymentStatus.FAILED);

        } catch (InterruptedException e) {
            payment.setStatus(Payment.PaymentStatus.FAILED);
            Thread.currentThread().interrupt();
        }

        return CompletableFuture.completedFuture(null);
    }
}
