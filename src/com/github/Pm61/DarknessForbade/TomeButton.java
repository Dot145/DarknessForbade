package com.github.Pm61.DarknessForbade;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import com.github.Pm61.DarknessForbade.Tome.Tomes;

public abstract class TomeButton implements MouseListener{
	private Player player;
	Tomes contains;
	Encounter ec = new Encounter();

    public Tomes getContains() {
		return contains;
	}
    
	public void setContains(Tomes contains) {
		this.contains = contains;
	}

    public void mouseClicked(MouseEvent e) {
    	if(ec.currentTurn == ec.ecplayer){
    		player.setTomeCasting(contains);
    		player.setAttackCasting(null);
    		ec.playerCastSpell();
    	}
    }
    
	public void mousePressed(MouseEvent e) {

    }
    public void mouseReleased(MouseEvent e) {

    }
    public void mouseEntered(MouseEvent e) {
    	
    }
    public void mouseExited(MouseEvent e) {
    	
    }
}
