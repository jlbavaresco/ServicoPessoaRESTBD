package br.edu.ifsul.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
public class Conexao {

    private static final String banco = "jdbc:postgresql://localhost:5432/servicopessoa";
    private static final String driver = "org.postgresql.Driver";
    private static final String usuario = "postgres";
    private static final String senha = "postgres";
    private static Connection con = null;

    public Conexao() {

    }

    public static Connection getConexao() throws Exception {
        if (con == null) {
            Class.forName(driver);
            con = DriverManager.getConnection(banco, usuario, senha);
        }
        return con;
    }

    public static PreparedStatement getPreparedStatement(String sql) throws Exception {
        if (con == null) {
            con = getConexao();
        }
        return con.prepareStatement(sql);

    }
}
