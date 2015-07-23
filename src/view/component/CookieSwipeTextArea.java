package view.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class CookieSwipeTextArea extends JTextArea {
    
    {
        setOpaque(false);
    }

	public CookieSwipeTextArea() {
		super();
		initComponent();
	    }

	    public CookieSwipeTextArea(String arg0) {
			super(arg0);
			initComponent();
	    }

	    public void initComponent() {
	    	//setSize(110, 20);
			Border thickBorder = new LineBorder(Color.white, 5);
			setBorder(thickBorder);
			setBackground(new Color(255, 255, 255));
                        setLineWrap(true);
                        setWrapStyleWord(true);

	    }
}
