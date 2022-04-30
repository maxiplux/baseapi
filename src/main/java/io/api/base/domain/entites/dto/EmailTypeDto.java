package io.api.base.domain.entites.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class EmailTypeDto {

    @NotBlank
    private Long id;

    private String name;


}
