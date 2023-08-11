package chess;

import BoardGame.Board;
import BoardGame.Piece;
import BoardGame.Position;

//Faz parte de uma peça, ou seja, é superclasse de peça
public abstract class ChessPiece extends Piece{

    private Colour colour;//Cor da peça

    public ChessPiece(Board board, Colour colour) {
        super(board);
        this.colour = colour;
    }

    public Colour getColour() {
        return colour;
    }

    protected boolean isThereOpponentPiece(Position position){
        ChessPiece p = (ChessPiece)getBoard().pieces(position);//Peça nessa posição
        return p != null && p.getColour() != colour;
        //Se o p existe e se a sua cor é diferente da peça que eu tenho
    }
}
