
import java.awt.GridLayout;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author FN
 */
public class Case extends JPanel{
    
    	private static final long serialVersionUID = -1839026893240660968L;
	
	private boolean eat;
	private boolean selection;
        	public Case(boolean e){
		setLayout(new GridLayout(1,0));
		this.eat=e;
	}
         
       public boolean getEat() {
		return eat;
	}
       
       	public boolean isSelection() {
		return selection;
	}
        
        	public void setSelection(boolean selection) {
		this.selection = selection;

	}

}
