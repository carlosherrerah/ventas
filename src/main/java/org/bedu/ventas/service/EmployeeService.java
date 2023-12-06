package org.bedu.ventas.service;
import java.util.*;

import org.bedu.ventas.dto.EmployeeDTO;

public interface EmployeeService {

    public List<EmployeeDTO> findAll();
    public EmployeeDTO getEmployee(long employeeid);
    public void deleteEmployee(long employeeid);

}
