package br.com.ronick.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author ronick
 */
public class TechbuyDB {

    public static Connection conectar() {
        Connection conn = null;
        final String USUARIO = "root";
        final String SENHA = "Rnew1103";
        final String URL = "jdbc:mysql://localhost:3306/techbuy";
        try {
            conn = DriverManager.getConnection(URL,
                    USUARIO, SENHA);
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
            System.exit(1);
        }
        return conn;
    }

    public static void desconectar(Connection c) {
        try {
            c.close();
        } catch (SQLException ex) {
        }
    }

    public static void execute(String sql, boolean teste) {
        Connection conn = conectar();
        try {
            conn.createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
            if (!teste) {
                System.exit(1);
            }
        }
        desconectar(conn);
    }

    public static void inicializarDB() {
        String sql;
        sql = "create table if not exists cliente ( "
                + "id int auto_increment not null primary key, "
                + "nome varchar(30) not null, "
                + "cpf varchar(14) not null unique, "
                + "telefone varchar(15) not null, "
                + "endereco varchar(40) default 'Sem endereco cadastrado' "
                + ")";
        execute(sql, true);
        sql = "create table if not exists produto ( "
                + "id int auto_increment not null primary key, "
                + "nome varchar(30) not null, "
                + "preco int not null, "
                + "qtdeEstoque int not null default 0 "
                + ")";
        execute(sql, true);
        sql = "create table if not exists venda ( "
                + "id int auto_increment not null primary key, "
                + "cpfcomprador varchar(14), "
                + "codigoProd int, "
                + "valorVenda int not null"
                + ")";
        execute(sql, true);
    }
}
