package com.example.demo.dto.response;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
public class DoctorResponse {
    private String amka;
    private String fullName;
    private String specialty;
    private String phone;
    private String area;

    public String getAmka() { return amka; }
    public void setAmka(String amka) { this.amka = amka; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getSpecialty() { return specialty; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }
}
