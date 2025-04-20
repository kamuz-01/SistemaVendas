package SistemaVendas.SistemaVendas.model;

import java.util.List;

public class VendaDTO {
	private String cnpjCliente;
    private String razaoSocialCliente;
    private Long idUsuario;
    private Long idProduto;
    private Integer quantidade;
 // Campos para os itens da venda
    private List<Long> produtos;
    private List<Integer> quantidades;
    
	public List<Long> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Long> produtos) {
		this.produtos = produtos;
	}
	public List<Integer> getQuantidades() {
		return quantidades;
	}
	public void setQuantidades(List<Integer> quantidades) {
		this.quantidades = quantidades;
	}
	public String getCnpjCliente() {
		return cnpjCliente;
	}
	public void setCnpjCliente(String cnpjCliente) {
		this.cnpjCliente = cnpjCliente;
	}
	public String getRazaoSocialCliente() {
		return razaoSocialCliente;
	}
	public void setRazaoSocialCliente(String razaoSocialCliente) {
		this.razaoSocialCliente = razaoSocialCliente;
	}
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Long getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

}
