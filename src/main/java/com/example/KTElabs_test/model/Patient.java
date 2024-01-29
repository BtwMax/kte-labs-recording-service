package com.example.KTElabs_test.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private UUID uuid;

    @Column
    private String fullName;

    @Column
    private LocalDate birthday;

    @Column
    private String email;

    @Column
    private String phoneNumber;
}
