package com.example.demo.repository;

import com.example.demo.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    boolean existsByDoctor_AmkaAndDateAndTimeAndStatus(
            String doctorAmka, LocalDate date, java.time.LocalTime time, String status
    );

    List<Appointment> findByPatient_AmkaAndDateBetweenAndStatus(
            String patientAmka, LocalDate from, LocalDate to, String status
    );
}
