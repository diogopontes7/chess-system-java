package BoardGame;

public class Piece {
    protected Position position;
    private Board board;//A cada peça tem um tabuleiro associado

    public Piece(Board board) {
        this.board = board;
        //Posicao inicial é nula
    }

    public Board getBoard() {
        return board;
    }
    
}
