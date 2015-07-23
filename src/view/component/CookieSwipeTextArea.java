package view.component;

import java.awt.Color;
import javax.swing.JEditorPane;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class CookieSwipeTextArea extends JEditorPane {
    
    {
        setOpaque(false);
    }

	public CookieSwipeTextArea() {
		super();
		initComponent();
	    }

	    public CookieSwipeTextArea(String arg0) {
			super();
			initComponent();
	    }

	    public void initComponent() {
	    	//setSize(110, 20);
			Border thickBorder = new LineBorder(Color.white, 5);
			setBorder(thickBorder);
			setBackground(new Color(255, 255, 255));
                        setContentType("text/html");

	    }
}
