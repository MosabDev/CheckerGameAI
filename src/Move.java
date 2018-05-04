public class Move {
    
    public int moveIndexRow;
    public int moveIndexCol;
    public int pieceIndexRow;
    public int pieceIndexCol;
    
    
    final static public int BLANK = 0;
    final static public int HUMAN = 1;
    final static public int PROGRAM = -1;
    final static public int DAME_PROGRAM = -10;
    final static public int DAME_HUMAN = 10;
    final static public int EMPTY = -2;
    final static public int CENTER = 5;
    
    int[][] board=new int[8][8];
    
       public int[][] getBoard(){
       return this.board;
   }
       
        public Move(int[][] board) {
		this.board=board;
	}
        
        public Move(){
            
        }
        

        
            public void init(){
       
        int[][] matrice=this.board;
        for (int row = 0; row < 8; row++){
         for (int col = 0; col < 8; col++){
            if ( row % 2 == col % 2) {
               if (row < 3)
                  matrice[row][col] = this.HUMAN;
               else if (row > 4)
                   matrice[row][col] = this.PROGRAM;
               else
                   matrice[row][col] = this.BLANK;
            }
            else {
                matrice[row][col] = this.EMPTY;
            }
         }
      }
      this.board=matrice;
    }

    public void init2(){
        
    	int[][] matrice=this.board;
        for (int row = 0; row < 8; row++){
         for (int col = 0; col < 8; col++){
            if ( row % 2 == col % 2) {
               if (row < 3)
                  matrice[row][col] = this.BLANK;
               else if (row > 4)
                   matrice[row][col] = this.BLANK;
               else
                   matrice[row][col] = this.BLANK;
            }
            else {
                matrice[row][col] = this.EMPTY;
            }
            matrice[0][2]=10;
            matrice[3][3]=-1;
            matrice[3][1]=-1;
            matrice[1][3]=-1;
            matrice[5][3]=-1;
         }
      }
      this.board=matrice;
    }
}
