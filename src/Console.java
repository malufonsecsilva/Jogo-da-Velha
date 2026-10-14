import java.util.Scanner;

/** Leitura do teclado e interações com o usuário. */
public class Console {
    private final Scanner scanner = new Scanner(System.in);

    public String lerNome(String mensagem) {
        System.out.print(mensagem);
        String nome = scanner.nextLine().trim();
        return nome.isEmpty() ? "Jogador" : nome;
    }

    /** Lê linha e coluna de 1 a 3 e converte para base 0. */
    public Jogada lerJogada(Jogador jogador) {
        while (true) {
            System.out.print(jogador + ", informe linha e coluna (ex: 2 3): ");
            if (!scanner.hasNextLine()) {
                throw new IllegalStateException("Entrada encerrada.");
            }
            String[] partes = scanner.nextLine().trim().split("\\s+");
            if (partes.length == 2) {
                try {
                    int linha = Integer.parseInt(partes[0]) - 1;
                    int coluna = Integer.parseInt(partes[1]) - 1;
                    return new Jogada(linha, coluna);
                } catch (NumberFormatException e) {
                    // Ignora e exibe a mensagem de entrada inválida abaixo
                }
            }
            System.out.println("Entrada inválida. Digite dois números separados por espaço.");
        }
    }

    public boolean perguntarNovaPartida() {
        System.out.print("Jogar novamente? (s/n): ");
        return scanner.hasNextLine() && scanner.nextLine().trim().equalsIgnoreCase("s");
    }
    public boolean perguntarSimNao(String mensagem) {
        System.out.print(mensagem);
        return scanner.hasNextLine() && scanner.nextLine().trim().equalsIgnoreCase("s");
    }
}
