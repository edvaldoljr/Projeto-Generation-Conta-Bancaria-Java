package conta.model;

public class ContaCorrenteLimite extends  Conta {

    private float limite;

    public ContaCorrenteLimite(int numero, int agencia, int tipo, String titular, Float saldo, float limite) {
        super(numero, agencia, tipo, titular, saldo);
        this.limite = limite;
    }

    public float getLimite() {
        return limite;
    }

    public void setLimite(float limite) {
        this.limite = limite;
    }

    @Override
    public boolean sacar(float valor) {
         if(this.getSaldo() + this.getLimite() < valor){
             System.out.println("Saldo insuficiente");
             return false;
         }
         this.setSaldo(this.getSaldo() - valor);
         return true;
    }

    public void vizualizar(){
        super.visualizar();
        System.out.println("Limite de crédito: " + this.limite);
    }
}
