package view.component;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.text.Document;

public class CookieSwipeTextField extends JTextField {

    public CookieSwipeTextField() {
	super();
	initComponent();
    }

    public CookieSwipeTextField(String arg0) {
	super(arg0);
	initComponent();
    }

    public void initComponent() {
	//setSize(110, 20);
	Border thickBorder = new LineBorder(Color.white, 5);
	setBorder(thickBorder);

    }

}
