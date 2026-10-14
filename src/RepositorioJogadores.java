import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Salva e carrega a lista de jogadores em um arquivo JSON (sem bibliotecas externas). */
public class RepositorioJogadores {
    // Lê objetos no formato {"nome": "...", "peca": "X"}
    private static final Pattern PADRAO = Pattern.compile(
            "\"nome\"\\s*:\\s*\"((?:[^\"\\\\]|\\\\.)*)\"\\s*,\\s*\"peca\"\\s*:\\s*\"(X|O)\"");

    private final Path arquivo;

    public RepositorioJogadores(Path arquivo) {
        this.arquivo = arquivo;
    }

    public void salvar(List<Jogador> jogadores) throws IOException {
        StringBuilder json = new StringBuilder("[\n");
        for (int i = 0; i < jogadores.size(); i++) {
            Jogador j = jogadores.get(i);
            json.append("  {\"nome\": \"").append(escapar(j.getNome()))
                    .append("\", \"peca\": \"").append(j.getPeca().name()).append("\"}");
            json.append(i < jogadores.size() - 1 ? ",\n" : "\n");
        }
        json.append("]\n");
        Files.writeString(arquivo, json.toString(), StandardCharsets.UTF_8);
    }

    public List<Jogador> carregar() throws IOException {
        List<Jogador> jogadores = new ArrayList<>();
        if (!Files.exists(arquivo)) {
            return jogadores;
        }
        String json = Files.readString(arquivo, StandardCharsets.UTF_8);
        Matcher m = PADRAO.matcher(json);
        while (m.find()) {
            jogadores.add(new Jogador(desescapar(m.group(1)), Peca.valueOf(m.group(2))));
        }
        return jogadores;
    }

    private static String escapar(String texto) {
        StringBuilder sb = new StringBuilder();
        for (char c : texto.toCharArray()) {
            if (c == '"' || c == '\\') {
                sb.append('\\');
            }
            sb.append(c);
        }
        return sb.toString();
    }

    private static String desescapar(String texto) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            if (c == '\\' && i + 1 < texto.length()) {
                i++;
                c = texto.charAt(i);
            }
            sb.append(c);
        }
        return sb.toString();
    }
}
