public class PosicaoLivre implements RegraPosicionamento {
    @Override
    public String instrucao() {
        return "informe linha e coluna (ex: 2 3)";
    }

    @Override
    public Jogada resolver(Tabuleiro tabuleiro, int[] entrada) {
        if (entrada.length != 2
                || entrada[0] < 1 || entrada[0] > tabuleiro.getLinhas()
                || entrada[1] < 1 || entrada[1] > tabuleiro.getColunas()) {
            throw new IllegalArgumentException(
                    "Informe uma linha e uma coluna dentro do tabuleiro.");
        }

        Jogada jogada = new Jogada(entrada[0] - 1, entrada[1] - 1);
        if (!tabuleiro.jogadaValida(jogada)) {
            throw new IllegalArgumentException("Essa casa já está ocupada.");
        }

        return jogada;
    }
}