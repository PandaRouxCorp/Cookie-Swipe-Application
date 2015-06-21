package view.component;

import javax.swing.DefaultListModel;
import javax.swing.JList;

public class CookieSwipeList<T> extends JList<T> {
	private static final long serialVersionUID = -1491933111777808360L;

	public CookieSwipeList(){
		super();
		initComponent();
	}
	
	public CookieSwipeList(T[] s){
		super(s);
		initComponent();
	}
	
	public CookieSwipeList(DefaultListModel<T> dlm){
		super(dlm);
		initComponent();
	}
	
	public void initComponent(){
		CookieSwipeListCellRenderer<T> csListCellRenderer = new CookieSwipeListCellRenderer<T>();
		setCellRenderer(csListCellRenderer);
	}
}
