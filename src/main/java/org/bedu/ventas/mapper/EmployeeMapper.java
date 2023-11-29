package org.bedu.ventas.mapper;

import java.util.Optional;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.bedu.ventas.dto.EmployeeDTO;
import org.bedu.ventas.model.Employee;
import org.mapstruct.InjectionStrategy;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface EmployeeMapper {
 
    EmployeeDTO toDTO(Employee model);

    @Mapping(target = "id", ignore = true)
    Employee toModel(Employee dto);
}
