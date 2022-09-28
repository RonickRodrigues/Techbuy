package br.com.ronick.model.entidade;

/**
 * @author ronick
 */
public class Venda {
    
    private int id;
    private String cpfComprador;
    private int codigoProd;
    private int valor;

    public Venda(int id, String cpfComprador, int codigoProd, int valor) {
        this.id = id;
        this.cpfComprador = cpfComprador;
        this.codigoProd = codigoProd;
        this.valor = valor;
    }

    public Venda(String cpfComprador, int codigoProd, int valor) {
        this.cpfComprador = cpfComprador;
        this.codigoProd = codigoProd;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCpfComprador() {
        return cpfComprador;
    }

    public void setCpfComprador(String cpfComprador) {
        this.cpfComprador = cpfComprador;
    }

    public int getCodigoProd() {
        return codigoProd;
    }

    public void setCodigoProd(int codigoProd) {
        this.codigoProd = codigoProd;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    
}
