package conta.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContaCorrenteLimite extends  Conta {

    private float limite;


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
