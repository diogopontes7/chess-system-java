package Aplicacao;

import java.util.InputMismatchException;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Programa {
    public static void main(String[] args) {

        // Position p = new Position(2, 4);
        // System.out.println(p);
        Scanner sc = new Scanner(System.in);
        ChessMatch chessMatch = new ChessMatch();
        while (true) {
            try {
                UI.clearScreen();
                UI.printBoard(chessMatch.getPieces());// Classe interface que vai imprimir a tela com as peças
                System.out.println();
                System.out.println("Source: ");
                ChessPosition source = UI.readChessPosition(sc);

                System.out.println();
                System.out.println("Target: ");
                ChessPosition target = UI.readChessPosition(sc);

                ChessPiece capturedPiece = chessMatch.performChessMoves(source, target);

                System.out.println("Peca capturada : " + capturedPiece);
                System.out.println();
            } catch (ChessException e) {
               System.out.println(e.getMessage());
               sc.nextLine();//Para o programa aguardar ate entrar enter
            }
            catch (InputMismatchException e){
                System.out.println(e.getMessage());
                sc.nextLine();
            }

        }

    }
}
