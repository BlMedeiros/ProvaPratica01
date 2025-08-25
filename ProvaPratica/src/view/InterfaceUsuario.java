package view;

import model.Equipamento;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InterfaceUsuario {
    private final Scanner sc;

    public InterfaceUsuario() {
        sc = new Scanner(System.in);
    }

    public int menuPrincipal() {
        System.out.println("\n===== Controle de Estoque WEG 2.0 =====");
        System.out.println("1- Cadastrar Equipamento");
        System.out.println("2- Listar Todos os Equipamentos");
        System.out.println("3- Listar Por Tipo de Equipamento");
        System.out.println("4- Pesquisar por Código");
        System.out.println("5- Remover por Código");
        System.out.println("6- Movimentar Estoque");
        System.out.println("7- Relatórios de Estoque");
        System.out.println("8- Busca Avançada por Nome");
        System.out.println("9- Busca Avançada por Preço");
        System.out.println("0- Sair");
        return lerOpcao("Escolha uma opção: ");
    }

    public int menuCadastrarEquipamento() {
        System.out.println("\n--- Cadastrar Equipamento ---");
        System.out.println("1- Motor Elétrico");
        System.out.println("2- Painel de Controle");
        System.out.println("0- Voltar");
        return lerOpcao("Escolha uma opção: ");
    }

    public int menuListarEquipamento() {
        System.out.println("\n--- Listar Equipamento ---");
        System.out.println("1- Motores Elétricos");
        System.out.println("2- Painéis de Controle");
        System.out.println("0- Voltar");
        return lerOpcao("Escolha uma opção: ");
    }

    public int menuMovimentarEstoque() {
        System.out.println("\n--- Movimentar Estoque ---");
        System.out.println("1- Adicionar Unidades");
        System.out.println("2- Retirar Unidades");
        System.out.println("0- Voltar");
        return lerOpcao("Escolha uma opção: ");
    }

    public String pedirCodigoEquipamento() {
        return lerTexto("Digite o código do equipamento: ");
    }

    public String pedirNomeEquipamento() {
        return lerTexto("Digite o nome do equipamento: ");
    }

    public int pedirQuantidadeEquipamento() {
        return lerInteiro("Digite a quantidade: ");
    }

    public double pedirPrecoEquipamento() {
        return lerDouble("Digite o preço: ");
    }

    public double pedirPotenciaMotor() {
        return lerDouble("Digite a potência do motor: ");
    }

    public String pedirTensaoPainelControle() {
        return lerTexto("Digite a tensão do painel de controle: ");
    }

    public String pesquisarEquipamentoCodigo() {
        return lerTexto("Digite o código do equipamento: ");
    }


    public void listarEquipamento(Equipamento equipamento) {
        System.out.println("--------------------------");
        System.out.println(equipamento);
    }

    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    public void mensagemSucessoRemocao(String nome) {
        System.out.println("✔ O equipamento '" + nome + "' foi removido com sucesso.");
    }

    public void mensagemNaoEncontrada() {
        System.out.println("⚠ Equipamento não encontrado.");
    }

    public void fecharScanner() {
        sc.close();
    }

    private int lerOpcao(String msg) {
        int valor;
        while (true) {
            try {
                System.out.print(msg);
                valor = sc.nextInt();
                sc.nextLine(); // limpar buffer
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("⚠ Entrada inválida. Digite um número inteiro.");
                sc.nextLine(); // limpa o que sobrou no buffer
            }
        }
    }

    private String lerTexto(String msg) {
        System.out.print(msg);
        return sc.nextLine().trim();
    }

    private int lerInteiro(String msg) {
        int valor;
        while (true) {
            try {
                System.out.print(msg);
                valor = sc.nextInt();
                sc.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("⚠ Valor inválido. Digite um número inteiro.");
                sc.nextLine();
            }
        }
    }

    private double lerDouble(String msg) {
        double valor;
        while (true) {
            try {
                System.out.print(msg);
                valor = sc.nextDouble();
                sc.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("⚠ Valor inválido. Digite um número decimal (ex: 199.99).");
                sc.nextLine();
            }
        }
    }
}
