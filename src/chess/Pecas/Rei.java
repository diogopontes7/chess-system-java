package chess.Pecas;

import BoardGame.Board;
import BoardGame.Position;
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

    private boolean canMove(Position position){
        ChessPiece p = (ChessPiece)getBoard().pieces(position);//Peça nessa posicao
        return p == null || p.getColour() !=getColour();//O rei vai oara esse sitio se a posicao esta vazia ou estiver uma peça adversária nessa posicao
    }

    @Override
    public boolean[][] PossibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getCollumns()];

		Position p = new Position(0, 0);
        //Só pode andar uma casa por isso usamos o if
		// above
		p.setValues(position.getRow() - 1, position.getColumn());
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// left
		p.setValues(position.getRow(), position.getColumn() - 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

		// right
		p.setValues(position.getRow(), position.getColumn() + 1);
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;;
		}

		// below
		p.setValues(position.getRow() + 1, position.getColumn());
		if (getBoard().positionExists(p) && canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}

        //nw
         p.setValues(position.getRow() -1 , position.getColumn() - 1);
        if (getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        //Diagonal direito pra cima
        p.setValues(position.getRow() -1 , position.getColumn() + 1);
        if (getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        //diagonal á esquerda pra cima
         p.setValues(position.getRow() +1 , position.getColumn() - 1);
        if (getBoard().positionExists(p) && canMove(p)){//Se a posicao existe no tabuleiro e se a peça pode mover
            mat[p.getRow()][p.getColumn()] = true;
        }
        //sw
         p.setValues(position.getRow() +1 , position.getColumn() + 1);
        if (getBoard().positionExists(p) && canMove(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        
		return mat;
	}


    }

