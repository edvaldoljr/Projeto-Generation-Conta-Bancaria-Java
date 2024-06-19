package conta.controller;

import conta.model.Conta;
import conta.repository.ContaRepository;

import java.util.ArrayList;

public class ContaController implements ContaRepository {

    public ArrayList<Conta> listaContas = new ArrayList<Conta>();
    int numero;

    @Override
    public void procurarPorNumero(int numero) {

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

    }

    @Override
    public void deletar(int numero) {

    }

    @Override
    public void sacar(int numero, float valor) {

    }

    @Override
    public void deposita(int numero, float valor) {

    }

    @Override
    public void transferir(int numeroOrigem, int numeroDestino, float valor) {

    }


    public int gerarNumero(){
        return ++numero;
    }
}
