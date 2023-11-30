package org.bedu.ventas.service;

import java.util.List;
import java.util.LinkedList;

import org.bedu.ventas.dto.EmployeeDTO;
import org.bedu.ventas.mapper.EmployeeMapper;
import org.bedu.ventas.model.Employee;
import org.bedu.ventas.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class EmployeeServiceImpl implements EmployeeService  {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public List<EmployeeDTO> getEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDTO> data = new LinkedList<>();
        
        for (Employee emp: employees) {
            data.add(employeeMapper.toDTO(emp));
        }

        return data;
        //throw new UnsupportedOperationException("Unimplemented method 'getEmployees'");
    }
    
}
