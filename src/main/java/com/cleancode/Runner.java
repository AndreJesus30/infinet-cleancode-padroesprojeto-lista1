package com.cleancode;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class Runner {
    public static void main(String[] args) {
        Cliente cliente1 = new Cliente("Maria", 25);
        Cliente cliente2 = new Cliente("João", 29);
        Cliente cliente3 = new Cliente("José", 36);

        Produto produto1 = new Produto("musica", Path.of("aquivos/musica"), BigDecimal.valueOf(2.25));
        Produto produto2 = new Produto("video", Path.of("aquivos/video"), BigDecimal.valueOf(3.50));
        Produto produto3 = new Produto("imagem", Path.of("aquivos/imagem"), BigDecimal.valueOf(0.50));


        // exercicio 1
        System.out.println("=================");
        Pagamento pagamento1 = new Pagamento(List.of(produto1, produto2), LocalDateTime.now(), cliente1);
        Pagamento pagamento2 = new Pagamento(List.of(produto1, produto1, produto2), LocalDateTime.now().minusDays(1), cliente2);
        Pagamento pagamento3 = new Pagamento(List.of(produto2, produto3, produto3), LocalDateTime.now().minusMonths(1), cliente3);


        // exercicio 2
        System.out.println("=================");
        List<Pagamento> pagamentos = List.of(pagamento1,pagamento2,pagamento3);
        List<Pagamento> listPagamentosOrdenda = new ArrayList<>(pagamentos);
        Collections.sort(listPagamentosOrdenda, Comparator.comparing(Pagamento::getDataCompra));

        listPagamentosOrdenda.forEach(p -> System.out.println(p.getDataCompra().format(DateTimeFormatter.ISO_DATE)));


        // exercicio 3
        System.out.println("=================");
        BigDecimal valorPagamento1 = pagamento1.getListaProdutos().stream().map(Produto::getPreco).reduce(BigDecimal.valueOf(0.0), (acm, preco)
                -> acm.add(preco));

        System.out.println(valorPagamento1);


        // exercicio 4
        System.out.println("=================");
        BigDecimal valorTotal = pagamentos.stream().flatMap(pgto
                -> pgto.getListaProdutos().stream()).map(Produto::getPreco).reduce(BigDecimal.valueOf(0.0), (acm, preco)
                -> acm.add(preco));

        System.out.println(valorTotal);


        // exercicio 5
        System.out.println("=================");
        Map<Produto, Long> quantidadePorProduto = pagamentos.stream()
                .flatMap(pagamento -> pagamento.getListaProdutos().stream())
                .collect(Collectors.groupingBy(produto -> produto, Collectors.counting()));

        quantidadePorProduto.forEach((produto, quantidade) ->
                System.out.println("Produto: " + produto.getName() + " - Quantidade: " + quantidade));


        // exercicio 6
        System.out.println("=================");
        Map<String, List<Produto>> clientesPorProduto = pagamentos.stream()
                .collect(Collectors.groupingBy(pagamento -> pagamento.getCliente().getNome(),
                        Collectors.flatMapping(pagamento -> pagamento.getListaProdutos().stream(), Collectors.toList())));


        // exercicio 7
        System.out.println("=================");
        Map<Cliente, BigDecimal> gastoPorCliente = pagamentos.stream()
                .collect(Collectors.groupingBy(Pagamento::getCliente,
                        Collectors.mapping(pagamento -> pagamento.getListaProdutos().stream()
                                .map(Produto::getPreco)
                                .reduce(BigDecimal.ZERO, BigDecimal::add), Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));

        Map.Entry<Cliente, BigDecimal> clienteQueMaisGastou = gastoPorCliente.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow();

        Cliente cliente = clienteQueMaisGastou.getKey();
        BigDecimal maiorGasto = clienteQueMaisGastou.getValue();

        System.out.println("Cliente que mais gastou: " + cliente.getNome());
        System.out.println("Valor total gasto pelo cliente:" + maiorGasto);


        // exercicio 8
        System.out.println("=================");

        Month mesEscolhido = Month.JULY; // Mês desejado

        BigDecimal faturamentoMensal = pagamentos.stream()
                .filter(pagamento -> pagamento.getDataCompra().getMonth() == mesEscolhido)
                .flatMap(pagamento -> pagamento.getListaProdutos().stream())
                .map(Produto::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("Faturamento mensal: " + faturamentoMensal);


        // exercicio 9
        System.out.println("=================");
        Assinatura assinatura1 = new Assinatura(BigDecimal.valueOf(99.98), LocalDateTime.of(2023,1,5,10,15));
        Assinatura assinatura2 = new Assinatura(BigDecimal.valueOf(99.98), LocalDateTime.of(2023,3,9,14,05), LocalDateTime.of(2023,6,5,20,15));
        Assinatura assinatura3 = new Assinatura(BigDecimal.valueOf(99.98), LocalDateTime.of(2023,4,26,21,40), LocalDateTime.of(2023,7,1,16,25));


        // exercicio 10
        System.out.println("=================");
        List<Assinatura> assinaturas = List.of(assinatura1, assinatura2, assinatura3);

        LocalDateTime dataAtual = LocalDateTime.now();
        assinaturas.stream()
                .filter(assinatura -> !assinatura.getFim().isPresent())
                .map(assinatura -> ChronoUnit.MONTHS.between(assinatura.getInicio(), dataAtual))
                .forEach(mesesAtivos -> System.out.println("Assinatura ainda ativa: Tempo em meses: " + mesesAtivos));


        // exercicio 11
        System.out.println("=================");
        assinaturas.stream()
                .filter(assinatura -> assinatura.getFim().isPresent())
                .map(assinatura -> ChronoUnit.MONTHS.between(assinatura.getInicio(), dataAtual))
                .forEach(mesesAtivos -> System.out.println("Assinatura já encerrada: Tempo em meses: " + mesesAtivos));


        // exercicio 12
        System.out.println("=================");
        assinaturas.stream()
                .map(assinatura -> {
                    LocalDateTime dataFim = assinatura.getFim().orElse(dataAtual);
                    long mesesAtivos = ChronoUnit.MONTHS.between(assinatura.getInicio(), dataFim);
                    return assinatura.getMensalidade().multiply(BigDecimal.valueOf(mesesAtivos));
                })
                .forEach(valorPago -> System.out.println("Valor pago por todo o período de assinatura: " + valorPago));
    }
}
