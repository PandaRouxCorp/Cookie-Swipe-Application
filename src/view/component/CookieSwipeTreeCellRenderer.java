package view.component;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;

public class CookieSwipeTreeCellRenderer implements TreeCellRenderer {

	CookieSwipeLabel cellLabel = new CookieSwipeLabel();

	JPanel renderer = new JPanel();

	DefaultTreeCellRenderer defaultRenderer = new DefaultTreeCellRenderer();

	Color backgroundSelectionColor;

	Color backgroundNonSelectionColor;

	public CookieSwipeTreeCellRenderer() {
		renderer.add(cellLabel);
		cellLabel.setMinimumSize(new Dimension(120, 30));
		cellLabel.setPreferredSize(new Dimension(120, 30));
		cellLabel.setMaximumSize(new Dimension(120, 30));
		backgroundSelectionColor = CookieSwipeColor.BUTTON;
		backgroundNonSelectionColor = CookieSwipeColor.BUTTON.brighter();
	}

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean selected, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {
		Component returnValue = null;
		if ((value != null) && (value instanceof DefaultMutableTreeNode)) {
			Object userObject = ((DefaultMutableTreeNode) value)
					.getUserObject();
			if (userObject instanceof String) {
				String e = (String) userObject;
				cellLabel.setText(e);
				//TODO
				//setToolTipText(e);
				if (selected) {
					renderer.setBackground(backgroundSelectionColor);
				} else if(expanded) {
					renderer.setBackground(CookieSwipeColor.BUTTON);
				} else{
					renderer.setBackground(backgroundNonSelectionColor);
					
				}
				renderer.setEnabled(tree.isEnabled());
				returnValue = renderer;
			}
		}
		if (returnValue == null) {
			returnValue = defaultRenderer.getTreeCellRendererComponent(tree,
					value, selected, expanded, leaf, row, hasFocus);
		}
		return returnValue;
	}

}
