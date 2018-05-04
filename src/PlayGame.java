
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author FN
 */
public class PlayGame {
    
        public static void main(String[] args) {
        
        Move pos=new Move();

        pos.init();
        AlphaBeta c=new AlphaBeta();
        Scanner input =new Scanner(System.in);
        
            System.out.println("For easy choose 1 for difficalt choose 2");
            int level = input.nextInt();
            System.out.println("B-row-colume this is the position of the pieces");
            System.out.println("Start");
        c.play(pos, false,pos.board,level);

    }
}
