package com.assessment.managementservice.domain.customer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "Customer")
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "office_email")
    private String officeEmail;

    @Column(name = "personal_email")
    private String personalEmail;

    @Column(name = "family_members")
    private String familyMembers;

    public Customer() {
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", officeEmail='" + officeEmail + '\'' +
                ", personalEmail='" + personalEmail + '\'' +
                ", familyMembers='" + familyMembers + '\'' +
                '}';
    }
}
