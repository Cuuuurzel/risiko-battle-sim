package com.cuuuurzel.risiko_battle_sim;

import com.cuuuurzel.risiko_battle_sim.risiko.Battle;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class BattleResultActivity extends Activity {

	Battle b;
	
	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		setContentView( R.layout.battle_result );
		int atko = getIntent().getIntExtra( "atko", 1 );
		int defo = getIntent().getIntExtra( "defo", 1 );
		int atkr = getIntent().getIntExtra( "atkr", 1 );
		int defr = getIntent().getIntExtra( "defr", 1 );
		int turns = getIntent().getIntExtra( "turns", 0 );
		this.b = new Battle( atko, defo, atkr, defr, turns );
		this.b.fastFight();
		this.setResult();
	}
	
	private void setResult() {
		TextView txvStats = (TextView) findViewById( R.id.txvBattleStats );
		txvStats.setText( getStringStats() );
		TextView txvWinner = (TextView) findViewById( R.id.txvWinner );
		txvWinner.setText( getStringResult() );
	}
	
	private String getStringResult() {
		if ( b.attackerWon() ) {
			return getResources().getString( R.string.attacker_won );
		} else {
			return getResources().getString( R.string.defender_won );
		}
	}
	
	private String getStringStats() {
		String s1 = getResources().getString( R.string.the_battle_lasted );
		String sa = getResources().getString( R.string.the_attacker_used );
		String sd = getResources().getString( R.string.the_defender_used );
		String s2 = getResources().getString( R.string.which_has_lost );
		String sr, sta, std;		
		
		if ( b.getTurns() != 1 ) {
			sr = getResources().getString( R.string.rounds );
		} else {
			sr = getResources().getString( R.string.round );			
		}
		
		if ( b.getAtk() != 1 ) {
			sta = getResources().getString( R.string.troops );
		} else {
			sta = getResources().getString( R.string.troop );			
		}
		
		if ( b.getDef() != 1 ) {
			std = getResources().getString( R.string.troops );
		} else {
			std = getResources().getString( R.string.troop );			
		}
		
		String s = "";
		s += s1 + " " + b.getTurns()  + " " + sr + ".\n";
		s += "\n";
		
		s += sa + " " + b.getAtk()  + " " + sta + ", ";
		s += s2  + " " + b.getTotalAtkLosses();
		s += " ( " + b.getPercentageAtkLosses() + "% ).\n";
		
		s += sd  + " " + b.getDef()  + " " + std + ", ";
		s += s2  + " " + b.getTotalDefLosses();
		s += " ( " + b.getPercentageDefLosses() + "% ).\n";
		return s;		
	}
}
