package com.trong.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "educational_level")
@Data
@ToString
@EqualsAndHashCode(exclude = "recruitments")
public class EducationalLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "educationalLevel", cascade = CascadeType.ALL)
    private Set<Recruitment> recruitments;
}
