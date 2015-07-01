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
     * @param s Actuellement 5 possibilitÃ©s qui sont les variables en static
     * final de la classe. Dans le cas oÃ¹ une des variables static final n'est
     * pas choisie, l'erreur "unknown sprite" apparaÃ®tra. Valeurs possibles:
     * CookieSwipeButtonSprite.NEW, CookieSwipeButton.REFRESH,
     * CookieSwipeButton.TRANSFER, CookieSwipeButton.SEND,
     * CookieSwipeButton.DELETE
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
			super.setText("\ue804");
			setToolTipText("Rafraîchir");
			break;
		    case FORWARD:
			super.setText("\ue809");
			setToolTipText("Transférer");
			break;
		    case SEND:
			super.setText("\ue805");
			setToolTipText("Envoyer");
			break;
		    case DELETE:
			super.setText("\ue803");
			setToolTipText("Supprimer");
			break;
		    case REPLY:
			super.setText("\ue801");
			setToolTipText("Répondre");
			break;
		    case REPLY_ALL:
			super.setText("\ue808");
			setToolTipText("Répondre à tous");
			break;
		    case ARCHIVE:
			super.setText("\ue806");
			setToolTipText("Archiver");
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
