package org.bedu.ventas.mapper;


import org.bedu.ventas.dto.UpdateEmployeeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.bedu.ventas.dto.EmployeeDTO;
import org.bedu.ventas.dto.EmployeeWithOrdersDTO;
import org.bedu.ventas.model.Employee;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.MappingTarget;

@Mapper(
    componentModel = "spring", 
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    uses = OrderMapper.class
)
public interface EmployeeMapper {
 
    EmployeeDTO toDTO(Employee model);

    @Mapping(source = "orders", target = "orders", qualifiedByName = "ordersDTOList")
    EmployeeWithOrdersDTO toDTOWithOrders(Employee model);

    @Mapping(target = "orders", ignore = true)
    Employee toModel(EmployeeDTO dto);

    @Mapping(target = "employeeid", ignore = true)
    void update (@MappingTarget Employee employee, UpdateEmployeeDTO data);
}
 