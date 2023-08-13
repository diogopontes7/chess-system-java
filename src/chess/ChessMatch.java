package chess;
//Coração do projeto, onde vai ter as regras de xadrez

import BoardGame.Board;
import BoardGame.Piece;
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
    private void initialSetup() {
		placeNewPiece('c', 1, new Rook(board, Colour.WHITE));
        placeNewPiece('c', 2, new Rook(board, Colour.WHITE));
        placeNewPiece('d', 2, new Rook(board, Colour.WHITE));
        placeNewPiece('e', 2, new Rook(board, Colour.WHITE));
        placeNewPiece('e', 1, new Rook(board, Colour.WHITE));
        placeNewPiece('d', 1, new Rei(board, Colour.WHITE));

        placeNewPiece('c', 7, new Rook(board, Colour.BLACK));
        placeNewPiece('c', 8, new Rook(board, Colour.BLACK));
        placeNewPiece('d', 7, new Rook(board, Colour.BLACK));
        placeNewPiece('e', 7, new Rook(board, Colour.BLACK));
        placeNewPiece('e', 8, new Rook(board, Colour.BLACK));
        placeNewPiece('d', 8, new Rei(board, Colour.BLACK));
	}

    private void placeNewPiece (char column, int row, ChessPiece chessPiece){
        board.placePiece(chessPiece, new ChessPosition(column, row).toPosition());//Vai converter de posicao normal, para peça de xadrez
    }

    public boolean[][] possibleMoves(ChessPosition sourcePosition){
        Position position = sourcePosition.toPosition();
        validateSourcePosition(position);
        return board.pieces(position).PossibleMoves();//Vai ver a peça nessa posicao e vai indicar os seus movimentos possiveis
    }//Para imprimir as posiçoes possiveis atraves da peça escolhida

    public ChessPiece performChessMoves(ChessPosition sourcePosition, ChessPosition targetPosition){
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();//Tornar de chessPosition, para posição normal, foi por isso que fizemos estes metodos todos
        validateSourcePosition(source);
        validateTargetPosition(source,target);
        Piece pecaCapturada = makeMove(source, target);
        return (ChessPiece)pecaCapturada;
    }

    private Piece makeMove(Position source, Position target){
        Piece p = board.removePiece(source);
        Piece capturedPiece = board.removePiece(target);
        board.placePiece(p, target);
        return capturedPiece;
    }
    //Verifica se nao existe uma peça na posicao de origem
    //O if vai ser para verificar se existem movimentos possiveis para essa peça
    private void validateSourcePosition(Position position){
        if(!board.ExisteUmaPeca(position)){
            throw new ChessException("Nao existe peça nesta posicao para retirar");
        }
        if(!board.pieces(position).IsThereAPossibleMove()){//Se nao tiver nnh movim possivel, nessa peça, nao dá
            throw new ChessException("Nao existe nenhum movimento possivel para essa peça!!");
        }
    }

    private void validateTargetPosition(Position source, Position target){
       if (!board.pieces(source).MovimentoPossivel(target)) {//Se a peça nao pode ir para aquele destino, nao vai, atiramos uma exceção
        throw new ChessException("A peca escolhida nao pode se mover para a posicao de destino");
       }
    }

}
