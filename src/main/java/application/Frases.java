/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package application;

import java.util.List;

/**
 *
 * @author Rosemeire
 */
public class Frases {
    private String frase;
    private String autor;

    //////////////////////////////////////////////////////////////////////////////////////
    public Frases() {
    }

    public Frases(String frase, String autor) {
        this.frase = frase;
        this.autor = autor;
    }

    //++++++++++++++++++++++++++++++++++++++++++++
    public String getFrase() {
        return frase;
    }
    public void setFrase(String frase) {
        this.frase = frase;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
    
 /////////////////////////////////////////////////////////////////////////////////////// 
}
