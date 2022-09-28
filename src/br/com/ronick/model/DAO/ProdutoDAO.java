package br.com.ronick.model.DAO;

import br.com.ronick.controller.TechbuyDB;
import br.com.ronick.model.entidade.Produto;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ronic
 */
public class ProdutoDAO {

    private static final String INSERIR_SQL = "insert into produto "
            + "(nome, preco, qtdeEstoque) "
            + "values ('%s','%d','%d')";
    private static final String ALTERAR_SQL = "update produto "
            + "set nome='%s', preco='%d', qtdeEstoque='%d' "
            + "where id=%d";
    private static final String ALTERAR_QTDE_SQL = "update produto "
            + "set qtdeEstoque='%d' where id=%d";
    private static final String REMOVER_SQL = "delete from produto "
            + "where id=%d";
    private static final String SELECIONAR_TODOS = "select * from produto order by nome";
    private static final String SELECIONAR_ID = "select * from produto "
            + "where id=%d";
    private static final String SELECIONAR_NOME = "select * from produto "
            + "where nome='%s'";

    public static void inserir(Produto produto) {
        String sql;
        sql = String.format(INSERIR_SQL, produto.getNome(), produto.getPreco(),
                produto.getQtdeEstoque());
        TechbuyDB.execute(sql, true);
    }

    public static void alterar(Produto produto) {
        String sql;
        sql = String.format(ALTERAR_SQL, produto.getNome(), produto.getPreco(), 
                produto.getQtdeEstoque(), produto.getId());
        TechbuyDB.execute(sql, true);
    }

    public static void alterarQtde(Produto produto) {
        String sql;
        sql = String.format(ALTERAR_QTDE_SQL, produto.getQtdeEstoque(), produto.getId());
        TechbuyDB.execute(sql, true);
    }
    
    public static void remove(int id) {
        String sql;
        sql = String.format(REMOVER_SQL, id);
        TechbuyDB.execute(sql, true);
    }

    public static List<Produto> selecionar(String sql) {
        List<Produto> lista = new ArrayList<>();
        Connection conn = TechbuyDB.conectar();
        try {
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                int preco = rs.getInt("preco");
                int qtdeEstoque = rs.getInt("qtdeEstoque");
                lista.add(new Produto(id, nome, preco,
                        qtdeEstoque));
            }
            TechbuyDB.desconectar(conn);
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
            System.exit(1);
        }
        return lista;
    }

    public static List<Produto> selecionarTodos() {
        return selecionar(String.format(SELECIONAR_TODOS));
    }

    public static Produto selecionarPorNome(String nome) {
        List<Produto> produto = selecionar(String.format(SELECIONAR_NOME, nome));
        Produto selecionaProduto = null;
        for (Produto prd : produto) {
            selecionaProduto = prd;
        }
        return selecionaProduto;
    }

    public static Produto selecionarPorId(int id) {
        List<Produto> produto = selecionar(String.format(SELECIONAR_ID, id));
        Produto selecionaProduto = null;
        for (Produto prd : produto) {
            selecionaProduto = prd;
        }
        return selecionaProduto;
    }
}
