package com.ra.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @Column(name = "employee_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer empId;

    @Column(name = "employee_name")
    @NotEmpty(message = "Employee name is empty!")
    private String empName;

    @Column(name = "address")
    @NotEmpty(message = "Address is empty!")
    private String address;

    @Column(name = "dateOfBirth")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "DOB is empty!")
    @Past(message = "Date is not valid")
    private Date dateOfBirth;

    @Column(name = "phone")
    @NotEmpty(message = "Phone is empty!")
    private String phone;
}

