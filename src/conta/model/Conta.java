package conta.model;

import conta.util.Cores;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public abstract class Conta {
    private int numero;
    private int agencia;
    private int tipo;
    private String titular;
    private Float saldo;

    public Conta(int numero, int agencia, int tipo, String titular, Float saldo) {
        this.numero = numero;
        this.agencia = agencia;
        this.tipo = tipo;
        this.titular = titular;
        this.saldo = saldo;
    }

    public Conta() {
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public Float getSaldo() {
        return saldo;
    }

    public void setSaldo(Float saldo) {
        this.saldo = saldo;
    }

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

