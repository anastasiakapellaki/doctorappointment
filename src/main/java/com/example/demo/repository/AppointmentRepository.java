package com.example.demo.repository;

import com.example.demo.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    boolean existsByDoctor_AmkaAndDateAndTimeAndStatus(
            String doctorAmka, LocalDate date, LocalTime time, String status);

    List<Appointment> findByDoctor_AmkaAndDateAndStatus(
            String doctorAmka, LocalDate date, String status);

    List<Appointment> findByPatient_AmkaAndDateBetweenAndStatus(
            String patientAmka, LocalDate from, LocalDate to, String status);

    Optional<Appointment> findByIdAndPatient_Amka(Long id, String patientAmka);
}