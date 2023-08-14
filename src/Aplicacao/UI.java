package Aplicacao;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.Colour;

public class UI {

	// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
	// Cores do texto
	public static final String ANSI_RESET = "\u001B[0m";// Reseta A cor
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	// Cores de fundo
	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

	public static void clearScreen(){
		System.out.print("\033[H\033[2J");
		System.out.flush();
	}


	public static ChessPosition readChessPosition(Scanner sc) {
		try {
			String s = sc.nextLine();
			char column = s.charAt(0);// Posicao do xadrez
			int row = Integer.parseInt(s.substring(1));// Divide a string pelo o indicador indicador
			return new ChessPosition(column, row);
		} catch (RuntimeException e) {
			throw new InputMismatchException("Error reading ChessPosition: Os valores validos sao de a1 a h8");
		}
	}

	public static void printBoard(ChessPiece[][] pieces) {
		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j], false);//Nenhuma peça vai ter colorido
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}

	private static void printPiece(ChessPiece piece, boolean background) {
		if(background){//Se o background for true
			System.out.print(ANSI_GREEN_BACKGROUND);
		}
		if (piece == null) {
			System.out.print("-" +ANSI_RESET);
		} else {
			if (piece.getColour() == Colour.WHITE) {// Se for branco, mete a peça em branco
				System.out.print(ANSI_WHITE + piece + ANSI_RESET);
			} else {// Se for preto mete em amarelo
				System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
			}
		}
		System.out.print(" ");
	}

	//Vai imprimir o tabuleiro e o turno e vez de cada um jogar, trocar no programa
	public static void printMatch(ChessMatch chessMatch, List<ChessPiece> capturada){
		printBoard(chessMatch.getPieces());
		System.out.println();
		printCapturedPieces(capturada);
		System.out.println("Turn: " +chessMatch.getTurn());
		if(!chessMatch.getCheckMate()){
			System.out.println("A esperar pelo o jogador jogar: " +chessMatch.getColour());
		if(chessMatch.getCheck()){
			System.out.println("CHECK!!");
		}
	}
	else{
		System.out.println("CHECKMATE!!!");
		System.out.println("Vencedor: " +chessMatch.getColour());
	}
	}

	//Este vai receber uma matriz de movimentos possiveis
	public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
		for (int i = 0; i < pieces.length; i++) {
			System.out.print((8 - i) + " ");
			for (int j = 0; j < pieces.length; j++) {
				printPiece(pieces[i][j], possibleMoves[i][j]);//VAi pintar o fundo colorido dependendo dessa variavel, if true
			}
			System.out.println();
		}
		System.out.println("  a b c d e f g h");
	}

	//Lista das peças capturadas pretas e brancas
	//Filtramos com uma expressao lambda
	private static void printCapturedPieces(List<ChessPiece> capturada){
		List<ChessPiece> white = capturada.stream().filter(x -> x.getColour() == Colour.WHITE).collect(Collectors.toList());
		List<ChessPiece> black = capturada.stream().filter(x -> x.getColour() == Colour.BLACK).collect(Collectors.toList());
		System.out.println("Pecas Capturadas ");
		System.out.print("Brancas ");
		System.out.print(ANSI_WHITE);
		System.out.println(Arrays.toString(white.toArray()));//Imprimir um array em java
		System.out.print(ANSI_RESET);
		System.out.print("Pretas ");
		System.out.println(Arrays.toString(black.toArray()));
		System.out.print(ANSI_RESET);
	}
}
