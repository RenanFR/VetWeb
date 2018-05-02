package com.vetweb.model;
 //@author renanrodrigues
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "prescricoes")
public class Prescricao implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer prescricaoId;
    double dosagem;
    Period tomarACada;
    LocalDate tomarAte;
    @OneToMany
    List<Medicamento> medicamentos;
}
