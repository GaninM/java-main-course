package com.reksoft.module1rest.mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.ReportingPolicy.ERROR;

@org.mapstruct.MapperConfig(
    componentModel = SPRING,
    unmappedTargetPolicy = ERROR
)
public interface MapperConfig {

}
