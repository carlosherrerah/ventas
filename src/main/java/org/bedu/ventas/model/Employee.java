package org.bedu.ventas.model;

import java.util.Date;
import java.io.Serializable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Employees")
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employeeID")
    private Long Id;

    @NotBlank(message = "No puede estar vacio")
    private String LastName;
    private String FirstName;

    @Temporal(TemporalType.DATE)
    private Date BirthDate;

    private Date HireDate;

}
