package org.bedu.ventas.mapper;

import java.util.List;

import org.bedu.ventas.dto.OrderDTO;
import org.bedu.ventas.model.Order;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(
    componentModel = "spring", 
    injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface OrderMapper {
  
    @Mapping(target = "employee.orders", ignore = true)
    //@Mapping(source = "employee", target = "employee", qualifiedByName = "toEmployeeDTO")
    OrderDTO toDTO(Order model);

    @Named("ordersDTOList")
    default List<OrderDTO> toOrderDtoList(List<Order> sourceList){
        return sourceList
            .stream()
            .map(this::toDTO)
            .peek(dto -> dto.setEmployee(null))
            .toList();
    }


    /*@Named("toEmployeeDTO")
    default EmployeeDTO toEmployeeDTO(Employee model) {
        if (model == null) {
            return null;
        }

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmployeeid(model.getId());
        employeeDTO.setFirstname(model.getFirstname());
        employeeDTO.setLastname(model.getLastname());
        employeeDTO.setBirthdate(model.getBirthdate().toString());
        employeeDTO.setHiredate(model.getHiredate().toString());
        
        return employeeDTO;
    }*/
    Order toModel(OrderDTO dto);
}
