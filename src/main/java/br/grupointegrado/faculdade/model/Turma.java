package br.grupointegrado.faculdade.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "turmas")
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer ano;

    @Column
    private Integer semestre;

    @ManyToOne
    @JoinColumn(name = "curso_id", referencedColumnName = "id")
    private Curso curso;

    @OneToMany(mappedBy = "turma")
    @JsonIgnoreProperties("turma")
    private List<Matricula> matriculas;

    @PrePersist
    @PreUpdate
    private void validarSemestre() {
        if (semestre != 1 && semestre != 2) {
            throw new IllegalArgumentException("O semestre deve ser 1 ou 2.");
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Integer getSemestre() {
        return semestre;
    }

    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public List<Matricula> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(List<Matricula> matriculas) {
        this.matriculas = matriculas;
    }

}
