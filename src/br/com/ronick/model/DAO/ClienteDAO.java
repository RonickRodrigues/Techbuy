package br.com.ronick.model.DAO;

import br.com.ronick.controller.TechbuyDB;
import br.com.ronick.model.entidade.Cliente;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ronick
 */
public class ClienteDAO {

    private static final String INSERIR_SQL = "insert into cliente "
            + "(nome, cpf, telefone, endereco) "
            + "values ('%s','%s','%s','%s')";
    private static final String ALTERAR_SQL = "update cliente "
            + "set nome='%s', cpf='%s', telefone='%s', endereco='%s' "
            + "where id=%d";
    private static final String REMOVER_SQL = "delete from cliente "
            + "where id=%d";
    private static final String SELECIONAR_TODOS = "select * from cliente order by nome";
    private static final String SELECIONAR_ID = "select * from cliente "
            + "where id=%d";
    private static final String SELECIONAR_CPF = "select * from cliente "
            + "where cpf='%s'";

    public static void inserir(Cliente cliente) {
        String sql;
        sql = String.format(INSERIR_SQL, cliente.getNome(), cliente.getCpf(),
                cliente.getTelefone(), cliente.getEndereco());
        TechbuyDB.execute(sql, true);
    }

    public static void alterar(Cliente cliente) {
        String sql;
        sql = String.format(ALTERAR_SQL, cliente.getNome(), cliente.getCpf(),
                cliente.getTelefone(), cliente.getEndereco(), cliente.getId());
        TechbuyDB.execute(sql, true);
    }

    public static void remove(int id) {
        String sql;
        sql = String.format(REMOVER_SQL, id);
        TechbuyDB.execute(sql, true);
    }

    private static List<Cliente> selecionar(String sql) {
        List<Cliente> lista = new ArrayList<>();
        Connection conn = TechbuyDB.conectar();
        try {
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String telefone = rs.getString("telefone");
                String endereco = rs.getString("endereco");
                lista.add(new Cliente(id, nome, cpf,
                        telefone, endereco));
            }
            TechbuyDB.desconectar(conn);
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
            System.exit(1);
        }
        return lista;
    }

    public static List<Cliente> selecionarTodos() {
        return selecionar(String.format(SELECIONAR_TODOS));
    }

    public static Cliente selecionarPorCpf(String cpf) {
        List<Cliente> cliente = selecionar(String.format(SELECIONAR_CPF, cpf));
        Cliente selecionaCliente = null;
        for (Cliente clt : cliente) {
            selecionaCliente = clt;
        }
        return selecionaCliente;
    }

    public static Cliente selecionarPorId(int id) {
        List<Cliente> cliente = selecionar(String.format(SELECIONAR_ID, id));
        Cliente selecionaCliente = null;
        for (Cliente clt : cliente) {
            selecionaCliente = clt;
        }
        return selecionaCliente;
    }
}
