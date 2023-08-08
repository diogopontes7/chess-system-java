package chess;
//Coração do projeto, onde vai ter as regras de xadrez

import BoardGame.Board;

public class ChessMatch {
    private Board board;//Um jogo de zadrez tem que ter um tabuleiro

    public ChessMatch(){
        board = new Board(8, 8);
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
}
