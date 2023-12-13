package org.bedu.ventas.controller;

import java.util.*;
import jakarta.validation.Valid;
import org.bedu.ventas.dto.EmployeeDTO;
import org.bedu.ventas.dto.EmployeeWithOrdersDTO;
import org.bedu.ventas.service.EmployeeService;
import org.bedu.ventas.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    //No sé sis esto está bien -mabahi
    @Autowired
    private EmployeeServiceImpl service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeDTO> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public EmployeeDTO getEmployee(@PathVariable Long id ) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO = employeeService.getEmployee(id);
        return employeeDTO;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDTO createEmployee(@Valid @RequestBody EmployeeDTO data){
        return service.save(data);
    }
    @GetMapping("/{employeeId}/orders")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeWithOrdersDTO findAllEmployeeOrders(@PathVariable long employeeId) {
        return employeeService.findByIdWithOrders(employeeId);
    }
    
    @GetMapping("saludo")
    public String all(){
        return "Hello world";
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