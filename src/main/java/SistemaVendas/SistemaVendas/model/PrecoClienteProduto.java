package SistemaVendas.SistemaVendas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class PrecoClienteProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPrecoClienteProduto;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_produto", nullable = false)
    private Produto produto;

    @Column(nullable = false)
    private Double preco;

	public Long getIdPrecoClienteProduto() {
		return idPrecoClienteProduto;
	}

	public void setIdPrecoClienteProduto(Long idPrecoClienteProduto) {
		this.idPrecoClienteProduto = idPrecoClienteProduto;
	}

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

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}
}