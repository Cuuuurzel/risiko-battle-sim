package com.cuuuurzel.risiko_battle_sim.risiko;

import java.util.Arrays;
import java.util.Collections;

public class Turn {

	int atk, def;
	int atkLosses, defLosses;
	Integer[] atkResult, defResult;

	public Turn( int atk, int def ) {
		this.atk = atk;
		this.def = def;
		this.calcLosses();
	}
	
	public boolean battleEndend() {
		return ( atk==atkLosses || def==defLosses );
	}

	public boolean draw() {
		return ( atk==atkLosses && def==defLosses );		
	}
	
	public boolean attackerWon() {
		return ( !draw() && def==defLosses );
	}
	
	public boolean defenderWon() {
		return ( !draw() && atk==atkLosses );
	}
	
	public Integer[] getAtkResult() {
		return this.atkResult; 
	}
	
	public Integer[] getDefResult() {
		return this.defResult; 
	}
	
	public int getAtkLosses() {
		return atkLosses;
	}
	
	public int getDefLosses() {
		return defLosses;
	}
	
	private int doRolls() {
		this.atkResult = roll( atk );
		this.defResult = roll( def );
		Arrays.sort( this.atkResult, Collections.reverseOrder() );
		Arrays.sort( this.defResult, Collections.reverseOrder() );
		return Math.min( this.atkResult.length, this.defResult.length );
	}
	
	private void calcLosses() {
		int launches = this.doRolls(); 
		this.atkLosses = 0;
		this.defLosses = 0;
		
		for ( int i=0; i<launches; i++ ) {
			if ( this.atkResult[i] > this.defResult[i] ) {
				this.defLosses ++;
			} else {
				this.atkLosses ++;
			}
		}
	}
	
	private int roll() {
		return 1 + (int)Math.floor( Math.random()*6 );
	}
	
	private Integer[] roll( int troops ) {
		Integer[] rolls = new Integer[ Math.min(troops, 3) ];
		for ( int i=0; i<Math.min(troops, 3); i++ ) {
			rolls[i] = roll();
		}
		return rolls;
	}
}
