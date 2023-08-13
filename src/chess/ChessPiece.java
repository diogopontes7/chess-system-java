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

    protected boolean isThereOpponentPiece(Position position) {
		ChessPiece p = (ChessPiece)getBoard().pieces(position);//Peça nessa posicao
		return p != null && p.getColour() != colour;//Se nao for nulo e nao for da mesma cor, existe uma peça nessa posicao oponente
	}
}
