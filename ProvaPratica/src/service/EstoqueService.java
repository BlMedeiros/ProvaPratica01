package service;

import model.Equipamento;
import model.MotorEletrico;
import model.PainelControle;
import view.InterfaceUsuario;

import java.util.List;

public class EstoqueService {

    private List<MotorEletrico> estoqueMotorList;
    private List<PainelControle> painelControleList;
    private List<Equipamento> equipamentoList;

    public EstoqueService(List<MotorEletrico> estoqueMotorList, List<PainelControle> painelControleList, List<Equipamento> equipamentoList) {
        this.estoqueMotorList = estoqueMotorList;
        this.painelControleList = painelControleList;
        this.equipamentoList = equipamentoList;
    }

    public void menuPrincipal(InterfaceUsuario interfaceUsuario) {
        int opcaoPrincipal;
        do {
            opcaoPrincipal = interfaceUsuario.menuPrincipal();

            switch (opcaoPrincipal) {
                case 1: gerenciarCadastro(interfaceUsuario); break;
                case 2: listarTodosProduto(interfaceUsuario); break;
                case 3: gerenciarListagem(interfaceUsuario); break;
                case 4: pesquisarCodigo(interfaceUsuario); break;
                case 5: removerEquipamento(interfaceUsuario); break;
                case 6: gerenciarEstoque(interfaceUsuario); break;
                case 7: gerarRelatorios(interfaceUsuario); break;
                case 8: buscaAvancadaPorNome(interfaceUsuario); break;
                case 9: buscaAvancadaPorPreco(interfaceUsuario); break;
                case 0: interfaceUsuario.exibirMensagem("Encerrando o programa..."); break;
                default: interfaceUsuario.exibirMensagem("Opção Inválida! Tente Novamente.");
            }
        } while (opcaoPrincipal != 0);
    }

    public void gerenciarCadastro(InterfaceUsuario interfaceUsuario) {
        int opcaoCadastro;
        do {
            opcaoCadastro = interfaceUsuario.menuCadastrarEquipamento();
            switch (opcaoCadastro) {
                case 1: cadastrarMotorEletrico(interfaceUsuario); break;
                case 2: cadastrarPainelControle(interfaceUsuario); break;
                case 0: interfaceUsuario.exibirMensagem("Voltando ao Menu..."); break;
                default: interfaceUsuario.exibirMensagem("Opção inválida! Tente novamente.");
            }
        } while (opcaoCadastro != 0);
    }

    private boolean existeCodigo(String codigo) {
        for (Equipamento e : equipamentoList) {
            if (e.getCodigo().equalsIgnoreCase(codigo)) return true;
        }
        return false;
    }

    public void cadastrarMotorEletrico(InterfaceUsuario interfaceUsuario) {
        String codigo = interfaceUsuario.pedirCodigoEquipamento();
        if (existeCodigo(codigo)) {
            interfaceUsuario.exibirMensagem("Já existe equipamento com esse código!");
            return;
        }
        String nome = interfaceUsuario.pedirNomeEquipamento();
        int quantidade = interfaceUsuario.pedirQuantidadeEquipamento();
        double preco = interfaceUsuario.pedirPrecoEquipamento();
        double potencia = interfaceUsuario.pedirPotenciaMotor();

        MotorEletrico novoMotor = new MotorEletrico(codigo, nome, quantidade, preco, potencia);
        estoqueMotorList.add(novoMotor);
        equipamentoList.add(novoMotor);
        interfaceUsuario.exibirMensagem("Motor Elétrico \"" + nome + "\" cadastrado com sucesso!");
    }

    public void cadastrarPainelControle(InterfaceUsuario interfaceUsuario) {
        String codigo = interfaceUsuario.pedirCodigoEquipamento();
        if (existeCodigo(codigo)) {
            interfaceUsuario.exibirMensagem("Já existe equipamento com esse código!");
            return;
        }
        String nome = interfaceUsuario.pedirNomeEquipamento();
        int quantidade = interfaceUsuario.pedirQuantidadeEquipamento();
        double preco = interfaceUsuario.pedirPrecoEquipamento();
        String tensao = interfaceUsuario.pedirTensaoPainelControle();

        PainelControle novoPainel = new PainelControle(codigo, nome, quantidade, preco, tensao);
        painelControleList.add(novoPainel);
        equipamentoList.add(novoPainel);
        interfaceUsuario.exibirMensagem("Painel de Controle \"" + nome + "\" cadastrado com sucesso!");
    }

