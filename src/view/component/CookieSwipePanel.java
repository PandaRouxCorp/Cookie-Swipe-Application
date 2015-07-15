package view.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class CookieSwipePanel extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Color panelColor = CookieSwipeColor.BUTTON;

    public CookieSwipePanel() {
		super();
		initComponent();
    }

    public void initComponent() {
    	setOpaque(false);
    }
    
    public CookieSwipePanel(Color color){
    	super();
    	panelColor = color;
		initComponent();
    }

    @Override
    protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int w = getWidth();
		int h = getHeight();
		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(panelColor);
		g2d.fillRect(0, 0, w, h);
		setPreferredSize(new Dimension((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight(), 45));
		setSize(new Dimension((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(), 45));
    }
}
