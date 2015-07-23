package view.component;

import java.awt.Color;
import java.awt.Component;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class CookieSwipeListCellRenderer<T> extends CookieSwipeLabel implements ListCellRenderer<T>{
	private static final long serialVersionUID = 2394066233067649610L;
	private DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();
	
	public CookieSwipeListCellRenderer(){
		super();
	}
	
	public CookieSwipeListCellRenderer(String s) {
		super(s);
	}
	
	@Override
	public Component getListCellRendererComponent(JList<? extends T> list,
			T value, int index, boolean isSelected, boolean cellHasFocus) {
		JLabel renderer = (JLabel) defaultRenderer.getListCellRendererComponent(list, value, index, isSelected, hasFocus());
		if(cellHasFocus){
			renderer.setBackground(new Color(139, 155, 197));
		} else {
			renderer.setBackground(new Color(91, 122, 143));
		}
		renderer.setForeground(CookieSwipeColor.LETTER);
                if(value != null) {
                    if(value instanceof Message) {
                            String render = null;
                            try {
                                    render = ((Message)value).getSubject();
                            } catch (MessagingException e) {
                                    e.printStackTrace();
                            }
                            if(render == null) render = "<no subject>"; 
                            renderer.setText(render);
                    }
                    else renderer.setText(value.toString());
                }
		
		return renderer;
	}

}
