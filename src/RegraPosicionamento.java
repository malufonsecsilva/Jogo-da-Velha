public interface RegraPosicionamento {
    String instrucao();

    Jogada resolver(Tabuleiro tabuleiro, int[] entrada);
}