package com.example.demo.repository;

import com.example.demo.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, String> {
    Optional<Patient> findByAmkaAndPassword(String amka, String password);
}
