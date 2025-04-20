package SistemaVendas.SistemaVendas.model;

import java.io.Serializable;

public class NotificacaoMessage implements Serializable {
	private static final long serialVersionUID = 1L;
	private String emailCliente;
    private String produtoDescricao;
    private double precoAntigo;
    private double precoNovo;

    public NotificacaoMessage(String emailCliente, String produtoDescricao, double precoAntigo, double precoNovo) {
        this.emailCliente = emailCliente;
        this.produtoDescricao = produtoDescricao;
        this.precoAntigo = precoAntigo;
        this.precoNovo = precoNovo;
    }

    // Getters e Setters
    public String getEmailCliente() { return emailCliente; }
    public void setEmailCliente(String emailCliente) { this.emailCliente = emailCliente; }
    public String getProdutoDescricao() { return produtoDescricao; }
    public void setProdutoDescricao(String produtoDescricao) { this.produtoDescricao = produtoDescricao; }
    public double getPrecoAntigo() { return precoAntigo; }
    public void setPrecoAntigo(double precoAntigo) { this.precoAntigo = precoAntigo; }
    public double getPrecoNovo() { return precoNovo; }
    public void setPrecoNovo(double precoNovo) { this.precoNovo = precoNovo; }
}