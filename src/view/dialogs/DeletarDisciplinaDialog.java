package view.dialogs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import controller.DisciplinaController;

public class DeletarDisciplinaDialog extends JDialog {

    private JTextField txtDisciplinaId;
    private final DisciplinaController disciplinaController;

    public DeletarDisciplinaDialog(JFrame parent, DisciplinaController disciplinaController) {
        super(parent, "Deletar Disciplina", true);
        this.disciplinaController = disciplinaController;

        setLayout(new BorderLayout());
        initializeComponents();
        configureDialog(parent);
    }

    // Método para inicializar e adicionar os componentes à janela
    private void initializeComponents() {
        JPanel inputPanel = createInputPanel();
        JPanel buttonPanel = createButtonPanel();

        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Cria e retorna o painel de entrada
    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
        panel.add(new JLabel("ID da Disciplina:"));
        txtDisciplinaId = new JTextField();
        panel.add(txtDisciplinaId);
        return panel;
    }

    // Cria e retorna o painel de botões
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel();
        JButton btnDeletar = createButton("Deletar", e -> handleDeletarAction());
        JButton btnCancelar = createButton("Cancelar", e -> dispose());

        panel.add(btnDeletar);
        panel.add(btnCancelar);
        return panel;
    }

    // Cria um botão e associa a ação fornecida
    private JButton createButton(String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.addActionListener(listener);
        return button;
    }

    // Configura o diálogo
    private void configureDialog(JFrame parent) {
        setSize(300, 150);
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    // Método para lidar com a ação do botão "Deletar"
    private void handleDeletarAction() {
        try {
            int disciplinaId = Integer.parseInt(txtDisciplinaId.getText());

            if (disciplinaController.buscarDisciplinaPorId(disciplinaId) != null) {
                disciplinaController.deletarDisciplina(disciplinaId);
                JOptionPane.showMessageDialog(this, "Disciplina deletada com sucesso!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Disciplina não encontrada.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira um ID válido.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
