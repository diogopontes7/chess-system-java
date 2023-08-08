package chess;

import BoardGame.Board;
import BoardGame.Piece;

//Faz parte de uma peça, ou seja, é superclasse de peça
public class ChessPiece extends Piece{

    private Colour colour;//Cor da peça

    public ChessPiece(Board board, Colour colour) {
        super(board);
        this.colour = colour;
    }

    public Colour getColour() {
        return colour;
    }
    
    
}
