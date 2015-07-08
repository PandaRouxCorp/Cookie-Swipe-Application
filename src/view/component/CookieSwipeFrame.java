package view.component;


import java.awt.Dimension;
import java.util.HashMap;

import javax.swing.JFrame;

public class CookieSwipeFrame extends JFrame {
    
    private static final long serialVersionUID = 1L;
    
    protected HashMap<String, Object> hsJcomponent;

    public CookieSwipeFrame(){
        hsJcomponent = new HashMap<>();
        initBaseComponents();
    }

    private void initBaseComponents() {
        //setBackground(new Color(0, 175, 240));
        //getContentPane().setBackground(new Color(0, 175, 240));
        //setBackground(new Color(44, 62, 80));
        getContentPane().setBackground(CookieSwipeColor.BACKGROUND_FRAME);
    }
    
    public HashMap<String, Object> getJComponent() {
        return hsJcomponent;
    }
}
