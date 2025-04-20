package SistemaVendas.SistemaVendas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long idCliente;

    @Column(name = "razaosocial_cliente")
    private String razaosocialCliente;

    @Column(name = "cnpj_cliente")
    private String cnpjCliente;

    // Relacionamento ManyToOne com a classe Usuario
    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "idUsuario")  // corrigir para "idUsuario"
    private Usuario usuario;
    
    @Column(name = "email")
    @NotBlank(message = "E-mail é obrigatório")
    @Email(message = "E-mail deve ser válido")
    private String email;

    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	// Getters and Setters
    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getRazaosocialCliente() {
        return razaosocialCliente;
    }

    public void setRazaosocialCliente(String razaosocialCliente) {
        this.razaosocialCliente = razaosocialCliente;
    }

    public String getCnpjCliente() {
        return cnpjCliente;
    }

    public void setCnpjCliente(String cnpjCliente) {
        this.cnpjCliente = cnpjCliente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}