public class Tabuleiro {
    private final int linhas;
    private final int colunas;
    private final Peca[][] casas;

    public Tabuleiro(int linhas, int colunas) {
        this.linhas = linhas;
        this.colunas = colunas;
        this.casas = new Peca[linhas][colunas];

        for (int linha = 0; linha < linhas; linha++) {
            for (int coluna = 0; coluna < colunas; coluna++) {
                casas[linha][coluna] = Peca.VAZIA;
            }
        }
    }

    public int getLinhas() {
    return linhas;
    }

    public int getColunas() {
    return colunas;
    }

    public boolean dentroDosLimites(Jogada jogada) {
        return dentroDosLimites(jogada.getLinha(), jogada.getColuna());
    }

    public boolean dentroDosLimites(int linha, int coluna) {
        return linha >= 0 && linha < linhas
                && coluna >= 0 && coluna < colunas;
    }

    public Peca getPeca(int linha, int coluna) {
    return casas[linha][coluna];
    }

    public boolean jogadaValida(Jogada jogada) {
        return dentroDosLimites(jogada)
                && casas[jogada.getLinha()][jogada.getColuna()] == Peca.VAZIA;
    }

    public void colocar(Jogada jogada, Peca peca) {
        if (!jogadaValida(jogada)) {
            throw new IllegalArgumentException("Jogada inválida.");
        }

        casas[jogada.getLinha()][jogada.getColuna()] = peca;
    }

    public boolean estaCheio() {
        for (Peca[] linha : casas) {
            for (Peca peca : linha) {
                if (peca == Peca.VAZIA) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean temVitoria(Peca peca, int quantidade) {
        int[][] direcoes = {
                {0, 1},  // horizontal
                {1, 0},  // vertical
                {1, 1},  // diagonal para a direita
                {1, -1}  // diagonal para a esquerda
        };

        for (int linha = 0; linha < linhas; linha++) {
            for (int coluna = 0; coluna < colunas; coluna++) {
                for (int[] direcao : direcoes) {
                    if (temSequencia(linha, coluna, direcao[0], direcao[1],
                            peca, quantidade)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean temSequencia(int linha, int coluna, int passoLinha,
                                 int passoColuna, Peca peca, int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            if (!dentroDosLimites(linha, coluna) || casas[linha][coluna] != peca) {
                return false;
            }

            linha += passoLinha;
            coluna += passoColuna;
        }
        return true;
    }

    public void exibir() {
        System.out.println();
        System.out.print("    ");

        for (int coluna = 1; coluna <= colunas; coluna++) {
            System.out.printf(" %2d ", coluna);
        }
        System.out.println();

        String separador = "    " + "+---".repeat(colunas) + "+";

        for (int linha = 0; linha < linhas; linha++) {
            System.out.println(separador);
            System.out.printf("%3d |", linha + 1);

            for (int coluna = 0; coluna < colunas; coluna++) {
                System.out.printf(" %c |", casas[linha][coluna].getSimbolo());
            }
            System.out.println();
        }
        System.out.println(separador);
    }
}