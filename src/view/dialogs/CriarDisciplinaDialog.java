package view.dialogs;

import javax.swing.*;
import java.awt.*;

import controller.DisciplinaController;

public class CriarDisciplinaDialog extends JDialog {

    private JTextField txtNome;
    private JTextField txtDescricao;
    private final DisciplinaController disciplinaController;

    public CriarDisciplinaDialog(JFrame parent, DisciplinaController disciplinaController) {
        super(parent, "Criar Disciplina", true);
        this.disciplinaController = disciplinaController;
        setLayout(new BorderLayout());

        // Configuração da interface
        add(createInputPanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);

        setSize(300, 200);
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    // Método para criar o painel de entrada de dados
    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.add(new JLabel("Nome da Disciplina:"));
        txtNome = new JTextField();
        inputPanel.add(txtNome);

        inputPanel.add(new JLabel("Descrição da Disciplina:"));
        txtDescricao = new JTextField();
        inputPanel.add(txtDescricao);

        return inputPanel;
    }

    // Método para criar o painel de botões
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        JButton btnSalvar = new JButton("Salvar");
        JButton btnCancelar = new JButton("Cancelar");

        btnSalvar.addActionListener(e -> salvarDisciplina());
        btnCancelar.addActionListener(e -> dispose());

        buttonPanel.add(btnSalvar);
        buttonPanel.add(btnCancelar);

        return buttonPanel;
    }

    // Método para salvar a disciplina
    private void salvarDisciplina() {
        String nome = txtNome.getText();
        String descricao = txtDescricao.getText();

        if (nome.isEmpty() || descricao.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            disciplinaController.criarDisciplina(nome, descricao);
            JOptionPane.showMessageDialog(this, "Disciplina criada com sucesso!");
            dispose();
        }
    }
}
