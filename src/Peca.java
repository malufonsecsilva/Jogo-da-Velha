/**o que fica no tabuleiro*/
public enum Peca {
    X('X'), O('O'), VAZIA(' ');

    private final char simbolo;

    Peca(char simbolo) { this.simbolo = simbolo; }

    public char getSimbolo(){
        return simbolo;
    }

}
