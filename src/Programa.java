import Aplicacao.UI;
import BoardGame.Board;
import BoardGame.Position;
import chess.ChessMatch;

public class Programa {
    public static void main(String[] args) {

        //Position p = new Position(2, 4);
        //System.out.println(p);

        ChessMatch chessMatch = new ChessMatch();

        UI.printBoard(chessMatch.getPieces());//Classe interface que vai imprimir a tela com as peças
    
    }
}
