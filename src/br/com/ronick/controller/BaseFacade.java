package br.com.ronick.controller;

import br.com.ronick.model.DAO.ClienteDAO;
import br.com.ronick.model.DAO.ProdutoDAO;
import br.com.ronick.model.DAO.VendaDAO;
import br.com.ronick.model.entidade.Cliente;
import br.com.ronick.model.entidade.Produto;
import br.com.ronick.model.entidade.Venda;
import java.util.List;

/**
 * @author ronic
 */
public class BaseFacade {

    public static String[][] dadosDosClientes(String[] cposCliente) {
        List<Cliente> cliente = ClienteDAO.selecionarTodos();
        String[][] dadosCliente;
        dadosCliente = new String[cliente.size()][cposCliente.length];
        int posicao = 0;
        for (Cliente clt : cliente) {
            String[] umcliente = new String[4];
            umcliente[0] = clt.getNome();
            umcliente[1] = clt.getCpf();
            umcliente[2] = clt.getTelefone();
            umcliente[3] = clt.getEndereco();
            dadosCliente[posicao++] = umcliente;
        }
        return dadosCliente;
    }

    public static String[][] dadosDosProdutos(String[] cposProduto) {
        List<Produto> produto = ProdutoDAO.selecionarTodos();
        String[][] dadosProduto;
        dadosProduto = new String[produto.size()][cposProduto.length];
        int posicao = 0;
        for (Produto pdt : produto) {
            String[] umproduto = new String[4];
            umproduto[0] = pdt.getNome();
            umproduto[1] = String.valueOf(pdt.getPreco());
            umproduto[2] = String.valueOf(pdt.getQtdeEstoque());
            umproduto[3] = String.valueOf(pdt.getId());
            dadosProduto[posicao++] = umproduto;
        }
        return dadosProduto;
    }

    public static String[][] dadosDasVendas(String[] cposVenda) {
        List<Venda> venda = VendaDAO.selecionarTodos();
        String[][] dadosVenda = new String[venda.size()][4];
        int posicao = 0;
        for (Venda vnd : venda) {
            String[] umavenda = new String[4];
            umavenda[0] = String.valueOf(vnd.getId());
            umavenda[1] = vnd.getCpfComprador();
            umavenda[2] = String.valueOf(vnd.getCodigoProd());
            umavenda[3] = String.valueOf(vnd.getValor());
            dadosVenda[posicao++] = umavenda;
        }
        return dadosVenda;
    }

    public static void fecharPrograma(boolean entrarPrincipal) {
        if (entrarPrincipal == false) {
            System.exit(0);
        }
    }
    
    public static void vendaRealizada(String cpf, String codigo) {
        Cliente cliente = ClienteDAO.selecionarPorCpf(cpf);
        Produto produto = ProdutoDAO.selecionarPorId(Integer.parseInt(codigo));
        Venda venda = new Venda(cliente.getCpf(), produto.getId(), produto.getPreco());
        VendaDAO.inserir(venda);
        Produto produtoQtdeMenos = new Produto(produto.getId(), produto.getNome(), produto.getPreco(),
        (produto.getQtdeEstoque() - 1));
        ProdutoDAO.alterarQtde(produtoQtdeMenos);
    }
    
}
