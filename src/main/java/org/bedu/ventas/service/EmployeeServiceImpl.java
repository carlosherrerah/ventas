package org.bedu.ventas.service;

import java.util.List;
import java.util.Optional;

import org.bedu.ventas.dto.EmployeeDTO;
import org.bedu.ventas.dto.EmployeeWithOrdersDTO;
import org.bedu.ventas.mapper.EmployeeMapper;
import org.bedu.ventas.model.Employee;
import org.bedu.ventas.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public List<EmployeeDTO> findAll() {
        List<Employee> data = employeeRepository.findAll(); // Truena
        return data.stream().map(employeeMapper::toDTO).toList();
    }

    public EmployeeDTO save(EmployeeDTO data){
        Employee entity = employeeRepository.save(employeeMapper.toModel(data));
        return employeeMapper.toDTO(entity);
    }
    @Override
    public EmployeeWithOrdersDTO findByIdWithOrders(long employeeId) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        return optionalEmployee.isPresent() ? optionalEmployee.map(employeeMapper::toDTOWithOrders).get() : new EmployeeWithOrdersDTO();
    }

    @Override
    public EmployeeDTO getEmployee(long employeeid) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeid);
        return optionalEmployee.stream().map(employeeMapper::toDTO).toList().get(0);
        //return employeeDTO;
    }
    

}
