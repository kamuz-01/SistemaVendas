package SistemaVendas.SistemaVendas.controllers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import SistemaVendas.SistemaVendas.config.RabbitMQConfig; // Adicione este import
import SistemaVendas.SistemaVendas.model.Produto;
import SistemaVendas.SistemaVendas.model.VendaItem;
import SistemaVendas.SistemaVendas.model.NotificacaoMessage;
import SistemaVendas.SistemaVendas.service.ProdutoService;
import java.util.List;

@Controller
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/cadastro-produtos")
    public String cadastroProduto(Model model) {
        Produto produto = new Produto();
        model.addAttribute("produto", produto);
        return "cadastro-produtos";
    }

    @PostMapping("/salvar-produto")
    public String salvarProduto(@ModelAttribute Produto produto, RedirectAttributes redirectAttributes) {
        try {
            produtoService.salvarProduto(produto);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Produto cadastrado com sucesso!");
            return "redirect:/lista-produtos";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Erro ao cadastrar o produto.");
            return "redirect:/cadastro-produtos";
        }
    }

    @GetMapping("/lista-produtos")
    public String listarProdutos(Model model) {
        model.addAttribute("produtos", produtoService.listarTodosProdutos());
        return "lista-produtos";
    }
    
    @Transactional
    @PostMapping("/atualizar-produto")
    public String atualizarProduto(@ModelAttribute Produto produto, RedirectAttributes redirectAttributes) {
        try {
            System.out.println("Iniciando atualização do produto com ID: " + produto.getIdProduto());
            Produto produtoExistente = produtoService.buscarPorId(produto.getIdProduto());
            if (produtoExistente == null) {
                redirectAttributes.addFlashAttribute("mensagemErro", "Produto não encontrado.");
                return "redirect:/lista-produtos";
            }

            double precoAntigo = produtoExistente.getPrecoProduto();
            double precoNovo = produto.getPrecoProduto();

            System.out.println("Produto: " + produto.getDescricaoProduto() + 
                ", Preço Antigo: " + precoAntigo + ", Preço Novo: " + precoNovo);

            produtoExistente.setDescricaoProduto(produto.getDescricaoProduto());
            produtoExistente.setPrecoProduto(produto.getPrecoProduto());
            produtoExistente.setQuantidadeProduto(produto.getQuantidadeProduto());
            produtoExistente.setModoPagamento(produto.getModoPagamento());
            produtoExistente.setQuantidadeParcelas(produto.getQuantidadeParcelas());
            produtoExistente.setSku(produto.getSku());

            produtoService.salvarProduto(produtoExistente);

            if (precoNovo < precoAntigo) {
                System.out.println("Preço reduzido detectado. Verificando vendas para notificação...");
                notificarClientesPrecoReduzido(produtoExistente, precoAntigo, precoNovo);
                redirectAttributes.addFlashAttribute("mensagemSucesso", 
                    "Produto atualizado com sucesso! Clientes serão notificados sobre a redução de preço.");
            } else {
                redirectAttributes.addFlashAttribute("mensagemSucesso", "Produto atualizado com sucesso!");
            }

            return "redirect:/lista-produtos";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Erro ao atualizar o produto: " + e.getMessage());
            return "redirect:/lista-produtos";
        }
    }

    @PostMapping("/deletar-produto/{id}")
    public String deletarProduto(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            Produto produto = produtoService.buscarPorId(id);
            if (produto == null) {
                redirectAttributes.addFlashAttribute("mensagemErro", "Produto não encontrado.");
            } else {
                produtoService.deletarProduto(id);
                redirectAttributes.addFlashAttribute("mensagemSucesso", "Produto apagado com sucesso!");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Erro ao apagar o produto: " + e.getMessage());
        }
        return "redirect:/lista-produtos";
    }
    
    private void notificarClientesPrecoReduzido(Produto produto, double precoAntigo, double precoNovo) {
        List<VendaItem> vendasItens = produto.getVendasItens();
        System.out.println("Verificando vendas para o produto: " + produto.getDescricaoProduto());
        if (vendasItens != null && !vendasItens.isEmpty()) {
            System.out.println("Total de itens de venda encontrados: " + vendasItens.size());
            for (VendaItem item : vendasItens) {
                double precoUnitarioVenda = item.getPrecoUnitario();
                System.out.println("VendaItem - ID: " + item.getIdVendaItem() + 
                    ", Preço Unitário: " + precoUnitarioVenda + 
                    ", Produto: " + item.getProduto().getDescricaoProduto());
                
                if (precoNovo < precoUnitarioVenda) {
                    String emailCliente = item.getVenda().getCliente().getEmail();
                    if (emailCliente != null && !emailCliente.isEmpty()) {
                        NotificacaoMessage mensagem = new NotificacaoMessage(
                            emailCliente, 
                            produto.getDescricaoProduto(), 
                            precoUnitarioVenda, 
                            precoNovo
                        );
                        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, mensagem);
                        System.out.println("Mensagem enviada para o RabbitMQ para: " + emailCliente);
                    }
                }
            }
        }
    }

    @GetMapping("/produtos/id/{id}")
    @ResponseBody
    public Produto getProdutoById(@PathVariable Long id) {
        return produtoService.buscarPorId(id);
    }
}