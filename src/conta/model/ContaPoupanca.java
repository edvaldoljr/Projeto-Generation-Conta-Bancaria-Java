package conta.model;

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

    public void setAniversarioConta(int aniversatio) {
        this.aniversarioConta = aniversatio;
    }

    @Override
    public void visualizar() {
        super.visualizar();
        System.out.println("Aniversário da conta: " + this.aniversarioConta);
    }
}
