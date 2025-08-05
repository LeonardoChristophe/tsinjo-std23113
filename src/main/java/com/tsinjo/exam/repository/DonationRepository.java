package com.tsinjo.exam.repository;


import com.tsinjo.exam.model.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {
    List<Donation> findAllByOrderByCreationDateDesc();
}
