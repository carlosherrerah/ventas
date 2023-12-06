package org.bedu.ventas.controller;

import java.util.List;

import org.bedu.ventas.dto.EmployeeDTO;
import org.bedu.ventas.dto.EmployeeWithOrdersDTO;
import org.bedu.ventas.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("saludo")
    public String all() {
        return "Hello world Cruelzote";
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeDTO> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public EmployeeDTO getEmployee(@PathVariable Long id) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO = employeeService.getEmployee(id);
        return employeeDTO;
    }

    // Delete mapping
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
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