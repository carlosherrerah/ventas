package org.bedu.ventas.repository;

import java.util.List;

import org.bedu.ventas.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository  extends JpaRepository<Employee, Long> {

  // SELECT * FROM movies WHERE title LIKE :title
  List<Employee> findByLastnameContaining(String title);

}
