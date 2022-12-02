package com.leadexperience.leadexperince.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Table
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserDataModel {

    @Id
    @SequenceGenerator(name = "lead_user_sequence", sequenceName = "lead_user_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lead_user_sequence")
    private Long id;

    private String userEmail;
    private String number;
    private String password;
    private String userName;

    private String profileUrl = "NO_URL";


    private String fcmToken = "";

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