    public void gerenciarListagem(InterfaceUsuario interfaceUsuario) {
        int opcaoListagem;
        do {
            opcaoListagem = interfaceUsuario.menuListarEquipamento();
            switch (opcaoListagem) {
                case 1: listarApenasMotores(interfaceUsuario); break;
                case 2: listarApenasPaineis(interfaceUsuario); break;
                case 0: interfaceUsuario.exibirMensagem("Voltando ao Menu..."); break;
                default: interfaceUsuario.exibirMensagem("Opção Inválida! Tente Novamente.");
            }
        } while (opcaoListagem != 0);
    }

    private void listarTodosProduto(InterfaceUsuario interfaceUsuario) {
        if (equipamentoList.isEmpty()) {
            interfaceUsuario.exibirMensagem("Nenhum Equipamento Registrado no Momento!");
            return;
        }
        interfaceUsuario.exibirMensagem("--- Lista de Equipamentos ---");
        for (Equipamento equipamento : equipamentoList) {
            interfaceUsuario.listarEquipamento(equipamento);
        }
    }

    private void listarApenasMotores(InterfaceUsuario interfaceUsuario) {
        if (estoqueMotorList.isEmpty()) {
            interfaceUsuario.exibirMensagem("Nenhum Motor Elétrico Registrado no Momento!");
            return;
        }
        interfaceUsuario.exibirMensagem("--- Lista de Motores Elétricos ---");
        for (MotorEletrico motor : estoqueMotorList) {
            interfaceUsuario.listarEquipamento(motor);
        }
    }

    private void listarApenasPaineis(InterfaceUsuario interfaceUsuario) {
        if (painelControleList.isEmpty()) {
            interfaceUsuario.exibirMensagem("Nenhum Painel de Controle Registrado no Momento!");
            return;
        }
        interfaceUsuario.exibirMensagem("--- Lista de Painéis de Controle ---");
        for (PainelControle painel : painelControleList) {
            interfaceUsuario.listarEquipamento(painel);
        }
    }

    private void pesquisarCodigo(InterfaceUsuario interfaceUsuario) {
        String codigoPesquisado = interfaceUsuario.pesquisarEquipamentoCodigo();
        for (Equipamento equipamento : equipamentoList) {
            if (equipamento.getCodigo().equalsIgnoreCase(codigoPesquisado)) {
                interfaceUsuario.exibirMensagem("Equipamento Encontrado: " + equipamento);
                return;
            }
        }
        interfaceUsuario.exibirMensagem("Nenhum Equipamento Encontrado!");
    }

    private void removerEquipamento(InterfaceUsuario interfaceUsuario) {
        String codigoRemover = interfaceUsuario.pedirCodigoEquipamento();
        boolean removido = false;

        for (int i = equipamentoList.size() - 1; i >= 0; i--) {
            Equipamento e = equipamentoList.get(i);
            if (e.getCodigo().equalsIgnoreCase(codigoRemover)) {
                equipamentoList.remove(i);
                removido = true;
                if (e instanceof MotorEletrico) {
                    estoqueMotorList.remove((MotorEletrico) e);
                } else if (e instanceof PainelControle) {
                    painelControleList.remove((PainelControle) e);
                }
                break;
            }
        }

        if (removido) {
            interfaceUsuario.mensagemSucessoRemocao(codigoRemover);
        } else {
            interfaceUsuario.exibirMensagem("Nenhum Equipamento do Código " + codigoRemover + " foi Encontrado! Tente Novamente!");
        }
    }

    public void gerenciarEstoque(InterfaceUsuario interfaceUsuario) {
        int opcaoEstoque;
        do {
            opcaoEstoque = interfaceUsuario.menuMovimentarEstoque();
            switch (opcaoEstoque) {
                case 1: adicionarUnidadesEstoque(interfaceUsuario); break;
                case 2: retirarUnidadeEstoque(interfaceUsuario); break;
                case 0: interfaceUsuario.exibirMensagem("Voltando ao Menu..."); break;
                default: interfaceUsuario.exibirMensagem("Opção Inválida! Tente Novamente.");
            }
        } while (opcaoEstoque != 0);
    }

