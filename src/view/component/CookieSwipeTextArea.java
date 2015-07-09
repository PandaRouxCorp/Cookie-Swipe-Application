package view.component;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class CookieSwipeTextArea extends JTextArea {

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

	    }
}
