/** Controla a partida. */
public class Jogo {
    private Tabuleiro tabuleiro;
    private final Jogador[] jogadores;
    private final Console console;
    private int indiceAtual;
    private StatusPartida status;
    private Jogador vencedor;
    private final RegraPosicionamento posicionamento = new PosicaoLivre();
    private final CondicaoVitoria condicaoVitoria = new Alinhamento(3);

    public Jogo(Jogador jogador1, Jogador jogador2, Console console) {
        this.jogadores = new Jogador[] {jogador1, jogador2};
        this.console = console;
        reiniciar();
    }

    public void reiniciar() {
        this.tabuleiro = new Tabuleiro(3, 3);
        this.indiceAtual = 0;
        this.status = StatusPartida.EM_ANDAMENTO;
        this.vencedor = null;
    }

    public void iniciar() {
        boolean continuar = true;
        while (continuar) {
            reiniciar();
            jogarPartida();
            continuar = console.perguntarNovaPartida();
        }
        System.out.println("Partida encerrada. Obrigado por jogar!");
    }

    private void jogarPartida() {
        while (status == StatusPartida.EM_ANDAMENTO) {
            tabuleiro.exibir();
            Jogador atual = jogadores[indiceAtual];
            executarTurno(atual);
            atualizarStatus(atual);
            if (status == StatusPartida.EM_ANDAMENTO) {
                indiceAtual = (indiceAtual + 1) % jogadores.length;
            }
        }
        tabuleiro.exibir();
        if (status == StatusPartida.VITORIA) {
            System.out.println("Vitória de " + vencedor + "!");
        } else {
            System.out.println("Empate (deu velha)!");
        }
    }

    private void executarTurno(Jogador atual) {
        while (true) {
            try {
                int[] entrada = console.lerJogada(atual, posicionamento.instrucao());
                Jogada jogada = posicionamento.resolver(tabuleiro, entrada);
                tabuleiro.colocar(jogada, atual.getPeca());
                return;
            } catch (IllegalArgumentException e) {
                System.out.println("Jogada inválida: " + e.getMessage());
            }
        }
    }

    private void atualizarStatus(Jogador atual) {
        if (condicaoVitoria.atingida(tabuleiro, atual.getPeca())) {
            status = StatusPartida.VITORIA;
            vencedor = atual;
        } else if (tabuleiro.estaCheio()) {
            status = StatusPartida.EMPATE;
        }
    }
}
