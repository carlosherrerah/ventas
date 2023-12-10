package org.bedu.ventas.service;
import java.util.*;

import org.bedu.ventas.dto.EmployeeDTO;
import org.bedu.ventas.dto.EmployeeWithOrdersDTO;
import org.bedu.ventas.dto.UpdateEmployeeDTO;
import org.bedu.ventas.exception.EmployeeNotFoundException;

public interface EmployeeService {

    public List<EmployeeDTO> findAll();

    public EmployeeWithOrdersDTO findByIdWithOrders(long employeeId);

    public EmployeeDTO getEmployee(long employeeid);
    public void deleteEmployee(long employeeid);

    public void update (long employeeid, UpdateEmployeeDTO data) throws EmployeeNotFoundException;

}
