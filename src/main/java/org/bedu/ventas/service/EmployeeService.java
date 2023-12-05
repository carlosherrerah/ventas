package org.bedu.ventas.service;
import java.util.*;

import org.bedu.ventas.dto.EmployeeDTO;
import org.bedu.ventas.dto.EmployeeWithOrdersDTO;

public interface EmployeeService {

    public List<EmployeeDTO> findAll();

    public EmployeeWithOrdersDTO findByIdWithOrders(long employeeId);

    public EmployeeDTO getEmployee(long employeeid);


}
