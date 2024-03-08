package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GerenciadorFrases {

    private List<Frases> frases;
    private Random random;

    public GerenciadorFrases() {
        this.frases = new ArrayList<>();
        this.random = new Random();
    }

    public void adicionarFrase(Frases novaFrase) {
        frases.add(novaFrase);
    }

    public Frases selecionarFraseAleatoria() {
        if (frases.isEmpty()) {
            return null; // Retorna null se não houver frases na lista
        }

        int indiceAleatorio = random.nextInt(frases.size());
        return frases.get(indiceAleatorio);
    }
}
