package chess.Pecas;

import BoardGame.Board;
import BoardGame.Position;
import chess.ChessPiece;
import chess.Colour;

//Uma peça de xadrez, ou seja vai extender de ChessPieces
public class Rook extends ChessPiece {

    public Rook(Board board, Colour colour) {
        super(board, colour);
    }

    @Override
    public String toString() {
        return "T";
    }

    @Override
    public boolean[][] PossibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getCollumns()];// MAtriz da mesma dimensao do
                                                                                      // tabuleiro

        Position p = new Position(0, 0);

        // Above the piece
        p.setValues(position.getRow() - 1, position.getColumn());
        while (getBoard().positionExists(p) && !getBoard().ExisteUmaPeca(p)) {
            // Enquanto a posicao p exisitr e nao existir uma peça lá, a posicao é valida
            mat[p.getRow()][p.getColumn()] = true;
            p.setRow(p.getRow() - 1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {// Se exisitr uma peça oponente, pega nela
            mat[p.getRow()][p.getColumn()] = true;// A peça move para esse sitio
        }

        //Para a esquerda
           p.setValues(position.getRow() - 1, position.getColumn() -1 );
        while (getBoard().positionExists(p) && !getBoard().ExisteUmaPeca(p)) {
            // Enquanto a posicao p exisitr e nao existir uma peça lá, a posicao é valida
            mat[p.getRow()][p.getColumn()] = true;
            p.setColumn(p.getColumn()-1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {// Se exisitr uma peça oponente, pega nela
            mat[p.getRow()][p.getColumn()] = true;// A peça move para esse sitio
        }
        //Para a direira
           p.setValues(position.getRow() - 1, position.getColumn() + 1 );
        while (getBoard().positionExists(p) && !getBoard().ExisteUmaPeca(p)) {
            // Enquanto a posicao p exisitr e nao existir uma peça lá, a posicao é valida
            mat[p.getRow()][p.getColumn()] = true;
            p.setColumn(p.getColumn() + 1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {// Se exisitr uma peça oponente, pega nela
            mat[p.getRow()][p.getColumn()] = true;// A peça move para esse sitio
        }
        //Para a baixo
         p.setValues(position.getRow() + 1, position.getColumn());
        while (getBoard().positionExists(p) && !getBoard().ExisteUmaPeca(p)) {
            // Enquanto a posicao p exisitr e nao existir uma peça lá, a posicao é valida
            mat[p.getRow()][p.getColumn()] = true;
            p.setRow(p.getRow() + 1);
        }
        if (getBoard().positionExists(p) && isThereOpponentPiece(p)) {// Se exisitr uma peça oponente, pega nela
            mat[p.getRow()][p.getColumn()] = true;// A peça move para esse sitio
        }
        //Logica da torre implementado
        return mat;
    }

}