package SistemaVendas.SistemaVendas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import SistemaVendas.SistemaVendas.model.Cliente;
import SistemaVendas.SistemaVendas.model.Produto;
import SistemaVendas.SistemaVendas.service.CadastroService;

@Controller
public class CadastroController {

    @Autowired
    private CadastroService cadastroService;

    @GetMapping("/cadastro-cliente")
    public String mostrarFormularioCliente(Model model) {
        model.addAttribute("cliente", new Cliente()); // Formulário do cliente
        return "cadastro-cliente";
    }

    @PostMapping("/cadastro-produto")
    public String salvarCliente(@ModelAttribute("cliente") Cliente cliente, Model model) {
        model.addAttribute("produto", new Produto()); // Formulário do produto
        model.addAttribute("cliente", cliente); // Passa o cliente para a próxima etapa
        return "cadastro-produto";
    }

    @PostMapping("/cadastro-condpagto")
    public String salvarProduto(@ModelAttribute("produto") Produto produto, @ModelAttribute("cliente") Cliente cliente, Model model) {
        model.addAttribute("cliente", cliente); // Passa o cliente
        model.addAttribute("produto", produto); // Passa o produto
        return "cadastro-condpagto";
    }

    @PostMapping("/finalizar-cadastro")
    public String finalizarCadastro(@ModelAttribute("produto") Produto produto, @ModelAttribute("cliente") Cliente cliente) {
        // Salvar Cliente, Produto e Condição de Pagamento
        cadastroService.salvarCliente(cliente);
        cadastroService.salvarProduto(produto);
        return "redirect:/cadastros"; // Página de confirmação ou listagem
    }
}
