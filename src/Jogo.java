/** Controla a partida. */
public class Jogo {
    private Tabuleiro tabuleiro;
    private final Jogador[] jogadores;
    private final Console console;
    private int indiceAtual;
    private StatusPartida status;
    private Jogador vencedor;

    public Jogo(Jogador jogador1, Jogador jogador2, Console console) {
        this.jogadores = new Jogador[] {jogador1, jogador2};
        this.console = console;
        reiniciar();
    }

    public void reiniciar() {
        this.tabuleiro = new Tabuleiro();
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
            Jogada jogada = console.lerJogada(atual);
            if (tabuleiro.jogadaValida(jogada)) {
                tabuleiro.colocar(jogada, atual.getPeca());
                return;
            }
            System.out.println("Jogada inválida: casa fora do tabuleiro ou já ocupada.");
        }
    }

    private void atualizarStatus(Jogador atual) {
        if (tabuleiro.temVitoria(atual.getPeca())) {
            status = StatusPartida.VITORIA;
            vencedor = atual;
        } else if (tabuleiro.estaCheio()) {
            status = StatusPartida.EMPATE;
        }
    }
}
