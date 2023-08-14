package Aplicacao;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

public class Programa {
    public static void main(String[] args) {


        // Position p = new Position(2, 4);
        // System.out.println(p);
        //Lingua definida para portugues
        Locale.setDefault(new Locale("pt", "PT"));
        Scanner sc = new Scanner(System.in);
        ChessMatch chessMatch = new ChessMatch();
        List<ChessPiece> captured = new ArrayList<>();

        //Enquanto nao houver checkmate, a partida continua
        while (!chessMatch.getCheckMate()) {
            try {
                System.out.println("Amarelos sao os pretos");
                System.out.println("Os brancos sao os brancos ne ;)");
                UI.clearScreen();
                UI.printMatch(chessMatch, captured);// Classe interface que vai imprimir a tela com as peças
                System.out.println();
                System.out.println("Source: ");
                ChessPosition source = UI.readChessPosition(sc);

                boolean[][] possibleMoves = chessMatch.possibleMoves(source);
                UI.clearScreen();
                UI.printBoard(chessMatch.getPieces(), possibleMoves);
                System.out.println();
                System.out.println("Target: ");
                ChessPosition target = UI.readChessPosition(sc);

                ChessPiece capturedPiece = chessMatch.performChessMoves(source, target);

                if(capturedPiece != null){//Se foi capturada
                    captured.add(capturedPiece);
                }

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
        UI.clearScreen();
        UI.printMatch(chessMatch, captured);//Mostra a partida finalizada
    }
}
