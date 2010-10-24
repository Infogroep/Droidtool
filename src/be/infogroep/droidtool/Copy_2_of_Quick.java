package be.infogroep.droidtool;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Copy_2_of_Quick extends Activity {

	private Context c = this;
	private String token;
	private String debugger;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.quick);
		token = StorageInterface.Get("token", c);
		debugger = StorageInterface.Get("name", c);
		
		Button quick1 = (Button) findViewById(R.id.quick_button1); 
		quick1.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				scribble(getString(R.string.bar_quick1));	
			}

		}); 

		View quick_button2 = findViewById(R.id.quick_button2); 
		quick_button2.setOnClickListener((OnClickListener) this);

		View quick_button3 = findViewById(R.id.quick_button3); 
		quick_button3.setOnClickListener((OnClickListener) this);

		View quick_button4 = findViewById(R.id.quick_button4); 
		quick_button4.setOnClickListener((OnClickListener) this); 

		View quick_button5 = findViewById(R.id.quick_button5); 
		quick_button5.setOnClickListener((OnClickListener) this);

		View quick_button6 = findViewById(R.id.quick_button6); 
		quick_button6.setOnClickListener((OnClickListener) this);

		View quick_button7 = findViewById(R.id.quick_button7); 
		quick_button7.setOnClickListener((OnClickListener) this); 

		View quick_button8 = findViewById(R.id.quick_button8); 
		quick_button8.setOnClickListener((OnClickListener) this);
	}
	public void onClick(View v) {
		try {
			switch (v.getId())
			{
			case R.id.quick_button1:
				if (URLReader.checkToken(token)) {
					scribble(getString(R.string.bar_quick1));
				}
				break;
			case R.id.quick_button2:
				if (URLReader.checkToken(token)) {
					scribble(getString(R.string.bar_quick2));
				}
				break;
			case R.id.quick_button3:
				if (URLReader.checkToken(token)) {
					scribble(getString(R.string.bar_quick3));
				}
				break;
			case R.id.quick_button4:
				if (URLReader.checkToken(token)) {
					scribble(getString(R.string.bar_quick4));
				}
				break;
			case R.id.quick_button5:
				if (URLReader.checkToken(token)) {
					scribble(getString(R.string.bar_quick5));
				}
				break;
			case R.id.quick_button6:
				if (URLReader.checkToken(token)) {
					scribble(getString(R.string.bar_quick6));
				}
				break;
			case R.id.quick_button7:
				if (URLReader.checkToken(token)) {
					scribble(getString(R.string.bar_quick7));
				}
				break;
			case R.id.quick_button8:
				if (URLReader.checkToken(token)) {
					scribble(getString(R.string.bar_quick8));
				}
				break;
			}

		}
		catch (Exception e) {

			final AlertDialog login_popup = Login.makeLogin(c);
			login_popup.show();

		}
	}
	
	protected void scribble(String barcode) {
					URLReader.postScribble(debugger, token, barcode, c);
	}
}
