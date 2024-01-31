package org.bedu.ventas.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Employees")
public class Employee /* implements Serializable */ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO
    @Column(name = "employeeid")
    private long employeeid;

    @NotBlank(message = "No puede estar vacio")
    private String lastname;

    //@Column(length = 100, nullable = false)  $$
    private String firstname;

    @Temporal(TemporalType.DATE)
    //@Column(nullable = false, name = "birthdate")  $$ // Nombre de la columna
    private Date birthdate;

    @Temporal(TemporalType.DATE)
    private Date hiredate;

    @OneToMany(mappedBy = "employee")
    private List<Order> orders = new ArrayList<>();

    /*
    @CreationTimestamp(source = SourceType.DB)
    @Column(name = "created_at")
    private Instant createdAt;

    @UpdateTimestamp(source = SourceType.DB)
    @Column(name = "updated_at")
    private Instant updatedAt;

    @OneToOne
    @JoinColumn(name = "medical_record_id", referencedColumnName = "id")
    private MedicalRecord medicalRecord;
    */
}
