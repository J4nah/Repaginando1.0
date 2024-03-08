package model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;
    private String celular;
    private String email;
    private String cidade;
    private int senha;
    private String uf;
    private ArrayList<Genero>generosInteresse;
    private ArrayList<Livro>livrosUsuario;

    public Usuario() {
    generosInteresse = new ArrayList();
    livrosUsuario = new ArrayList();
    }
    
    public Usuario(Integer id, String nome, String celular, String email, String cep, int senha) {
        this.id = id;
        this.nome = nome;
        this.celular = celular;
        this.email = email;
        this.cidade = cep;
        this.senha = senha;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public int getSenha() {
        return senha;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public ArrayList<Genero> getGenerosInteresse() {
        return generosInteresse;
    }

    public void setGenerosInteresse(ArrayList<Genero> generosInteresse) {
        this.generosInteresse = generosInteresse;
    }

    public ArrayList<Livro> getLivrosUsuario() {
        return livrosUsuario;
    }

    public void setLivrosUsuario(ArrayList<Livro> livrosUsuario) {
        this.livrosUsuario = livrosUsuario;
    }
    
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Usuario other = (Usuario) obj;
        return id == other.id;
    }

    @Override
    public String toString() {
        return "Usuario [id=" + id + ", nome=" + nome + ", celular=" + celular + ", email=" + email + ", cep=" + cidade
                + ", senha=" + senha + "]";
    }
    
      public void adicionarGenerosInteresse(Genero g){
        this.generosInteresse.add(g);
    }
    
    public void removerGenerosInteresse(Genero g){
        this.generosInteresse.remove(g);
    }
    
    public void limparGenerosInteresse(){
        this.generosInteresse.clear();
    }
    
    public void adicionarLivro(Livro l){
        this.livrosUsuario.add(l);
    }
    
    public void removerLivro(Livro l){
        this.livrosUsuario.remove(l);
    }

}
