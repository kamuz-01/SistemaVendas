package SistemaVendas.SistemaVendas.controllers;

import SistemaVendas.SistemaVendas.model.PrecoClienteProduto;
import SistemaVendas.SistemaVendas.service.PrecoClienteProdutoService;
import SistemaVendas.SistemaVendas.service.ClienteService;
import SistemaVendas.SistemaVendas.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/preco")
public class PrecoClienteProdutoController {

    @Autowired
    private PrecoClienteProdutoService precoClienteProdutoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProdutoService produtoService;

    // Página de cadastro de preço
    @GetMapping("/cadastrar")
    public String mostrarFormularioCadastro(Model model) {
        // Passa a lista de clientes corretamente para a página
        model.addAttribute("clientes", clienteService.listarClientes()); 
        model.addAttribute("produtos", produtoService.listarTodosProdutos());
        model.addAttribute("precoClienteProduto", new PrecoClienteProduto());
        return "cadastroPreco";  // Página HTML do cadastro
    }

    // Cadastrar preço para cliente e produto
    @PostMapping("/salvar")
    public String salvarPreco(@ModelAttribute PrecoClienteProduto precoClienteProduto) {
        precoClienteProdutoService.salvarPreco(precoClienteProduto);
        return "redirect:/preco/listar";  // Redireciona para a lista de preços
    }

    // Listar preços cadastrados
    @GetMapping("/listar")
    public String listarPrecos(Model model) {
        model.addAttribute("precos", precoClienteProdutoService.listarPrecos());
        return "listarPrecos";  // Página HTML para listar preços
    }

    // Excluir preço
    @GetMapping("/excluir/{id}")
    public String excluirPreco(@PathVariable Long id) {
        precoClienteProdutoService.excluirPreco(id);
        return "redirect:/preco/listar";  // Redireciona após a exclusão
    }
}
