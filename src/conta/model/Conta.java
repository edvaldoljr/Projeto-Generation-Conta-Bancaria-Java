package conta.model;

import conta.util.Cores;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
//@NoArgsConstructor
public class Conta {
    private Integer numero;
    private Integer agencia;
    private Integer tipo;
    private String titular;
    private Float saldo;

    public boolean sacar(float valor) {
        if (this.getSaldo() < valor) {
            System.out.println("Saldo insuficiente");
        }
        this.setSaldo(this.getSaldo() - valor);
        return true;
    }

    public void depositar(float valor) {
        this.setSaldo(this.getSaldo() + valor);
    }

    public void visualizar() {
        String tipo = "";

        switch (this.tipo) {
            case 1 -> tipo = "Conta Corrente";

            case 2 -> tipo = "Conta Poupança";
        }

        System.out.println(Cores.TEXT_BLUE_BOLD + Cores.ANSI_BLACK_BACKGROUND);
        System.out.println("\n\n***********************************************************");
        System.out.println("Dados da Conta:");
        System.out.println("***********************************************************");
        System.out.println("Numero da Conta: " + this.numero);
        System.out.println("Agência: " + this.agencia);
        System.out.println("Tipo da Conta: " + tipo);
        System.out.println("Titular: " + this.titular);
        System.out.println("Saldo: " + this.saldo);
        System.out.println("                                                                  " + Cores.TEXT_RESET);
    }
}

