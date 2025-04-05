package SistemaVendas.SistemaVendas.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import SistemaVendas.SistemaVendas.model.Cliente;
import SistemaVendas.SistemaVendas.model.Produto;
import SistemaVendas.SistemaVendas.model.Usuario;
import SistemaVendas.SistemaVendas.model.Venda;
import SistemaVendas.SistemaVendas.model.VendaItem;
import SistemaVendas.SistemaVendas.repository.ClienteRepository;
import SistemaVendas.SistemaVendas.repository.ProdutoRepository;
import SistemaVendas.SistemaVendas.repository.UsuarioRepository;
import SistemaVendas.SistemaVendas.repository.VendaRepository;
import SistemaVendas.SistemaVendas.service.ClienteService;
import SistemaVendas.SistemaVendas.service.ProdutoService;
import jakarta.servlet.http.HttpSession;

@Controller
public class VendaController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/cadastro-vendas")
    public String exibirFormularioVenda(Model model, HttpSession session) {
        Object idUsuarioObj = session.getAttribute("idUsuario");

        if (idUsuarioObj == null) {
            return "redirect:/login";
        }

        Long idUsuario = (idUsuarioObj instanceof Long) ? (Long) idUsuarioObj : ((Integer) idUsuarioObj).longValue();

        model.addAttribute("produtos", produtoService.listarTodosProdutos());
        model.addAttribute("clientes", clienteService.listarClientes());
        model.addAttribute("venda", new Venda());
        model.addAttribute("idUsuario", idUsuario);

        return "cadastro-vendas";
    }

    @PostMapping("/realizar-venda")
    public String realizarVenda(
            @RequestParam String cnpjCliente,
            @RequestParam String razaoSocialCliente,
            @RequestParam String emailCliente,
            @RequestParam Long idUsuario,
            @RequestParam("produtos") String produtos,
            @RequestParam("quantidades") String quantidades,
            Model model) {

        if (idUsuario == null) {
            model.addAttribute("mensagemErro", "O parâmetro 'idUsuario' é obrigatório.");
            return "cadastro-vendas";
        }

        Usuario usuario = usuarioRepository.findByIdUsuario(idUsuario).orElse(null);
        if (usuario == null) {
            model.addAttribute("mensagemErro", "Usuário não encontrado.");
            return "cadastro-vendas";
        }

        Cliente cliente = new Cliente();
        cliente.setCnpjCliente(cnpjCliente);
        cliente.setRazaosocialCliente(razaoSocialCliente);
        cliente.setEmail(emailCliente);
        cliente.setUsuario(usuario);
        clienteRepository.save(cliente);

        Venda venda = new Venda();
        venda.setCliente(cliente);
        venda.setUsuario(usuario);
        venda.setDataVenda(new java.util.Date());
        double precoVendaTotal = 0;
        int quantidadeTotal = 0;

        String[] produtoIds = produtos.split(",");
        String[] quantidadesArray = quantidades.split(",");

        if (produtoIds.length != quantidadesArray.length) {
            model.addAttribute("mensagemErro", "Número de produtos e quantidades não correspondem.");
            return "cadastro-vendas";
        }

        List<VendaItem> itensVenda = new ArrayList<>();

        for (int i = 0; i < produtoIds.length; i++) {
            Long idProduto = Long.parseLong(produtoIds[i].trim());
            Integer quantidade = Integer.parseInt(quantidadesArray[i].trim());

            Produto produto = produtoRepository.findById(idProduto).orElse(null);
            if (produto == null) {
                model.addAttribute("mensagemErro", "Produto com ID " + idProduto + " não encontrado.");
                return "cadastro-vendas";
            }

            if (produto.getQuantidadeProduto() < quantidade) {
                model.addAttribute("mensagemErro", "Quantidade insuficiente para o produto " + produto.getDescricaoProduto());
                return "cadastro-vendas";
            }

            produto.setQuantidadeProduto(produto.getQuantidadeProduto() - quantidade);
            produtoRepository.save(produto);

            VendaItem vendaItem = new VendaItem();
            vendaItem.setVenda(venda);
            vendaItem.setProduto(produto);
            vendaItem.setQuantidadeItens(quantidade);
            vendaItem.setPrecoUnitario(produto.getPrecoProduto());

            itensVenda.add(vendaItem);
            precoVendaTotal += vendaItem.getSubtotal();
            quantidadeTotal += quantidade;
        }

        if (!itensVenda.isEmpty()) {
            venda.setProduto(itensVenda.get(0).getProduto()); // Define o primeiro produto como representativo
        }
        venda.setPrecoVenda(precoVendaTotal);
        venda.setQuantidade(quantidadeTotal);
        venda.setItens(itensVenda);
        vendaRepository.save(venda);

        model.addAttribute("mensagemSucesso", "Venda realizada com sucesso!");
        return "cadastro-vendas";
    }
}