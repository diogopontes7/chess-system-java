package chess.Pecas;

import BoardGame.Board;
import BoardGame.Position;
import chess.ChessPiece;
import chess.Colour;

public class Rainha extends ChessPiece{

    public Rainha(Board board, Colour colour) {
        super(board, colour);
    }

    @Override
    public boolean[][] PossibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getCollumns()];
        Position p = new Position(0, 0);
        //Diagonais
        p.setValues(position.getRow()+1, position.getColumn()-1);
        while(getBoard().positionExists(p) && !getBoard().ExisteUmaPeca(p)){
            mat[p.getRow()][p.getColumn()]=true;
            p.setValues(p.getRow()+1, p.getColumn()-1);        
        }
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()]=true;
        }

         p.setValues(position.getRow()-1, position.getColumn()+1);
        while(getBoard().positionExists(p) && !getBoard().ExisteUmaPeca(p)){
            mat[p.getRow()][p.getColumn()]=true;
            p.setValues(p.getRow()-1, p.getColumn()+1);        
        }
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()]=true;
        }

         p.setValues(position.getRow()-1, position.getColumn()-1);
        while(getBoard().positionExists(p) && !getBoard().ExisteUmaPeca(p)){
            mat[p.getRow()][p.getColumn()]=true;
            p.setValues(p.getRow()-1, p.getColumn()-1);        
        }
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()]=true;
        }

         p.setValues(position.getRow()+1, position.getColumn()+1);
        while(getBoard().positionExists(p) && !getBoard().ExisteUmaPeca(p)){
            mat[p.getRow()][p.getColumn()]=true;
            p.setValues(p.getRow()+1, p.getColumn()+1);        
        }
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()]=true;
        }

        //direita
        p.setValues(position.getRow(), position.getColumn() + 1);
		while (getBoard().positionExists(p) && !getBoard().ExisteUmaPeca(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() + 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

        //Esquerda
        p.setValues(position.getRow(), position.getColumn() - 1);
		while (getBoard().positionExists(p) && !getBoard().ExisteUmaPeca(p)) {
			mat[p.getRow()][p.getColumn()] = true;
			p.setColumn(p.getColumn() - 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
        //Abaixo
        p.setValues(position.getRow() + 1, position.getColumn() );
		while (getBoard().positionExists(p) && !getBoard().ExisteUmaPeca(p)) {
			mat[p.getRow()][p.getColumn()] = true;
            p.setRow(p.getRow() + 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

        //Acima
         p.setValues(position.getRow() - 1, position.getColumn() );
		while (getBoard().positionExists(p) && !getBoard().ExisteUmaPeca(p)) {
			mat[p.getRow()][p.getColumn()] = true;
            p.setRow(p.getRow() - 1);
		}
		if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
        return mat;
    }

    @Override
    public String toString() {
        return "Q";
    }
    
}
