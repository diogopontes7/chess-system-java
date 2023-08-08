package BoardGame;

public class Board {
    private int rows;//Um tabuleiro tem linhas
    private int collumns;//Um tabuleiro tem colunas
    protected Piece[][] pieces;//Matriz de peças

    public Board(int rows, int collumns) {
        this.rows = rows;
        this.collumns = collumns;
        pieces = new Piece[rows][collumns];//Definicao dos movimentos das peças
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCollumns() {
        return collumns;
    }

    public void setCollumns(int collumns) {
        this.collumns = collumns;
    }
 
    public Piece pieces(int row, int column){
        return pieces[row][column];
    }

    public Piece pieces(Position position){
        return pieces[position.getRow()][position.getColumn()];
    }

    //Indicação onde por a peça e qual peça
    public void placePiece(Piece piece, Position position){
        pieces[position.getRow()][position.getColumn()] = piece;//Vai igualar á peça
        //piece.position=position;//A posicao ja nao vai ser nula
    }
}
