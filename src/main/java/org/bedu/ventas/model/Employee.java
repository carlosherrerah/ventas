package org.bedu.ventas.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.Serializable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
/*@Data*/
@Getter
@Setter
@Entity
@Table(name = "Employees")
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employeeid")
    private long employeeid;

    @NotBlank(message = "No puede estar vacio")
    private String lastname;
    private String firstname;
    
    @Temporal(TemporalType.DATE)
    private Date birthdate;

    @Temporal(TemporalType.DATE)
    private Date hiredate;
    
    @OneToMany(mappedBy = "employee")
    private List<Order> orders = new ArrayList<>();

}
