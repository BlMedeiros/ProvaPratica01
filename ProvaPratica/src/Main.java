import model.Equipamento;
import model.MotorEletrico;
import model.PainelControle;
import service.EstoqueService;
import view.InterfaceUsuario;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Criar as listas vazias para estoque
        List<MotorEletrico> motores = new ArrayList<>();
        List<PainelControle> paineis = new ArrayList<>();
        List<Equipamento> equipamentos = new ArrayList<>();

        // Criar a interface do usuário (dependendo de como sua InterfaceUsuario funciona)
        InterfaceUsuario interfaceUsuario = new InterfaceUsuario();

        // Criar o serviço passando as listas
        EstoqueService estoqueService = new EstoqueService(motores, paineis, equipamentos);

        // Chamar o menu principal para iniciar o programa
        estoqueService.menuPrincipal(interfaceUsuario);
    }
}
