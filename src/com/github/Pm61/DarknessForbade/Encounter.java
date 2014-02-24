package com.github.Pm61.DarknessForbade;
import java.util.Random;

public class Encounter {
	
	public Player ecplayer;
	public Hostile ecenemy;
	public Character currentTurn, winner;
	public int bonusDef;
	public boolean playerBlinded, playerSilenced, enemyBlinded, enemySilenced, playerRooted, enemyRooted, pHasNT_Tome, eHasNT_Tome, pHasNT_Attack, eHasNT_Attack, pHasNT_AttackDebuff, pHasNT_DebuffTome, eHasNT_AttackDebuff, eHasNT_TomeDebuff;
	Tome.Tomes pNT_Tome, eNT_Tome, pNT_DebuffTome, eNT_DebuffTome;
	Weapon.Skill pNT_Attack, eNT_Attack, pNT_DebuffAttack, eNT_DebuffAttack;

	//begins the Encounter
	public void beginEncounter(Player player, Hostile enemy){
		pHasNT_Tome = false;
		pHasNT_Attack = false;
		if(setTurn() == player){
			playerTurn();
		}else{
			enemyTurn();
		}

	}
	
	//called when new turn begins.
	public void enemyTurn() {
		//Undoes all Next-Turn modifying abilities
		if(pHasNT_Tome){
			undo_pNT_Tome();
			pHasNT_Tome = false;
		}
		if(pHasNT_Attack){
			undo_pNT_Attack();
			pHasNT_Attack = false;
		}
		if(pHasNT_AttackDebuff){
			undo_pNT_AttackDebuff();
			pHasNT_AttackDebuff = false;
		}
		if(pHasNT_DebuffTome){
			undo_pNT_TomeDebuff();
			pHasNT_DebuffTome = false;
		}
		
		if(ecenemy.getHealth() <= 0){
			endEncounter(ecplayer);
		}
		if(ecplayer.getHealth() <= 0){
			endEncounter(ecenemy);
		}
		currentTurn = ecenemy;
	}
	public void playerTurn() {
		
		//checks to see if Encounter is won by either party
		if(ecenemy.getHealth() <= 0){
			endEncounter(ecplayer);
		}
		if(ecplayer.getHealth() <= 0){
			endEncounter(ecenemy);
		}

		//Undoes all Next-Turn modifying abilities
		if(eHasNT_Tome){
			undo_eNT_Tome();
			eHasNT_Tome = false;
		}
		if(eHasNT_Attack){
			undo_eNT_Attack();
			eHasNT_Attack = false;
		}
		if(eHasNT_AttackDebuff){
			undo_eNT_AttackDebuff();
			eHasNT_AttackDebuff = false;
		}
		if(eHasNT_TomeDebuff){
			undo_eNT_TomeDebuff();
			eHasNT_TomeDebuff = false;
		}
		
		//checks if player has an Next-Turn abilities
		if(pHasNT_Tome){
			do_pNT_Tome();
		}
		if(pHasNT_Attack){
			do_pNT_Attack();
		}
		if(pHasNT_AttackDebuff){
			do_pNT_AttackDebuff();
		}
		if(pHasNT_DebuffTome){
			do_pNT_TomeDebuff();
		}
		
		//removes any bonus def the player had last enemy turn
		bonusDef = 0;
		
		currentTurn = ecplayer;

	}
	
	//ends the Encounter
	public void endEncounter(Character winner){
		ecplayer.setInEncounter(false);
		ecplayer.setAttackCasting(null);
		ecplayer.setTomeCasting(null);
		ecenemy.setInEncounter(false);
		ecenemy.setAttackCasting(null);
		ecenemy.setTomeCasting(null);
	}
	
	//deals with the current turn of the Encounter
	public Character getCurrentTurn() {
		return currentTurn;
	}
	public void setCurrentTurn(Character currentTurn) {
		this.currentTurn = currentTurn;
	}
	
