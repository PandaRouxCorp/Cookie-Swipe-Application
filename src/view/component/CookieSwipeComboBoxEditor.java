package view.component;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicComboBoxEditor;

public class CookieSwipeComboBoxEditor extends BasicComboBoxEditor {
    private JLabel label = new JLabel();
    private JPanel panel = new JPanel();
    private Object selectedItem;
    private String title;
     
    public CookieSwipeComboBoxEditor(String title) {
         
        label.setOpaque(false);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(CookieSwipeColor.BUTTON);
         
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 2));
        panel.add(label);
        panel.setBackground(CookieSwipeColor.LETTER);
        
        this.title = title;
    }
     
    public Component getEditorComponent() {
        return this.panel;
    }
     
    public Object getItem() {
        return "[" + this.selectedItem.toString() + "]";
    }
     
    public void setItem(Object item) {
        this.selectedItem = item;
//        if(item != null){
//        	label.setText(item.toString());
//        }else{
//        	label.setText(title);
//        }
        label.setText(title);
    }
     
}
