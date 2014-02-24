package com.github.Pm61.DarknessForbade;
public abstract class Weapon extends Item{
	enum Rarity {COMMON, UNCOMMON, RARE, LEGENDARY};
	enum Element {WATER, FIRE, WIND, EARTH, SHADOW, LIGHT, ICE};
	enum Skill {DOUBLESTRIKE, LIFESTEAL, ACCURACY, POWER, HEALING, REFRESHING, MYSTERY, SAFETY, SILENCING, ROOTING, STUNNING, DURABILITY, CHICKEN};
	Rarity rarity;
	Element element;
	Skill skill;
	int minDmg, maxDmg, bonusDmg;
	double accuracy;
	public Weapon() {
		count = 1;
	}
	public int getMinDmg() {
		return minDmg;
	}
	public void setMinDmg(int minDmg) {
		this.minDmg = minDmg;
	}
	public int getMaxDmg() {
		return maxDmg;
	}
	public void setMaxDmg(int maxDmg) {
		this.maxDmg = maxDmg;
	}
	public int getBonusDmg() {
		return bonusDmg;
	}
	public void setBonusDmg(int bonusDmg) {
		this.bonusDmg = bonusDmg;
	}
}
