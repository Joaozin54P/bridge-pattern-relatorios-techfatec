package abstracao;

import implementacao.FormatoExportacao;
import java.util.List;

/**
 * Refinamento concreto da abstração: Relatório de Desempenho de RH.
 */
public class RelatorioRH extends Relatorio {

    private static final String TITULO = "Relatório de Desempenho de RH - TechFatec";

    private static final List<String> DADOS = List.of(
            "Carlos Souza   | Cargo: Analista Jr.    | Avaliação: 9.2",
            "Marina Alves   | Cargo: Desenvolvedora  | Avaliação: 8.7",
            "Rafael Lima    | Cargo: Suporte Técnico | Avaliação: 9.5"
    );

    public RelatorioRH(FormatoExportacao exportador) {
        super(exportador);
    }

    @Override
    public void gerarRelatorio() {
        System.out.println(">> Gerando Relatório de Desempenho de RH...");
        executarExportacao(TITULO, DADOS);
    }
}
