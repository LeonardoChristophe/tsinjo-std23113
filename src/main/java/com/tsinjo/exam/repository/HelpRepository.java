package com.tsinjo.exam.repository;


import com.tsinjo.exam.model.Help;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HelpRepository extends JpaRepository<Help, Long> {
    List<Help> findAllByOrderByCreationDateDesc();
}
