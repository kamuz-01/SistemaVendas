package SistemaVendas.SistemaVendas.model;

import jakarta.persistence.*;

@Entity
@Table(name = "vendas_itens")
public class VendaItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_venda_item")
    private Long idVendaItem; // Alterado para Long para consistência

    @ManyToOne
    @JoinColumn(name = "id_venda", nullable = false)
    private Venda venda; // Relacionamento ManyToOne com Venda

    @ManyToOne
    @JoinColumn(name = "id_produto", nullable = false)
    private Produto produto; // Relacionamento ManyToOne com Produto

    @Column(name = "quantidade_itens", nullable = false)
    private Integer quantidadeItens; // Alterado para Integer

    @Column(name = "preco_unitario", nullable = false)
    private double precoUnitario;

    @Column(name = "subtotal", nullable = false)
    private double subtotal;

    // Construtor padrão
    public VendaItem() {
    }

    // Getters e Setters
    public Long getIdVendaItem() {
        return idVendaItem;
    }

    public void setIdVendaItem(Long idVendaItem) {
        this.idVendaItem = idVendaItem;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
        if (produto != null) {
            this.precoUnitario = produto.getPrecoProduto();
            atualizarSubtotal(); // Atualiza o subtotal ao definir o produto
        }
    }

    public Integer getQuantidadeItens() {
        return quantidadeItens;
    }

    public void setQuantidadeItens(Integer quantidadeItens) {
        this.quantidadeItens = quantidadeItens;
        atualizarSubtotal(); // Atualiza o subtotal ao mudar a quantidade
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
        atualizarSubtotal(); // Atualiza o subtotal ao mudar o preço unitário
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    // Método auxiliar para atualizar o subtotal
    private void atualizarSubtotal() {
        if (quantidadeItens != null && precoUnitario > 0) {
            this.subtotal = this.quantidadeItens * this.precoUnitario;
        }
    }
}