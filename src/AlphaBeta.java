
import java.net.URL;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author FN
 */
public class AlphaBeta {
     public static final boolean DEBUG = false;

    public  int[] tab={-3,-3,-3,-3,-3,-3,-3,-3,-3,-3,-3,-3,-3,-3,-3,-3};

    public int[] getTab() {
        return tab;
    }

    public static boolean PROGRAM = false;
    public static boolean HUMAN = true;



        public boolean drawnPosition(Move p) {

        boolean ret = false;
        Move pos = p;
        int blank=0,dp=0,dh=0;
        for (int i=0; i<8; i++) {
        	for (int j=0; j<8; j++) {   		
	            if (pos.board[i][j] == pos.DAME_HUMAN) 
                        dh++;
	            if (pos.board[i][j] == pos.DAME_PROGRAM) 
                        dp++;
	            if (pos.board[i][j] == pos.BLANK) 
                        blank++;
        	}
        }
        if(dp==1 && dh==1 && blank==30){
            ret=true;
        }
    
        return ret;
    }

        public boolean wonPosition(Move p, boolean player) {
       int b = 0;
       int program=0;
       int human=0;
        Move pos=p;
        if (player)
            b = pos.HUMAN;
        else        
            b = pos.PROGRAM;
        
        if(b==1){
            for(int i=0;i<8;i++){
            	for (int j=0; j<8; j++) {  
            		if(pos.board[i][j] ==-1 || pos.board[i][j] ==-10){
                        program++;
                    }
            	}
                
            }
            if(program==0) 
                return true;
        }
        if(b==-1){
            for(int i=0;i<8;i++){
            	for (int j=0; j<8; j++) {  
	                if(pos.board[i][j] ==1 || pos.board[i][j] ==10){
	                    human++;
	                }
            	}
            }
            if(human==0) 
                return true;
        }
        return false;
    }

    
        public float positionEvaluation(Move p, boolean player) {
    	Move pos = p;
    	float value =bordEval(pos);
    	
    	if(player)
        return -value;
    	else return value;
        
    }
       public float positionEvaluation2(Move p, boolean player) {
    	Move pos = p;
    	float value =bordEval2(pos);
    	
    	if(player)
        return -value;
    	else return value;
        
    }
       
            public void printPosition(Move p) {
         System.out.println("Checkers Position:");
        Move pos = p;
                
        for (int row=7; row>=0; row--){
           
            System.out.println();
            for (int col=0; col<8; col++) {
                
                if (pos.board[row][col] == Move.HUMAN){
                    System.out.print("B"+row+col);
                } else if (pos.board[row][col] == Move.PROGRAM) {
                    System.out.print("W"+row+col);
                } else if (pos.board[row][col] == Move.DAME_HUMAN) {
                    System.out.print("BK"+row+col);
                } else if (pos.board[row][col] == Move.DAME_PROGRAM) {
                    System.out.print("WK"+row+col);
                } else if (pos.board[row][col] == Move.EMPTY) {
                    System.out.print(" ");
                }else{
                    System.out.print("-");
                }
            }
        }
        System.out.println();
    }
    
