# Padrão Bridge — Módulo de Relatórios TechFatec

Projeto acadêmico (FATEC Zona Leste) que aplica o **padrão de projeto Bridge**
para resolver a explosão de subclasses no módulo de relatórios de um sistema
de inteligência de negócios.

**Aluno:** João Pedro Machado

## 1. Problema

O sistema legado só gerava o **Relatório de Vendas** em **PDF**. Com a
entrada do **Relatório de Desempenho de RH** e a exigência de exportar tudo
em **PDF, Excel (XLSX) e HTML**, herdar diretamente (`RelatorioVendasPDF`,
`RelatorioVendasExcel`, `RelatorioRHPDF`, `RelatorioRHHTML`...) geraria uma
combinação `N relatórios × M formatos` de subclasses a cada novo item.

## 2. Solução — Padrão Bridge

O Bridge separa **o que é gerado** (Abstração) de **como é exportado**
(Implementação), unindo as duas hierarquias por **composição/agregação**
em vez de herança. Isso mantém o sistema:

- **Aberto para extensão**: um novo relatório ou um novo formato entra sem
  tocar no que já existe.
- **Fechado para modificação**: nenhuma classe existente precisa mudar
  (Princípio Aberto/Fechado — OCP).

### Diagrama de Classes

![Diagrama de Classes](docs/diagrama-classes.png)

- **Abstração**: `Relatorio` (abstrata) guarda uma referência protegida a
  `exportador: FormatoExportacao`; `RelatorioVendas` e `RelatorioRH` herdam
  dela.
- **Implementação**: a interface `FormatoExportacao` declara
  `desenharCabecalho`, `desenharCorpo` e `finalizarArquivo`; `ExportadorPDF`,
  `ExportadorExcel` e `ExportadorHTML` a implementam.
- **Ponte**: agregação de `Relatorio` sobre `FormatoExportacao`.

### Diagrama de Sequência

![Diagrama de Sequência](docs/diagrama-sequencia.png)

O cliente (`Main`) instancia o exportador concreto, injeta essa
implementação no relatório e chama `gerarRelatorio()`. O relatório delega
cada etapa da exportação ao objeto recebido, sem conhecer os detalhes de
como o arquivo é montado.

## 3. Estrutura do projeto

Projeto Maven (NetBeans). O código-fonte fica em `src/main/java`, organizado
nos três pacotes exigidos — **abstracao**, **implementacao** e **cliente**:

```
bridge-pattern-relatorios-techfatec/
├── README.md
├── pom.xml
├── docs/
│   ├── diagrama-classes.png
│   └── diagrama-sequencia.png
└── src/main/java/com/mycompany/badge_fase2/
    ├── abstracao/
    │   ├── Relatorio.java          (classe abstrata)
    │   ├── RelatorioVendas.java    (abstração refinada)
    │   └── RelatorioRH.java        (abstração refinada)
    ├── implementacao/
    │   ├── FormatoExportacao.java  (interface)
    │   ├── ExportadorPDF.java
    │   ├── ExportadorExcel.java
    │   └── ExportadorHTML.java
    └── cliente/
        └── Main.java               (classe de execução / validação)
```

> Os pacotes Java (`abstracao`, `implementacao`, `cliente`) correspondem
> exatamente à separação física exigida na atividade.

## 4. Injeção de dependência

Nenhuma classe de `abstracao` instancia um exportador com `new`. O
construtor de `Relatorio` só recebe uma `FormatoExportacao` já pronta:

```java
protected Relatorio(FormatoExportacao exportador) {
    this.exportador = exportador;
}
```

Quem decide **qual** implementação usar é sempre o cliente
(`cliente/Main.java`) — é o único ponto do projeto onde aparecem
`new ExportadorPDF()`, `new ExportadorExcel()` e `new ExportadorHTML()`.

A troca de formato em tempo de execução é feita via:

```java
public void setExportador(FormatoExportacao exportador) {
    this.exportador = exportador;
}
```

## 5. Como compilar e rodar

**Pela IDE (NetBeans):** abra o projeto e rode `Main.java` (botão direito →
Run File).

**Pela linha de comando (Maven):**

```bash
mvn compile
java -cp target/classes cliente.Main
```

## 6. Script de validação (`cliente/Main.java`)

A execução comprova o desacoplamento entre Abstração e Implementação em
três rotinas, na ordem exigida pela Fase 2:

1. **Geração do Relatório de Vendas em PDF**
   `new RelatorioVendas(new ExportadorPDF())` → `gerarRelatorio()`
2. **Troca dinâmica do mesmo relatório de Vendas para Excel**
   `relatorioVendas.setExportador(new ExportadorExcel())` → `gerarRelatorio()`
   (mesmo objeto `RelatorioVendas`, sem recriar nada)
3. **Geração do Relatório de RH em HTML**
   `new RelatorioRH(new ExportadorHTML())` → `gerarRelatorio()`

### Saída esperada (resumida)

```
ROTINA 1 - Relatório de Vendas exportado em PDF
[PDF] Relatório de Vendas - TechFatec
...
ROTINA 2 - Mesmo objeto RelatorioVendas, trocando dinamicamente o exportador para Excel
[EXCEL] Célula A1 (negrito): Relatório de Vendas - TechFatec
...
ROTINA 3 - Relatório de RH exportado em HTML
[HTML] <h1>Relatório de Desempenho de RH - TechFatec</h1>
...
```

## 7. Aderência ao Bridge / OCP

| Requisito | Como é atendido |
|---|---|
| Duas hierarquias independentes | `Relatorio` (abstração) e `FormatoExportacao` (implementação) unidas por agregação, não herança |
| Novo relatório | Basta criar uma subclasse de `Relatorio` — nenhum exportador é alterado |
| Novo formato | Basta implementar `FormatoExportacao` — nenhum relatório é alterado |
| Sem `new` na abstração | Exportador é sempre recebido via construtor (injeção de dependência) |
| Troca em runtime | `setExportador(...)` troca a implementação sem recriar o relatório |
