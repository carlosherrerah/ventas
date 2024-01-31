package org.bedu.ventas.controller;

import java.util.List;
import jakarta.validation.Valid;

import org.bedu.ventas.dto.CreateEmployeeDTO;
import org.bedu.ventas.dto.EmployeeDTO;
import org.bedu.ventas.dto.EmployeeWithOrdersDTO;
import org.bedu.ventas.dto.UpdateEmployeeDTO;
import org.bedu.ventas.exception.EmployeeNotFoundException;
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

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("saludo")
    @ResponseStatus(HttpStatus.OK)
    public String saludar() {
        return "Hello world";
    }

    @Operation(summary = "Obtiene la lista de todos los empleados", description = "No requiere parámetros", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Lista de empleados") })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeDTO> findAll() {
        return employeeService.findAll();
    }

    @Operation(summary = "Obtiene la información de un empleado", description = "Agrega el id del empleado a la URL", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Empleado encontrado"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Empleado no encontrado") })
    @GetMapping("/{id}")
    public EmployeeDTO getEmployee(@PathVariable Long id) throws EmployeeNotFoundException {
        EmployeeDTO employeeDTO; 
        employeeDTO = employeeService.getEmployee(id);
        return employeeDTO;
    }

    @Operation(summary = "Borra la información de un empleado", description = "Agrega el id del empleado a la URL", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "Empleado borrado"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Empleado no encontrado") })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.delete(id);
    }

    @Operation(summary = "Actualiza la información de un empleado", description = "Agrega el id del empleado a la URL", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "Empleado actualizado"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Empleado no encontrado") })
    @PutMapping("/{employeeid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable long employeeid, @Valid @RequestBody UpdateEmployeeDTO data)
            throws EmployeeNotFoundException {
        employeeService.update(employeeid, data);
    }

    @Operation(summary = "Crea un nuevo empleado", description = "Se debe enviar un JSON con los datos del empleado", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Empleado creado correctamente"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "El empleado no se pudo crear correctamente") })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDTO save(@Valid @RequestBody CreateEmployeeDTO data) {
        return employeeService.save(data);
    }

    @Operation(summary = "Actualizacion Parcial del empleado", description = "Agrega el id del empleado a la URL", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "Empleado actualizado") })
    @PatchMapping("/{employeeid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateParcial(@PathVariable long employeeid, @Valid @RequestBody EmployeeDTO data)  {
        employeeService.updateParcial(employeeid, data);
    }

    // @Operation(summary = "Asocia una Orden a un Empleado") Post
    // @Operation(summary = "Obtiene las Ordenes de un Empleado determinado") Get
    @GetMapping("/{employeeId}/orders")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeWithOrdersDTO findAllEmployeeOrders(@PathVariable long employeeId) throws EmployeeNotFoundException {
        return employeeService.findByIdWithOrders(employeeId);
    }
}
