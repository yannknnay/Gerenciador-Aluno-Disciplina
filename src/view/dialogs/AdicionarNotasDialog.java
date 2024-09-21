package view.dialogs;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import controller.AlunoController;

public class AdicionarNotasDialog extends JDialog {

    private JTextField txtAlunoId;
    private JTextField txtNota1;
    private JTextField txtNota2;
    private JTextField txtNota3;
    private JTextField txtNotaFinal;
    private final AlunoController alunoController;

    public AdicionarNotasDialog(JFrame parent, AlunoController alunoController) {
        super(parent, "Adicionar Notas", true);
        this.alunoController = alunoController;
        setLayout(new BorderLayout());

        // Configuração da interface
        add(createInputPanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);

        setSize(300, 300);
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    // Método para criar o painel de entrada de dados
    private JPanel createInputPanel() {
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        inputPanel.add(new JLabel("ID do Aluno:"));
        txtAlunoId = new JTextField();
        inputPanel.add(txtAlunoId);

        inputPanel.add(new JLabel("Nota 1:"));
        txtNota1 = new JTextField();
        inputPanel.add(txtNota1);

        inputPanel.add(new JLabel("Nota 2:"));
        txtNota2 = new JTextField();
        inputPanel.add(txtNota2);

        inputPanel.add(new JLabel("Nota 3:"));
        txtNota3 = new JTextField();
        inputPanel.add(txtNota3);

        inputPanel.add(new JLabel("Nota Final (se necessário):"));
        txtNotaFinal = new JTextField();
        inputPanel.add(txtNotaFinal);

        return inputPanel;
    }

    // Método para criar o painel de botões
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        JButton btnSalvar = new JButton("Salvar");
        JButton btnCancelar = new JButton("Cancelar");

        btnSalvar.addActionListener(e -> salvarNotas());
        btnCancelar.addActionListener(e -> dispose());

        buttonPanel.add(btnSalvar);
        buttonPanel.add(btnCancelar);

        return buttonPanel;
    }

    // Método para salvar as notas
    private void salvarNotas() {
        try {
            int alunoId = Integer.parseInt(txtAlunoId.getText());
            double nota1 = Double.parseDouble(txtNota1.getText());
            double nota2 = Double.parseDouble(txtNota2.getText());
            double nota3 = Double.parseDouble(txtNota3.getText());
            Double notaFinal = txtNotaFinal.getText().isEmpty() ? null : Double.parseDouble(txtNotaFinal.getText());

            ArrayList<Double> notas = new ArrayList<>();
            notas.add(nota1);
            notas.add(nota2);
            notas.add(nota3);

            alunoController.adicionarNotasAoAluno(alunoId, notas, notaFinal);
            JOptionPane.showMessageDialog(this, "Notas adicionadas com sucesso!");

            dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, insira valores válidos para as notas.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}

