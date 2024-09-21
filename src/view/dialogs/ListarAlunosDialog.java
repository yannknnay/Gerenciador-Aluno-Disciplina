package view.dialogs;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import controller.AlunoController;
import model.Aluno;

public class ListarAlunosDialog extends JDialog {

    private final AlunoController alunoController;

    public ListarAlunosDialog(JFrame parent, AlunoController alunoController) {
        super(parent, "Listar Alunos", true);
        this.alunoController = alunoController;

        setLayout(new BorderLayout());
        add(createListPanel(), BorderLayout.CENTER);
        add(createButtonPanel(), BorderLayout.SOUTH);

        setSize(300, 200);
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    // Método para criar o painel de lista
    private JPanel createListPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        String[] columnNames = {"ID", "Nome"};
        List<Aluno> alunos = alunoController.listarAlunos();
        String[][] dados = new String[alunos.size()][2];

        for (int i = 0; i < alunos.size(); i++) {
            Aluno aluno = alunos.get(i);
            dados[i][0] = String.valueOf(aluno.getId());
            dados[i][1] = aluno.getNome();
        }

        JTable table = new JTable(dados, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    // Método para criar o painel de botões
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        JButton btnFechar = new JButton("Fechar");

        btnFechar.addActionListener(e -> dispose());

        buttonPanel.add(btnFechar);

        return buttonPanel;
    }
}

