package view.component;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class CookieSwipeFrame extends JFrame{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CookieSwipeFrame(){
		initBaseComponents();
	}
	
	public void initBaseComponents(){
		setSize(400, 400);
		
//		setBackground(new Color(0, 175, 240));
		
//		getContentPane().setBackground(new Color(0, 175, 240));
		
		//setBackground(new Color(44, 62, 80));
		getContentPane().setBackground(CookieSwipeColor.BACKGROUND_FRAME);
	}
	
}
