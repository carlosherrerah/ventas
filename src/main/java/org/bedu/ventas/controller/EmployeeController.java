package org.bedu.ventas.controller;

import java.util.List;

import jakarta.validation.Valid;
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

@Tag(name = "Employee", description = "CRUD de empleados")
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    
    @Operation(summary = "Obtener todos los empleados")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeDTO> findAll() {
        return employeeService.findAll();
    }
    @Operation(summary = "Obtener un empleado por id")
    @GetMapping("/{id}")
    public EmployeeDTO getEmployee(@PathVariable Long id ) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO = employeeService.getEmployee(id);
        return employeeDTO;
    }
    @Operation(summary = "Hola mundo con swagger")
    @GetMapping("saludo")
    public String all(){
        return "Hello world";
    }
    //Delete mapping
    @Operation(summary = "Eliminar un empleado por id")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
    }
    @Operation(summary = "Obtener un empleado con sus ordenes por id")
    @PutMapping("/{employeeid}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update (@PathVariable long employeeid, @Valid @RequestBody UpdateEmployeeDTO data) throws EmployeeNotFoundException {
        employeeService.update(employeeid, data);
    }


    /*
     * @GetMapping("/{id}")
     * private Employee getEmpleadoById(@PathVariable long id) {
     * <Optional> Employee empleado = employeeRepository.findById(id);
     * return empleado;
     * }
     */
}

/*
 * // http://localhost:8585/asesores/asesores/asesor?idAsesor=6
 * 
 * @GetMapping(produces = "application/json", value = { "asesor" })
 * 
 * @ApiOperation(value = "Obtener asesor.")
 * public ResponseEntity<TrAsesorDto> obtenerAsesor(@RequestParam(required =
 * true) long idAsesor) {
 * TrAsesorDto trAsesorDto = new TrAsesorDto();
 * try {
 * trAsesorDto = asesorService.obtenerAsesor(idAsesor);
 * } catch (ExcepcionRecursoNoEncontrado e) {
 * throw new ExcepcionRecursoNoEncontrado(e.getMessage());
 * } catch (ExcepcionErrorInternoDelServidor e) {
 * throw new ExcepcionErrorInternoDelServidor(e.getMessage());
 * }
 * return new ResponseEntity<TrAsesorDto>(trAsesorDto, HttpStatus.OK);
 * }
 */