package conta;

import java.util.InputMismatchException;
import java.util.Scanner;
import conta.controller.ContaController;
import conta.util.Cores;
import conta.model.ContaCorrenteLimite;
import conta.model.ContaPoupanca;
import static conta.util.MensagensInterativas.*;

public class Menu {

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
                case 1 -> {
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
                }
                case 2 -> {
                    System.out.println(Cores.TEXT_BLUE_BOLD + "Listar todas as Contas\n\n");
                    contas.listarTodas();
                    keyPress();
                }
                case 3 -> {
                    System.out.println(Cores.TEXT_BLUE_BOLD + "Consultar dados da Conta - por número\n\n");
                    System.out.println("Digite o Numero da Conta: ");
                    numero = leia.nextInt();
                    contas.procurarPorNumero(numero);
                    keyPress();
                }
                case 4 -> {
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
                }
                case 5 -> {
                    System.out.println(Cores.TEXT_BLUE_BOLD + "Apagar a Conta\n\n");
                    System.out.println("Digite o Numero da Conta: ");
                    numero = leia.nextInt();
                    contas.deletar(numero);
                    keyPress();
                }
                case 6 -> {
                    System.out.println(Cores.TEXT_BLUE_BOLD + "Saque\n\n");
                    System.out.println("Digite o Numero da Conta: ");
                    numero = leia.nextInt();
                    do {
                        System.out.println("Digite o valor do Saque (R$): ");
                        valor = leia.nextFloat();
                    }while (valor <= 0);
                    contas.sacar(numero, valor);
                    keyPress();
                }
                case 7 -> {
                    System.out.println(Cores.TEXT_BLUE_BOLD + "Depósito\n\n");
                    System.out.println("Digite o numero da conta: ");
                    numero = leia.nextInt();
                    do {
                        System.out.println("Digite o valor de Depósito (R$): ");
                        valor = leia.nextFloat();
                    }while (valor <=0);
                    keyPress();
                }
                case 8 -> {
                    System.out.println(Cores.TEXT_BLUE_BOLD + "Transferência entre Contas\n\n");
                    System.out.println("Digite o numero da Cota de origem: ");
                    numero = leia.nextInt();
                    System.out.println("Digite o numero da Cota de Destino: ");
                    numeroDestino = leia.nextInt();
                    do {
                        System.out.println("Digite o valor da transferencia (R$): ");
                        valor = leia.nextFloat();
                    }while (valor <=0);
                    contas.transferir(numero, numeroDestino, valor);
                    keyPress();
                }
                default ->  System.out.println(Cores.TEXT_BLUE_BOLD + "\nOpção Inválida!\n" + Cores.TEXT_RESET);
            }
        }
    }
}