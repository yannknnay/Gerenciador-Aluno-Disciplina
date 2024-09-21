import controller.*;
import view.TelaInicial;

public class Main {
    public static void main(String[] args) {
        AlunoController alunoController = new AlunoController();
        DisciplinaController disciplinaController = new DisciplinaController();
        TelaInicial telaInicial = new TelaInicial(alunoController, disciplinaController);

        telaInicial.setVisible(true);
    }
}
