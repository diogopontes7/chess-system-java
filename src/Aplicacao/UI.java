package Aplicacao;

import chess.ChessPiece;

public class UI {
    
    public static void printPiece(ChessPiece piece){
        if(piece == null){
            System.out.print("-");
        }
        else{
            System.out.print(piece);
        }
        System.out.print(" ");
    }

    //Imprime uma matriz de peças de um tabuleiro, logo mtemos uma matriz de peças de xadrez
    public static void printBoard(ChessPiece[][] pieces){
        for(int i = 0; i<pieces.length; i++){
            System.out.print((8 - i) + " ");
            for(int j =0; j <pieces.length; j++){//è possivel fazer o lenght, já que é quadratica
                printPiece(pieces[i][j]);
            }
            System.out.println();//Quebra de linha
        }
        System.out.print("  a b c d e f g h");
    }
}
