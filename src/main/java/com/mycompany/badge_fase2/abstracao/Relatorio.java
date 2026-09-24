package abstracao;

import implementacao.FormatoExportacao;
import java.util.List;

/**
 * Lado da ABSTRAÇÃO do padrão Bridge.
 *
 * Mantém uma referência ao formato de exportação (a Implementação),
 * recebida via injeção de dependência pelo construtor. A abstração
 * NUNCA instancia um exportador concreto com "new" — apenas depende
 * da interface FormatoExportacao.
 */
public abstract class Relatorio {

    protected FormatoExportacao exportador;

    protected Relatorio(FormatoExportacao exportador) {
        this.exportador = exportador;
    }

    /**
     * Permite trocar o formato de exportação em tempo de execução,
     * sem recriar o relatório nem alterar seu tipo concreto.
     */
    public void setExportador(FormatoExportacao exportador) {
        this.exportador = exportador;
    }

    /**
     * Cada tipo de relatório concreto define seu próprio título e dados,
     * mas delega a exportação em si ao exportador injetado.
     */
    public abstract void gerarRelatorio();

    /**
     * Template comum de exportação, reaproveitado pelas subclasses.
     */
    protected void executarExportacao(String titulo, List<String> dados) {
        exportador.desenharCabecalho(titulo);
        exportador.desenharCorpo(dados);
        exportador.finalizarArquivo();
    }
}
