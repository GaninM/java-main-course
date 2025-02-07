package com.reksoft.module1rest.mapper.soap;

import com.example.soap.tasks.CreateTaskRequest;
import com.example.soap.tasks.Task;
import com.reksoft.module1rest.mapper.MapperConfig;
import com.reksoft.module1rest.persistance.model.TaskEntity;
import com.reksoft.module1rest.persistance.model.UserEntity;
import java.time.LocalDate;
import javax.xml.datatype.XMLGregorianCalendar;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class)
public interface TaskSoapMapper {

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "name", source = "source.name")
  @Mapping(target = "description", source = "source.description")
  @Mapping(target = "creationDate", source = "creationDate")
  @Mapping(target = "deadLine", source = "source.deadLine", qualifiedByName = "covertToLocalDate")
  @Mapping(target = "user", source = "user")
  TaskEntity map(CreateTaskRequest source, UserEntity user, LocalDate creationDate);

  Task map(TaskEntity source);

  @Named("covertToLocalDate")
  default LocalDate covertToLocalDate(XMLGregorianCalendar gregorianCalendar) {
    return LocalDate.of(gregorianCalendar.getYear(), gregorianCalendar.getMonth(),
        gregorianCalendar.getDay());
  }
}
