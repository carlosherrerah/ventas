package org.bedu.ventas.repository;

import java.util.List;

import org.bedu.ventas.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository  extends JpaRepository<Employee, Long> {
    //List<Employee> findAll();
/*
    @Query(value = "SELECT new com.perea.pereaapp.dto.GananciasDTO(" +
            "SUM(v.cantidad * (v.precioVenta - v.precioCompra))," +
            "SUM(v.cantidad * v.precioCompra)," +
            "SUM((v.cantidad * (v.precioVenta - v.precioCompra)) + (v.cantidad * v.precioCompra))" +
            ")" +
            "FROM Venta v " +
            "WHERE v.fecha BETWEEN :fechaInicio AND :fechaFin")
    GananciasDTO obtenerGanancias(@Param("fechaInicio") Date fechaInicio, @Param("fechaFin") Date fechaFin);
*/
}
