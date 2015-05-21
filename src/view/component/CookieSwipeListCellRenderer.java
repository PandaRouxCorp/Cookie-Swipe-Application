package view.component;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class CookieSwipeListCellRenderer extends CookieSwipeLabel implements ListCellRenderer<String>{

	private DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();
	
	public CookieSwipeListCellRenderer(){
		super();
	}
	
	public CookieSwipeListCellRenderer(String s) {
		super(s);
	}
	
	@Override
	public Component getListCellRendererComponent(JList<? extends String> list,
			String value, int index, boolean isSelected, boolean cellHasFocus) {
		JLabel renderer = (JLabel) defaultRenderer.getListCellRendererComponent(list, value, index, isSelected, hasFocus());
		if(cellHasFocus){
			renderer.setBackground(CookieSwipeColor.BUTTON);
		} else {
			renderer.setBackground(CookieSwipeColor.BACKGROUND_FRAME);
		}
		renderer.setForeground(CookieSwipeColor.LETTER);
		
		return renderer;
	}

}
