package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import view.component.CookieSwipeColor;

public class CookieSwipeButtonAttach extends JButton {

	protected int inset = 5;
	// private Color buttonColor = new Color(12, 125, 175);
	protected Color buttonColor = CookieSwipeColor.BUTTON;
	protected int cell = 0;
	protected int height, width;
	protected boolean sizeUpdated = false;

	public CookieSwipeButtonAttach(String text) {
		setText(text);
		initComponent();
	}

	public CookieSwipeButtonAttach() {
		super();
		initComponent();
	}

	public CookieSwipeButtonAttach(Color background) {
		super();
		buttonColor = background;
		initComponent();
	}
	
	@Override
	public void setText(String text){
		super.setText("<html><u>" + text + "</u></html>");
	}

	protected void initComponent() {
		setForeground(CookieSwipeColor.LETTER);

		setFocusPainted(false);

		Border thickBorder = new LineBorder(buttonColor, 2);
		setBorder(thickBorder);

		setContentAreaFilled(false);

		setCursor(new Cursor(Cursor.HAND_CURSOR));

		setLayout(null);
		

	}

	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(buttonColor);
		
		g.fillRect(0, 0, getWidth(), getHeight());

		if (!sizeUpdated) {
			height = (int) getSize().getHeight() + 20;
			width = (int) getSize().getWidth() + 20;
			sizeUpdated = true;
		}
		setPreferredSize(new Dimension(width, height));
		setSize(width, height);

		super.paintComponent(g);
	}

}
