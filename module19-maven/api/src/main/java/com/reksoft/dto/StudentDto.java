package com.reksoft.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public record StudentDto(
    String name,
    String surname,
    Integer age
) {
}
