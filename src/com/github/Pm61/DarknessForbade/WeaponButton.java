package com.github.Pm61.DarknessForbade;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class WeaponButton implements MouseListener{
	enum Attacks {DAMAGE, ACCURACY, MIXED};
	private Player player;
	Attacks contains;
	Encounter ec = new Encounter();
	public Attacks getContains() {
		return contains;
	}

	public void setContains(Attacks contains) {
		this.contains = contains;
	}

	public void mouseClicked(MouseEvent e) {
			if (ec.currentTurn == ec.ecplayer){
			player.setAttackCasting(contains);
			player.setTomeCasting(null);
			ec.playerBasicAttack();
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
