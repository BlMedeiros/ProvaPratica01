import model.Equipamento;
import model.MotorEletrico;
import model.PainelControle;
import service.EstoqueService;
import view.InterfaceUsuario;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<MotorEletrico> motores = new ArrayList<>();
        List<PainelControle> paineis = new ArrayList<>();
        List<Equipamento> equipamentos = new ArrayList<>();

        InterfaceUsuario interfaceUsuario = new InterfaceUsuario();

        EstoqueService estoqueService = new EstoqueService(motores, paineis, equipamentos);

        estoqueService.menuPrincipal(interfaceUsuario);
    }
}

