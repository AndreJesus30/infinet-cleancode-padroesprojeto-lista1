package com.cleancode;
import java.time.LocalDateTime;
import java.util.List;

public class Pagamento {

   private List<Produto> listaProdutos;
   private LocalDateTime dataCompra;
   private Cliente cliente;

    public Pagamento(List<Produto> listaProdutos, LocalDateTime dataCompra, Cliente cliente) {
        this.listaProdutos = listaProdutos;
        this.dataCompra = dataCompra;
        this.cliente = cliente;
    }

    public List<Produto> getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(List<Produto> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }

    public LocalDateTime getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDateTime dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
