package org.bedu.ventas.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.bedu.ventas.dto.EmployeeDTO;
import org.bedu.ventas.model.Employee;
import org.mapstruct.InjectionStrategy;

@Mapper(
    componentModel = "spring", 
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    uses = OrderMapper.class
)
public interface EmployeeMapper {
 
    //@Mapping(source = "id", target = "employeeid")
    @Mapping(source = "orders", target = "orders", qualifiedByName = "ordersDTOList")
    EmployeeDTO toDTO(Employee model);

    //@Mapping(source = "employeeid", target = "id", ignore = false)
    Employee toModel(EmployeeDTO dto);
}
 