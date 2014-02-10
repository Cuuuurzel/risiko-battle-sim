package com.cuuuurzel.risiko_battle_sim;

import com.cuuuurzel.risiko_battle_sim.risiko.Battle;
import com.cuuuurzel.risiko_battle_sim.risiko.Turn;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StepByStepActivity extends Activity {

	Battle b;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.step_by_step);
		int atk = getIntent().getIntExtra( "atk", 1 );
		int def = getIntent().getIntExtra( "def", 1 );
		this.b = new Battle( atk, def );
	}

	public void showResult( View v ) {
		Intent intentMain = new Intent( StepByStepActivity.this, BattleResultActivity.class );
		intentMain.putExtra( "atko", b.getAtk() );
		intentMain.putExtra( "defo", b.getDef() );
		intentMain.putExtra( "atkr", b.getAtk()-b.getTotalAtkLosses() );
		intentMain.putExtra( "defr", b.getDef()-b.getTotalDefLosses() );
		intentMain.putExtra( "turns", b.getTurns() );
		StepByStepActivity.this.startActivityForResult( intentMain, 1 );
	}	
	
	public void updateGui( View v ) {
		Turn t = this.b.step();
		this.setRounds();
		this.setTroops();
		this.setResults( t );
		this.setLosses( t );
		this.battleEnd();
	}
	
	public void battleEnd() {
		if ( b.battleEndend() ) {
			int l;
			String st, sl;
			String saw = getResources().getString( R.string.attacker_won );
			String sdw = getResources().getString( R.string.defender_won );
			
			TextView txvDetails = (TextView) findViewById( R.id.txvDetails );
			Button btnStep = (Button) findViewById( R.id.btnStep );
			btnStep.setEnabled( false );
			
			String msg;
			String cr = getResources().getString( R.string.click_result );
			if ( b.attackerWon() ) {
				msg = "\n" + saw;
				l = b.getAtk()-b.getTotalAtkLosses();
			} else {
				msg = "\n" + sdw;
				l = b.getDef()-b.getTotalDefLosses();
			}
			
			if ( l != 1 ) {
				st = getResources().getString( R.string.troops );
				sl = getResources().getString( R.string.lasted_plural );
			} else {
				st = getResources().getString( R.string.troop );
				sl = getResources().getString( R.string.lasted );			
			}
			msg += "\n" + l + " " + st + " " + sl;
			msg += "\n" + cr + ".";
			txvDetails.setText( msg );
		}
	}
	
	private void setLosses( Turn t ) {
		setLosses( R.id.txvAtkLosses, t.getAtkLosses() );
		setLosses( R.id.txvDefLosses, t.getDefLosses() );
	}
	
	private void setLosses( int txvId, Integer l ) {
		TextView txv = (TextView) findViewById( txvId );
		txv.setText( l.toString() );		
	}
	
	private void setResults( Turn t ) {
		setResults( R.id.txvAtkResult, t.getAtkResult() );
		setResults( R.id.txvDefResult, t.getDefResult()  );
	}
	
	private void setResults( int txvId, Integer[] r ) {
		TextView txv = (TextView) findViewById( txvId );
		String s = r[0].toString();
		try {
			s += ", " + r[1];
			s += ", " + r[2];
		} catch ( IndexOutOfBoundsException e ) {}
		txv.setText( s );		
	}
	
	private void setTroops() {
		setTroops( R.id.txvAtk, b.getAtk(), b.getTotalAtkLosses() );
		setTroops( R.id.txvDef, b.getDef(), b.getTotalDefLosses() );
	}
	
	private void setTroops( int txvId, int t, int l ) {
		String st;
		String oo = getResources().getString( R.string.out_of );
		if ( t-l != 1 ) {
			st = getResources().getString( R.string.troops );
		} else {
			st = getResources().getString( R.string.troop );			
		}
		
		TextView txv = (TextView) findViewById( txvId );
		String s = (t-l) + " " + st + " " + oo + " " + t + " ( " + (100-100*l/t) + "% )";
		txv.setText( s );
	}
	
	private void setRounds() {
		TextView txvRound = (TextView) findViewById( R.id.txvRound );
		String suffix;
		String rr = getResources().getString( R.string.round_result );
		switch ( b.getTurns() ) {
			case 1 :  { suffix = getResources().getString( R.string.st ); break; }
			case 2 :  { suffix = getResources().getString( R.string.nd ); break; }
			case 3 :  { suffix = getResources().getString( R.string.rd ); break; }
			default : { suffix = getResources().getString( R.string.th ); break; }
		}
		txvRound.setText( b.getTurns() + suffix + " " + rr );
	}
}
