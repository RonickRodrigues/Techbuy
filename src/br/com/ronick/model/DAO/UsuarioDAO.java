package br.com.ronick.model.DAO;

import br.com.ronick.controller.TechbuyDB;
import br.com.ronick.model.entidade.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author ronick
 */
public class UsuarioDAO {

    private static final String INSERIR_SQL = "insert into usuario "
            + "(login, senha) "
            + "values ('%s', '%s')";
    private static final String ALTERAR_SQL = "update usuario "
            + "set login='%s', senha='%s' "
            + "where id=%d";
    private static final String REMOVER_SQL = "delete from usuario "
            + "where id=%d";
    private static final String SELECIONAR_SQL = "select * from usuario ";

    public static void inserir(Usuario usuario) {
        String sql;
        sql = String.format(INSERIR_SQL, usuario.getLogin(), usuario.getSenha());
        TechbuyDB.execute(sql, true);
    }

    public static void alterar(Usuario usuario) {
        String sql;
        sql = String.format(ALTERAR_SQL, usuario.getLogin(), usuario.getSenha(), usuario.getId());
        TechbuyDB.execute(sql, true);
    }

    public static void remover(int id) {
        String sql;
        sql = String.format(REMOVER_SQL, id);
        TechbuyDB.execute(sql, true);
    }

    public static List<Usuario> selecionar(String sql) {
        List<Usuario> usuario = new ArrayList<>();
        Connection conn = TechbuyDB.conectar();
        try {
            ResultSet rs = conn.createStatement().executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String login = rs.getString("login");
                String senha = rs.getString("senha");
                usuario.add(new Usuario(id, login, senha));
            }
            TechbuyDB.desconectar(conn);
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
            System.exit(1);
        }
        return usuario;
    }
    
    public static List<Usuario> selecionarTodos() {
        return selecionar(String.format(SELECIONAR_SQL));
    }
}
