package view.component;

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

    public CookieSwipePanel() {
	super();
	initComponent();
    }

    public void initComponent() {
	setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	int w = getWidth();
	int h = getHeight();
	Graphics2D g2d = (Graphics2D) g;
	g2d.setPaint(CookieSwipeColor.BUTTON);
	g2d.fillRect(0, 0, w, h);
	setPreferredSize(new Dimension((int) Toolkit.getDefaultToolkit().getScreenSize().getHeight(), 45));
	setSize(new Dimension((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(), 45));
    }
}
