package com.cleancode;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public class Assinatura {

    private BigDecimal mensalidade;
    private LocalDateTime inicio;
    private Optional<LocalDateTime> fim;


    public Assinatura(BigDecimal mensalidade, LocalDateTime inicio) {
        this.mensalidade = mensalidade;
        this.inicio = inicio;
        this.fim = Optional.empty();
    }

    public Assinatura(BigDecimal mensalidade, LocalDateTime begin, LocalDateTime fim) {
        this.mensalidade = mensalidade;
        this.inicio = begin;
        this.fim = Optional.of(fim);
    }

    public BigDecimal getMensalidade() {
        return mensalidade;
    }

    public void setMensalidade(BigDecimal mensalidade) {
        this.mensalidade = mensalidade;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public Optional<LocalDateTime> getFim() {
        return fim;
    }

    public void setFim(Optional<LocalDateTime> fim) {
        this.fim = fim;
    }


}
