package view.dialogs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import controller.*;
import model.*;

public class MatricularAlunoDialog extends JDialog {

    private JTextField txtAlunoId;
    private JTextField txtDisciplinaId;
    private AlunoController alunoController;
    private DisciplinaController disciplinaController;

    public MatricularAlunoDialog(JFrame parent, AlunoController alunoController, DisciplinaController disciplinaController) {
        super(parent, "Matricular Aluno", true);
        this.alunoController = alunoController;
        this.disciplinaController = disciplinaController;

        setLayout(new BorderLayout());
        add(createInputPanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);

        setSize(300, 150);
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    // Cria o painel de entrada com campos de texto para IDs
    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        inputPanel.add(new JLabel("ID do Aluno:"));
        txtAlunoId = new JTextField();
        inputPanel.add(txtAlunoId);

        inputPanel.add(new JLabel("ID da Disciplina:"));
        txtDisciplinaId = new JTextField();
        inputPanel.add(txtDisciplinaId);

        return inputPanel;
    }

    // Cria o painel de botões com ações associadas
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();

        JButton btnMatricular = createButton("Matricular", e -> matricularAluno());
        JButton btnCancelar = createButton("Cancelar", e -> dispose());

        buttonPanel.add(btnMatricular);
        buttonPanel.add(btnCancelar);

        return buttonPanel; // Retorna o painel de botões
    }

    // Cria um botão e associa a ação fornecida
    private JButton createButton(String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.addActionListener(listener);
        return button;
    }

    // Ação do botão "Matricular"
    private void matricularAluno() {
        try {
            int alunoId = Integer.parseInt(txtAlunoId.getText());
            int disciplinaId = Integer.parseInt(txtDisciplinaId.getText());

            Aluno aluno = alunoController.buscarAlunoPorId(alunoId);
            Disciplina disciplina = disciplinaController.buscarDisciplinaPorId(disciplinaId);

            if (aluno != null && disciplina != null) {
                alunoController.matricularAlunoEmDisciplina(alunoId, disciplina);
                JOptionPane.showMessageDialog(this, "Aluno matriculado com sucesso!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Aluno ou disciplina não encontrados.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira IDs válidos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}

