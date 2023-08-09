package BoardGame;

public class Board {
    private int rows;//Um tabuleiro tem linhas
    private int collumns;//Um tabuleiro tem colunas
    protected Piece[][] pieces;//Matriz de peças

    public Board(int rows, int collumns) {
        if(rows <1 || collumns <1){
            throw new BoardException("Nao pode ser menor que 1");
        }
        this.rows = rows;//Numero de linhas no tabuleiro
        this.collumns = collumns;//Numero de colunas
        pieces = new Piece[rows][collumns];//Definicao dos movimentos das peças
    }

    public int getRows() {
        return rows;
    }

    public int getCollumns() {
        return collumns;
    }

    public Piece pieces(int row, int column){
        if(!positionExists(row, column)){
            throw new BoardException("Erro: Posicao nao existente");
        }
        return pieces[row][column];
    }

    public Piece pieces(Position position){
        if(!positionExists(position)){
            throw new BoardException("Erro: Posicao nao existe");
        }
        return pieces[position.getRow()][position.getColumn()];
    }

    //Indicação onde por a peça e qual peça
    public void placePiece(Piece piece, Position position){
        if(ExisteUmaPeca(position)){
            throw new BoardException("Já existe uma peça nesta posição : " +position);
        }
        pieces[position.getRow()][position.getColumn()] = piece;//Vai igualar á peça
        piece.position=position;//A posicao ja nao vai ser nula
    }

    //O numero de linhas e colunas nao pode ser menor que zero e maior que o tabuleiro
    private boolean positionExists(int row, int column){
       return row >=0 && row < rows && column >=0 && column < collumns;
    }
    
    public boolean positionExists(Position position){
        return positionExists(position.getRow(), position.getColumn());
    }

    public boolean ExisteUmaPeca(Position position){
         if(!positionExists(position)){
            throw new BoardException("Erro: Posicao nao existe");
        }//Se a posicao nao existe, nao é possivel haver la uma peça
       return pieces(position) !=null;//Do metodo anterior que vai ver se existe uma peça na matriz naquela posição
       //Se for diferente de nula significa que existe uma peça nessa posiçao e vai retornar la
    }

}
