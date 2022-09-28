package br.com.ronick.model.entidade;

/**
 * @author ronick
 */
public class Produto {

    private int id, qtdeEstoque;
    private String nome;
    private int preco;

    public Produto(int id, String nome, int preco, int qtdeEstoque) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.qtdeEstoque = qtdeEstoque;
    }

    public Produto(String nome, int preco, int qtdeEstoque) {
        this.nome = nome;
        this.preco = preco;
        this.qtdeEstoque = qtdeEstoque;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQtdeEstoque() {
        return qtdeEstoque;
    }

    public void setQtdeEstoque(int qtdeEstoque) {
        this.qtdeEstoque = qtdeEstoque;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }

}
