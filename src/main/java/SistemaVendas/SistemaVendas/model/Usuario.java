package SistemaVendas.SistemaVendas.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
    name = "usuario", 
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "cpfUsuario"), 
        @UniqueConstraint(columnNames = "email"),
        @UniqueConstraint(columnNames = "celular")
    }
)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    @Column(name="cpf_usuario")
    private String cpfUsuario;

    @Column(name="nome_usuario")
    private String nomeUsuario;

    @Column(name="sobrenome_usuario")
    private String sobrenomeUsuario;
    
    @Column(name="data_nascimento")
    private LocalDate dataNascimento;
    
    @Column(name = "celular")
    private String celular;
    
	@Column(name="email")
    private String email;
	
	@Column(name="nome_login")
    private String nomeLogin;
	
	@Column(name="senha")
    private String senha;

	@Column(name="nivel_acesso")
    private String nivelAcesso;	

    // Getters e setters
    public Long getIdUsuario() {
        return idUsuario;
    }
    
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public String getCpfUsuario() {
        return cpfUsuario;
    }

    public void setCpfUsuario(String cpfUsuario) {
        this.cpfUsuario = cpfUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSobrenomeUsuario() {
        return sobrenomeUsuario;
    }

    public void setSobrenomeUsuario(String sobrenomeUsuario) {
        this.sobrenomeUsuario = sobrenomeUsuario;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNivelAcesso() {
        return nivelAcesso;
    }

    public void setNivelAcesso(String nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getNomeLogin() {
        return nomeLogin;
    }

    public void setNomeLogin(String nomeLogin) {
        this.nomeLogin = nomeLogin;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}