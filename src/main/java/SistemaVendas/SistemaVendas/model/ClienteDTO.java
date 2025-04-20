package SistemaVendas.SistemaVendas.model;

public class ClienteDTO {
    private Long idCliente;
    private String razaosocialCliente;
    private String cnpjCliente;

    // Construtores, getters e setters
    public ClienteDTO(Long idCliente, String razaosocialCliente, String cnpjCliente) {
        this.idCliente = idCliente;
        this.razaosocialCliente = razaosocialCliente;
        this.cnpjCliente = cnpjCliente;
    }

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
}
