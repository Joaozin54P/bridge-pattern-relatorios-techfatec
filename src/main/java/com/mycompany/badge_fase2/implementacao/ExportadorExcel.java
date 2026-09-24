package implementacao;

import java.util.List;

/**
 * Implementação concreta: simula a geração de uma planilha Excel (XLSX).
 */
public class ExportadorExcel implements FormatoExportacao {

    private int linhaAtual;

    @Override
    public void desenharCabecalho(String titulo) {
        System.out.println("[EXCEL] Planilha criada.");
        System.out.println("[EXCEL] Célula A1 (negrito): " + titulo);
        linhaAtual = 3;
    }

    @Override
    public void desenharCorpo(List<String> dados) {
        for (String dado : dados) {
            System.out.println("[EXCEL] Linha " + linhaAtual + " -> " + dado);
            linhaAtual++;
        }
    }

    @Override
    public void finalizarArquivo() {
        System.out.println("[EXCEL] Documento finalizado -> relatorio.xlsx gerado com sucesso.\n");
    }
}
