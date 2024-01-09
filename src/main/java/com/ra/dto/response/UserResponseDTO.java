package com.ra.dto.response;

import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserResponseDTO {
    private String token;
    private String userName;
    private Set<String>roles;
}
