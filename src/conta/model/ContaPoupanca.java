package conta.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContaPoupanca extends Conta{

    private int aniversarioConta;

    @Override
    public void visualizar() {
        super.visualizar();
        System.out.println("Aniversário da conta: " + this.aniversarioConta);
    }
}
