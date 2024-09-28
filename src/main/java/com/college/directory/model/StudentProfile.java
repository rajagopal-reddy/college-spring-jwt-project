package com.college.directory.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentProfile {
    @Id
    private Long id;
    private String photo;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    private String year;
}
