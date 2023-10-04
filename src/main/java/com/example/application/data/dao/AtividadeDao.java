package com.example.application.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.application.data.entity.Atividades;

@Repository
public class AtividadeDao extends BaseDAO {

    public List<Atividades> listar() {
        String sql = "SELECT * FROM atividade";
        List<Atividades> retorno = new ArrayList<>();
        try (Connection c = obterConexao()) {
            try (PreparedStatement p = c.prepareStatement(sql)) {
                ResultSet rs = p.executeQuery();
                while (rs.next()) {
                    Atividades atividade = new Atividades();
                    atividade.setId(rs.getLong("id"));
                    atividade.setTituloAtividade(rs.getString("tituloAtividade"));
                    atividade.setDescAtividade(rs.getString("descAtividade"));
                    atividade.setDataAtividade(rs.getDate("dataAtividade"));

                    retorno.add(atividade);
                }

            } catch (SQLException ex) {
                System.out.println("Erro ao consultar");
                ex.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("Erro na conexão.");
            e.printStackTrace();
        }
        return retorno;
    }

    public Optional<Atividades> obter(Long id) {
        String sql = "SELECT * FROM atividade WHERE id = ?";
        Atividades retorno = null;
        try (Connection c = obterConexao()) {
            try (PreparedStatement p = c.prepareStatement(sql)) {
                p.setLong(1, id);
                ResultSet rs = p.executeQuery();

                if (rs.next()) {
                    retorno = new Atividades();
                    retorno.setId(rs.getLong("id"));
                    retorno.setTituloAtividade(rs.getString("tituloAtividade"));
                    retorno.setDescAtividade(rs.getString("descAtividade"));
                    retorno.setDataAtividade(rs.getDate("dataAtividade"));
                }

            } catch (SQLException ex) {
                System.out.println("Erro ao consultar Atividade com id " + id);
                ex.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println("Erro na conexão.");
            e.printStackTrace();
        }
        return Optional.ofNullable(retorno);
    }

    public boolean inserir(Atividades atividade) {
        String sql = "INSERT INTO atividade(tituloAtividade, descAtividade, dataAtividade) VALUES(?, ?, ?)";

        try (Connection c = obterConexao()) {
            c.setAutoCommit(false);
            try (PreparedStatement p = c.prepareStatement(sql)) {
                p.setString(1, atividade.getTituloAtividade());
                p.setString(2, atividade.getDescAtividade());
                p.setDate(3, new java.sql.Date(atividade.getDataAtividade().getTime()));

                p.executeUpdate();
                c.commit();
                return true;
            } catch (SQLException e) {
                System.out.println("Erro ao inserir Atividade. " + atividade.getTituloAtividade());
                e.printStackTrace();
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Erro na conexão.");
            e.printStackTrace();
            return false;
        }
    }

    public boolean alterar(Atividades atividade) {
        String sql = "UPDATE atividade SET tituloAtividade=?, descAtividade=?, dataAtividade=? WHERE id=?";

        try (Connection c = obterConexao()) {
            c.setAutoCommit(false);
            try (PreparedStatement p = c.prepareStatement(sql)) {

                p.setString(1, atividade.getTituloAtividade());
                p.setString(2, atividade.getDescAtividade());
                p.setDate(3, new java.sql.Date(atividade.getDataAtividade().getTime()));
                p.setLong(4, atividade.getId());

                p.executeUpdate();
                c.commit();
                return true;

            } catch (SQLException e) {
                System.out.println("Erro ao atualizar atividade. " + atividade.getTituloAtividade());
                e.printStackTrace();
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Erro na conexão.");
            e.printStackTrace();
            return false;
        }
    }

    public boolean remover(Atividades atividade) {
        String sql = "DELETE from atividade where id = ?";

        try (Connection c = obterConexao()) {
            c.setAutoCommit(false);
            try (PreparedStatement p = c.prepareStatement(sql)) {

                p.setLong(1, atividade.getId());

                p.executeUpdate();
                c.commit();
                return true;
            } catch (SQLException e) {
                System.out.println("Erro ao deletar atividade. " + atividade.getTituloAtividade());
                e.printStackTrace();
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Erro na conexão.");
            e.printStackTrace();
            return false;
        }
    }
}
