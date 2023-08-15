package chess.Pecas;



import BoardGame.Board;
import BoardGame.Position;
import chess.ChessPiece;
import chess.Colour;

public class Peao extends ChessPiece{

    public Peao(Board board, Colour colour) {
        super(board, colour);
    }

    @Override
    public boolean[][] PossibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getCollumns()];

        Position p = new Position(0, 0);
        //O peao, no seu primeiro movimento, pode andar duas casas para frente
        //Se for branca, temos que ter a lógica de andar para cima, ou seja, linhas a menos
        if(getColour() == Colour.WHITE){
            p.setValues(position.getRow()-1, position.getColumn());
            if(getBoard().positionExists(p) && !getBoard().ExisteUmaPeca(p)){
                mat[p.getRow()][p.getColumn()] = true;
            }
            p.setValues(position.getRow()-2 , position.getColumn());
            Position p2 = new Position(position.getRow()-1, position.getColumn());//Criamos esta posicao, caso tmb acha a possibilidade de haver uma peça á frente do peao
            if(getBoard().positionExists(p) && !getBoard().ExisteUmaPeca(p) && getBoard().positionExists(p2) && !getBoard().ExisteUmaPeca(p2) && getMoveCount() == 0){//Se isso for verdade, pode mover ate la
                mat[p.getRow()][p.getColumn()] = true;
            }
            //diagonais, so vai pra diagonal se tiver peça adversario
             p.setValues(position.getRow()-1, position.getColumn()-1);
            if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
                mat[p.getRow()][p.getColumn()] = true;
            }
            p.setValues(position.getRow()-1, position.getColumn() + 1);
            if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
                mat[p.getRow()][p.getColumn()] = true;
            }
        }
        else{//Para o peao preto, ele vai pra baixo, por isso vai mais um
            p.setValues(position.getRow()+1, position.getColumn());
            if(getBoard().positionExists(p) && !getBoard().ExisteUmaPeca(p)){
                mat[p.getRow()][p.getColumn()] = true;
            }
            p.setValues(position.getRow()+2 , position.getColumn());
            Position p2 = new Position(position.getRow()+1, position.getColumn());//Criamos esta posicao, caso tmb acha a possibilidade de haver uma peça á frente do peao
            if(getBoard().positionExists(p) && !getBoard().ExisteUmaPeca(p) && getBoard().positionExists(p2) && !getBoard().ExisteUmaPeca(p2) && getMoveCount() == 0){//Se isso for verdade, pode mover ate la
                mat[p.getRow()][p.getColumn()] = true;
            }
            //diagonais, so vai pra diagonal se tiver peça adversario
             p.setValues(position.getRow()+1, position.getColumn()-1);
            if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
                mat[p.getRow()][p.getColumn()] = true;
            }
            p.setValues(position.getRow()+1, position.getColumn() + 1);
            if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
                mat[p.getRow()][p.getColumn()] = true;
            }
        }
        return mat;
    }
    @Override
    public String toString(){
        return "P";
    }
}
