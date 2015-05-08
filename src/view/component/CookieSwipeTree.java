package view.component;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.JTree;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import org.w3c.dom.events.EventTarget;
import org.w3c.dom.views.AbstractView;

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

	setBackground(Color.white);
	Border thickBorder = new LineBorder(Color.white, 10);
	setBorder(thickBorder);

    }

}
