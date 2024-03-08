package model.dao;

import java.util.List;
import model.entities.Estado;
import model.entities.Genero;

import model.entities.Livro;

public interface LivroDao {

    void insert(Livro obj);
    
    void insertTrocas(int usuario_id, int livro_id);

    void deleteById(Integer id);

    Livro findById(Integer id);
    
    Boolean livroExists(Livro livro);
    
    int findLivroIdByNome(String nomeLivro);
    
    Livro findLivroByAllAttributes(String nome, String autor, String editora, int ano, int qtdPaginas, Genero genero, Estado estado);

    List<Livro> findAll();
}
