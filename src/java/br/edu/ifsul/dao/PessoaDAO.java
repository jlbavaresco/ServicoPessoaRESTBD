package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Pessoa;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Singleton;

/**
 * @author Prof. Me. Jorge Luis Boeira Bavaresco
 * @email jorge.bavaresco@passofundo.ifsul.edu.br
 * @organization IFSUL - Campus Passo Fundo
 */
@Singleton
public class PessoaDAO {

    private List<Pessoa> lista;

    public PessoaDAO() {
        lista = new ArrayList<>();
        lista.add(new Pessoa(1, "Jorge", "(54)99878-0876"));
        lista.add(new Pessoa(2, "Lucas", "(54)93423-0344"));
    }

    public Pessoa persist(Pessoa objeto) throws Exception {
        String sql = "insert into pessoas (nome,telefone) values(?,?)";
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        pst.setString(1, objeto.getNome());
        pst.setString(2, objeto.getTelefone());
        if (pst.executeUpdate() <= 0) {
            throw new Exception("Erro ao inserir");
        }
        return objeto;
    }

    public Pessoa merge(Pessoa objeto) throws Exception {
        String sql = "update pessoas set nome = ?, telefone = ? where id = ?";
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        pst.setString(1, objeto.getNome());
        pst.setString(2, objeto.getTelefone());
        pst.setInt(3, objeto.getId());
        if (pst.executeUpdate() > 0) {
            return findById(objeto.getId());
        } else {
            throw new Exception("Erro ao inserir");
        }
    }

    public boolean remove(Object id) throws Exception {
        String sql = "delete from pessoas where id = ?";
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        pst.setInt(1, (int) id);
        if (pst.executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Pessoa findById(Integer id) throws Exception {
        String sql = "select * from pessoas where id = ?";
        Pessoa obj = new Pessoa();
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            obj.setId(rs.getInt("id"));
            obj.setNome(rs.getString("nome"));
            obj.setTelefone(rs.getString("telefone"));
            return obj;
        }
        return obj;
    }

    public List<Pessoa> getLista() throws Exception {
        String sql = "select * from pessoas";
        List<Pessoa> lista = new ArrayList<>();
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            Pessoa obj = new Pessoa();
            obj.setId(rs.getInt("id"));
            obj.setNome(rs.getString("nome"));
            obj.setTelefone(rs.getString("telefone"));
            lista.add(obj);
        }
        return lista;
    }

    public void setLista(List<Pessoa> lista) {
        this.lista = lista;
    }

}
