package chess.Pecas;

import BoardGame.Board;
import chess.ChessPiece;
import chess.Colour;

//Uma peça de xadrez, ou seja vai extender de ChessPieces
public class Rook extends ChessPiece{

    public Rook(Board board, Colour colour) {
        super(board, colour);
    }

    @Override
    public String toString() {
        return "T";
    }

    @Override
    public boolean[][] PossibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getCollumns()];//MAtriz da mesma dimensao do tabuleiro
        return mat;
    }
    
}
