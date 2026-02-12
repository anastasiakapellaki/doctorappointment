package com.example.demo.repository;

import com.example.demo.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, String> {
    List<Doctor> findBySpecialtyAndArea(String specialty, String area);
}
