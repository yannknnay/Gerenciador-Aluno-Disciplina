package view.dialogs;

import javax.swing.*;
import java.awt.*;


import controller.AlunoController;
import model.Aluno;

public class ListarDisciplinasAlunoDialog extends JDialog {
    private JTextArea textArea;
    private AlunoController alunoController;

    public ListarDisciplinasAlunoDialog(JFrame parent, AlunoController alunoController, int alunoId) {
        super(parent, "Listar Disciplinas do Aluno", true);
        this.alunoController = alunoController;
        setSize(600, 400);
        setLocationRelativeTo(parent);
        createInterface(alunoId);
    }

    private void createInterface(int alunoId) {
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JScrollPane scrollPane = new JScrollPane(textArea);

        JButton btnClose = new JButton("Fechar");
        btnClose.addActionListener(e -> dispose());

        add(scrollPane, BorderLayout.CENTER);
        add(btnClose, BorderLayout.SOUTH);

        listDisciplinasDoAluno(alunoId);
    }

    private void listDisciplinasDoAluno(int alunoId) {
        Aluno aluno = alunoController.buscarAlunoPorId(alunoId);
        if (aluno != null) {
            String disciplinasDetails = aluno.getFormattedDisciplinasMatriculadas();
            textArea.setText(disciplinasDetails);
        } else {
            textArea.setText("Aluno não encontrado.");
        }
    }
}
