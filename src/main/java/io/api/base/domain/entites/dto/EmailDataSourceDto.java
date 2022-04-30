package io.api.base.domain.entites.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailDataSourceDto {
    @NotBlank
    private Long id;


    private String name;
    private String query;
    private String entityName;
}
