public class Jogador {
    private final String nome;
    private final Peca peca;

    public Jogador(String nome, Peca peca) {
        this.nome = nome;
        this.peca = peca;
    }

    public String getNome() {
        return nome;
    }

    public Peca getPeca() {
        return peca;
    }

    @Override
    public String toString() {
        return nome + " (" + peca.getSimbolo() + ")";
    }
}
