package chess;
//Coração do projeto, onde vai ter as regras de xadrez

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import BoardGame.Board;
import BoardGame.Piece;
import BoardGame.Position;
import chess.Pecas.Bispo;
import chess.Pecas.Cavalo;
import chess.Pecas.Peao;
import chess.Pecas.Rainha;
import chess.Pecas.Rei;
import chess.Pecas.Rook;

public class ChessMatch {

    private int turn;
    private Colour currentPlayer;
    private Board board;// Um jogo de xadrez tem que ter um tabuleiro
    // Ao chamar o jogo, cria se um tabuleiro 8 por 8 e implementa se o setup
    // inicial
    private List<Piece> pecasNoTabuleiro = new ArrayList<>();
    private List<Piece> pecasCapturadas = new ArrayList<>();
    private boolean check;
    private boolean checkMate;

    public ChessMatch() {
        board = new Board(8, 8);
        turn = 1;
        currentPlayer = Colour.WHITE;// O primeiro a jogar é o branco
        initialSetup();// Instancias o setupInicial
        check = false;
    }

    public int getTurn() {
        return turn;
    }

    public Colour getColour() {
        return currentPlayer;
    }

    public boolean getCheck() {
        return check;
    }

    public boolean getCheckMate() {
        return checkMate;
    }

