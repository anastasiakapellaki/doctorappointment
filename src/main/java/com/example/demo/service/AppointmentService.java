package com.example.demo.service;

import com.example.demo.domain.Appointment;
import com.example.demo.domain.Doctor;
import com.example.demo.domain.Patient;
import com.example.demo.dto.request.AppointmentCreateRequest;
import com.example.demo.dto.response.AppointmentResponse;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.repository.DoctorRepository;
import com.example.demo.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public AppointmentService(AppointmentRepository appointmentRepository,
                              PatientRepository patientRepository,
                              DoctorRepository doctorRepository) {
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    public List<AppointmentResponse> doctorBookedSlots(String doctorAmka, LocalDate date) {
        return appointmentRepository.findByDoctor_AmkaAndDateAndStatus(doctorAmka, date, "BOOKED")
                .stream().map(this::toResponse).toList();
    }

    public AppointmentResponse create(String patientAmka, AppointmentCreateRequest req) {
        Patient p = patientRepository.findById(patientAmka)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Doctor d = doctorRepository.findById(req.getDoctorAmka())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        boolean taken = appointmentRepository.existsByDoctor_AmkaAndDateAndTimeAndStatus(
                d.getAmka(), req.getDate(), req.getTime(), "BOOKED"
        );
        if (taken) throw new RuntimeException("Slot not available");

        Appointment a = new Appointment();
        a.setPatient(p);
        a.setDoctor(d);
        a.setDate(req.getDate());
        a.setTime(req.getTime());
        a.setReason(req.getReason());
        a.setStatus("BOOKED");

        return toResponse(appointmentRepository.save(a));
    }

    public void cancel(String patientAmka, Long appointmentId) {
        Appointment a = appointmentRepository.findByIdAndPatient_Amka(appointmentId, patientAmka)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        a.setStatus("CANCELLED");
        appointmentRepository.save(a);
    }

    public List<AppointmentResponse> week(String patientAmka, LocalDate anyDate) {
        LocalDate monday = anyDate.with(DayOfWeek.MONDAY);
        LocalDate sunday = monday.plusDays(6);
        return appointmentRepository.findByPatient_AmkaAndDateBetweenAndStatus(patientAmka, monday, sunday, "BOOKED")
                .stream().map(this::toResponse).toList();
    }

    public List<AppointmentResponse> month(String patientAmka, int year, int month) {
        LocalDate first = LocalDate.of(year, month, 1);
        LocalDate last = first.withDayOfMonth(first.lengthOfMonth());
        return appointmentRepository.findByPatient_AmkaAndDateBetweenAndStatus(patientAmka, first, last, "BOOKED")
                .stream().map(this::toResponse).toList();
    }

    private AppointmentResponse toResponse(Appointment a) {
        AppointmentResponse r = new AppointmentResponse();
        r.setId(a.getId());
        r.setPatientAmka(a.getPatient().getAmka());

        r.setDoctorAmka(a.getDoctor().getAmka());
        r.setDoctorName(a.getDoctor().getFullName());
        r.setSpecialty(a.getDoctor().getSpecialty());
        r.setArea(a.getDoctor().getArea());

        r.setDate(a.getDate());
        r.setTime(a.getTime());
        r.setReason(a.getReason());
        r.setStatus(a.getStatus());
        return r;
    }
}
