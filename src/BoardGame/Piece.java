package BoardGame;

public abstract class Piece {
    protected Position position;
    private Board board;//A cada peça tem um tabuleiro associado

    public Piece(Board board) {
        this.board = board;
        //Posicao inicial é nula
    }

    public Board getBoard() {
        return board;
    }
    
    public abstract boolean[][] PossibleMoves();

    public boolean MovimentoPossivel(Position position){
        return PossibleMoves()[position.getRow()][position.getColumn()];//Hook methods, ver isto que não percebi
    }
    
    public boolean IsThereAPossibleMove(){
         boolean[][] mat = PossibleMoves();//Uma matriz criada com os movimentos possiveis para verificar se é possivel mover
         for(int i=0; i<mat.length; i++){
            for(int j=0; j<mat.length; j++){
                if(mat[i][j]){//Se for verdade, se existir movimento possivel
                    return true;//Vai para esse sitio
                }
            }
         }
         return false;//Nao vai
    }
}