    // Obter todas as peças de xadrez
    public ChessPiece[][] getPieces() {
        ChessPiece[][] matriz = new ChessPiece[board.getRows()][board.getCollumns()];// Criação da matriz
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getCollumns(); j++) {
                matriz[i][j] = (ChessPiece) board.pieces(i, j);// Fazemos downcast para ser definida como uma peça de
                                                               // xadrez e nao uma peça comum
            }
        }
        return matriz;
    }

    private void initialSetup() {
        placeNewPiece('a', 1, new Rook(board, Colour.WHITE));
        placeNewPiece('b', 1, new Cavalo(board, Colour.WHITE));
        placeNewPiece('c', 1, new Bispo(board, Colour.WHITE));
        placeNewPiece('e', 1, new Rei(board, Colour.WHITE));
        placeNewPiece('f', 1, new Bispo(board, Colour.WHITE));
        placeNewPiece('g', 1, new Cavalo(board, Colour.WHITE));
        placeNewPiece('d', 1, new Rainha(board, Colour.WHITE));
        placeNewPiece('h', 1, new Rook(board, Colour.WHITE));
        placeNewPiece('a', 2, new Peao(board, Colour.WHITE));
        placeNewPiece('b', 2, new Peao(board, Colour.WHITE));
        placeNewPiece('h', 2, new Peao(board, Colour.WHITE));

        placeNewPiece('a', 8, new Rook(board, Colour.BLACK));
        placeNewPiece('b', 8, new Cavalo(board, Colour.BLACK));
        placeNewPiece('c', 8, new Bispo(board, Colour.BLACK));
        placeNewPiece('e', 8, new Rei(board, Colour.BLACK));
        placeNewPiece('f', 8, new Bispo(board, Colour.BLACK));
        placeNewPiece('g', 8, new Cavalo(board, Colour.BLACK));
        placeNewPiece('d', 8, new Rainha(board, Colour.BLACK));
        placeNewPiece('h', 8, new Rook(board, Colour.BLACK));
        placeNewPiece('a', 7, new Peao(board, Colour.BLACK));
        placeNewPiece('b', 7, new Peao(board, Colour.BLACK));
    }

    private void nextTurn() {
        turn++;
        currentPlayer = (currentPlayer == Colour.WHITE) ? Colour.BLACK : Colour.WHITE;// Expressão condicional
        // Se o jogador atual for branco, é a vez do preto , caso contarrio vai ser o
        // branco,
    }

    private Colour opponent(Colour colour) {
        return (colour == Colour.WHITE) ? Colour.BLACK : Colour.WHITE;
    }
    // Se a minha cor for branca, retorna preto neste metodos como a cor do metodo

    private ChessPiece king(Colour colour) {
        List<Piece> list = pecasNoTabuleiro.stream().filter(x -> ((ChessPiece) x).getColour() == colour)
                .collect((Collectors.toList()));

        for (Piece p : list) {
            if (p instanceof Rei) {// Se a peca for uma instancia de um rei, retorna o rei com a cor escolhida pelo
                                   // o usuario
                return (ChessPiece) p;
            }
        }
        throw new IllegalStateException("Nao existe o rei da cor " + colour);
    }

    private void placeNewPiece(char column, int row, ChessPiece chessPiece) {
        board.placePiece(chessPiece, new ChessPosition(column, row).toPosition());// Vai converter de posicao normal,
                                                                                  // para peça de xadrez
        pecasNoTabuleiro.add(chessPiece);
    }

    public boolean[][] possibleMoves(ChessPosition sourcePosition) {
        Position position = sourcePosition.toPosition();
        validateSourcePosition(position);
        return board.pieces(position).PossibleMoves();// Vai ver a peça nessa posicao e vai indicar os seus movimentos
                                                      // possiveis
    }// Para imprimir as posiçoes possiveis atraves da peça escolhida

    public ChessPiece performChessMoves(ChessPosition sourcePosition, ChessPosition targetPosition) {
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();// Tornar de chessPosition, para posição normal, foi por isso que
                                                      // fizemos estes metodos todos
        validateSourcePosition(source);
        validateTargetPosition(source, target);
        Piece pecaCapturada = makeMove(source, target);

        if (testCheck(currentPlayer)) {// Se ele meter se em check
            undoMove(source, target, pecaCapturada);// Remove mos o movimento e fazemos um throw
            throw new ChessException("VOCE NAO PODE SE COLOCAR EM CHECK!!!");
        }

        check = (testCheck(opponent(currentPlayer))) ? true : false;// Se for verdade o oponenete esta em check se
                                                                    // nao,// nao

        if (testCheckMate(opponent(currentPlayer))) {// Se a jogada que eu fiz deixou o oponente em checkmate, a partida
                                                     // acaba
            checkMate = true;//
        } else {
            nextTurn();// Dps de fazer uma jogada, fazemos a proxima jogada, troca o turno
        }
        return (ChessPiece) pecaCapturada;
    }

    private Piece makeMove(Position source, Position target) {
        ChessPiece p = (ChessPiece)board.removePiece(source);
        p.increaseMoveCount();//Ao fazer o movimento, aumento o numero de jogadas
        Piece capturedPiece = board.removePiece(target);
        board.placePiece(p, target);// Tira na origem e mete no destino

        if (capturedPiece != null) {// Significa que foi capturada
            pecasNoTabuleiro.remove(capturedPiece);// JA nao esta no tabuleiro
            pecasCapturadas.add(capturedPiece);// Está agora capturada
        }

        return capturedPiece;
    }

    // Verifica se nao existe uma peça na posicao de origem
    // O if vai ser para verificar se existem movimentos possiveis para essa peça
    private void validateSourcePosition(Position position) {
        if (!board.ExisteUmaPeca(position)) {
            throw new ChessException("Nao existe peça nesta posicao para retirar");
        }
        if (!board.pieces(position).IsThereAPossibleMove()) {// Se nao tiver nnh movim possivel, nessa peça, nao dá
            throw new ChessException("Nao existe nenhum movimento possivel para essa peça!!");
        }
        // Se a cor do jogador atual for diferente da peça que queremos mexer, lançamos
        // uma exceção
        if (currentPlayer != ((ChessPiece) board.pieces(position)).getColour()) {
            throw new ChessException("Nao e possivel mover uma peca do adversario!");
        }
    }

    private void validateTargetPosition(Position source, Position target) {
        if (!board.pieces(source).MovimentoPossivel(target)) {// Se a peça nao pode ir para aquele destino, nao vai,
                                                              // atiramos uma exceção
            throw new ChessException("A peca escolhida nao pode se mover para a posicao de destino");
        }
    }

    // Se a pessoa se tentar se mover e por em check, temos que desfazer o movimento
    private void undoMove(Position source, Position target, Piece capturedPiece) {
        ChessPiece p = (ChessPiece)board.removePiece(target);// Tira a peça de destino
        p.decreaseMoveCount();//Ao remover o move, decrementamos o numero de movimentos
        board.placePiece(p, source);

        if (capturedPiece != null) {
            board.placePiece(capturedPiece, target);
            pecasCapturadas.remove(capturedPiece);// A logica é inversa do MakeMove
            pecasNoTabuleiro.add(capturedPiece);
        }
    }

    // temos de percorrer todas as pecas adversarias, temos que testar se um
    // movimento de uma peça está em xeque com o nosso rei
    private boolean testCheck(Colour colour) {
        Position kingPosition = king(colour).getChessPosition().toPosition();// Posicao do rei de uma cor pedida
        List<Piece> opponentPieces = pecasNoTabuleiro.stream()
                .filter(x -> ((ChessPiece) x).getColour() == opponent(colour)).collect((Collectors.toList()));//A lista dos oponentes
        for (Piece piece : opponentPieces) {
            boolean[][] mat = piece.PossibleMoves();// Matriz de movimentos possiveis dessa matriz
            if (mat[kingPosition.getRow()][kingPosition.getColumn()]) {// Se uma das peças tem alcance verdadeiro ate ao
                                                                       // rei, significa que esta em check
                return true;// O rei está em check
            }//se nesta matriz for true que alguma peça tem alcance sobre o rei, dá true que o rei esta em check
        }
        return false;
    }

    private boolean testCheckMate(Colour colour) {
        if (!testCheck(colour)) {
            return false;
        }
        // Todas as peças da cor definida no metodo
        List<Piece> list = pecasNoTabuleiro.stream().filter(x -> ((ChessPiece) x).getColour() == colour)
                .collect(Collectors.toList());
        for (Piece p : list) {
            boolean[][] mat = p.PossibleMoves();// MAtriz dos movimentos possiveis, atencao so pode mexer se as posiçoes
                                                // sao verdadeiras
            for (int i = 0; i < board.getRows(); i++) {
                for (int j = 0; j < board.getCollumns(); j++) {
                    if (mat[i][j]) {// Se um destes movimentos for possivel
                        // Vai pegar na peça p, mover para a posicao em cima e verificar se esta em
                        // check, se nao estiver, significa que saiu do check
                        Position source = ((ChessPiece) p).getChessPosition().toPosition();// Posicao inicial da peça
                        Position target = new Position(i, j);// Posicao final, ja que é possivel ir pra ai
                        Piece pecaCapturada = makeMove(source, target);// Fizemos o movimento
                        boolean testCheck = testCheck(colour);// Vai testar se o rei da nossa cor esta em check
                        undoMove(source, target, pecaCapturada);// Fazer undo que é necessário
                        if (!testCheck) { // Se nao esta em check
                            return false;// Retorna falso
                        }
                    }
                }
            }
        }
        return true;
    }
}
