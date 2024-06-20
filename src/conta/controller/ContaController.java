package conta.controller;

import conta.model.Conta;
import conta.repository.ContaRepository;

import java.util.ArrayList;

public class ContaController implements ContaRepository {

    public ArrayList<Conta> listaContas = new ArrayList<Conta>();
    int numero = 0;

    @Override
    public void procurarPorNumero(int numero) {
        var conta = buscarNaCollections(numero);

        if (conta != null)
            conta.visualizar();
        else
            System.out.println("A Conta numero: " + " não foi encontrada na nossa base de dados.");
    }

    @Override
    public void listarTodas() {
        for (var conta : listaContas) {
            conta.visualizar();
        }
    }

    @Override
    public void cadastrar(Conta conta) {
        listaContas.add(conta);
        System.out.println("Conta número: " + conta.getNumero() + " Conta cadastrada com sucesso!");
    }

    @Override
    public void atualizar(Conta conta) {
        var buscarConta = buscarNaCollections(conta.getNumero());
        if (buscarConta != null) {
            listaContas.set(listaContas.indexOf(buscarConta), conta);
            System.out.println("A Conta número: " + conta.getNumero() + " atualizada com sucesso!");
        } else
            System.out.println("A Conta número: " + conta.getNumero() + " não foi encontrada na nossa base de dados.");
    }

    @Override
    public void deletar(int numero) {
        var conta = buscarNaCollections(numero);
        if (conta != null) {
            if (listaContas.remove(conta) == true)
                System.out.println("A Conta número: " + numero + " deletada com sucesso!");
        } else
            System.out.println("A Conta número: " + conta.getNumero() + " não foi encontrada na nossa base de dados.");
    }

    @Override
    public void sacar(int numero, float valor) {
        var conta = buscarNaCollections(numero);
        if (conta != null) {
            if (conta.sacar(valor) ==  true)
                System.out.println("O Saque na Conta numero: " + numero + " foi efetuado com sucesso!");
        } else
            System.out.println("A Conta número: " + conta.getNumero() + " não foi encontrada na nossa base de dados.");
    }

    @Override
    public void deposita(int numero, float valor) {
        var conta = buscarNaCollections(numero);
        if (conta != null) {
            conta.depositar(valor);
            System.out.println("O Depósito na Conta numero: " + numero + " foi efetuado com sucesso!");
        } else System.out.println("Conta numero: " + " não foi encontrado na nossa base de dados ou a Conta destino não é uma conta Corrente!");
    }

    @Override
    public void transferir(int numeroOrigem, int numeroDestino, float valor) {
        var contaOrigem = buscarNaCollections(numeroOrigem);
        var contaDestino = buscarNaCollections(numeroDestino);
        if (contaOrigem != null || contaDestino != null) {
            if(contaOrigem.sacar(valor) == true) {
                contaDestino.depositar(valor);
                System.out.println("A transferência foi efetuada com sucesso!");
            } else
                System.out.println("A conta de Origem e/ou Destino não foram encontradas!");
        }
    }

    public int gerarNumero(){
        return ++numero;
    }

    public Conta buscarNaCollections(int numero){
        for (var conta : listaContas) {
            if(conta.getNumero() == numero){
                return conta;
            }
        }
        return null;
    }
}
