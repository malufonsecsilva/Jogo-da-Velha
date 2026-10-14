public class Tabuleiro {
    public static final int TAMANHO = 3;

    private final Peca[][] casas = new Peca[TAMANHO][TAMANHO];

    public Tabuleiro() {
        for (int i = 0; i < TAMANHO; i++) {
            for (int j = 0; j < TAMANHO; j++) {
                casas[i][j] = Peca.VAZIA;
            }
        }
    }

    public boolean dentroDosLimites(Jogada jogada) {
        return jogada.getLinha() >= 0 && jogada.getLinha() < TAMANHO
                && jogada.getColuna() >= 0 && jogada.getColuna() < TAMANHO;
    }

    public boolean jogadaValida(Jogada jogada) {
        return dentroDosLimites(jogada) && casas[jogada.getLinha()][jogada.getColuna()] == Peca.VAZIA;
    }

    public void colocar(Jogada jogada, Peca peca) {
        if (!jogadaValida(jogada)) {
            throw new IllegalArgumentException("Jogada inválida.");
        }
        casas[jogada.getLinha()][jogada.getColuna()] = peca;
    }

    public boolean estaCheio() {
        for (Peca[] linha : casas) {
            for (Peca p : linha) {
                if (p == Peca.VAZIA) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean temVitoria(Peca peca) {
        for (int i = 0; i < TAMANHO; i++) {
            if (casas[i][0] == peca && casas[i][1] == peca && casas[i][2] == peca) return true; // linha
            if (casas[0][i] == peca && casas[1][i] == peca && casas[2][i] == peca) return true; // coluna
        }
        if (casas[0][0] == peca && casas[1][1] == peca && casas[2][2] == peca) return true;     // diagonal
        return casas[0][2] == peca && casas[1][1] == peca && casas[2][0] == peca;               // diagonal inversa
    }

    public void exibir() {
        System.out.println();
        System.out.println("     1   2   3");
        for (int i = 0; i < TAMANHO; i++) {
            System.out.println("   +---+---+---+");
            System.out.print(" " + (i + 1) + " |");
            for (int j = 0; j < TAMANHO; j++) {
                System.out.print(" " + casas[i][j].getSimbolo() + " |");
            }
            System.out.println();
        }
        System.out.println("   +---+---+---+");
    }
}
