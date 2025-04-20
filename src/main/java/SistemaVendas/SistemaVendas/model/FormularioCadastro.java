package SistemaVendas.SistemaVendas.model;

public class FormularioCadastro {

    private Cliente cliente = new Cliente();
    private Produto produto = new Produto();

    // Getters e setters para cliente, produto
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}