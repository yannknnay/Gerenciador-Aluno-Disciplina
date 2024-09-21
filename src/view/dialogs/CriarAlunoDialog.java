package view.dialogs;

import javax.swing.*;
import java.awt.*;

import controller.AlunoController;

public class CriarAlunoDialog extends JDialog {

    private JTextField txtNome;
    private final AlunoController alunoController;

    public CriarAlunoDialog(JFrame parent, AlunoController alunoController) {
        super(parent, "Criar Aluno", true);
        this.alunoController = alunoController;
        setLayout(new BorderLayout());

        // Configuração da interface
        add(createInputPanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);

        setSize(300, 150);
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    // Método para criar o painel de entrada de dados
    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        inputPanel.add(new JLabel("Nome do Aluno:"));
        txtNome = new JTextField();
        inputPanel.add(txtNome);
        return inputPanel;
    }

    // Método para criar o painel de botões
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        JButton btnSalvar = new JButton("Salvar");
        JButton btnCancelar = new JButton("Cancelar");

        btnSalvar.addActionListener(e -> salvarAluno());
        btnCancelar.addActionListener(e -> dispose());

        buttonPanel.add(btnSalvar);
        buttonPanel.add(btnCancelar);

        return buttonPanel;
    }

    // Método para salvar o aluno
    private void salvarAluno() {
        String nome = txtNome.getText();

        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha o nome do aluno.", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            alunoController.criarAluno(nome);
            JOptionPane.showMessageDialog(this, "Aluno criado com sucesso!");
            dispose();
        }
    }
}

