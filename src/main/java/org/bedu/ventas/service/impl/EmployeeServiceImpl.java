package org.bedu.ventas.service.impl;

import java.util.List;
import java.util.Optional;

import org.bedu.ventas.dto.CreateEmployeeDTO;
import org.bedu.ventas.dto.EmployeeDTO;
import org.bedu.ventas.dto.EmployeeWithOrdersDTO;
import org.bedu.ventas.dto.UpdateEmployeeDTO;
import org.bedu.ventas.exception.EmployeeNotFoundException;
import org.bedu.ventas.mapper.EmployeeMapper;
import org.bedu.ventas.model.Employee;
import org.bedu.ventas.repository.EmployeeRepository;
import org.bedu.ventas.service.EmployeeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public List<EmployeeDTO> findAll() {
        List<Employee> data = employeeRepository.findAll();
        return employeeMapper.toDTO(data);
    }

    @Override
    public EmployeeDTO getEmployee(long employeeid) throws EmployeeNotFoundException   {
        EmployeeDTO employeeDTO;
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeid);
        if (!optionalEmployee.isPresent()) {
            throw new EmployeeNotFoundException(employeeid);
        }
        employeeDTO = employeeMapper.toDTO(optionalEmployee.get());
        return employeeDTO;
    }

    // delete
    @Override
    public void delete(long employeeid) {
        employeeRepository.deleteById(employeeid);
    }

    // update PUT
    @Override
    public void update(long employeeid, UpdateEmployeeDTO data) throws EmployeeNotFoundException {
        Optional<Employee> result = employeeRepository.findById(employeeid);

        if (!result.isPresent()) {
            throw new EmployeeNotFoundException(employeeid);
        }

        Employee employee = result.get();
        employeeMapper.update(employee, data);   // (target, source)
        employeeRepository.save(employee);
    }

    // Insert
    @Transactional
    public EmployeeDTO save(CreateEmployeeDTO data) {
        Employee model = employeeMapper.toModel(data);
        Employee result = employeeRepository.save(model);
        return employeeMapper.toDTO(result);
    }

    @Override
    public EmployeeWithOrdersDTO findByIdWithOrders(long employeeId) throws EmployeeNotFoundException {
        EmployeeWithOrdersDTO employeeWithOrdersDTO; 
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);

        if (!optionalEmployee.isPresent()) {
            throw new EmployeeNotFoundException(employeeId);
        }
        employeeWithOrdersDTO = optionalEmployee.stream().map(employeeMapper::toDTOWithOrders).toList().get(0);
        return employeeWithOrdersDTO;
    }

}
