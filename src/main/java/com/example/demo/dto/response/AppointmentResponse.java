package com.example.demo.dto.response;


import java.time.LocalDate;
import java.time.LocalTime;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentResponse {
    private Long id;
    private String patientAmka;
    private String doctorAmka;
    private String doctorName;
    private String specialty;
    private String area;
    private LocalDate date;
    private LocalTime time;
    private String reason;
    private String status;
}