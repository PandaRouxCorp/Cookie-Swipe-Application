/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.component;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Suiken
 */
public class CookieSwipeButtonSprite extends CookieSwipeButton {

    private static final long serialVersionUID = 1L;

    public static final String DELETE = "delete";
    public static final String REFRESH = "refresh";
    public static final String SEND = "send";
    public static final String TRANSFER = "transfer";
    public static final String NEW = "new";

    public CookieSwipeButtonSprite() {
	super();

    }

    /**
     *
     * @param s Actuellement 5 possibilités qui sont les variables en static
     * final de la classe. Dans le cas où une des variables static final n'est
     * pas choisie, l'erreur "unknown sprite" apparaîtra. Valeurs possibles:
     * CookieSwipeButtonSprite.NEW, CookieSwipeButton.REFRESH,
     * CookieSwipeButton.TRANSFER, CookieSwipeButton.SEND,
     * CookieSwipeButton.DELETE
     *
     */
    public void setText(String s) {
	try (InputStream is = getClass().getResourceAsStream("fontello.ttf")) {
	    Font font = Font.createFont(Font.TRUETYPE_FONT, is);
	    font = font.deriveFont(Font.PLAIN, 24f);

	    super.setFont(font);
	    switch (s) {
		case NEW:
		    super.setText("\ue806");
		    break;
		case REFRESH:
		    super.setText("\ue805");
		    break;
		case TRANSFER:
		    super.setText("\ue803");
		    break;
		case SEND:
		    super.setText("\ue804");
		    break;
		case DELETE:
		    super.setText("\ue807");
		    break;
		default:
		    throw new Exception("Unknown sprite");
	    }
	} catch (IOException | FontFormatException exp) {
	    System.out.println("Erreur sur le font");
	    exp.printStackTrace();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

}
