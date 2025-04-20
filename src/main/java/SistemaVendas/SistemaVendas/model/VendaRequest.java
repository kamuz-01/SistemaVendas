package SistemaVendas.SistemaVendas.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;

public class VendaRequest {
    private double totalVenda;
    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<VendaItem> items;
    
	public VendaRequest(double totalVenda, List<VendaItem> items) {
		this.totalVenda = totalVenda;
		this.items = items;
	}
	
	public double getTotalVenda() {
		return totalVenda;
	}
	public void setTotalVenda(double totalVenda) {
		this.totalVenda = totalVenda;
	}
	public List<VendaItem> getItems() {
		return items;
	}
	public void setItems(List<VendaItem> items) {
		this.items = items;
	}
}