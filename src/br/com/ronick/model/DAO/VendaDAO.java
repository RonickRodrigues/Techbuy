package br.com.ronick.model.DAO;

import br.com.ronick.controller.TechbuyDB;
import br.com.ronick.model.entidade.Venda;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author ronick
 */
public class VendaDAO {

    private static final String INSERIR_SQL = "insert into venda "
            + "(cpfcomprador, codigoProd, valorVenda) "
            + "values ('%s', %d, %d)";
    private static final String SELECIONAR_TODOS = "select * from venda";
    
    public static void inserir(Venda venda) {
        String sql;
        sql = String.format(INSERIR_SQL, venda.getCpfComprador(), venda.getCodigoProd(), venda.getValor());
        TechbuyDB.execute(sql, true);
    }
    
    private static List<Venda> selecionar(String sql) {
        List<Venda> lista = new ArrayList<>();
        Connection conn = TechbuyDB.conectar();
        try {
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String cpfComprador = rs.getString("cpfcomprador");
                int codigoProd = rs.getInt("codigoProd");
                int valorVenda = rs.getInt("valorVenda");
                lista.add(new Venda(id, cpfComprador,
                        codigoProd, valorVenda));
            }
            TechbuyDB.desconectar(conn);
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
            System.exit(1);
        }
        return lista;
    }

    public static List<Venda> selecionarTodos() {
        return selecionar(String.format(SELECIONAR_TODOS));
    }
    
    public static int totalValor() {
        List<Venda> venda = selecionar(String.format(SELECIONAR_TODOS));
        int total = 0;
        for (Venda vnd : venda) {
            total = total + vnd.getValor();
        }
        return total;
    }
    
}
