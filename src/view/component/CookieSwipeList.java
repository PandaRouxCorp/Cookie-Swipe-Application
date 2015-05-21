package view.component;

import javax.swing.DefaultListModel;
import javax.swing.JList;

public class CookieSwipeList extends JList<String> {
	
	public CookieSwipeList(){
		super();
		initComponent();
	}
	
	public CookieSwipeList(String[] s){
		super(s);
		initComponent();
	}
	
	public CookieSwipeList(DefaultListModel<String> dlm){
		super(dlm);
		initComponent();
	}
	
	public void initComponent(){
		CookieSwipeListCellRenderer csListCellRenderer = new CookieSwipeListCellRenderer();
		setCellRenderer(csListCellRenderer);
	}
}
