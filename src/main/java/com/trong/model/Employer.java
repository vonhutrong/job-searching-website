package com.trong.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "employer")
@Data
@ToString
@EqualsAndHashCode(exclude = {"user", "recruitments"})
public class Employer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private String address;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "contact_email")
    private String contactEmail;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "employer", cascade = CascadeType.ALL)
    private Set<Recruitment> recruitments;
}
