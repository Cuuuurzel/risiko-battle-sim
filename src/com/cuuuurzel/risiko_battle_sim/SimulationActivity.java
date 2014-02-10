package com.cuuuurzel.risiko_battle_sim;

import com.cuuuurzel.risiko_battle_sim.risiko.Simulation;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class SimulationActivity extends Activity {

	Simulation s;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simulation);
		int atk = getIntent().getIntExtra( "atk", 1 );
		int def = getIntent().getIntExtra( "def", 1 );
		s = new Simulation( atk, def );
		this.updateGui();
	}
	
	public void updateGui() {
		String msg = "";
		msg += getMsg( getResources().getString( R.string.attacker ), 
				       s.getAtkWinningChance(),
				       s.getAtkWinningWithoutLossesChange(), 
				       s.getAvgTurnsForAtkWin(), 
					   s.getAvgWinningAtkLosses() );
		msg += "\n\n";
		msg += getMsg( getResources().getString( R.string.defender ),
					   s.getDefWinningChance(),
					   s.getDefWinningWithoutLossesChange(),
				       s.getAvgTurnsForDefWin(), 
				       s.getAvgWinningDefLosses() );
		TextView txvResult = (TextView) findViewById( R.id.txvResult );
		txvResult.setText( msg );
	}

	private String getMsg( String p, float c, float cll, int t, int l ) {
		String msg = "";
		String s1 = getResources().getString( R.string.chance );
		String s2 = getResources().getString( R.string.without_losses );
		String s3 = getResources().getString( R.string.avg_rounds );
		String s4 = getResources().getString( R.string.avg_losses );
		
		msg += "-- " + p + " --\n";
		msg += s1 + " : " + c + "%\n";
		msg += "\" \" \" " + s2 + " : " + cll + "%\n";
		msg += s3 + " : " + t + "\n";
		msg += s4 + " : " + l;
		return msg;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.simulation, menu);
		return true;
	}

}
