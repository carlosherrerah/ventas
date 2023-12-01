package org.bedu.ventas.model;

import java.util.Date;
import java.io.Serializable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "Employees")
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employeeid")
    private Long Id;

    @NotBlank(message = "No puede estar vacio")
    private String lastname;
    private String firstname;
    
    @Temporal(TemporalType.DATE)
    private Date birthdate;

    private Date hiredate;
    
}
