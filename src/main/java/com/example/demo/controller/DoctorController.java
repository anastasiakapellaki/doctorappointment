package com.example.demo.controller;

import com.example.demo.dto.response.DoctorResponse;
import com.example.demo.service.DoctorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    // GET /doctors?specialty=Cardiology&area=Athens
    @GetMapping
    public List<DoctorResponse> search(@RequestParam String specialty, @RequestParam String area) {
        return doctorService.search(specialty, area);
    }
}
