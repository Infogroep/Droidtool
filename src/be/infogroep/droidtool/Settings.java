package be.infogroep.droidtool;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class Settings extends Activity {
	
	private Context c = this;
	private String token;
	private String debugger;
	private String server;

	private void setupViews(Activity a) {
		TextView et_server = (TextView) a.findViewById(R.id.current_server);
		TextView et_uname = (TextView) a.findViewById(R.id.current_user);
		TextView et_disc = (TextView) a.findViewById(R.id.settings_disclamer);
		String disc = "Disclaimer: \n" +
				"currently the values do not change instantaneously. " +
				"You will have to reopen the window to see the updated values. \n" +
				"We apologies for the inconvenience";
		et_server.setText("current server: " + server);
		et_uname.setText("current user: " + debugger);
		et_disc.setText(disc);
	}
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		token = StorageInterface.Get("token", c);
		debugger = StorageInterface.Get("name", c);
		server = StorageInterface.Get("server", c);
		
		setupViews(this);
		
		
		
		TableRow change_server = (TableRow) findViewById(R.id.change_server_row);
		
		change_server.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				final AlertDialog server_popup = ChangeServer.makeServerPopup(c);
				server_popup.show();
				//Toast.makeText(c, "TESTING", Toast.LENGTH_SHORT).show();
			}
		});
		
		TableRow change_user = (TableRow) findViewById(R.id.change_user_row); 
		change_user.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				final AlertDialog login_popup = Login.makeLogin(c);
				login_popup.show();
				//Toast.makeText(c, "TESTING", Toast.LENGTH_SHORT).show();	
			}

		});
			
	}
	
	protected void scribble(String barcode) {
					URLReader.postScribble(debugger, token, barcode, c);
	}
}
