package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import java.util.HashMap;
import java.util.Map;
import model.dao.UsuarioDao;
import model.entities.Genero;
import model.entities.Usuario;

public class UsuarioDaoJDBC implements UsuarioDao {

    private Connection conn;

    public UsuarioDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Usuario obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("INSERT INTO usuario "
                    + "(Nome_Usuario, Celular, Email, Cidade, Uf, Senha) "
                    + "VALUES "
                    + "(?, ?, ?, ?, ?,?)", Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getNome());
            st.setString(2, obj.getCelular());
            st.setString(3, obj.getEmail());
            st.setString(4, obj.getCidade());
            st.setString(5, obj.getUf());
            st.setInt(6, obj.getSenha());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                DB.closeResultSet(rs);
            } else {
                throw new DbException("Unexpected error! No rows affected!");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }

    }

    //BIGEUS: guardar generosinteresse no banco de dados par poder mostrar na tela de Meu Perfil
    @Override
    public void insertGenerosInteresse(Usuario obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("INSERT INTO genero_usuario (Genero_id, Usuario_id) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);

            for (Genero genero : obj.getGenerosInteresse()) {
                st.setInt(2, obj.getId()); // Supondo que getId() retorne o ID do usuário
                st.setInt(1, genero.getId());
                st.executeUpdate();
            }

        } catch (SQLException e) {
            throw new DbException("Erro ao inserir os generos de interesse." + e.getMessage());
        } finally {
            DB.closeStatement(st);
        }

    }

    @Override
    public void updateSenha(Usuario obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("UPDATE usuario set Senha = ? Where Usuario_id = ?");

            st.setInt(1, obj.getSenha());
            st.setInt(2, obj.getId());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }

    }
    
    @Override
    public void updatePerfil(Usuario obj){
        PreparedStatement st = null;
    try {
        st = conn.prepareStatement("UPDATE usuario SET Cidade = ?, Celular = ?, Email = ?, UF = ? WHERE Usuario_id = ?");

       
        st.setString(1, obj.getCidade());
        st.setString(2, obj.getCelular());
        st.setString(3, obj.getEmail());
        st.setString(4, obj.getUf());
        st.setInt(5, obj.getId());

        st.executeUpdate();
    } catch (SQLException e) {
        throw new DbException(e.getMessage());
    } finally {
        DB.closeStatement(st);
    }
    }
    
    

    @Override
    public Usuario findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM usuario WHERE Usuario_id = ?");

            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                Usuario obj = instantiateUsuario(rs);
                return obj;
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }

    }

    //BIGEUS: PARA MOSTRAR OS GENEROS ASSOCIADOS AO USUARIO NO BANCO DE DADOS ESTOU TRAZENDO ESSAS INFOS AQ
    @Override
    public List<Genero> findGenerosByUsuarioId(int usuarioId) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT g.* FROM genero g "
                    + "INNER JOIN genero_usuario gu ON g.Genero_id = gu.Genero_id "
                    + "WHERE gu.Usuario_id = ?");
            st.setInt(1, usuarioId);
            rs = st.executeQuery();

            List<Genero> generos = new ArrayList<>();

            while (rs.next()) {
                Genero genero = instantiateGenero(rs);
                generos.add(genero);
            }

            return generos;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private Usuario instantiateUsuario(ResultSet rs) throws SQLException {
        Usuario obj = new Usuario();
        obj.setId(rs.getInt("Usuario_id"));
        obj.setNome(rs.getString("Nome_usuario"));
        obj.setCelular(rs.getString("Celular"));
        obj.setEmail(rs.getString("Email"));
        obj.setCidade(rs.getString("Cidade"));
        obj.setUf(rs.getString("Uf"));
        obj.setSenha(rs.getInt("Senha"));
        return obj;
    }

    @Override
    public List<Usuario> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM usuario ORDER BY Nome_usuario");

            rs = st.executeQuery();

            List<Usuario> list = new ArrayList<>();

            while (rs.next()) {

                Usuario obj = instantiateUsuario(rs);
                list.add(obj);

            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    //BIGEUS: ADICIONEI UM METODO PRO LOGIN
    public Map<String, String> findByEmailMAP(String email) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM usuario WHERE Email = ?");
            st.setString(1, email);
            rs = st.executeQuery();

            if (rs.next()) {
                Map<String, String> usuarioInfo = new HashMap<>();
                usuarioInfo.put("email", rs.getString("Email"));
                usuarioInfo.put("senha", rs.getString("Senha"));
                usuarioInfo.put("nome", rs.getString("Nome_usuario"));
                return usuarioInfo;
            }

            return null; // Usuário não encontrado
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public Usuario findByEmailUSER(String email) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM usuario WHERE Email = ?");
            st.setString(1, email);
            rs = st.executeQuery();

            if (rs.next()) {
                Usuario obj = instantiateUsuario(rs);
                return obj;
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    //BIGEUS: PARA PODER PEDIR OS DADOS DE GENERO_USUARIO
    private Genero instantiateGenero(ResultSet rs) throws SQLException {

        Integer id = rs.getInt("Genero_id");
        String nome = rs.getString("Genero_nome");

        return new Genero(id, nome);
    }

    @Override
    public void excluirGenerosInteresse(int idUsuario) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("DELETE FROM genero_usuario WHERE Usuario_id = ?");
            st.setInt(1, idUsuario);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Erro ao excluir os gêneros de interesse: " + e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    }

