package io.api.base.service.dto;

import liquibase.pro.packaged.A;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto {
    private String id_token;
}
