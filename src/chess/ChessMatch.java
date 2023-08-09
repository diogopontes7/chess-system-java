package chess;
//Coração do projeto, onde vai ter as regras de xadrez

import BoardGame.Board;
import BoardGame.Position;
import chess.Pecas.Rei;
import chess.Pecas.Rook;

public class ChessMatch {
    private Board board;//Um jogo de xadrez tem que ter um tabuleiro
    //Ao chamar o jogo, cria se um tabuleiro 8 por 8 e implementa se o setup inicial
    public ChessMatch(){
        board = new Board(8, 8);
        initialSetup();//Instancias o setupInicial
    }

    //Obter todas as peças de xadrez
    public ChessPiece[][] getPieces(){
        ChessPiece[][] matriz = new ChessPiece[board.getRows()][board.getCollumns()];//Criação da matriz
        for(int i = 0; i<board.getRows(); i++){
            for(int j=0; j<board.getCollumns(); j++){
                matriz[i][j] = (ChessPiece) board.pieces(i,j);//Fazemos downcast para ser definida como uma peça de xadrez e nao uma peça comum 
            }
        }
        return matriz;
    }
    private void initialSetup(){
        placeNewPiece('b', 6, new Rook(board, Colour.WHITE));
        placeNewPiece('c', 3, new Rei(board, Colour.WHITE));

    }

    private void placeNewPiece (char column, int row, ChessPiece chessPiece){
        board.placePiece(chessPiece, new ChessPosition(column, row).toPosition());//Vai converter de posicao normal, para peça de xadrez
    }
}
