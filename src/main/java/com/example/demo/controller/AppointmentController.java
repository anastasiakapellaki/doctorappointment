package com.example.demo.controller;

import com.example.demo.dto.request.AppointmentCreateRequest;
import com.example.demo.dto.response.AppointmentResponse;
import com.example.demo.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    // Δες κλεισμένες ώρες για γιατρό σε ημερομηνία
    // GET /appointments/doctor/{doctorAmka}/booked?date=2026-02-15
    @GetMapping("/doctor/{doctorAmka}/booked")
    public List<AppointmentResponse> booked(@PathVariable String doctorAmka,
                                            @RequestParam LocalDate date) {
        return appointmentService.doctorBookedSlots(doctorAmka, date);
    }

    // Κλείσιμο ραντεβού (patientAmka ως query param για απλότητα)
    // POST /appointments?patientAmka=123...
    @PostMapping
    public AppointmentResponse create(@RequestParam String patientAmka,
                                      @RequestBody AppointmentCreateRequest req) {
        return appointmentService.create(patientAmka, req);
    }

    // Ακύρωση
    // PUT /appointments/{id}/cancel?patientAmka=123...
    @PutMapping("/{id}/cancel")
    public ResponseEntity<?> cancel(@RequestParam String patientAmka, @PathVariable Long id) {
        appointmentService.cancel(patientAmka, id);
        return ResponseEntity.ok("CANCELLED");
    }

    // Ραντεβού εβδομάδας (δίνεις μια μέρα μέσα στην εβδομάδα)
    // GET /appointments/week?patientAmka=123...&date=2026-02-12
    @GetMapping("/week")
    public List<AppointmentResponse> week(@RequestParam String patientAmka,
                                          @RequestParam LocalDate date) {
        return appointmentService.week(patientAmka, date);
    }

    // Ραντεβού μήνα
    // GET /appointments/month?patientAmka=123...&year=2026&month=2
    @GetMapping("/month")
    public List<AppointmentResponse> month(@RequestParam String patientAmka,
                                           @RequestParam int year,
                                           @RequestParam int month) {
        return appointmentService.month(patientAmka, year, month);
    }
}
