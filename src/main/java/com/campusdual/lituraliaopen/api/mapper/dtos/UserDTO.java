package com.campusdual.lituraliaopen.api.mapper.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDTO {

    private String username;

    private String password;
    private String name;
    private String surname;
    private String email;
    private String nif;

    private LocalDate birthdate;

    private String avatar;

    private LocalDateTime userblocked;

    private LocalDateTime lastpasswordupdate;

    private boolean firstlogin;
}
