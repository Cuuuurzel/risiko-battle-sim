package com.cuuuurzel.risiko_battle_sim.risiko;

public class Simulation {

	int atk, def, times;
	float atkWinningPercentage, defWinningPercentage;
	float atkWinningPercentageWOL, defWinningPercentageWOL;
	int avgAtkWinningTurns, avgDefWinningTurns;
	int avgAtkWinningLosses, avgDefWinningLosses;
	
	public Simulation( int atk, int def ) {
		this.atk = atk;
		this.def = def;
		this.times = 1000;
		this.sim();
	}
	
	public float getAtkWinningWithoutLossesChange() {
		return atkWinningPercentageWOL;		
	}
	
	public float getDefWinningWithoutLossesChange() {
		return defWinningPercentageWOL;		
	}

	public float getAtkWinningChance() {
		return atkWinningPercentage;
	}
	
	public float getDefWinningChance() {
		return defWinningPercentage;
	}
	
	public int getAvgTurnsForAtkWin() {
		return avgAtkWinningTurns;
	}
	
	public int getAvgTurnsForDefWin() {
		return avgDefWinningTurns;
	}
	
	public int getAvgWinningAtkLosses() {
		return avgAtkWinningLosses;
	}
	
	public int getAvgWinningDefLosses() {
		return avgDefWinningLosses;
	}
	
	private void sim() {
		for ( int i=0; i<times; i++ ) {
			Battle b = new Battle( atk, def );
			b.fastFight();
			if ( b.attackerWon() ) {
				atkWinningPercentage ++;
				avgAtkWinningTurns += b.getTurns();
				avgAtkWinningLosses += b.getTotalAtkLosses();
				if ( b.getTotalAtkLosses() == 0 ) {
					atkWinningPercentageWOL ++;
				}
			}
			if ( b.defenderWon() ) { 
				defWinningPercentage++;
				avgDefWinningTurns += b.getTurns();
				avgDefWinningLosses += b.getTotalDefLosses();
				if ( b.getTotalDefLosses() == 0 ) {
					defWinningPercentageWOL ++;
				}
			}
		}		
		atkWinningPercentage = 100 * atkWinningPercentage / times;
		defWinningPercentage = 100 * defWinningPercentage / times;
		atkWinningPercentageWOL = 100 * atkWinningPercentageWOL / times;
		defWinningPercentageWOL = 100 * defWinningPercentageWOL / times;
		avgAtkWinningTurns = avgAtkWinningTurns / times;
		avgDefWinningTurns = avgDefWinningTurns / times;
		avgAtkWinningLosses = avgAtkWinningLosses / times;
		avgDefWinningLosses = avgDefWinningLosses / times;		
	}	
}
