package org.bedu.ventas.mapper;


import org.bedu.ventas.dto.UpdateEmployeeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Optional;

import org.bedu.ventas.dto.CreateEmployeeDTO;
import org.bedu.ventas.dto.EmployeeDTO;
import org.bedu.ventas.dto.EmployeeWithOrdersDTO;
import org.bedu.ventas.model.Employee;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
    componentModel = "spring", 
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    uses = OrderMapper.class
)
public interface EmployeeMapper {
 
    EmployeeDTO toDTO(Employee model);

    List<EmployeeDTO> toDTO(List<Employee> model);

    @Mapping(source = "orders", target = "orders", qualifiedByName = "ordersDTOList")
    EmployeeWithOrdersDTO toDTOWithOrders(Employee model);

    @Mapping(target = "employeeid", ignore = true)
    //@Mapping(source = "medicalRecordId", target = "medicalRecord.id")    
    Employee toModel(CreateEmployeeDTO data);

    @Mapping(target = "employeeid", ignore = true)
    void update (@MappingTarget Employee employee, UpdateEmployeeDTO data);

    EmployeeDTO toDTO(Optional<Employee> optionalEmployee);  // checar
    
}
 