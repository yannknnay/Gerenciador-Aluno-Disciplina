package view.dialogs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import controller.*;
import model.*;

public class DesmatricularAlunoDialog extends JDialog {

    private JTextField txtAlunoId;
    private JTextField txtDisciplinaId;
    private AlunoController alunoController;
    private DisciplinaController disciplinaController;

    public DesmatricularAlunoDialog(JFrame parent, AlunoController alunoController, DisciplinaController disciplinaController) {
        super(parent, "Desmatricular Aluno", true);
        this.alunoController = alunoController;
        this.disciplinaController = disciplinaController;

        setLayout(new BorderLayout());
        add(createInputPanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);

        setSize(300, 150);
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    // Cria o painel de entrada
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

    // Cria o painel de botões
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();

        JButton btnDesmatricular = createButton("Desmatricular", e -> desmatricularAluno());
        JButton btnCancelar = createButton("Cancelar", e -> dispose());

        buttonPanel.add(btnDesmatricular);
        buttonPanel.add(btnCancelar);

        return buttonPanel;
    }

    // Método para criar o botão
    private JButton createButton(String text, ActionListener actionListener) {
        JButton button = new JButton(text);
        button.addActionListener(actionListener);
        return button;
    }

    // Método para desmatricular o aluno
    private void desmatricularAluno() {
        try {
            int alunoId = Integer.parseInt(txtAlunoId.getText());
            int disciplinaId = Integer.parseInt(txtDisciplinaId.getText());

            Aluno aluno = alunoController.buscarAlunoPorId(alunoId);
            Disciplina disciplina = disciplinaController.buscarDisciplinaPorId(disciplinaId);

            if (aluno != null && disciplina != null) {
                alunoController.desmatricularAlunoDeDisciplina(alunoId, disciplina);
                JOptionPane.showMessageDialog(this, "Aluno desmatriculado com sucesso!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Aluno ou disciplina não encontrados.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira IDs válidos.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}

