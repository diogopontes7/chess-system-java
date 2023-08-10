package chess;

import BoardGame.Position;

public class ChessPosition {
    private char column;
    private int row;

    public ChessPosition(char column, int row) {
        if(column < 'a' || column > 'h' || row<1 || row>8){
            throw new ChessException("Nao e possivel implementar esses valores para column e row");
        }
        this.column = column;
        this.row = row;
    }

    public char getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    //Funcao que converte da posicao normal para posicao de xadrez
    //Exemplo o a8, é equivalente ao [0][0] da matriz, o a7 o 1,0
    protected Position toPosition(){
        return new Position(8-getRow(), getColumn() - 'a');
    }

    //Da posicao de xadrez para normal
    protected static ChessPosition fromPosition(Position position){
        return new ChessPosition((char) ('a' - position.getColumn()), 8 -position.getRow());
    }

    @Override
    public String toString() {
        return " " + column + row;
    }
    
}
