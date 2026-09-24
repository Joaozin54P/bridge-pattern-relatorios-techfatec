package cliente; 

import abstracao.Relatorio;
import abstracao.RelatorioRH;
import abstracao.RelatorioVendas;
import implementacao.ExportadorExcel;
import implementacao.ExportadorHTML;
import implementacao.ExportadorPDF;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String[] args) {
        // Garante acentuação correta no console, independente do SO/terminal.
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        System.out.println("========================================================");
        System.out.println(" ROTINA 1 - Relatório de Vendas exportado em PDF");
        System.out.println("========================================================");
        Relatorio relatorioVendas = new RelatorioVendas(new ExportadorPDF());
        relatorioVendas.gerarRelatorio();

        System.out.println("========================================================");
        System.out.println(" ROTINA 2 - Mesmo objeto RelatorioVendas, trocando");
        System.out.println("            dinamicamente o exportador para Excel");
        System.out.println("========================================================");
        relatorioVendas.setExportador(new ExportadorExcel());
        relatorioVendas.gerarRelatorio();

        System.out.println("========================================================");
        System.out.println(" ROTINA 3 - Relatório de RH exportado em HTML");
        System.out.println("========================================================");
        Relatorio relatorioRH = new RelatorioRH(new ExportadorHTML());
        relatorioRH.gerarRelatorio();

        System.out.println("========================================================");
        System.out.println(" Fim da execução - desacoplamento entre Abstração e");
        System.out.println(" Implementação comprovado (padrão Bridge).");
        System.out.println("========================================================");
    }
}
