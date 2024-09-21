package view.dialogs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import controller.DisciplinaController;
import model.Disciplina;

public class EditarDisciplinaDialog extends JDialog {

    private JTextField txtDisciplinaId;
    private JTextField txtNovoNome;
    private JTextField txtNovaDescricao;
    private DisciplinaController disciplinaController;

    public EditarDisciplinaDialog(JFrame parent, DisciplinaController disciplinaController) {
        super(parent, "Editar Disciplina", true);
        this.disciplinaController = disciplinaController;

        setLayout(new BorderLayout());

        // Cria e adiciona os painéis
        add(createInputPanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);

        // Configurações do JDialog
        setSize(400, 200);
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    // Cria o painel de entrada com campos de texto
    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));

        inputPanel.add(new JLabel("ID da Disciplina:"));
        txtDisciplinaId = new JTextField();
        inputPanel.add(txtDisciplinaId);

        inputPanel.add(new JLabel("Novo Nome:"));
        txtNovoNome = new JTextField();
        inputPanel.add(txtNovoNome);

        inputPanel.add(new JLabel("Nova Descrição:"));
        txtNovaDescricao = new JTextField();
        inputPanel.add(txtNovaDescricao);

        return inputPanel;
    }

    // Cria o painel de botões
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();

        JButton btnSalvar = new JButton("Salvar");
        JButton btnCancelar = new JButton("Cancelar");

        btnSalvar.addActionListener(this::handleSaveAction);
        btnCancelar.addActionListener(e -> dispose());

        buttonPanel.add(btnSalvar);
        buttonPanel.add(btnCancelar);

        return buttonPanel;
    }

    // Lida com a ação do botão "Salvar"
    private void handleSaveAction(ActionEvent e) {
        try {
            int disciplinaId = Integer.parseInt(txtDisciplinaId.getText());
            Disciplina disciplina = disciplinaController.buscarDisciplinaPorId(disciplinaId);

            if (disciplina != null) {
                String novoNome = txtNovoNome.getText();
                String novaDescricao = txtNovaDescricao.getText();

                disciplinaController.editarDisciplina(disciplinaId, novoNome, novaDescricao);
                JOptionPane.showMessageDialog(this, "Disciplina editada com sucesso!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Disciplina não encontrada.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira um ID válido.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}

