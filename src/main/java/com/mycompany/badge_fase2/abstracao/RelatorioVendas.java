package abstracao;

import implementacao.FormatoExportacao;
import java.util.List;

/**
 * Refinamento concreto da abstração: Relatório de Vendas.
 */
public class RelatorioVendas extends Relatorio {

    private static final String TITULO = "Relatório de Vendas - TechFatec";

    private static final List<String> DADOS = List.of(
            "Notebook Gamer     | Qtd: 12 | Total: R$ 45.600,00",
            "Monitor 27\"        | Qtd: 30 | Total: R$ 27.000,00",
            "Teclado Mecânico   | Qtd: 55 | Total: R$ 11.000,00"
    );

    public RelatorioVendas(FormatoExportacao exportador) {
        super(exportador);
    }

    @Override
    public void gerarRelatorio() {
        System.out.println(">> Gerando Relatório de Vendas...");
        executarExportacao(TITULO, DADOS);
    }
}
