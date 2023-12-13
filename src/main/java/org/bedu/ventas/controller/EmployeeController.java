package org.bedu.ventas.controller;

import java.util.List;
import jakarta.validation.Valid;

import org.bedu.ventas.dto.CreateEmployeeDTO;
import org.bedu.ventas.dto.EmployeeDTO;
import org.bedu.ventas.dto.EmployeeWithOrdersDTO;
import org.bedu.ventas.dto.UpdateEmployeeDTO;
import org.bedu.ventas.exception.EmployeeNotFoundException;
import org.bedu.ventas.exception.ExcepcionRecursoNoEncontrado;
import org.bedu.ventas.exception.RuntimeException;
import org.bedu.ventas.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Endpoints de Empleados", description = "CRUD de empleados")
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("saludo")
    @ResponseStatus(HttpStatus.OK)
    public String saludar() {
        return "Hello world";
    }

    @Operation(summary = "Obtiene la lista de todos los empleados")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeDTO> findAll() {
        return employeeService.findAll();
    }

    @Operation(summary = "Obtiene la información de un empleado")
    @GetMapping("/{id}")
    public EmployeeDTO getEmployee(@PathVariable Long id) throws EmployeeNotFoundException {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO = employeeService.getEmployee(id);
        return employeeDTO;
    }

    @Operation(summary = "Borra la información de un empleado")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.delete(id);
    }

    @Operation(summary = "Actualiza la información de un empleado")
    @PutMapping("/{employeeid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable long employeeid, @Valid @RequestBody UpdateEmployeeDTO data)
            throws EmployeeNotFoundException {
        employeeService.update(employeeid, data);
    }

    @Operation(summary = "Crea un nuevo empleado")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDTO save(@Valid @RequestBody CreateEmployeeDTO data) {
        return employeeService.save(data);
    }

    @Operation(summary = "Actualizacion Parcial del empleado")
    @PatchMapping("/{employeeid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateParcial(@PathVariable long employeeid, @Valid @RequestBody EmployeeDTO data)
            throws EmployeeNotFoundException {
        employeeService.updateParcial(employeeid, data);
    }

    // @Operation(summary = "Asocia una Orden a un Empleado") Post
    // @Operation(summary = "Obtiene las Ordenes de un Empleado determinado") Get
}