    public void adicionarUnidadesEstoque(InterfaceUsuario interfaceUsuario) {
        String codigoPesquisado = interfaceUsuario.pesquisarEquipamentoCodigo();
        int qtdeAdicionar = interfaceUsuario.pedirQuantidadeEquipamento();

        for (Equipamento equipamento : equipamentoList) {
            if (equipamento.getCodigo().equalsIgnoreCase(codigoPesquisado)) {
                if (qtdeAdicionar < 0) {
                    interfaceUsuario.exibirMensagem("Quantidade inválida.");
                    return;
                }
                equipamento.setQuantidade(equipamento.getQuantidade() + qtdeAdicionar);
                interfaceUsuario.exibirMensagem(qtdeAdicionar + " adicionadas ao estoque de " + equipamento.getNome() +
                        "\nEstoque Atual do Equipamento: " + equipamento.getQuantidade());
                return;
            }
        }
        interfaceUsuario.exibirMensagem("Nenhum equipamento encontrado com o código informado.");
    }

    public void retirarUnidadeEstoque(InterfaceUsuario interfaceUsuario) {
        String codigoPesquisado = interfaceUsuario.pesquisarEquipamentoCodigo();
        int qtdeRemover = interfaceUsuario.pedirQuantidadeEquipamento();

        for (Equipamento equipamento : equipamentoList) {
            if (equipamento.getCodigo().equalsIgnoreCase(codigoPesquisado)) {
                if (qtdeRemover < 0) {
                    interfaceUsuario.exibirMensagem("Quantidade inválida.");
                    return;
                }
                if (qtdeRemover > equipamento.getQuantidade()) {
                    interfaceUsuario.exibirMensagem("Quantidade a remover é maior que a do estoque! Tente novamente!");
                    return;
                }
                equipamento.setQuantidade(equipamento.getQuantidade() - qtdeRemover);
                interfaceUsuario.exibirMensagem(qtdeRemover + " removidas do estoque de " + equipamento.getNome() +
                        "\nEstoque Atual do Equipamento: " + equipamento.getQuantidade());
                return;
            }
        }
        interfaceUsuario.exibirMensagem("Nenhum equipamento encontrado com o código informado.");
    }

    public void gerarRelatorios(InterfaceUsuario interfaceUsuario) {
        if (equipamentoList.isEmpty()) {
            interfaceUsuario.exibirMensagem("Nenhum equipamento cadastrado!");
            return;
        }

        int total = 0;
        Equipamento maiorPreco = null;
        Equipamento maiorQuantidade = null;

        for (Equipamento e : equipamentoList) {
            total += e.getQuantidade();
            if (maiorPreco == null || e.getPreco() > maiorPreco.getPreco()) {
                maiorPreco = e;
            }
            if (maiorQuantidade == null || e.getQuantidade() > maiorQuantidade.getQuantidade()) {
                maiorQuantidade = e;
            }
        }

        interfaceUsuario.exibirMensagem("===== RELATÓRIOS =====");
        interfaceUsuario.exibirMensagem("Quantidade total em estoque: " + total);
        interfaceUsuario.exibirMensagem("Equipamento mais caro: " + (maiorPreco != null ? maiorPreco : "—"));
        interfaceUsuario.exibirMensagem("Equipamento com maior quantidade: " + (maiorQuantidade != null ? maiorQuantidade : "—"));

        boolean temBaixo = false;
        interfaceUsuario.exibirMensagem("\nEquipamentos com estoque baixo (< 5):");
        for (Equipamento e : equipamentoList) {
            if (e.getQuantidade() < 5) {
                interfaceUsuario.listarEquipamento(e);
                temBaixo = true;
            }
        }
        if (!temBaixo) {
            interfaceUsuario.exibirMensagem("Nenhum equipamento com estoque baixo.");
        }
    }

    public void buscaAvancadaPorNome(InterfaceUsuario interfaceUsuario) {
        String parteNome = interfaceUsuario.pedirNomeEquipamento();
        boolean encontrou = false;
        for (Equipamento e : equipamentoList) {
            if (e.getNome().toLowerCase().contains(parteNome.toLowerCase())) {
                interfaceUsuario.listarEquipamento(e);
                encontrou = true;
            }
        }
        if (!encontrou) interfaceUsuario.exibirMensagem("Nenhum equipamento corresponde ao nome informado.");
    }

    public void buscaAvancadaPorPreco(InterfaceUsuario interfaceUsuario) {
        double precoMin = interfaceUsuario.pedirPrecoEquipamento();
        boolean encontrou = false;
        for (Equipamento e : equipamentoList) {
            if (e.getPreco() > precoMin) {
                interfaceUsuario.listarEquipamento(e);
                encontrou = true;
            }
        }
        if (!encontrou) interfaceUsuario.exibirMensagem("Nenhum equipamento acima desse preço.");
    }
}

