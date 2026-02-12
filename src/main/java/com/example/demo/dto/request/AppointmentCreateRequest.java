package com.example.demo.dto.request;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentCreateRequest {
    private String doctorAmka;
    private LocalDate date;
    private LocalTime time;
    private String reason;

    public String getDoctorAmka() { return doctorAmka; }
    public void setDoctorAmka(String doctorAmka) { this.doctorAmka = doctorAmka; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public LocalTime getTime() { return time; }
    public void setTime(LocalTime time) { this.time = time; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
}
