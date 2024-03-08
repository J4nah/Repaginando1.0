package model.dao;

import java.util.List;
import java.util.Map;
import model.entities.Genero;

import model.entities.Usuario;

public interface UsuarioDao {

    void insert(Usuario obj);

    void insertGenerosInteresse(Usuario obj);
    
    void excluirGenerosInteresse(int idUsuario);

    void updateSenha(Usuario obj);

    public void updatePerfil(Usuario obj);

    Usuario findById(Integer Id);
    
   //BIGEUS: ADICIONEI UM METODO PRO LOGIN
    public Map<String, String> findByEmailMAP(String email);

    public List<Genero> findGenerosByUsuarioId(int usuarioId);

    public Usuario findByEmailUSER(String email);

    List<Usuario> findAll();
}
