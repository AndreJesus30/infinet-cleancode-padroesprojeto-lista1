package com.cleancode;

import java.math.BigDecimal;
import java.nio.file.Path;

public class Produto {

    private String name;
    private Path file;
    private BigDecimal preco;

    public Produto(String name, Path file, BigDecimal preco) {
        this.name = name;
        this.file = file;
        this.preco = preco;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Path getFile() {
        return file;
    }

    public void setFile(Path file) {
        this.file = file;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
