package implementacao;

import java.util.List;

/**
 * Implementação concreta: simula a geração de um arquivo PDF.
 */
public class ExportadorPDF implements FormatoExportacao {

    @Override
    public void desenharCabecalho(String titulo) {
        System.out.println("[PDF] ==========================================");
        System.out.println("[PDF]  " + titulo);
        System.out.println("[PDF] ==========================================");
    }

    @Override
    public void desenharCorpo(List<String> dados) {
        for (String linha : dados) {
            System.out.println("[PDF]  * " + linha);
        }
    }

    @Override
    public void finalizarArquivo() {
        System.out.println("[PDF] Documento finalizado -> relatorio.pdf gerado com sucesso.\n");
    }
}
