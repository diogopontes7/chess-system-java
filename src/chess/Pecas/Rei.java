package chess.Pecas;

import BoardGame.Board;
import chess.ChessPiece;
import chess.Colour;

public class Rei extends ChessPiece{

    public Rei(Board board, Colour colour) {
        super(board, colour);
    }

    @Override
    public String toString(){
        return "K";
    }

    @Override
    public boolean[][] PossibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getCollumns()];//MAtriz da mesma dimensao do tabuleiro
        return mat;
    }
}
