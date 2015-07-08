/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.component;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 *
 * @author Suiken
 */
public class CookieSwipeButtonSprite extends CookieSwipeButton{

    private static final long serialVersionUID = 1L;

    public static final String DELETE = "delete";
    public static final String REFRESH = "refresh";
    public static final String SEND = "send";
    public static final String FORWARD = "forward";
    public static final String NEW = "new";
    public static final String ARCHIVE = "archive";
    public static final String REPLY = "reply";
    public static final String REPLY_ALL = "reply_all";
    public static final String TO_BLACKLIST = "to_blacklist";
    public static final String BLACKLIST_ADD = "blacklist_add";
    public static final String BLACKLIST_DELETE = "blacklist_delete";
    public static final String ATTACH = "attach";
    
    
    private static Font font;
    
    static{
    	try{
	//	    font = Font.createFont(Font.TRUETYPE_FONT, 
	//		CookieSwipeButtonSprite.class.getClassLoader().getResourceAsStream("fontello/font/fontello.ttf"));
	//		font = font.deriveFont(14f);
	
		    font = Font.createFont(Font.TRUETYPE_FONT, CookieSwipeButtonSprite.class.getClassLoader().getResourceAsStream("fontello/font/fontello.ttf"));
		    font = font.deriveFont(14f);
	    
//		    InputStream is = new FileInputStream("fontello/font/fontello.ttf");
//		    font = Font.createFont(Font.TRUETYPE_FONT, is);
//		    font.deriveFont(14f);
		    
		    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		    ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("fontello/font/fontello.ttf")));
		    
//		    setFont(font);
    	}catch(IOException | FontFormatException ex){
 	    	ex.printStackTrace();
     	}catch (Exception e) {
		e.printStackTrace();
	}
    }

    public CookieSwipeButtonSprite() {
    	super();
    	buttonColor = CookieSwipeColor.BACKGROUND_FRAME;
    }
    
    protected void initComponent(){	
		setForeground(CookieSwipeColor.LETTER);
		
		setFocusPainted(false);
		
		Border thickBorder = new LineBorder(CookieSwipeColor.BACKGROUND_FRAME, 2);
		setBorder(thickBorder);
	
		setContentAreaFilled(false);
		
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		setLayout(null);
		
	}
    
    /**
     *
     * @param Plusieurs possibilités qui sont les variables en static
     * final de la classe. Dans le cas où une des variables static final n'est
     * pas choisie, l'erreur "unknown sprite" apparaîtra. 
     *
     */
    public void setText(String s) {
    	try{ 
    		
	    switch (s) {
//		case NEW:
//		    super.setText("<html><font face=fontello>\ue808</font> <font face=Sans Serif>Nouveau Mail</font></html>"); // 806
//		    break;
//		case REFRESH:
//		    super.setText("<html>\ue805 <font face=Sans Serif>Rafraîchir</font></html>");
//		    break;
//		case FORWARD:
//		    super.setText("<html>\ue803 <font face=Sans Serif>Transférer</font></html>");
//		    break;
//		case SEND:
//		    super.setText("<html>\ue804 <font face=Sans Serif>Envoyer</font></html>");
//		    break;
//		case DELETE:
//		    super.setText("<html>\ue807 <font face=Sans Serif>Supprimer</font></html>");
//		    break;
//		case REPLY:
//		    super.setText("<html>\ue801 <font face=Sans Serif>Répondre</font></html>");
//		    break;
//		case REPLY_ALL:
//		    super.setText("<html>\ue808 <font face=Sans Serif>Répondre à tous</font></html>");
//		    break;
//		case ARCHIVE:
//		    super.setText("<html>\ue806 <font face=Sans Serif>Archiver</font></html>");
//		    break;
			case NEW:
				super.setText("\ue800");
				setToolTipText("Nouveau mail");
				break;
		    case REFRESH:
				super.setText("\ue801");
				setToolTipText("Rafraîchir");
				break;
		    case FORWARD:
				super.setText("\ue802");
				setToolTipText("Transférer");
				break;
		    case SEND:
				super.setText("\ue803");
				setToolTipText("Envoyer");
				break;
		    case DELETE:
				super.setText("\ue804");
				setToolTipText("Supprimer");
				break;
		    case REPLY:
				super.setText("\ue805");
				setToolTipText("Répondre");
				break;
		    case REPLY_ALL:
				super.setText("\ue806");
				setToolTipText("Répondre à tous");
				break;
		    case ARCHIVE:
				super.setText("\ue807");
				setToolTipText("Archiver");
				break;
		    case TO_BLACKLIST:
		    	super.setText("\ue808");
		    	setToolTipText("Blacklister");
		    	break;
		    case BLACKLIST_ADD:
		    	super.setText("\ue809");
		    	setToolTipText("Ajouter à la blacklist");
		    	break;
		    case BLACKLIST_DELETE:
		    	super.setText("\ue810");
		    	setToolTipText("Supprimer de la blacklist");
		    	break;
		    case ATTACH:
		    	super.setText("\ue811");
		    	setToolTipText("Ajouter pièce jointe");
		    	break;
		    default:
		    	throw new Exception("Unknown sprite");
		    }
	    
	    	
    	}catch (Exception e) {
		    e.printStackTrace();
		}
    	super.setFont(font);
    }
}
