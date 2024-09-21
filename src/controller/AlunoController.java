package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import model.Aluno;
import model.Disciplina;

public class AlunoController {
    private List<Aluno> alunos;

    public AlunoController() {
        alunos = new ArrayList<>();
    }

    // Criar aluno
    public void criarAluno(String nome) {
        Aluno aluno = new Aluno(nome);
        alunos.add(aluno);
    }

    // Matricular aluno em uma disciplina
    public void matricularAlunoEmDisciplina(int alunoId, Disciplina disciplina) {
        Aluno aluno = buscarAlunoPorId(alunoId);
        if (aluno != null) {
            disciplina.matricularAluno(aluno);
        }
    }

    // Desmatricular aluno de uma disciplina
    public void desmatricularAlunoDeDisciplina(int alunoId, Disciplina disciplina) {
        Aluno aluno = buscarAlunoPorId(alunoId);
        if (aluno != null) {
            disciplina.desmatricularAluno(aluno);
        }
    }

    // Adicionar notas ao aluno
    public void adicionarNotasAoAluno(int alunoId, ArrayList<Double> notas, Double notaFinal) {
        Aluno aluno = buscarAlunoPorId(alunoId);
        if (aluno != null) {
            aluno.setNotasRegulares(notas);
            aluno.setNotaFinal(notaFinal);
            aluno.determinarSituacao();
        }
    }

    // Verificar situação do aluno
    public String verificarSituacaoDoAluno(int alunoId) {
        Aluno aluno = buscarAlunoPorId(alunoId);
        if (aluno != null) {
            return aluno.getSituacao();
        }
        return null;
    }

    // Listar todos os alunos
    public List<Aluno> listarAlunos() {
        return alunos;
    }

    // Listar disciplinas de um aluno por ID
    public List<Disciplina> listarDisciplinasDoAluno(int alunoId) {
        Aluno aluno = buscarAlunoPorId(alunoId);
        if (aluno != null) {
            return aluno.getDisciplinasMatriculadas();
        }
        return null;
    }

    // Buscar aluno por ID
    public Aluno buscarAlunoPorId(int id) {
        for (Aluno a : alunos) {
            if (a.getId() == id) {
                return a;
            }
        }
        return null; // Retorna null se não encontrar
    }

    // Método para salvar dados em arquivo binário
    public void salvarAlunosEmArquivo(String caminhoArquivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminhoArquivo))) {
            oos.writeObject(alunos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para carregar dados de arquivo binário
    @SuppressWarnings("unchecked")
    public void carregarAlunosDeArquivo(String caminhoArquivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(caminhoArquivo))) {
            alunos = (List<Aluno>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Listar todos os alunos com IDs e nomes
    public String listarAlunosComNomesEIds() {
        StringBuilder sb = new StringBuilder();
        for (Aluno aluno : alunos) {
            sb.append("ID: ").append(aluno.getId()).append(", Nome: ").append(aluno.getNome()).append("\n");
        }
        return sb.toString();
    }
}
