package com.example.demo.service;

import com.example.demo.domain.Doctor;
import com.example.demo.dto.response.DoctorResponse;
import com.example.demo.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public List<DoctorResponse> search(String specialty, String area) {
        return doctorRepository.findBySpecialtyAndArea(specialty, area)
                .stream().map(this::toResponse).toList();
    }

    private DoctorResponse toResponse(Doctor d) {
        DoctorResponse r = new DoctorResponse();
        r.setAmka(d.getAmka());
        r.setFullName(d.getFullName());
        r.setSpecialty(d.getSpecialty());
        r.setPhone(d.getPhone());
        r.setArea(d.getArea());
        return r;
    }
}
