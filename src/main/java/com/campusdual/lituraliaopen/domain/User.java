package com.campusdual.lituraliaopen.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity(name = "tuser")
public class User {

    @Id
    @Column(name = "user_")
    private String username;

    private String password;
    private String name;
    private String surname;
    private String email;
    private String nif;

    @Column(name = "birthdate", columnDefinition = "DATE")
    private LocalDate birthdate;

    private String avatar;

    @Column(name = "userblocked", columnDefinition = "DATE")
    private LocalDateTime userblocked;

    @Column(name = "lastpasswordupdate", columnDefinition = "DATE")
    private LocalDateTime lastpasswordupdate;

    private boolean firstlogin;
}
