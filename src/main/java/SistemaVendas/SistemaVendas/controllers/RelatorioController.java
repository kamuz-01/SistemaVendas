package SistemaVendas.SistemaVendas.controllers;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import SistemaVendas.SistemaVendas.model.Venda;
import SistemaVendas.SistemaVendas.repository.VendaRepository;
import SistemaVendas.SistemaVendas.service.RelatorioService;
import jakarta.servlet.http.HttpSession;

@RestController
public class RelatorioController {

    @Autowired
    private VendaRepository vendaRepository;
    
    @Autowired
    private RelatorioService relatorioService;

    // Endpoint para mostrar o relatório no DataTable
    @GetMapping("/relatorios")
    public ModelAndView relatorioVendas(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Venda> vendas = vendaRepository.findAll(pageable).getContent();
        model.addAttribute("vendas", vendas);
        return new ModelAndView("relatorios");
    }

    // Endpoint para exportar o relatório em PDF com as vendas de um cliente específico
    @GetMapping("/relatorios/exportar-pdf/{idCliente}")
    public ResponseEntity<Map<String, String>> gerarRelatorioPdf(@PathVariable Long idCliente, HttpSession session) {
        // Recupera o nome do usuário da sessão (via GlobalControllerAdvice)
        String usuarioNome = (String) session.getAttribute("usuario");

        if (usuarioNome == null) {
            return ResponseEntity.status(400).body(Map.of("message", "Usuário não autenticado"));
        }

        // Lógica para gerar o PDF com os dados do cliente e o nome do usuário
        String pdfUrl = relatorioService.gerarRelatorioPdf(idCliente, usuarioNome);

        // Retorna a URL do PDF gerado junto com uma mensagem de sucesso
        Map<String, String> response = new HashMap<>();
        response.put("pdfUrl", pdfUrl);
        response.put("message", "Relatório gerado com sucesso!");
        
        return ResponseEntity.ok(response);
    }

    
 // Endpoint para servir os arquivos PDF gerados
    @GetMapping("/relatorios/{filename:.+}")
    public ResponseEntity<Resource> servePdf(@PathVariable String filename) throws MalformedURLException {
        // Caminho onde os PDFs são salvos
        Path path = Paths.get("C:/Users/Usuario/Documents/POO/").resolve(filename);
        Resource resource = new UrlResource(path.toUri());

        if (resource.exists() && resource.isReadable()) {
            return ResponseEntity.ok()
                    .header("Content-Type", "application/pdf")
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
