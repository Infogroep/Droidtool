package be.infogroep.droidtool;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Quick extends Activity {

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
				try {
					if (URLReader.checkToken(token)) {
						scribble(getString(R.string.bar_quick1));
						Toast.makeText(c, R.string.bar_quick1, Toast.LENGTH_SHORT).show();
					}
				} catch (Exception e) {
					final AlertDialog login_popup = Login.makeLogin(c);
					login_popup.show();
				}	
			}

		}); 
		
		Button quick2 = (Button) findViewById(R.id.quick_button2); 
		quick2.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				try {
					if (URLReader.checkToken(token)) {
						scribble(getString(R.string.bar_quick2));
						Toast.makeText(c, R.string.bar_quick2, Toast.LENGTH_SHORT).show();
					}
				} catch (Exception e) {
					final AlertDialog login_popup = Login.makeLogin(c);
					login_popup.show();
				}	
			}

		});
		
		Button quick3 = (Button) findViewById(R.id.quick_button3); 
		quick3.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				try {
					if (URLReader.checkToken(token)) {
						scribble(getString(R.string.bar_quick3));
						Toast.makeText(c, R.string.bar_quick3, Toast.LENGTH_SHORT).show();
					}
				} catch (Exception e) {
					final AlertDialog login_popup = Login.makeLogin(c);
					login_popup.show();
				}	
			}

		});
		
		Button quick4 = (Button) findViewById(R.id.quick_button4); 
		quick4.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				try {
					if (URLReader.checkToken(token)) {
						scribble(getString(R.string.bar_quick4));
						Toast.makeText(c, R.string.bar_quick4, Toast.LENGTH_SHORT).show();
					}
				} catch (Exception e) {
					final AlertDialog login_popup = Login.makeLogin(c);
					login_popup.show();
				}	
			}

		});
		
		Button quick5 = (Button) findViewById(R.id.quick_button5); 
		quick5.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				try {
					if (URLReader.checkToken(token)) {
						scribble(getString(R.string.bar_quick5));
						Toast.makeText(c, R.string.bar_quick5, Toast.LENGTH_SHORT).show();
					}
				} catch (Exception e) {
					final AlertDialog login_popup = Login.makeLogin(c);
					login_popup.show();
				}	
			}

		});
		
		Button quick6 = (Button) findViewById(R.id.quick_button6); 
		quick6.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				try {
					if (URLReader.checkToken(token)) {
						scribble(getString(R.string.bar_quick6));
						Toast.makeText(c, R.string.bar_quick7, Toast.LENGTH_SHORT).show();
					}
				} catch (Exception e) {
					final AlertDialog login_popup = Login.makeLogin(c);
					login_popup.show();
				}	
			}

		});
		
		Button quick7 = (Button) findViewById(R.id.quick_button7); 
		quick7.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				try {
					if (URLReader.checkToken(token)) {
						scribble(getString(R.string.bar_quick7));
						Toast.makeText(c, R.string.bar_quick7, Toast.LENGTH_SHORT).show();
					}
				} catch (Exception e) {
					final AlertDialog login_popup = Login.makeLogin(c);
					login_popup.show();
				}	
			}

		});
		
		Button quick8 = (Button) findViewById(R.id.quick_button8); 
		quick8.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				try {
					if (URLReader.checkToken(token)) {
						scribble(getString(R.string.bar_quick8));
						Toast.makeText(c, R.string.bar_quick8, Toast.LENGTH_SHORT).show();
					}
				} catch (Exception e) {
					final AlertDialog login_popup = Login.makeLogin(c);
					login_popup.show();
				}	
			}

		});
		
	}
	
	protected void scribble(String barcode) {
					URLReader.postScribble(debugger, token, barcode, c);
	}
}
