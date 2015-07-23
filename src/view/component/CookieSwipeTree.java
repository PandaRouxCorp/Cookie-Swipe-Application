package view.component;

import java.awt.Color;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.JTree;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;


public class CookieSwipeTree extends JTree {

    private TreePath oldSelectedPath = null;

    public CookieSwipeTree() {
	super();
	initComponent();
    }

    public CookieSwipeTree(Object[] arg0) {
	super(arg0);
	initComponent();
	// TODO Auto-generated constructor stub
    }

    public CookieSwipeTree(Vector<?> arg0) {
	super(arg0);
	initComponent();
	// TODO Auto-generated constructor stub
    }

    public CookieSwipeTree(Hashtable<?, ?> arg0) {
	super(arg0);
	initComponent();
    }

    public CookieSwipeTree(TreeNode arg0) {
	super(arg0);
	initComponent();
    }

    public CookieSwipeTree(TreeModel arg0) {
	super(arg0);
	initComponent();
    }

    public CookieSwipeTree(TreeNode arg0, boolean arg1) {
	super(arg0, arg1);
	initComponent();
    }

    public void initComponent() {
	setCellRenderer(new CookieSwipeTreeCellRenderer());

//	Border thickBorder = new LineBorder(CookieSwipeColor.BACKGROUND_FRAME, 10);
//	setBorder(thickBorder);
        
        setBackground(new Color(91, 122, 143));

    }

}
