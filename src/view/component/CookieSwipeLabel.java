package view.component;

import java.awt.Color;

import javax.swing.Icon;
import javax.swing.JLabel;

public class CookieSwipeLabel extends JLabel {

	public CookieSwipeLabel() {
		super();
		initComponent();
	}

	public CookieSwipeLabel(String text) {
		super(text);
		initComponent();
	}
	
	public void initComponent(){
		setForeground(new Color(255, 255, 255));
	}

}
