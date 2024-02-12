package org.bedu.ventas.mapper;

import java.util.List;

import org.bedu.ventas.dto.OrderDTO;
import org.bedu.ventas.model.Order;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(
    componentModel = "spring", 
    injectionStrategy = InjectionStrategy.CONSTRUCTOR
)
public interface OrderMapper {

    //@Mapping(source = "employee", target = "employee", qualifiedByName = "toEmployeeDTO")
    OrderDTO toDTO(Order model);

    @Named("ordersDTOList")
    default List<OrderDTO> toOrderDtoList(List<Order> sourceList){
        return sourceList
            .stream()
            .map(this::toDTO)
            .toList();
    }
}
