import java.util.Scanner;

/** Leitura do teclado e interações com o usuário. */
public class Console {
    private final Scanner scanner = new Scanner(System.in);

    public String lerNome(String mensagem) {
        System.out.print(mensagem);
        String nome = scanner.nextLine().trim();
        return nome.isEmpty() ? "Jogador" : nome;
    }

    /** Lê a linha e a coluna de 1 a 3 e converte tudo pra base 0 */
    public int[] lerJogada(Jogador jogador, String instrucao) {
        System.out.print(jogador + ", " + instrucao + ": ");

        if (!scanner.hasNextLine()) {
           throw new IllegalStateException("Entrada encerrada.");
        }

        String[] partes = scanner.nextLine().trim().split("\\s+");
        int[] numeros = new int[partes.length];

        try {
            for (int i = 0; i < partes.length; i++) {
                numeros[i] = Integer.parseInt(partes[i]);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                "Digite apenas números inteiros separados por espaço.");
    }

    return numeros;
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
