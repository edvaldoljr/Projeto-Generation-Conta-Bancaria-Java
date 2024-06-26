package conta.model;

import conta.util.Cores;

public class ContaPoupanca extends Conta{

    private int aniversarioConta;

    public ContaPoupanca(int numero, int agencia, int tipo, String titular, Float saldo, int aniversarioConta) {
        super(numero, agencia, tipo, titular, saldo);
        this.aniversarioConta = aniversarioConta;
    }

    public ContaPoupanca(){
    }

    public int getAniversarioConta() {
        return aniversarioConta;
    }

    public void setAniversarioConta(int aniversarioConta) {
        this.aniversarioConta = aniversarioConta;
    }

    @Override
    public void visualizar() {
        super.visualizar();
        System.out.println(Cores.TEXT_BLUE_BOLD + Cores.ANSI_BLACK_BACKGROUND  + "Aniversário da Conta: " + this.aniversarioConta);
    }
}
