
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class pieces extends JPanel{
    
	private static final long serialVersionUID = 1436178861615738480L;
        
	private int statusPiece; 

        private  String imgPath;
        
        
	public pieces(int statusPiece) {
		
		this.statusPiece = statusPiece;
		

	}

	    
        
	public int getStatusPiece() {
		return this.statusPiece;
	}


        
    
}
