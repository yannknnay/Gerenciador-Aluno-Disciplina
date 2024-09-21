package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


import controller.*;
import view.dialogs.*;


public class TelaInicial extends JFrame {
    private final AlunoController alunoController;
    private final DisciplinaController disciplinaController;
    private final JTabbedPane tabbedPane;

    public TelaInicial(AlunoController alunoController, DisciplinaController disciplinaController) {
        this.alunoController = alunoController;
        this.disciplinaController = disciplinaController;

        // Configurações da janela
        setTitle("Gerenciador de Disciplinas e Alunos");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);


        // Salvar antes de fechar a tela inicial
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int resposta = JOptionPane.showConfirmDialog(
                        TelaInicial.this,
                        "Deseja salvar as informações antes de sair?",
                        "Salvar Dados",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE);

                if (resposta == JOptionPane.YES_OPTION) {
                    // Chama os métodos de salvar
                    disciplinaController.salvarDisciplinasEmArquivo("disciplinas.dat");
                    alunoController.salvarAlunosEmArquivo("alunos.dat");
                    System.exit(0);  // Fecha o programa após salvar
                } else if (resposta == JOptionPane.NO_OPTION) {
                    System.exit(0);  // Fecha o programa sem salvar
                }
            }
        });


        // Utilização do JTabbedPane
        tabbedPane = new JTabbedPane();

        // Organiza a interface
        createInterface();

        setVisible(true);
    }

    // Método principal para criar a interface e adicionar abas
    private void createInterface() {
        // Adiciona abas de disciplinas e alunos
        tabbedPane.addTab("Disciplinas", createDisciplinePanel());
        tabbedPane.addTab("Alunos", createStudentPanel());

        // Adiciona o JTabbedPane ao JFrame
        add(tabbedPane);
    }

    // Método que cria o painel de disciplinas
    private JPanel createDisciplinePanel() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));

        JButton btnCriarDisciplina = createButton("Criar Disciplina", e -> new CriarDisciplinaDialog(this, disciplinaController));
        JButton btnDeletarDisciplina = createButton("Deletar Disciplina", e -> new DeletarDisciplinaDialog(this, disciplinaController));
        JButton btnEditarDisciplina = createButton("Editar Disciplina", e -> new EditarDisciplinaDialog(this, disciplinaController));
        JButton btnListarDisciplinas = createButton("Listar Disciplinas", e -> new ListarDisciplinasDialog(this, disciplinaController));
        JButton btnCarregardisciplinas = createButton("Carregar Informações das disciplinas", e -> {
            disciplinaController.carregarDisciplinasDeArquivo("disciplina.yann");
            JOptionPane.showMessageDialog(this, "Dados das disciplinas Carregados com Sucesso!!");
        });

        panel.add(btnCriarDisciplina);
        panel.add(btnDeletarDisciplina);
        panel.add(btnEditarDisciplina);
        panel.add(btnListarDisciplinas);
        panel.add(btnCarregardisciplinas);

        return panel;
    }

    // Método que cria o painel de alunos
    private JPanel createStudentPanel() {
        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));

        JButton btnCriarAluno = createButton("Adicionar Aluno", e -> new CriarAlunoDialog(this, alunoController));
        JButton btnMatricularAluno = createButton("Matricular Aluno", e -> new MatricularAlunoDialog(this, alunoController, disciplinaController));
        JButton btnDesmatricularAluno = createButton("Desmatricular Aluno", e -> new DesmatricularAlunoDialog(this, alunoController, disciplinaController));
        JButton btnAdicionarNotas = createButton("Adicionar Notas", e -> new AdicionarNotasDialog(this, alunoController));
        JButton btnListarDisciplinasAluno = createButton("Listar Disciplinas de um Aluno", e -> listarDisciplinasAluno());
        JButton btnListarAlunos = createButton("Listar Alunos", e -> new ListarAlunosDialog(this, alunoController));

        panel.add(btnCriarAluno);
        panel.add(btnMatricularAluno);
        panel.add(btnDesmatricularAluno);
        panel.add(btnAdicionarNotas);
        panel.add(btnListarDisciplinasAluno);
        panel.add(btnListarAlunos);

        return panel;
    }

    // Método auxiliar para criar um botão com ActionListener
    private JButton createButton(String text, java.awt.event.ActionListener listener) {
        JButton button = new JButton(text);
        button.addActionListener(listener);
        return button;
    }


    // Método para pedir o ID do aluno antes de "chamar" a classe ListarDisciplinasAlunoDialog
    private void listarDisciplinasAluno() {
        String idString = JOptionPane.showInputDialog(this, "Digite o ID do aluno:");
        try {
            int alunoId = Integer.parseInt(idString);
            new ListarDisciplinasAlunoDialog(this, alunoController, alunoId).setVisible(true);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID inválido. Por favor, insira um número.");
        }
    }

}
