package chess.Pecas;

import BoardGame.Board;
import BoardGame.Position;
import chess.ChessPiece;
import chess.Colour;

public class Cavalo extends ChessPiece{

    public Cavalo(Board board, Colour colour) {
        super(board, colour);
    }

    private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece)getBoard().pieces(position);//Igual com o rei
		return p == null || p.getColour() != getColour();
	}

    @Override
    public boolean[][] PossibleMoves() {
       boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getCollumns()];
       Position p = new Position(0, 0);

       p.setValues(position.getRow() -2 , position.getColumn()-1);
       if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
       }

       p.setValues(position.getRow() -2 , position.getColumn()+1);
       if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
       }

       p.setValues(position.getRow() +2 , position.getColumn()-1);
       if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
       }

       p.setValues(position.getRow() +2 , position.getColumn()+1);
       if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
       }

        p.setValues(position.getRow() -1 , position.getColumn()-2);
       if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
       }

        p.setValues(position.getRow() -1 , position.getColumn() +2);
       if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
       }
        p.setValues(position.getRow() +1 , position.getColumn()-2);
       if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
       }
        p.setValues(position.getRow() +1 , position.getColumn()+2);
       if(getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
       }
       return mat;
    }

    @Override
    public String toString() {
        return "C";
    }
   
}
