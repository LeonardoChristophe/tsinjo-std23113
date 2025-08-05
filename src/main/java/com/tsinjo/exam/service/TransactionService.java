package com.tsinjo.exam.service;

import com.tsinjo.exam.model.*;
import com.tsinjo.exam.repository.DonationRepository;
import com.tsinjo.exam.repository.HelpRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

public class TransactionService {






    @Service
    public class TransactionService {
        private final DonationRepository donationRepository;
        private final HelpRepository helpRepository;
        private final VolaService volaService;

        public TransactionService(DonationRepository donationRepository,
                                  HelpRepository helpRepository,
                                  VolaService volaService) {
            this.donationRepository = donationRepository;
            this.helpRepository = helpRepository;
            this.volaService = volaService;
        }

        @Transactional
        public Donation createDonation(Donor donor, Payment payment) {
            payment.setPaymentDate(Instant.now());
            payment.setStatus(Payment.PaymentStatus.VERIFYING);

            Donation donation = new Donation();
            donation.setDonor(donor);
            donation.setPayment(payment);
            donation.setCreationDate(Instant.now());

            // Start async verification
            volaService.verifyPaymentAsync(payment);

            return donationRepository.save(donation);
        }

        @Transactional
        public Help createHelp(Beneficiary beneficiary, Payment payment, String accidentDescription) {
            payment.setPaymentDate(Instant.now());
            payment.setStatus(Payment.PaymentStatus.SUCCEEDED); // Aides sont directement validées

            Help help = new Help();
            help.setBeneficiary(beneficiary);
            help.setPayment(payment);
            help.setAccidentDescription(accidentDescription);
            help.setCreationDate(Instant.now());

            return helpRepository.save(help);
        }

        public List<Donation> getAllDonations() {
            return donationRepository.findAllByOrderByCreationDateDesc();
        }

        public List<Help> getAllHelps() {
            return helpRepository.findAllByOrderByCreationDateDesc();
        }
    }
}