	//called by Tome/Attack buttons, deals with casting and attacking. Animations should go here.
	public void playerCastSpell(){
		switch(ecplayer.getTomeCasting()){
			case BLINDING:
				pHasNT_DebuffTome = true;
				pNT_DebuffTome = ecplayer.getTomeCasting();
				break;
			case DODGING:
				pHasNT_Tome = true;
				pNT_Tome = ecplayer.getTomeCasting();
				break;
			case HEALING: //heals for 5% of health (+5% per level)
				ecplayer.setHealth((int)(ecplayer.getHealth() + ecplayer.getHealth()*(0.05*ecplayer.getIntelligence())));
				break;
			case RAGE:
				pHasNT_Tome = true;
				pNT_Tome = ecplayer.getTomeCasting();
				break;
			case SHIELDING:
				bonusDef = (int)(ecplayer.getDefense()*1.5);
			case SILENCING:
				pHasNT_DebuffTome = true;
				pNT_DebuffTome = ecplayer.getTomeCasting();
				break;
			case STUNNING:
				playerTurn();
				break;
			case DRAINING:
				pHasNT_DebuffTome = true;
				pNT_DebuffTome = ecplayer.getTomeCasting();
				break;
			case ESCAPE:
				pHasNT_Tome = true;
				pNT_Tome = ecplayer.getTomeCasting();
				break;
			case POWER:
				pHasNT_Tome = true;
				pNT_Tome = ecplayer.getTomeCasting();
				break;
			case REFLECTION:
				pHasNT_DebuffTome = true;
				pNT_DebuffTome = ecplayer.getTomeCasting();
				break;
			case RISK:
				double a = Math.random();
				if(a<0.5){
					ecplayer.setHealth((int) (ecplayer.getHealth()*0.5));
				}else{
					ecenemy.setHealth((int) (ecenemy.getHealth()*0.5));
				}
				break;
			case ROOTING:
				enemyRooted = true;
				break;
			case WEAKENING:
				pHasNT_DebuffTome = true;
				pNT_DebuffTome = ecplayer.getTomeCasting();
				break;

				
			//NOT USING BECAUSE HOW DOES
			//case TIME_MANIPULATING: 
			//	playerAbilityNextTurn = true;
			//	playerNextTurnTome = ecplayer.getTomeCasting();
			//	break;
		}
		ecplayer.setTomeCasting(null);
		enemyTurn();
	}
	public void playerBasicAttack(){
		switch(ecplayer.getAttackCasting()){
			case ACCURACY:
				ecenemy.setHealth(ecenemy.getHealth()-dmgAccuracy(ecplayer.equipped));
				break;
			case DAMAGE:
				ecenemy.setHealth(ecenemy.getHealth()-dmgDamage(ecplayer.equipped));
				break;
			case MIXED:
				ecenemy.setHealth(ecenemy.getHealth()-dmgMixed(ecplayer.equipped));
				break;
		}
		enemyTurn();
	}
	public void enemyCastSpell(){
		
	}
	public void enemyBasicAttack(){
		
	}
	
	//Returns the damage dealt by a basic attack given a weapon
	public int dmgDamage(Weapon weapon){
		Random rand = new Random();
		if((rand.nextInt(100)+1)>=ecplayer.getAccuracy()+25){ //-25% chance to succeed
			int damage = rand.nextInt(weapon.getMaxDmg() - weapon.getMinDmg());
			return (damage + ecplayer.getAttack() + (ecplayer.getAttack()/10)); //+10% damage
		}
		return 0;
	}
	public int dmgAccuracy(Weapon weapon){
		Random rand = new Random();
		if((rand.nextInt(100)+1)>=ecplayer.getAccuracy()-25){ //+25% chance to succeed
			int damage = rand.nextInt(weapon.getMaxDmg() - weapon.getMinDmg());
			return (damage + ecplayer.getAttack() - (ecplayer.getAttack()/10)); //-10% damage
		}
		return 0;
		
	}
	public int dmgMixed(Weapon weapon){
		Random rand = new Random();
		if((rand.nextInt(100)+1)>=ecplayer.getAccuracy()){
			int damage = rand.nextInt(weapon.getMaxDmg() - weapon.getMinDmg());
			return (damage + ecplayer.getAttack());
		}
		return 0;
		
	}
	
