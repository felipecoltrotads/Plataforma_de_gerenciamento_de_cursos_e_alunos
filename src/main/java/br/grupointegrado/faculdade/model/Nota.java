package br.grupointegrado.faculdade.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name= "notas")
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Double nota;

    @Column
    private Date data_lancamento;

    @ManyToOne
    @JoinColumn(name = "matricula_id", referencedColumnName = "id")
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "disciplina_id", referencedColumnName = "id")
    private Professor professor;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public Date getData_lancamento() {
        return data_lancamento;
    }

    public void setData_lancamento(Date data_lancamento) {
        this.data_lancamento = data_lancamento;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
