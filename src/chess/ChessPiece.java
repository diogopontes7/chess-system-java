package chess;

import BoardGame.Board;
import BoardGame.Piece;
import BoardGame.Position;

//Faz parte de uma peça, ou seja, é superclasse de peça
public abstract class ChessPiece extends Piece{

    private Colour colour;//Cor da peça
    private int moveCount;

    public ChessPiece(Board board, Colour colour) {
        super(board);
        this.colour = colour;
    }

    public Colour getColour() {
        return colour;
    }

    public int getMoveCount(){
        return moveCount;
    }

    public void increaseMoveCount(){
        moveCount++;
    }
    public void decreaseMoveCount(){
        moveCount--;
    }

    public ChessPosition getChessPosition(){
        return ChessPosition.fromPosition(position);//A posição da peça é convertida em posicao de xadrez e retornada
    }
    protected boolean isThereOpponentPiece(Position position) {
		ChessPiece p = (ChessPiece)getBoard().pieces(position);//Peça nessa posicao
		return p != null && p.getColour() != colour;//Se nao for nulo e nao for da mesma cor, existe uma peça nessa posicao oponente
	}
    
}