            public Move[] possibleMoves(Move p,boolean player) {
    
        Move pos=p;
        int[][] board=pos.board;
      

      int playerKing;  
      if (player)
         playerKing = pos.DAME_HUMAN;
      else
         playerKing = pos.DAME_PROGRAM;

      Vector moves = new Vector();  
      for (int row = 0; row < 8; row++) {
         for (int col = 0; col < 8; col++) {
            if (board[row][col] == pos.HUMAN || board[row][col] == playerKing) {
               if (canJump(player,p, row, col, row+1, col+1, row+2, col+2)){
                   int[][] mat=pos.board;
                   mat=CheckerChangeJump(player,mat,row, col, row+1, col+1, row+2, col+2);
                   if(canJump(player,p, row, col,row+3, col+3, row+4, col+4)){
                       mat=CheckerChangeJump(player,mat,row+2, col+2,row+3, col+3, row+4, col+4);
                       if(canJump(player,p,row,col,row+5, col+5, row+6, col+6)){
                           mat=CheckerChangeJump(player,mat,row+4,col+4,row+5, col+5, row+6, col+6);
                       }
                       if(canJump(player,p, row, col,row+5, col+3, row+6, col+2)){
                           mat=CheckerChangeJump(player,mat,row+4, col+4,row+5, col+3, row+6, col+2);
                       }
                   }
                   if(canJump(player,p, row, col,row+3, col+1, row+4, col)){
                       mat=CheckerChangeJump(player,mat,row+2, col+2,row+3, col+1, row+4, col);
                       if(canJump(player,p,row,col,row+5, col+1, row+6, col+2)){
                           mat=CheckerChangeJump(player,mat,row+4,col,row+5, col+1, row+6, col+2);
                       }
                       if(canJump(player,p, row, col,row+5, col-1, row+6, col-2)){
                           mat=CheckerChangeJump(player,mat,row+4, col,row+5, col-1, row+6, col-2);
                       }
                   }
                   moves.addElement(new Move(mat));
               }
               /*********************************************************************/
               if (canJump(player,p, row, col, row+1, col-1, row+2, col-2)){
            	   int[][] mat=pos.board;
                   mat=CheckerChangeJump(player,mat,row, col, row+1, col-1, row+2, col-2);
                   if(canJump(player,p, row, col,row+3, col-1, row+4, col)){
                       mat=CheckerChangeJump(player,mat,row+2, col-2,row+3, col-1, row+4, col);
                       if(canJump(player,p,row,col,row+5, col+1, row+6, col+2)){
                           mat=CheckerChangeJump(player,mat,row+4,col,row+5, col+1, row+6, col+2);
                       }
                       if(canJump(player,p, row, col,row+5, col-1, row+6, col-2)){
                           mat=CheckerChangeJump(player,mat,row+4, col,row+5, col-1, row+6, col-2);
                       }
                   }
                   if(canJump(player,p, row, col,row+3, col-3, row+4, col-4)){
                       mat=CheckerChangeJump(player,mat,row+2, col-2,row+3, col-3, row+4, col-4);
                       if(canJump(player,p,row,col,row+5, col-3, row+6, col-2)){
                           mat=CheckerChangeJump(player,mat,row+4,col-4,row+5, col-3, row+6, col-2);
                       }
                       if(canJump(player,p, row, col,row+5, col-5, row+6, col-6)){
                           mat=CheckerChangeJump(player,mat,row+4, col-4,row+5, col-5, row+6, col-6);
                       }
                   }
                   moves.addElement(new Move(mat));
               }
            }
            /*********************************************************************************************************/
            if (board[row][col] == pos.PROGRAM || board[row][col] == playerKing) {
                if (canJump(player,p, row, col, row-1, col+1, row-2, col+2)){
                	int[][] mat=pos.board;
                    mat=CheckerChangeJump(player,mat, row, col, row-1, col+1, row-2, col+2);
                    if (canJump(player,p, row, col, row-3, col+3, row-4, col+4)){
                        mat=CheckerChangeJump(player, mat,row-2, col+2, row-3, col+3, row-4, col+4);
                        if (canJump(player,p, row, col, row-5, col+5, row-6, col+6)){
                            mat=CheckerChangeJump(player,mat, row-4, col+4, row-5, col+5, row-6, col+6);
                        }
                        if (canJump(player,p, row, col, row-5, col+3, row-6, col+2)){
                            mat=CheckerChangeJump(player,mat, row-4, col+4, row-5, col+3, row-6, col+2);
                        }
                    }
                    if (canJump(player,p, row, col, row-3, col+1, row-4, col)){
                        mat=CheckerChangeJump(player,mat,row-2, col+2, row-3, col+1, row-4, col);
                        if (canJump(player,p, row, col, row-5, col+1, row-6, col+2)){
                            mat=CheckerChangeJump(player,mat,row-4, col, row-5, col+1, row-6, col+2);
                        }
                        if (canJump(player,p, row, col, row-5, col-1, row-6, col-2)){
                            mat=CheckerChangeJump(player,mat,row-4, col, row-5, col-1, row-6, col-2);
                        }
                    }
                    moves.addElement(new Move(mat));
                }
                if (canJump(player,p, row, col, row-1, col-1, row-2, col-2)){
                	int[][] mat=pos.board;
                    mat=CheckerChangeJump(player,mat,row, col, row-1, col-1, row-2, col-2);
                    if (canJump(player,p, row, col, row-3, col-1, row-4, col)){
                        mat=CheckerChangeJump(player,mat, row-2, col-2, row-3, col-1, row-4, col);
                        if (canJump(player,p, row, col, row-5, col+1, row-6, col+2)){
                            mat=CheckerChangeJump(player,mat, row-4, col, row-5, col+1, row-6, col+2);
                        }
                        if (canJump(player,p, row, col, row-5, col-1, row-6, col-2)){
                            mat=CheckerChangeJump(player,mat, row-4, col, row-5, col-1, row-6, col-2);
                        }
                    }
                    if (canJump(player,p, row, col, row-3, col-3, row-4, col-4)){
                        mat=CheckerChangeJump(player,mat, row-2, col-2, row-3, col-3, row-4, col-4);
                        if (canJump(player,p, row, col, row-5, col-3, row-6, col-2)){
                            mat=CheckerChangeJump(player,mat,row-4, col-4, row-5, col-3, row-6, col-2);
                        }
                        if (canJump(player,p, row, col, row-5, col-5, row-6, col-6)){
                            mat=CheckerChangeJump(player,mat, row-4, col-4, row-5, col-5, row-6, col-6);
                        }
                    }
                    moves.addElement(new Move(mat));
                }
            }
         }
      }
      
    
      
      if (moves.size() == 0) {
         for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (board[row][col] == pos.HUMAN || board[row][col] == playerKing) {
                	 
                      if (canMove(player,p,row,col,row+1,col+1))
                         moves.addElement(new Move(CheckerChangeMove(player,pos.board,row, col, row+1, col+1)));                      
                      if (canMove(player,p,row,col,row+1,col-1))
                         moves.addElement(new Move(CheckerChangeMove(player,pos.board,row, col, row+1, col-1)));                      
                 }
                if(board[row][col] == pos.PROGRAM || board[row][col] == playerKing){
                	 
                    if (canMove(player,p,row,col,row-1,col+1))
                         moves.addElement(new Move(CheckerChangeMove(player,pos.board,row, col, row-1, col+1)));
                    if (canMove(player,p,row,col,row-1,col-1))
                         moves.addElement(new Move(CheckerChangeMove(player,pos.board,row, col, row-1, col-1)));
                }
            }
         }
      }
      
    
      
      if (moves.size() == 0)
         return null;
      else {
         Move[] moveArray = new Move[moves.size()];
         for (int i = 0; i < moves.size(); i++)
            moveArray[i] = (Move)moves.elementAt(i);
         return moveArray;
      }

   }  
            
        public static int[][] returnMatrice(int [] board){
        int[][] matrice=new int[8][8];
         for (int i=0; i<8; i++){
            for (int j=0; j<8; j++) {
                matrice[i][j]=board[i*8+j];
            }
        }
         return matrice;
    }

      public static int[] returnTaleau(int [][] matrice){
        int[] tableau=new int[64];
         for (int i=0; i<8; i++){
            for (int j=0; j<8; j++) {
                tableau[i*8+j]=matrice[i][j];
            }
        }
         return tableau;
    }
      
      public int[][] CheckerChangeJump(boolean player,int[][] m,int row,int col,int row1,int col1,int row2,int col2){
    	  int[][] matrice=new int[8][8];
    	  matrice=remplirPar(m);
     	 if(player){
     		 if(matrice[row][col]==1 && row2==7) 
                     matrice[row2][col2]=10;
     	     else matrice[row2][col2]=1;
     	     if(matrice[row][col]==10) 
                 matrice[row2][col2]=10;
     	 }else{
     		 if(matrice[row][col]==-1 && row2==0) 
                     matrice[row2][col2]=-10;
     	     else 
                     matrice[row2][col2]=-1;
     	     if(matrice[row][col]==-10) 
                 matrice[row2][col2]=-10;
     	 }
 
         matrice[row][col]=0;
         matrice[row1][col1]=0;  
         return matrice;
         
     }
     
     
         
     public int[][] CheckerChangeMove(boolean player,int[][] m,int row,int col,int row1,int col1){
         int [][] matrice=new int[8][8];
         matrice=remplirPar(m);
         if(player){
         	if(matrice[row][col]==1 && row1==7) matrice[row1][col1]=10;
             else matrice[row1][col1]=1;
             if(matrice[row][col]==10) matrice[row1][col1]=10;
         }else{
         	if(matrice[row][col]==-1 && row1==0) matrice[row1][col1]=-10;
             else  matrice[row1][col1]=-1;
             if(matrice[row][col]==-10) matrice[row1][col1]=-10;
         }
         matrice[row][col]=0;
         return matrice;
     }
     
    public boolean canJump(boolean player,Move p,int r1, int c1, int r2, int c2, int r3, int c3) {
    
         Move pos =p;
        int[][] board=pos.board;

        if (r3 < 0 || r3 >= 8 || c3 < 0 || c3 >= 8)
           return false;  
        if (r2 < 0 || r2 >= 8 || c2 < 0 || c2 >= 8)
           return false;  
        
        if (board[r3][c3] != pos.BLANK)
           return false; 
       
        if (player) {
        	if (board[r1][c1] != pos.HUMAN && board[r1][c1] != pos.DAME_HUMAN)
                return false;  
           if (board[r1][c1] == pos.HUMAN && r3 < r1)
              return false;  
           if (board[r2][c2] != pos.PROGRAM && board[r2][c2] != pos.DAME_PROGRAM)
              return false; 
           return true; 
        }
        else {
        	if (board[r1][c1] != pos.PROGRAM && board[r1][c1] != pos.DAME_PROGRAM)
                return false;  
           if (board[r1][c1] == pos.PROGRAM && r3 > r1)
              return false;  
           if (board[r2][c2] != pos.HUMAN && board[r2][c2] != pos.DAME_HUMAN)
              return false;  
           return true;  
        }

     }  
    private boolean canMove(boolean player,Move p,int r1, int c1, int r2, int c2) {
          
        Move pos = p;
        int[][] board=pos.board;
        
        if (r2 < 0 || r2 >= 8 || c2 < 0 || c2 >= 8){
           return false; 
        }
        if (board[r2][c2] != pos.BLANK){
           return false; 
        }
        if (player) {
           if (board[r1][c1] == pos.HUMAN  && r2 > r1)
               return true;  
           if(board[r1][c1] == pos.DAME_HUMAN)
       		   return true;
            return false;  
        }
        else {
           
        	if (board[r1][c1] == pos.PROGRAM  && r2 < r1)
                return true;  
        	if(board[r1][c1] == pos.DAME_PROGRAM)
        		return true;
             return false;  
        }

    } 
    
    public Move[] possibleMovesFrom(Move p,boolean player,int row,int col){
        Move pos=(Move)p;
        int[][] board=pos.board;
        int playerKing;  
        if (player)
           playerKing = pos.DAME_HUMAN;
        else
           playerKing = pos.DAME_PROGRAM;
        Vector moves = new Vector();  
        
        if (board[row][col] == pos.HUMAN || board[row][col] == playerKing) {
               if (canJump(player,p, row, col, row+1, col+1, row+2, col+2)){
            	   int[][] mat=pos.board;
                   mat=CheckerChangeJump(player,mat,row, col, row+1, col+1, row+2, col+2);
                   tab[0]=row+2;
                   tab[1]=col+2;
                   if(canJump(player,p, row, col,row+3, col+3, row+4, col+4)){
                       mat=CheckerChangeJump(player,mat,row+2, col+2,row+3, col+3, row+4, col+4);
                       tab[0]=row+4;
                       tab[1]=col+4;
                       if(canJump(player,p,row,col,row+5, col+5, row+6, col+6)){
                           mat=CheckerChangeJump(player,mat,row+4,col+4,row+5, col+5, row+6, col+6);
                            tab[0]=row+6;
                            tab[1]=col+6;
                       }
                       if(canJump(player,p, row, col,row+5, col+3, row+6, col+2)){
                           mat=CheckerChangeJump(player,mat,row+4, col+4,row+5, col+3, row+6, col+2);
                            tab[0]=row+6;
                            tab[1]=col+2;
                       }
                   }
                   if(canJump(player,p, row, col,row+3, col+1, row+4, col)){
                       mat=CheckerChangeJump(player,mat,row+2, col+2,row+3, col+1, row+4, col);
                        tab[0]=row+4;
                        tab[1]=col;
                       if(canJump(player,p,row,col,row+5, col+1, row+6, col+2)){
                           mat=CheckerChangeJump(player,mat,row+4,col,row+5, col+1, row+6, col+2);
                            tab[0]=row+6;
                            tab[1]=col+2;
                       }
                       if(canJump(player,p, row, col,row+5, col-1, row+6, col-2)){
                           mat=CheckerChangeJump(player,mat,row+4, col,row+5, col-1, row+6, col-2);
                            tab[0]=row+6;
                            tab[1]=col-2;
                       }
                   }
                   moves.addElement(new Move(mat));
               }
               /*********************************************************************/
               if (canJump(player,p, row, col, row+1, col-1, row+2, col-2)){
            	   int[][] mat=pos.board;
                   mat=CheckerChangeJump(player,mat,row, col, row+1, col-1, row+2, col-2);
                    tab[2]=row+2;
                    tab[3]=col-2;
                   if(canJump(player,p, row, col,row+3, col-1, row+4, col)){
                       mat=CheckerChangeJump(player,mat,row+2, col-2,row+3, col-1, row+4, col);
                       tab[2]=row+4;
                       tab[3]=col;
                       if(canJump(player,p,row,col,row+5, col+1, row+6, col+2)){
                           mat=CheckerChangeJump(player,mat,row+4,col,row+5, col+1, row+6, col+2);
                            tab[2]=row+6;
                            tab[3]=col+2;
                       }
                       if(canJump(player,p, row, col,row+5, col-1, row+6, col-2)){
                           mat=CheckerChangeJump(player,mat,row+4, col,row+5, col-1, row+6, col-2);
                           tab[2]=row+6;
                           tab[3]=col-2;
                       }
                   }
                   if(canJump(player,p, row, col,row+3, col-3, row+4, col-4)){
                       mat=CheckerChangeJump(player,mat,row+2, col-2,row+3, col-3, row+4, col-4);
                       tab[2]=row+4;
                       tab[3]=col-4;
                       if(canJump(player,p,row,col,row+5, col-3, row+6, col-2)){
                           mat=CheckerChangeJump(player,mat,row+4,col-4,row+5, col-3, row+6, col-2);
                           tab[2]=row+6;
                           tab[3]=col-2;
                       }
                       if(canJump(player,p, row, col,row+5, col-5, row+6, col-6)){
                           mat=CheckerChangeJump(player,mat,row+4, col-4,row+5, col-5, row+6, col-6);
                           tab[2]=row+6;
                           tab[3]=col-6;
                       }
                   }
                   moves.addElement(new Move(mat));
               }
            }
            /*********************************************************************************************************/
            if (board[row][col] == pos.PROGRAM || board[row][col] == playerKing) {
                if (canJump(player,p, row, col, row-1, col+1, row-2, col+2)){
                	int[][] mat=pos.board;
                    mat=CheckerChangeJump(player,mat, row, col, row-1, col+1, row-2, col+2);
                    tab[4]=row-2;
                    tab[5]=col+2;
                    if (canJump(player,p, row, col, row-3, col+3, row-4, col+4)){
                        mat=CheckerChangeJump(player, mat,row-2, col+2, row-3, col+3, row-4, col+4);
                        tab[4]=row-4;
                        tab[5]=col+4;
                        if (canJump(player,p, row, col, row-5, col+5, row-6, col+6)){
                            mat=CheckerChangeJump(player,mat, row-4, col+4, row-5, col+5, row-6, col+6);
                            tab[4]=row-6;
                            tab[5]=col+6;
                        }
                        if (canJump(player,p, row, col, row-5, col+3, row-6, col+2)){
                            mat=CheckerChangeJump(player,mat, row-4, col+4, row-5, col+3, row-6, col+2);
                            tab[4]=row-6;
                            tab[5]=col+2;
                        }
                    }
                    if (canJump(player,p, row, col, row-3, col+1, row-4, col)){
                        mat=CheckerChangeJump(player,mat,row-2, col+2, row-3, col+1, row-4, col);
                        tab[4]=row-4;
                        tab[5]=col;
                        if (canJump(player,p, row, col, row-5, col+1, row-6, col+2)){
                            mat=CheckerChangeJump(player,mat,row-4, col, row-5, col+1, row-6, col+2);
                            tab[4]=row-6;
                            tab[5]=col+2;
                        }
                        if (canJump(player,p, row, col, row-5, col-1, row-6, col-2)){
                            mat=CheckerChangeJump(player,mat,row-4, col, row-5, col-1, row-6, col-2);
                            tab[4]=row-6;
                            tab[5]=col-2;
                        }
                    }
                    moves.addElement(new Move(mat));
                }
                /***********************************************************************/
                if (canJump(player,p, row, col, row-1, col-1, row-2, col-2)){
                	int[][] mat=pos.board;
                    mat=CheckerChangeJump(player,mat,row, col, row-1, col-1, row-2, col-2);
                    tab[6]=row-2;
                    tab[7]=col-2;
                    if (canJump(player,p, row, col, row-3, col-1, row-4, col)){
                        mat=CheckerChangeJump(player,mat, row-2, col-2, row-3, col-1, row-4, col);
                        tab[6]=row-4;
                        tab[7]=col;
                        if (canJump(player,p, row, col, row-5, col+1, row-6, col+2)){
                            mat=CheckerChangeJump(player,mat, row-4, col, row-5, col+1, row-6, col+2);
                            tab[6]=row-6;
                            tab[7]=col+2;
                        }
                        if (canJump(player,p, row, col, row-5, col-1, row-6, col-2)){
                            mat=CheckerChangeJump(player,mat, row-4, col, row-5, col-1, row-6, col-2);
                            tab[6]=row-6;
                            tab[7]=col-2;
                        }
                    }
                    if (canJump(player,p, row, col, row-3, col-3, row-4, col-4)){
                        mat=CheckerChangeJump(player,mat, row-2, col-2, row-3, col-3, row-4, col-4);
                        tab[6]=row-4;
                        tab[7]=col-4;
                        if (canJump(player,p, row, col, row-5, col-3, row-6, col-2)){
                            mat=CheckerChangeJump(player,mat,row-4, col-4, row-5, col-3, row-6, col-2);
                            tab[6]=row-6;
                            tab[7]=col-2;
                        }
                        if (canJump(player,p, row, col, row-5, col-5, row-6, col-6)){
                            mat=CheckerChangeJump(player,mat, row-4, col-4, row-5, col-5, row-6, col-6);
                            tab[6]=row-6;
                            tab[7]=col-6;
                        }
                    }
                    moves.addElement(new Move(mat));
                }
            }
            if (moves.size() == 0){
                if (board[row][col] == pos.HUMAN || board[row][col]==playerKing) {
                          if (canMove(player,p,row,col,row+1,col+1)){
                             moves.addElement(new Move(CheckerChangeMove(player,pos.board,row, col, row+1, col+1)));
                             tab[8]=row+1;
                             tab[9]=col+1;
                          }
                          if (canMove(player,p,row,col,row+1,col-1)){
                             moves.addElement(new Move(CheckerChangeMove(player,pos.board,row, col, row+1, col-1)));
                             tab[10]=row+1;
                             tab[11]=col-1;
                          }
                     }
                if(board[row][col] == pos.PROGRAM || board[row][col]==playerKing){
                    if (canMove(player,p,row,col,row-1,col+1)){
                         moves.addElement(new Move(CheckerChangeMove(player,pos.board,row, col, row-1, col+1)));
                         tab[12]=row-1;
                         tab[13]=col+1;
                    }
                    if (canMove(player,p,row,col,row-1,col-1)){
     
                         moves.addElement(new Move(CheckerChangeMove(player,pos.board,row, col, row-1, col-1)));
                         tab[14]=row-1;
                         tab[15]=col-1;
                    }
                }
                
            }
        
             if (moves.size() == 0)
                return null;
             else {
                Move[] moveArray = new Move[moves.size()];
                for (int i = 0; i < moves.size(); i++)
                   moveArray[i] = (Move)moves.elementAt(i);
                return moveArray;
             }
    }

        public Move makeMove(Move pos, boolean player,int[] caseDept,int[] caseDest) {

              Move pos2 = new  Move();
              Move p=(Move)pos;
              pos2=pos;
              int pp=0;
              int row=caseDept[0];
              int col=caseDept[1];
              Move[] posArray=possibleMovesFrom(p, player,row,col);
              
              Move[] array=new Move[posArray.length];
              for(int i=0;i<posArray.length;i++){
            	  array[i]=(Move) posArray[i];
              }
              
              int rowDestination=caseDest[0];
              int colDestination=caseDest[1];
 
              if(exist(rowDestination,colDestination)){
                if(pos.board[caseDept[0]][caseDept[1]]==pos.HUMAN){
                    pp=pos.HUMAN;
                }
                if(pos.board[caseDept[0]][caseDept[1]]==pos.PROGRAM){
                    pp=pos.PROGRAM;
                }
                if(pos.board[caseDept[0]][caseDept[1]]==pos.DAME_HUMAN){
                    pp=pos.DAME_HUMAN;
                }
                if(pos.board[caseDept[0]][caseDept[1]]==pos.DAME_PROGRAM){
                     pp=pos.DAME_PROGRAM;
                }
                for(int j=0;j<array.length;j++){
	            	if(pp==1){
	                    if(array[j].board[caseDest[0]][caseDest[1]]==pp || array[j].board[caseDest[0]][caseDest[1]]==pp+9){
	                        pos2=array[j];
	                    }
	            	}
	            	else{
	            		if(array[j].board[caseDest[0]][caseDest[1]]==pp){
	            			 pos2=array[j];
	            		}
	            	}
                }
              }
              else{
                  System.out.println("Unvailable Move ! ");

              }
              
              return pos2;
          }

         public Move createMove() {
         if (this.DEBUG) System.out.println("Enter blank square index [0,64]:");
        int i = 0,j=0,i1=0,j1=0;
        try {
            System.out.println("The row of the piece:");
            Scanner s = new Scanner(System.in);
    		i = s.nextInt();            
            System.out.println("The colume of the piece:");
            j=s.nextInt();
            System.out.println("The row of the move");
            i1=s.nextInt();
            System.out.println("The colume of the move");
            j1=s.nextInt();

        } catch (Exception e) { }
        Move mm = new Move();
        mm.pieceIndexRow = i;
        mm.pieceIndexCol=j;
        mm.moveIndexRow = i1;
        mm.moveIndexCol=j1;

        return mm;
    }
    
      public boolean reachedMaxDepth(Move p, int depth) {
              boolean ret = false;
              int d=depth;
              if (wonPosition(p, false)) ret = true;
              else if (wonPosition(p, true))  ret = true;
              else if (drawnPosition(p))      ret = true;
              else if (d==8) ret=true;
              if (this.DEBUG) {
                  System.out.println("reachedMaxDepth: pos=" + p.toString() + ", depth="+depth
                                     +", ret=" + ret);
              }
              return ret;
        }
      
      public boolean exist(int row,int col){
          boolean ret=false;
          for(int i=0;i<8;i++){
                if(tab[2*i]==row && tab[2*i+1]==col) ret=true;
                
          }
          return ret;
      }
       
      int[][] remplirPar(int[][] m){
      	int[][] matrice = new int[8][8];
      	for(int i=0;i<8;i++){
      		for(int j=0;j<8;j++){
      			matrice[i][j]=m[i][j];
      		}
      	}
      	return matrice;
      }


    public float bordEval(Move t){
    	int[][] m = t.board;
    	float x;
    	
    	float  programPieces=0, totalPieces=1, programDames=0, totalDames=1, programInCenter=0, totalInCenter=1;
    	for(int row=0;row<8;row++){
    		for(int col=0;col<8;col++){
    			if(m[row][col]==t.PROGRAM)
    				programPieces++;
    		}
    	}
    	
    	for(int row=0;row<8;row++){
    		for(int col=0;col<8;col++){
    			if(m[row][col]==t.DAME_PROGRAM)
    				programDames++;
    		}
    	}
    	
    	for(int row=3;row<5;row++){
    		for(int col=0;col<8;col++){
    			if(m[row][col]==t.PROGRAM || m[row][col]==t.DAME_PROGRAM)
    				programInCenter++;
    		}
    	}
    	
    	for(int row=0;row<8;row++){
    		for(int col=0;col<8;col++){
    			if(m[row][col]==t.PROGRAM || m[row][col]==t.HUMAN || m[row][col]==t.DAME_PROGRAM || m[row][col]==t.DAME_HUMAN)
    				totalPieces++;
    		}
    	}
    	
    	for(int row=0;row<8;row++){
    		for(int col=0;col<8;col++){
    			if(m[row][col]==t.DAME_PROGRAM || m[row][col]==t.DAME_HUMAN )
    				totalDames++;
    		}
    	}
    	
    	for(int row=3;row<5;row++){
    		for(int col=0;col<8;col++){
    			if(m[row][col]==t.PROGRAM || m[row][col]==t.HUMAN || m[row][col]==t.DAME_PROGRAM || m[row][col]==t.DAME_HUMAN)
    				totalInCenter++;
    		}
    	}
    	
    	x = (programPieces/totalPieces) + 10*(programDames/totalDames) + (t.CENTER)*(programInCenter/totalInCenter);
    	return x;
    }
    
        public float bordEval2(Move t){
    	int[][] m = t.board;
    	float x;
    	
    	float  programPieces=0, totalPieces=1, programDames=0, totalDames=1, programInCenter=0, totalInCenter=1;
    	for(int row=0;row<8;row++){
    		for(int col=0;col<8;col++){
    			if(m[row][col]==t.PROGRAM)
    				programPieces++;
    		}
    	}
    	
    	for(int row=0;row<8;row++){
    		for(int col=0;col<8;col++){
    			if(m[row][col]==t.DAME_PROGRAM)
    				programDames++;
    		}
    	}
    	
    	for(int row=3;row<5;row++){
    		for(int col=0;col<8;col++){
    			if(m[row][col]==t.PROGRAM || m[row][col]==t.DAME_PROGRAM)
    				programInCenter++;
    		}
    	}
    	
    	for(int row=0;row<8;row++){
    		for(int col=0;col<8;col++){
    			if(m[row][col]==t.PROGRAM || m[row][col]==t.HUMAN || m[row][col]==t.DAME_PROGRAM || m[row][col]==t.DAME_HUMAN)
    				totalPieces++;
    		}
    	}
    	
    	x = (programPieces/totalPieces) + 10*(programDames/totalDames) + (t.CENTER)*(programInCenter/totalInCenter);
    	return x;
    }
      

    public Vector alphaBeta(int depth, Move p, boolean player) {
        Vector v = alphaBetaHelper(depth, p, player, 1000000.0f, -1000000.0f);

        return v;
    }
    
    public Vector alphaBetaHelper(int depth, Move p,boolean player, float alpha, float beta) {

        if (reachedMaxDepth(p, depth)) {
            Vector v = new Vector(2);
            float value = positionEvaluation(p, player);
            v.addElement(new Float(value));
            v.addElement(null);

            return v;
        }
        Vector best = new Vector();
        Move [] moves = possibleMoves(p, player);
        if(moves !=null){
	        for (int i=0; i<moves.length; i++) {

	            Vector v2 = alphaBetaHelper(depth + 1, moves[i], !player, -beta, -alpha);

	            float value = -((Float)v2.elementAt(0)).floatValue();
	            if (value > beta) {

	                beta = value;
	                best = new Vector();
	                best.addElement(moves[i]);
	                Enumeration enum2 = v2.elements();
	                enum2.nextElement(); 
	                while (enum2.hasMoreElements()) {
	                    Object o = enum2.nextElement();
	                    if (o != null) best.addElement(o);
	                }
	            }
	           
	            if (beta >= alpha) {
	                break;
	            }
	        }
        }
        Vector v3 = new Vector();
        v3.addElement(new Float(beta));
        Enumeration enum2 = best.elements();
        while (enum2.hasMoreElements()) {
            v3.addElement(enum2.nextElement());
        }
        return v3;
    }
    
    
        public void play(Move startingPosition, boolean humanPlayFirst,int [][] Board,int x) {
            Scanner input =new Scanner(System.in);
            int [] casedepth=new int [2];
            int [] casedept=new int [2];
              JOptionPane jop1;
         jop1 = new JOptionPane();
         ImageIcon img1,img2,img3;
          
         Move cp=new Move();
         printPosition(startingPosition);
        if (humanPlayFirst == false) {
            Vector v = alphaBeta(0, startingPosition, PROGRAM);
            startingPosition = (Move)v.elementAt(1);
            cp=(Move)startingPosition;
           Board=cp.getBoard();
            System.out.println();
           printPosition(startingPosition);
            System.out.println();
           humanPlayFirst=true;
        }else{

                humanPlayFirst=false;
        }
        while (true) {
        	try {

			} catch (Exception e1) {
				e1.printStackTrace();
			}
            if (wonPosition(startingPosition, PROGRAM)) {

                img1=new ImageIcon("C:/Users/FN/Desktop/Checker/src/checkers/gameover.png");
                jop1.showMessageDialog(null, "", "Résultat", JOptionPane.ERROR_MESSAGE,img1);

                break;
            }
            if (wonPosition(startingPosition, HUMAN)) {

                img2=new ImageIcon("C:/Users/FN/Desktop/Checker/src/checkers/win.png");
                jop1.showMessageDialog(null, "", "Résultat", JOptionPane.ERROR_MESSAGE,img2);
                break;
            }
            if (drawnPosition(startingPosition)) {

                img3=new ImageIcon("C:/Users/FN/Desktop/Checker/src/checkers/draw.png");
                jop1.showMessageDialog(null, "", "Résultat", JOptionPane.ERROR_MESSAGE,img3);
                break;
            }

            
            
            startingPosition = (Move)new Move(Board);

            if(wonPosition(startingPosition,true)){
               img1=new ImageIcon("C:/Users/FN/Desktop/Checker/src/checkers/win.png");
                jop1.showMessageDialog(null, "", "Résultat", JOptionPane.ERROR_MESSAGE,img1);
                break;
            }
            
                    if (humanPlayFirst == false) {
            Vector v = alphaBeta(0, startingPosition, PROGRAM);
            startingPosition = (Move)v.elementAt(1);
            cp=(Move)startingPosition;
           Board=cp.getBoard();
            System.out.println();
           printPosition(startingPosition);
                        System.out.println();
           humanPlayFirst=true;
        }else{

                Move m= (Move)this.createMove();

                        
                while(Board[m.pieceIndexRow][m.pieceIndexCol]==-2 ||Board[m.pieceIndexRow][m.pieceIndexCol]==0||Board[m.pieceIndexRow][m.pieceIndexCol]==-1){
                    System.out.println("You Entered a wrong piece position!Please try agian");
                    m= (Move)this.createMove();
                    

                }
                casedept[0]=m.pieceIndexRow;
                casedept[1]=m.pieceIndexCol;
                casedepth[0]=m.moveIndexRow;
                casedepth[1]=m.moveIndexCol;

                Move temp =new Move(Board);
                Move m1 = this.makeMove(new Move(Board), HUMAN, casedept, casedepth);
               while(m1.getBoard()==temp.getBoard()){

                   m= (Move)this.createMove();
                
                casedept[0]=m.pieceIndexRow;
                casedept[1]=m.pieceIndexCol;
                casedepth[0]=m.moveIndexRow;
                casedepth[1]=m.moveIndexCol;
                 temp =new Move(Board);
                 m1 = this.makeMove(new Move(Board), HUMAN, casedept, casedepth);
               }
                               
                cp=(Move)m1;
                Board=cp.getBoard();
                 System.out.println();
                printPosition(m1);
                        System.out.println();
                humanPlayFirst=false;
        }

                   
            try {
                   
            } catch (Exception e) { }
        }       

        }
    
}
