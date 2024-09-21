package view.dialogs;

import javax.swing.*;
import java.awt.*;

import controller.DisciplinaController;

public class ListarDisciplinasDialog extends JDialog {
    private JTextArea textArea;
    private DisciplinaController disciplinaController;

    public ListarDisciplinasDialog(JFrame parent, DisciplinaController disciplinaController) {
        super(parent, "Listar Disciplinas", true);
        this.disciplinaController = disciplinaController;
        setSize(600, 400);
        setLocationRelativeTo(parent);
        createInterface();
        setVisible(true);
    }

    private void createInterface() {
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JScrollPane scrollPane = new JScrollPane(textArea);

        JButton btnClose = new JButton("Fechar");
        btnClose.addActionListener(e -> dispose());

        add(scrollPane, BorderLayout.CENTER);
        add(btnClose, BorderLayout.SOUTH);

        listAllDisciplinas();
    }

    private void listAllDisciplinas() {
        String allDisciplinasDetails = disciplinaController.getFormattedAllDisciplinesDetails();
        textArea.setText(allDisciplinasDetails);
    }
}
