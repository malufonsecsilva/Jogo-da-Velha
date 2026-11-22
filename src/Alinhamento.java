public class Alinhamento implements CondicaoVitoria {
    private static final int[][] DIRECOES = {
            {0, 1},  // horizontal
            {1, 0},  // vertical
            {1, 1},  // diagonal para a direita
            {1, -1}  // diagonal para a esquerda
    };

    private final int quantidade;

    public Alinhamento(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public boolean atingida(Tabuleiro tabuleiro, Peca peca) {
        for (int linha = 0; linha < tabuleiro.getLinhas(); linha++) {
            for (int coluna = 0; coluna < tabuleiro.getColunas(); coluna++) {
                for (int[] direcao : DIRECOES) {
                    if (temSequencia(tabuleiro, linha, coluna,
                            direcao[0], direcao[1], peca)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean temSequencia(Tabuleiro tabuleiro, int linha, int coluna,
                                int passoLinha, int passoColuna, Peca peca) {
        for (int i = 0; i < quantidade; i++) {
            if (!tabuleiro.dentroDosLimites(linha, coluna)
                    || tabuleiro.getPeca(linha, coluna) != peca) {
                return false;
            }

            linha += passoLinha;
            coluna += passoColuna;
        }
        return true;
    }
}
