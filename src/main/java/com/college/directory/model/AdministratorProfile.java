package com.college.directory.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdministratorProfile {
    @Id
    private Long userId;
    private String photo;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
