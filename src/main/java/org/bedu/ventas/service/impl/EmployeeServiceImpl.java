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
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<EmployeeDTO> findAll() {
        List<Employee> data = employeeRepository.findAll();
        // return data.stream().map(employeeMapper::toDTO).toList();
        return employeeMapper.toDTO(data);
    }

    @Override
    public EmployeeDTO getEmployee(long employeeid) throws EmployeeNotFoundException   {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeid);
        if (!optionalEmployee.isPresent()) {
            throw new EmployeeNotFoundException(employeeid);
        }
        employeeDTO = employeeMapper.toDTO(optionalEmployee.get());
        //return optionalEmployee.stream().map(employeeMapper::toDTO).toList().get(0);
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
        // MedicalRecordDTO medicalRecord = medicalRecordService.save();
        Employee model = employeeMapper.toModel(data);
        Employee result = employeeRepository.save(model);
        return employeeMapper.toDTO(result);
    }

    @Override
    @Transactional
    public void updateParcial(long employeeid, EmployeeDTO data) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeid);
        EmployeeDTO employeeDTO = modelMapper.map(optionalEmployee, EmployeeDTO.class); // && vacio
      
        employeeDTO.setLastname(data.getLastname());
        employeeDTO = data;
        Employee employee = modelMapper.map(employeeDTO ,Employee.class);
       
        employeeRepository.save(employee);
    }

    @Override
    public EmployeeWithOrdersDTO findByIdWithOrders(long employeeId) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        return optionalEmployee.isPresent() ? optionalEmployee.map(employeeMapper::toDTOWithOrders).get()
                : new EmployeeWithOrdersDTO();
    }

}
