package com.cuuuurzel.risiko_battle_sim.risiko;

public class Battle {

	int atk, def;
	int turns;
	int totalAtkLosses, totalDefLosses;
	Turn lastTurnResult;
	Turn finalTurnResult;

	public Battle( int atko, int defo, int atkr, int defr, int t ) {
		this( atko, defo );
		this.totalAtkLosses = atko - atkr;
		this.totalDefLosses = defo - defr;
		this.turns = t;
	}
	
	public Battle( int atk, int def ) {
		this.atk = atk;
		this.def = def;
		this.turns = 0;
	}
	
	public int getAtk() {
		return this.atk;
	}
	
	public int getDef() {
		return this.def;
	}

	public boolean battleEndend() {
		return lastTurnResult.battleEndend();
	}
	
	public boolean attackerWon() {
		return lastTurnResult.attackerWon();
	}
	
	public boolean defenderWon() {
		return lastTurnResult.defenderWon();
	}
	
	public int getTurns() {
		return turns;
	}
	
	public int getTotalAtkLosses() {
		return totalAtkLosses;
	} 
	
	public int getTotalDefLosses() {
		return totalDefLosses;
	} 
	
	public float getPercentageAtkLosses() {
		return 100 * totalAtkLosses / atk;
	} 
	
	public float getPercentageDefLosses() {
		return 100 * totalDefLosses / def;
	} 
	
	public Turn step() {
		this.turns ++;
		Turn t = new Turn( this.atk-this.totalAtkLosses, 
						   this.def-this.totalDefLosses );
		this.totalAtkLosses += t.getAtkLosses();
		this.totalDefLosses += t.getDefLosses();
		if ( this.atk==this.totalAtkLosses || this.def==this.totalDefLosses ) {
			this.finalTurnResult = t;
		}
		lastTurnResult = t;
		return t;
	}
	
	public void fastFight() {
		while ( !this.step().battleEndend() ) {}
	}
}
