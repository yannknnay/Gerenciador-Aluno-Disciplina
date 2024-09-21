package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Disciplina implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int contadorId = 1; // Contador estático para gerar IDs automáticos
    private int id;
    private String nome;
    private String descricao;
    private List<Aluno> alunosMatriculados;

    public Disciplina(String nome, String descricao) {
        this.id = contadorId++; // ID único atribuído automaticamente
        this.nome = nome;
        this.descricao = descricao;
        this.alunosMatriculados = new ArrayList<>();
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Aluno> getAlunosMatriculados() {
        return alunosMatriculados;
    }

    // Método para adicionar aluno à disciplina
    public void matricularAluno(Aluno aluno) {
        alunosMatriculados.add(aluno);
        aluno.getDisciplinasMatriculadas().add(this); // Relacionamento bidirecional
    }

    // Método para remover aluno da disciplina
    public void desmatricularAluno(Aluno aluno) {
        alunosMatriculados.remove(aluno);
        aluno.getDisciplinasMatriculadas().remove(this); // Relacionamento bidirecional
    }

    // Método para listar as disciplinas
    public String getFormattedDisciplineDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(id).append("\n");
        sb.append("Nome: ").append(nome).append("\n");
        sb.append("Descrição: ").append(descricao).append("\n");
        sb.append("Alunos Matriculados:\n");

        if (alunosMatriculados.isEmpty()) {
            sb.append("\tNenhum aluno matriculado.\n");
        } else {
            for (Aluno aluno : alunosMatriculados) {
                sb.append("\tID do Aluno: ").append(aluno.getId()).append("\n");
                sb.append("\tNome: ").append(aluno.getNome()).append("\n");
                sb.append("\t==========================\n");
            }
        }
        return sb.toString();
    }

}
