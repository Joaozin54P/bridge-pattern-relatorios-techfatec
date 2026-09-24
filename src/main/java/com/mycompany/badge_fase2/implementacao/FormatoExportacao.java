package implementacao;

import java.util.List;

/**
 * Lado da IMPLEMENTAÇÃO do padrão Bridge.
 * Define o contrato que todo formato de exportação deve cumprir,
 * independente do tipo de relatório que o utiliza.
 */
public interface FormatoExportacao {

    void desenharCabecalho(String titulo);

    void desenharCorpo(List<String> dados);

    void finalizarArquivo();
}