	//determines the first turn at beginning of encounter
	public Character setTurn() {
		Random rand = new Random();
		Character currentTurn;
		if(ecplayer.hasFirstStrike() == false){
			currentTurn = ecenemy;
		}else{
			currentTurn = ecplayer;
		}
		if(ecplayer.hasFirstStrike() && ecenemy.hasFirstStrike()){
			if(rand.nextInt(1) == 1){
				currentTurn = ecenemy;
			}else{
				currentTurn = ecplayer;
			}
		}
		return currentTurn;
	}
	
	
	
	
	//~~PLAYER NEXT TURN AFFECTS~~
	
	//called at beginning of turn when player attacked with a next-turn weapon skill last turn
	private void do_pNT_Attack() {
		
		
	}
	//restores all next-turn attack modifiers
	private void undo_pNT_Attack(){
		
	}
	
	//called at beginning of turn when player casted a next-turn tome last turn
	private void do_pNT_Tome() {
		switch(pNT_Tome){
			case DODGING:
				ecplayer.setAccuracy((int)(ecplayer.getAccuracy()*1.75));
				break;
			case RAGE:
				ecplayer.setAttack((int)(ecplayer.getAttack()*1.5));
				break;
			}
	}
	//restores all next-turn tome modifiers
	private void undo_pNT_Tome() {
		switch(pNT_Tome){
			case DODGING:
				ecplayer.setAccuracy((int)(ecplayer.getAccuracy()/1.75));
				break;
			case RAGE:
				ecplayer.setAttack((int)(ecplayer.getAttack()/1.5));
				break;
			}
		
	}

	//called at beginning of turn when player casted a next-turn tome debuff last turn
	private void do_pNT_TomeDebuff(){
		switch(pNT_DebuffTome){
			case SILENCING:
				enemySilenced = true;
				break;
			case BLINDING:
				enemyBlinded = true;
				break;
			case WEAKENING:
				ecenemy.setAttack((int) (ecenemy.getAttack()*0.5));
		}
	}
	//restores all next-turn tome debuffs
	private void undo_pNT_TomeDebuff(){
		switch(pNT_DebuffTome){
			case SILENCING:
				enemySilenced = false;
				break;
			case BLINDING:
				enemyBlinded = false;
				break;
		}
	}
	
	//called at beginning of turn when player casted a next-turn attack debuff last turn
	private void do_pNT_AttackDebuff(){
		
	}
	//restores all next-turn attack debuffs
	private void undo_pNT_AttackDebuff(){
		
	}
	
	
	//~~ENEMY NEXT TURN EFFECTS~~
	
	//called at beginning of turn when enemy attacked with a next-turn weapon skill last turn
	private void do_eNT_Attack() {
			
			
	}
	//restores all next-turn attack modifiers
	private void undo_eNT_Attack(){
			
	}
		
	//called at beginning of turn when enemy casted a next-turn tome last turn
	private void do_eNT_Tome() {
		switch(eNT_Tome){
			case DODGING:
				ecenemy.setAccuracy((int)(ecenemy.getAccuracy()*1.75));
				break;
			case RAGE:
				ecenemy.setAttack((int)(ecenemy.getAttack()*1.5));
				break;
			}
		
	}
	//restores all next-turn tome modifiers
	private void undo_eNT_Tome() {
		switch(eNT_Tome){
			case DODGING:
				ecenemy.setAccuracy((int)(ecenemy.getAccuracy()/1.75));
				break;
			case RAGE:
				ecenemy.setAttack((int)(ecenemy.getAttack()/1.5));
				break;
			}
		
	}
	
	//called at beginning of turn when enemy casted a next-turn tome debuff last turn
	private void do_eNT_TomeDebuff(){
		switch(eNT_DebuffTome){
			case SILENCING:
				playerSilenced = true;
				break;
			case BLINDING:
				playerBlinded = true;
				break;
		}
	}
	//restores all next-turn tome debuffs
	private void undo_eNT_TomeDebuff(){
		switch(eNT_DebuffTome){
			case SILENCING:
				playerSilenced = false;
				break;
			case BLINDING:
				playerBlinded = false;
				break;
		}
	}
	
	//called at beginning of turn when enemy casted a next-turn attack debuff last turn
	private void do_eNT_AttackDebuff(){
		
	}
	//restores all next-turn attack debuffs
	private void undo_eNT_AttackDebuff(){
		
	}	
}