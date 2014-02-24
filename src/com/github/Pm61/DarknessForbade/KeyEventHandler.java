package com.github.Pm61.DarknessForbade;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyEventHandler implements KeyListener {

	DarknessForbade parent;
	
	boolean upArrowPressed, downArrowPressed, rightArrowPressed, leftArrowPressed;
	
	public KeyEventHandler(DarknessForbade parent) {
		this.parent = parent;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			parent.getPlayer().move(Math.PI / 2);
			upArrowPressed = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			parent.getPlayer().move(3 * Math.PI / 2);
			downArrowPressed = true;
		}if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			parent.getPlayer().move(0);
			rightArrowPressed = true;
		}if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			parent.getPlayer().move(Math.PI);
			leftArrowPressed = true;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			parent.getPlayer().stopMoving(Math.PI / 2);
			upArrowPressed = false;
		}if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			parent.getPlayer().stopMoving(3 * Math.PI / 2);
			downArrowPressed = false;
		}if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			parent.getPlayer().stopMoving(0);
			rightArrowPressed = false;
		}if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			parent.getPlayer().stopMoving(Math.PI);
			leftArrowPressed = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	public DarknessForbade getParent() {
		return parent;
	}

	public void setParent(DarknessForbade parent) {
		this.parent = parent;
	}

	public boolean isUpArrowPressed() {
		return upArrowPressed;
	}

	public void setUpArrowPressed(boolean upArrowPressed) {
		this.upArrowPressed = upArrowPressed;
	}

	public boolean isDownArrowPressed() {
		return downArrowPressed;
	}

	public void setDownArrowPressed(boolean downArrowPressed) {
		this.downArrowPressed = downArrowPressed;
	}

	public boolean isRightArrowPressed() {
		return rightArrowPressed;
	}

	public void setRightArrowPressed(boolean rightArrowPressed) {
		this.rightArrowPressed = rightArrowPressed;
	}

	public boolean isLeftArrowPressed() {
		return leftArrowPressed;
	}

	public void setLeftArrowPressed(boolean leftArrowPressed) {
		this.leftArrowPressed = leftArrowPressed;
	}

	
}
