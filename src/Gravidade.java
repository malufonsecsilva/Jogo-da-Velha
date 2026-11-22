public class Gravidade implements RegraPosicionamento {
    @Override
    public String instrucao() {
        return "informe a coluna (ex: 3)";
    }

    @Override
    public Jogada resolver(Tabuleiro tabuleiro, int[] entrada) {
        if (entrada.length != 1
                || entrada[0] < 1 || entrada[0] > tabuleiro.getColunas()) {
            throw new IllegalArgumentException(
                    "Informe uma coluna dentro do tabuleiro.");
        }

        int coluna = entrada[0] - 1;

        for (int linha = tabuleiro.getLinhas() - 1; linha >= 0; linha--) {
            Jogada jogada = new Jogada(linha, coluna);
            if (tabuleiro.jogadaValida(jogada)) {
                return jogada;
            }
        }

        throw new IllegalArgumentException("Essa coluna está cheia.");
    }
}
