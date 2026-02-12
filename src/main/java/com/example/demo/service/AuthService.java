package com.example.demo.service;



import com.example.demo.domain.Patient;
import com.example.demo.dto.request.RegisterRequest;
import com.example.demo.repository.PatientRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final PatientRepository patientRepository;

    public AuthService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public void register(RegisterRequest req) {
        Patient p = new Patient();
        p.setAmka(req.getAmka());
        p.setFullName(req.getFullName());
        p.setPhone(req.getPhone());
        p.setPassword(req.getPassword());
        patientRepository.save(p);
    }
}
