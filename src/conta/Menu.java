package conta;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import conta.controller.ContaController;
import conta.util.Cores;
import conta.model.ContaCorrenteLimite;
import conta.model.ContaPoupanca;

public class Menu {

    public static void apresentacao() {
        System.out.println(Cores.TEXT_BLUE_BOLD + Cores.ANSI_BLACK_BACKGROUND
                + "*****************************************************");
        System.out.println("                                                     ");
        System.out.println("                BANCO DO BRAZIL COM Z                ");
        System.out.println("                                                     ");
        System.out.println("*****************************************************");
        System.out.println("                                                     ");
        System.out.println("            1 - Criar Conta                          ");
        System.out.println("            2 - Listar todas as Contas               ");
        System.out.println("            3 - Buscar Conta por Numero              ");
        System.out.println("            4 - Atualizar Dados da Conta             ");
        System.out.println("            5 - Apagar Conta                         ");
        System.out.println("            6 - Sacar                                ");
        System.out.println("            7 - Depositar                            ");
        System.out.println("            8 - Transferir valores entre Contas      ");
        System.out.println("            9 - Sair                                 ");
        System.out.println("                                                     ");
        System.out.println("*****************************************************");
        System.out.println("Entre com a opção desejada:                          ");
        System.out.println("                                                     " + Cores.TEXT_RESET);
    }

    public static void keyPress() {
        try {
            System.out.println(Cores.TEXT_RESET + "\n\nPresione Enter para continuar");
            System.in.read();
        } catch (IOException e ){
            System.out.println("Você pressionou uma tecla diferente de enter" );
        }
    }

    public static void sobre() {
        System.out.println("\n*********************************************************");
        System.out.println("Projeto Desenvolvido por: ");
        System.out.println("Generation Brasil - generation@generation.org");
        System.out.println("github.com/conteudoGeneration");
        System.out.println("*********************************************************");
    }

    public static void main(String[] args) {

        ContaController contas = new ContaController();

        Scanner leia = new Scanner(System.in);

        int opcao, numero, agencia, tipo, aniversario, numeroDestino;
        String titular;
        float saldo, limite, valor;

        ContaCorrenteLimite cc1 = new ContaCorrenteLimite(contas.gerarNumero(), 123, 1, "João da Silva", 1000f,100.0f);
        contas.cadastrar(cc1);
        ContaCorrenteLimite cc2 = new ContaCorrenteLimite(contas.gerarNumero(), 124, 1, "Matheus da Silva", 2000f,100.0f);
        contas.cadastrar(cc2);
        ContaPoupanca cp1 = new ContaPoupanca(contas.gerarNumero(), 125, 2, "Mariana do Santos", 4000f, 12);
        contas.cadastrar(cp1);
        ContaPoupanca cp2 = new ContaPoupanca(contas.gerarNumero(), 125, 2, "Juliana Ramos", 8000f, 15);
        contas.cadastrar(cp2);

        while(true) {
            apresentacao();
            try {
                opcao = leia.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("\nDigite valores inteiros! ");
                leia.nextLine();
                opcao = 0;
            }

            if (opcao == 9) {
                System.out.println(Cores.TEXT_WHITE_BOLD + "\nBanco do Brazil com Z - O seu Futuro começa aqui!");
                sobre();
                leia.close();
                System.exit(0);
            }

            switch (opcao) {
                case 1 :
                    System.out.println(Cores.TEXT_BLUE_BOLD + "Criar Conta\n\n");

                    System.out.println("Digite o Numero da Agência: ");
                    agencia = leia.nextInt();
                    System.out.println("Digite um Titular: ");
                    leia.skip("\\R?");
                    titular = leia.nextLine();

                    do {
                        System.out.println("Digite o Tipo de conte (1-CC ou 2-CP): ");
                        tipo = leia.nextInt();
                    }while (tipo < 1 && tipo > 2);

                    System.out.println("Digite o saldo da Conta (R$): ");
                    saldo = leia.nextFloat();

                    switch (tipo) {
                        case 1 -> {
                            System.out.println("Digite o limete de Crédito (R$): ");
                            limite = leia.nextFloat();
                            contas.cadastrar(new ContaCorrenteLimite(contas.gerarNumero(), agencia, tipo, titular, saldo, limite));
                        }
                        case 2 -> {
                            System.out.println("Digite o dia do anivérsario da Conta: ");
                            aniversario = leia.nextInt();
                            contas.cadastrar(new ContaPoupanca(contas.gerarNumero(), agencia, tipo, titular, saldo, aniversario));
                        }
                    }
                    keyPress();
                    break;
                case 2 :
                    System.out.println(Cores.TEXT_BLUE_BOLD + "Listar todas as Contas\n\n");
                    contas.listarTodas();
                    keyPress();
                    break;
                case 3 :
                    System.out.println(Cores.TEXT_BLUE_BOLD + "Consultar dados da Conta - por número\n\n");
                    System.out.println("Digite o Numero da Conta: ");
                    numero = leia.nextInt();
                    contas.procurarPorNumero(numero);
                    keyPress();
                    break;
                case 4 :
                    System.out.println(Cores.TEXT_BLUE_BOLD + "Atualizar dados da Conta\n\n");
                    System.out.println("Digite o Numero da Conta: ");
                    numero = leia.nextInt();
                    var buscarConta = contas.buscarNaCollections(numero);
                    if (buscarConta == null) {
                        tipo = buscarConta.getTipo();
                        System.out.println("Digite o Tipo de Agencia: ");
                        agencia = leia.nextInt();
                        System.out.println("Digite o Titular: ");
                        leia.skip("\\R?");
                        titular = leia.nextLine();
                        System.out.println("Digite o saldo da Conta (R$): ");
                        saldo = leia.nextFloat();

                        switch (tipo) {
                            case 1 -> {
                                System.out.println("Digite o limite de Crédito (R$): ");
                                limite = leia.nextFloat();
                                contas.atualizar(new ContaCorrenteLimite(numero, agencia, tipo, titular, saldo, limite));
                            }
                            case 2 -> {
                                System.out.println("Digite o dia do aniversário da Conta: ");
                                aniversario = leia.nextInt();
                                contas.atualizar(new ContaPoupanca(numero, agencia, tipo, titular, saldo, aniversario));
                            }
                            default -> System.out.println("TIpo de conta inválido!");
                        }
                    } else {
                        System.out.println("A conta não foi encontrada!");
                    }
                    keyPress();
                    break;
                case 5 :
                    System.out.println(Cores.TEXT_BLUE_BOLD + "Apagar a Conta\n\n");
                    System.out.println("Digite o Numero da Conta: ");
                    numero = leia.nextInt();
                    contas.deletar(numero);
                    keyPress();
                    break;
                case 6 :
                    System.out.println(Cores.TEXT_BLUE_BOLD + "Saque\n\n");
                    System.out.println("Digite o Numero da Conta: ");
                    numero = leia.nextInt();
                    do {
                        System.out.println("Digite o valor do Saque (R$): ");
                        valor = leia.nextFloat();
                    }while (valor <= 0);
                    contas.sacar(numero, valor);
                    keyPress();
                    break;
                case 7 :
                    System.out.println(Cores.TEXT_BLUE_BOLD + "Depósito\n\n");
                    keyPress();
                    break;
                case 8 :
                    System.out.println(Cores.TEXT_BLUE_BOLD + "Transferência entre Contas\n\n");
                    keyPress();
                    break;
                default :  System.out.println(Cores.TEXT_BLUE_BOLD + "\nOpção Inválida!\n" + Cores.TEXT_RESET);
            }
        }
    }
}