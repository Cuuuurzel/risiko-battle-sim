package com.cuuuurzel.risiko_battle_sim;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setEditTextFilters();
	}

	public void showMyApps(View v) {
		try {
			startActivity(new Intent(Intent.ACTION_VIEW,
					Uri.parse("market://developer?id=Cuuuurzel")));
		} catch (ActivityNotFoundException anfe) {
			startActivity(new Intent(
					Intent.ACTION_VIEW,
					Uri.parse("http://play.google.com/store/apps/developer?id=Cuuuurzel")));
		}
	}

	public void share(View v) {
		String message = getResources().getString(R.string.share_msg);
		String link = getResources().getString(R.string.play_store_link);
		Intent share = new Intent(Intent.ACTION_SEND);
		share.putExtra(Intent.EXTRA_TEXT, message + "\n" + link);
		share.setType("text/plain");
		startActivity(Intent.createChooser(share,
				"Title of the dialog the system will open"));
	}
	
	public void showSimulationResult(View v) {
		EditText edtAtk = (EditText) findViewById( R.id.edtAtk );
		EditText edtDef = (EditText) findViewById( R.id.edtDef );
		int atk = Integer.parseInt( edtAtk.getText().toString() );
		int def = Integer.parseInt( edtDef.getText().toString() );		
		
		Intent intentMain = new Intent( MainActivity.this, SimulationActivity.class );
		intentMain.putExtra( "atk", atk );
		intentMain.putExtra( "def", def );
		MainActivity.this.startActivityForResult( intentMain, 1 );
	}	

	public void showFightResult(View v) {
		EditText edtAtk = (EditText) findViewById( R.id.edtAtk );
		EditText edtDef = (EditText) findViewById( R.id.edtDef );
		int atk = Integer.parseInt( edtAtk.getText().toString() );
		int def = Integer.parseInt( edtDef.getText().toString() );		
		
		Intent intentMain = new Intent( MainActivity.this, BattleResultActivity.class );
		intentMain.putExtra( "atko", atk );
		intentMain.putExtra( "defo", def );
		intentMain.putExtra( "atkr", atk );
		intentMain.putExtra( "defr", def );
		MainActivity.this.startActivityForResult( intentMain, 1 );
	}
	
	public void fightStepByStep( View v ) {
		EditText edtAtk = (EditText) findViewById( R.id.edtAtk );
		EditText edtDef = (EditText) findViewById( R.id.edtDef );
		int atk = Integer.parseInt( edtAtk.getText().toString() );
		int def = Integer.parseInt( edtDef.getText().toString() );		
		
		Intent intentMain = new Intent( MainActivity.this, StepByStepActivity.class );
		intentMain.putExtra( "atk", atk );
		intentMain.putExtra( "def", def );
		MainActivity.this.startActivityForResult( intentMain, 1 );		
	}
	
	private void setEditTextFilters() {
		InputFilter inf = new InputFilter() {
			public CharSequence filter(CharSequence source, int start, int end,
					Spanned dest, int dstart, int dend) {
				try {
					int input = Integer.parseInt(dest.toString()
							+ source.toString());
					if (input > 0)
						return null;
				} catch (NumberFormatException nfe) {
				}
				return "";
			}
		};
		InputFilter[] f = new InputFilter[] { inf };

		((EditText) findViewById(R.id.edtAtk)).setFilters(f);
		((EditText) findViewById(R.id.edtDef)).setFilters(f);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void updateText(int id, int i) {
		EditText a = (EditText) findViewById(id);
		Integer n;
		try {
			n = i + Integer.parseInt(a.getText().toString());
			if (n < 1) {
				n = 1;
			}
		} catch (NumberFormatException e) {
			n = 1;
		}
		a.setText(n.toString());
	}

	public void incAtk(View v) {
		updateText(R.id.edtAtk, +1);
	}

	public void decAtk(View v) {
		updateText(R.id.edtAtk, -1);
	}

	public void incDef(View v) {
		updateText(R.id.edtDef, +1);
	}

	public void decDef(View v) {
		updateText(R.id.edtDef, -1);
	}
}






