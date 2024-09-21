package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import model.Disciplina;

public class DisciplinaController {
    private List<Disciplina> disciplinas;

    public DisciplinaController() {
        disciplinas = new ArrayList<>();
    }

    // Criar nova disciplina
    public void criarDisciplina(String nome, String descricao) {
        Disciplina disciplina = new Disciplina(nome, descricao);
        disciplinas.add(disciplina);
    }

    // Deletar disciplina
    public void deletarDisciplina(int id) {
        disciplinas.removeIf(d -> d.getId() == id);
    }

    // Editar disciplina
    public void editarDisciplina(int id, String novoNome, String novaDescricao) {
        for (Disciplina d : disciplinas) {
            if (d.getId() == id) {
                d.setNome(novoNome);
                d.setDescricao(novaDescricao);
                break;
            }
        }
    }

    // Listar todas as disciplinas
    public List<Disciplina> listarDisciplinas() {
        return disciplinas;
    }

    // Obter uma disciplina por ID
    public Disciplina buscarDisciplinaPorId(int id) {
        for (Disciplina d : disciplinas) {
            if (d.getId() == id) {
                return d;
            }
        }
        return null;
    }

    // Detalhar as Disciplinas
    public String getFormattedAllDisciplinesDetails() {
        StringBuilder sb = new StringBuilder();

        if (disciplinas.isEmpty()) {
            sb.append("Nenhuma disciplina cadastrada.\n");
        } else {
            for (Disciplina disciplina : disciplinas) {
                sb.append(disciplina.getFormattedDisciplineDetails()).append("\n");
                sb.append("==========================\n");
            }
        }

        return sb.toString();
    }

    // Método para salvar dados em arquivo binário
    public void salvarDisciplinasEmArquivo(String caminhoArquivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminhoArquivo))) {
            oos.writeObject(disciplinas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para carregar dados de arquivo binário
    @SuppressWarnings("unchecked")
    public void carregarDisciplinasDeArquivo(String caminhoArquivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(caminhoArquivo))) {
            disciplinas = (List<Disciplina>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
