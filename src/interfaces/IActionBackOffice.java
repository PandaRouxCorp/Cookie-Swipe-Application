/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package interfaces;

import model.User;

/**
 *
 * @author Lucas
 */
public interface IActionBackOffice {
    
    public void setFrame(IJFrame frame);
    
    public void setUser(User user);
    
    public void execute();
}
