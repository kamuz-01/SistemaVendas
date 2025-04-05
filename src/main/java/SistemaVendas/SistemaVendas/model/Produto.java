package SistemaVendas.SistemaVendas.model;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto")
    private Long idProduto;

    @Column(name = "sku_produto", unique = true, nullable = false)
    private String sku;

    @Column(name = "descricao_produto", nullable = false)
    private String descricaoProduto;

    @Column(name = "preco_produto", nullable = false)
    private double precoProduto;

    @Column(name = "quantidade_produto", nullable = false)
    private int quantidadeProduto;

    @Column(name = "modo_pagamento", nullable = false)
    private String modoPagamento;

    @Column(name = "quantidade_parcelas", nullable = false)
    private String quantidadeParcelas;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore // Evita problemas de serialização circular
    private List<VendaItem> vendasItens = new ArrayList<>(); // Alterado de Venda para VendaItem

    @OneToMany(mappedBy = "produto")
    @JsonIgnore
    private List<Notificacao> notificacoes;

    // Getters e Setters
    public Long getIdProduto() { return idProduto; }
    public void setIdProduto(Long idProduto) { this.idProduto = idProduto; }
    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }
    public String getDescricaoProduto() { return descricaoProduto; }
    public void setDescricaoProduto(String descricaoProduto) { this.descricaoProduto = descricaoProduto; }
    public double getPrecoProduto() { return precoProduto; }
    public void setPrecoProduto(double precoProduto) { this.precoProduto = precoProduto; }
    public int getQuantidadeProduto() { return quantidadeProduto; }
    public void setQuantidadeProduto(int quantidadeProduto) { this.quantidadeProduto = quantidadeProduto; }
    public String getModoPagamento() { return modoPagamento; }
    public void setModoPagamento(String modoPagamento) { this.modoPagamento = modoPagamento; }
    public String getQuantidadeParcelas() { return quantidadeParcelas; }
    public void setQuantidadeParcelas(String quantidadeParcelas) { this.quantidadeParcelas = quantidadeParcelas; }
    public List<VendaItem> getVendasItens() { return vendasItens; }
    public void setVendasItens(List<VendaItem> vendasItens) { this.vendasItens = vendasItens; }
    public List<Notificacao> getNotificacoes() { return notificacoes; }
    public void setNotificacoes(List<Notificacao> notificacoes) { this.notificacoes = notificacoes; }
}