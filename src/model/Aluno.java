package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Aluno implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int contadorId = 1; // Contador estático para gerar IDs automáticos
    private int id;
    private String nome;
    private ArrayList<Double> notasRegulares; // Três notas regulares
    private double notaFinal; // Nota da prova final
    private String situacao; // Aprovado, Reprovado, Recuperacao
    private List<Disciplina> disciplinasMatriculadas;

    public Aluno(String nome) {
        this.id = contadorId++; // ID único atribuído automaticamente
        this.nome = nome;
        this.notasRegulares = new ArrayList<>();
        this.disciplinasMatriculadas = new ArrayList<>();
        this.notaFinal = 0; // Inicializa a nota final
        this.situacao = "Não Avaliado"; // Inicializa a situação
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

    public ArrayList<Double> getNotasRegulares() {
        return notasRegulares;
    }

    public void setNotasRegulares(ArrayList<Double> notasRegulares) {
        this.notasRegulares = notasRegulares;
        determinarSituacao();
    }

    public double getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(double notaFinal) {
        this.notaFinal = notaFinal;
        verificarAprovacaoFinal(); // Atualiza a situação após definir a nota final
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public List<Disciplina> getDisciplinasMatriculadas() {
        return disciplinasMatriculadas;
    }

    // Método para calcular a média das duas maiores notas
    public double calcularMedia() {
        if (notasRegulares.size() < 2) return 0;
        ArrayList<Double> notas = new ArrayList<>(notasRegulares);
        Collections.sort(notas, Collections.reverseOrder()); // Ordena em ordem decrescente
        return (notas.get(0) + notas.get(1)) / 2;
    }

    // Método para determinar a situação do aluno
    public void determinarSituacao() {
        double media = calcularMedia();
        if (media >= 7.0) {
            this.situacao = "Aprovado";
        } else if (media >= 3.0) {
            this.situacao = "Recuperacao";
        } else {
            this.situacao = "Reprovado";
        }
    }

    // Método para verificar aprovação após a nota final
    public void verificarAprovacaoFinal() {
        if (this.situacao.equals("Recuperacao")) {
            double media = calcularMedia();
            double notaNecessaria = 10 - media;
            if (this.notaFinal >= notaNecessaria) {
                this.situacao = "Aprovado na Final";
            } else {
                this.situacao = "Reprovado na Final";
            }
        }
    }

    // Método para detalhar as disciplinas nas quais um aluno está matriculado
    public String getFormattedDisciplinasMatriculadas() {
        StringBuilder sb = new StringBuilder();
        sb.append("Disciplinas Matriculadas:\n");

        if (disciplinasMatriculadas.isEmpty()) {
            sb.append("\tNenhuma disciplina matriculada.\n");
        } else {
            for (Disciplina disciplina : disciplinasMatriculadas) {
                sb.append("\tID da Disciplina: ").append(disciplina.getId()).append("\n");
                sb.append("\tNome: ").append(disciplina.getNome()).append("\n");
                sb.append("\tDescrição: ").append(disciplina.getDescricao()).append("\n");
                sb.append("\t==========================\n");
            }
        }
        return sb.toString();
    }
}
