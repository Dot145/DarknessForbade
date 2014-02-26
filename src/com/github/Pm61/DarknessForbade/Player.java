package com.github.Pm61.DarknessForbade;

public class Player extends Character{
	
	boolean inEncounter;
	public Player(){
		velocity = 200;
	}
	public void move(double angle) {
		changeDirection(angle);
	}
	
	public void stopMoving(double angle) {
		changeDirection(angle);
		
	}
}