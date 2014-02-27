package com.github.Pm61.DarknessForbade;

import java.util.ArrayList;

import com.github.Pm61.DarknessForbade.Tome.Tomes;
import com.github.Pm61.DarknessForbade.WeaponButton.Attacks;

public abstract class Character{

	ArrayList<Item> inventory;
	int invSlots, attack, defense, accuracy, agility, intelligence, crafting, health;

	boolean inEncounter, firstStrike, torch;
	double xvelocity, yvelocity, speed, direction, x, y, maxVelocity;
	String imageLocation;
	Weapon equipped;
	Tomes tomes_casting;
	Attacks attack_casting;
	
	//The spell that a user is currently casting.
	public Tomes getTomeCasting() {
		return tomes_casting;
	}
	
	public void setTomeCasting(Tomes tomes_casting) {
		this.tomes_casting = tomes_casting;
	}
	
	//The attack that a user is currently casting.
	public Attacks getAttackCasting(){
		return attack_casting;
	}
	public void setAttackCasting(Attacks attack_casting) {
		this.attack_casting = attack_casting;
	}
	
	//Character accuracy (1-100%)
	public int getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(int accuracy) {
		this.accuracy = accuracy;
	}
	
	//Character stats
	public int getAttack() {
		return attack;
	}
	public void setAttack(int attack) {
		this.attack = attack;
	}
	public int getDefense() {
		return defense;
	}
	public void setDefense(int defense) {
		this.defense = defense;
	}
	public int getAgility() {
		return agility;
	}
	public void setAgility(int agility) {
		this.agility = agility;
	}
	public int getIntelligence() {
		return intelligence;
	}
	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}
	public int getTomebuilding() {
		return crafting;
	}
	public void setTomebuilding(int tomebuilding) {
		this.crafting = tomebuilding;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	
	//whether or not character is in an encounter
	public boolean isInEncounter() {
		return inEncounter;
	}
	public void setInEncounter(boolean inEncounter) {
		this.inEncounter = inEncounter;
	}
	
	//direction character is moving
	public double getDirection() {
		return direction;
	}
	public void setDirection(double direction) {
		this.direction = direction;
	}
	
	//weapon character currently has equipped
	public Weapon getEquipped() {
		return equipped;
	}
	public void setEquipped(Weapon equipped) {

		this.equipped = equipped;
	}

	//whether or not character has first strike
	public boolean hasFirstStrike() {
		return firstStrike;
	}
	public void setFirstStrike(boolean firstStrike) {
		this.firstStrike = firstStrike;
	}
	
	//character inventory contents
	public ArrayList<Item> getInventory() {
		return inventory;
	}
	public void setInventory(ArrayList<Item> inventory) {
		this.inventory = inventory;
	}
	
	//number of inventory slots a character can have
	public int getInvSlots() {
		return invSlots;
	}
	public void setInvSlots(int invSlots) {
		this.invSlots = invSlots;
	}
	
	//Velocity of a character (pixels per second)
	public double getXvelocity() {
		return xvelocity;
	}

	public void setXvelocity(double xvelocity) {
		this.xvelocity = xvelocity;
	}

	public double getYvelocity() {
		return yvelocity;
	}

	public void setYvelocity(double yvelocity) {
		this.yvelocity = yvelocity;
	}

//	public void changeVelocity(double dt){
//		if(xVelocity > maxVelocity)
//			xVelocity = maxVelocity;
//		if(yVelocity > maxVelocity) 
//			yVelocity = maxVelocity;
//		xVelocity = acceleration*dt;
//	}
	
	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	//changes the direction of a character. angle in radians
//	public void changeDirection(double angle) { 
//		if(angle < 0 || angle > (2*Math.PI))
//			angle %= 2*Math.PI;
//		direction = angle;
//	}
//	
//	//acceleration of a character
//	public double getAcceleration() {
//		return acceleration;
//	}
//	public void setAcceleration(double acceleration) {
//		this.acceleration = acceleration;
//	}
	
	//position of a character on screen.
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}

	public boolean isTorch() {
		return torch;
	}

	public void setTorch(boolean torch) {
		this.torch = torch;
	}
	
}