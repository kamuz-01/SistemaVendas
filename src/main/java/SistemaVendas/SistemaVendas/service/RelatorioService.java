package SistemaVendas.SistemaVendas.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import SistemaVendas.SistemaVendas.model.Produto;
import SistemaVendas.SistemaVendas.model.Venda;
import SistemaVendas.SistemaVendas.model.VendaItem;
import SistemaVendas.SistemaVendas.repository.VendaRepository;
import SistemaVendas.SistemaVendas.repository.VendasItensRepository;

@Service
public class RelatorioService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private VendasItensRepository vendaItensRepository;

    // Alteração: adicionar o nome do usuário como parâmetro
    public String gerarRelatorioPdf(Long idCliente, String usuarioNome) {
        // Definir o caminho onde o PDF será gerado
        String diretorioRelatorios = "C:\\Users\\Usuario\\Documents\\POO\\";
        String nomeArquivoPdf = "relatorio_cliente_" + idCliente + ".pdf";
        String caminhoArquivoPdf = diretorioRelatorios + nomeArquivoPdf;

        // Criar o documento PDF
        Document document = new Document();
        try {
            // Gerar o PDF
            PdfWriter.getInstance(document, new FileOutputStream(caminhoArquivoPdf));
            document.open();

            // Adicionar cabeçalho com o nome do usuário
            document.add(new Paragraph("Relatório Gerado por: " + usuarioNome)); // Adiciona o nome do usuário
            document.add(new Paragraph("\n"));  // Pular linha

            // Obter a venda do cliente
            List<Venda> vendas = vendaRepository.findByClienteIdCliente(idCliente); // Consulta ajustada
            for (Venda venda : vendas) {
                // Adicionar dados gerais da venda (ex. Data e ID da venda)
                document.add(new Paragraph("Cliente N°: " + idCliente));
                document.add(new Paragraph("Data e Hora da Venda: " + venda.getDataVenda()));
                document.add(new Paragraph("ID da Venda: " + venda.getIdVenda()));
                document.add(new Paragraph("\n"));

                // Cria a tabela para os itens da venda
                PdfPTable table = new PdfPTable(5);  // Cria 5 colunas
                table.addCell("ID Produto");
                table.addCell("Descrição do Produto");
                table.addCell("Quantidade");
                table.addCell("Preço Unitário");
                table.addCell("Subtotal");

                // Obter os itens da venda
                List<VendaItem> itensVenda = vendaItensRepository.findByVenda(venda);
                for (VendaItem item : itensVenda) {
                    Produto produto = item.getProduto();  // Aqui acesso diretamente o produto

                    if (produto != null) {
                        // Adicionar uma linha para cada item
                        table.addCell(String.valueOf(produto.getIdProduto()));
                        table.addCell(produto.getDescricaoProduto());
                        table.addCell(String.valueOf(item.getQuantidadeItens()));
                        table.addCell(String.valueOf(item.getPrecoUnitario()));
                        table.addCell(String.valueOf(item.getSubtotal())); 
                    }
                }

                // Adicionar a tabela ao documento
                document.add(table);

                // Adicionar o preço de venda no rodapé
                document.add(new Paragraph("\n"));
                document.add(new Paragraph("Valor total da Venda: R$ " + venda.getPrecoVenda()));
            }

            document.close();

            // Retornar a URL do PDF gerado
            return "http://localhost:8080/relatorios/" + nomeArquivoPdf;

        } catch (DocumentException | IOException e) {
            e.printStackTrace();
            return null; // Retorne um erro ou mensagem caso ocorra algum problema
        }
    }
}
