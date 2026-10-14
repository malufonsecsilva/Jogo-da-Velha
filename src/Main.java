import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Console console = new Console();
        RepositorioJogadores repo = new RepositorioJogadores(Path.of("jogadores.json"));

        System.out.println("=== JOGO DA VELHA ===");

        Jogador j1;
        Jogador j2;

        List<Jogador> salvos = carregarSalvos(repo);
        boolean reutilizar = salvos.size() == 2 && console.perguntarSimNao(
                "Usar os jogadores da última partida (" + salvos.get(0) + " e " + salvos.get(1) + ")? (s/n): ");

        if (reutilizar) {
            j1 = salvos.get(0);
            j2 = salvos.get(1);
        } else {
            j1 = new Jogador(console.lerNome("Nome do Jogador 1 (X): "), Peca.X);
            j2 = new Jogador(console.lerNome("Nome do Jogador 2 (O): "), Peca.O);
            salvar(repo, j1, j2);
        }

        new Jogo(j1, j2, console).iniciar();
    }

    private static List<Jogador> carregarSalvos(RepositorioJogadores repo) {
        try {
            return repo.carregar();
        } catch (IOException e) {
            System.out.println("Não foi possível ler os jogadores salvos: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private static void salvar(RepositorioJogadores repo, Jogador j1, Jogador j2) {
        try {
            repo.salvar(List.of(j1, j2));
        } catch (IOException e) {
            System.out.println("Não foi possível salvar os jogadores: " + e.getMessage());
        }
    }
}