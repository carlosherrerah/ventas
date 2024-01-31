package org.bedu.ventas.service;
import java.util.*;

import org.bedu.ventas.dto.CreateEmployeeDTO;
import org.bedu.ventas.dto.EmployeeDTO;
import org.bedu.ventas.dto.EmployeeWithOrdersDTO;
import org.bedu.ventas.dto.UpdateEmployeeDTO;
import org.bedu.ventas.exception.EmployeeNotFoundException;

public interface EmployeeService {

    public List<EmployeeDTO> findAll();

    public EmployeeDTO getEmployee(long employeeid) throws EmployeeNotFoundException;

    public void delete(long employeeid);

    public EmployeeDTO save(CreateEmployeeDTO data);

    public void update(long employeeid, UpdateEmployeeDTO data) throws EmployeeNotFoundException;

    public void updateParcial(long employeeid, EmployeeDTO data);  // No Funciona 

    public EmployeeWithOrdersDTO findByIdWithOrders(long employeeId) throws EmployeeNotFoundException;

}
