package be.infogroep.droidtool;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import android.app.Activity;
//import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.app.AlertDialog;
import android.os.Bundle;
import android.content.Context;
import android.widget.EditText;
//import android.widget.Button;
import android.widget.Toast;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class DroidTool extends Activity implements OnClickListener {
	private Context c = this;
	private Activity a = this;
	private String token;
	private String debugger;
	private String pw;
	private String server;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		server = StorageInterface.Get("server", c);
		token = StorageInterface.Get("token", c);
		debugger = StorageInterface.Get("name", c);
		pw = StorageInterface.Get("pw", c);
		setContentView(R.layout.main);
		setDefaultServer(); //sets default server if it there is no saved value
		setupViews();
	}
	private void setupViews() {
		View scanButton = findViewById(R.id.scan_button); 
		scanButton.setOnClickListener(this); 

		View inputButton = findViewById(R.id.input_button); 
		inputButton.setOnClickListener(this);
		
		View quickButton = findViewById(R.id.quick_button); 
		quickButton.setOnClickListener(this);

		View aboutButton = findViewById(R.id.about_button); 
		aboutButton.setOnClickListener(this); 

		View exitButton = findViewById(R.id.exit_button); 
		exitButton.setOnClickListener(this);
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}

	
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_settings:  
			try {
				Intent i = new Intent(DroidTool.this, Settings.class);
				startActivity(i);
			}
			catch (Exception e){
				Toast.makeText(this, e.getMessage(), 800).show();
			}
			break;
		}
		return true;
	}

	@Override
	public void onClick(View v) {
		//Intent i = null;
		token = StorageInterface.Get("token", c);
		debugger = StorageInterface.Get("name", c);
		
		try {
			switch (v.getId())
			{
			case R.id.scan_button:
				if (URLReader.checkToken(token, c)) {
					IntentIntegrator.initiateScan(a);
				}
				break;
			case R.id.input_button:
				if (URLReader.checkToken(token, c)) {
					//Manual Input view here!
				}
				break;
			case R.id.quick_button:
				if (URLReader.checkToken(token, c)) {
					Intent i = new Intent(DroidTool.this, Quick.class);
					startActivity(i);
				}
				break;
			case R.id.exit_button:
				finish();
				break;
			}
		}
		catch (Exception e) {
			
			if (! Login.tryLogin(c)) {
			final AlertDialog login_popup = Login.makeLogin(c);
			login_popup.show();
			}

		}
	}
	
	private void setDefaultServer() {
		if (StorageInterface.Get("server", c) == ""){
			StorageInterface.Save("server", "http://infogroep.be:2007/", c);
			//StorageInterface.Save("server", "http://bennit.be:2007/", c);
		}
		//Toast.makeText(c, "TESTING", Toast.LENGTH_SHORT).show();
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case IntentIntegrator.REQUEST_CODE:
			if (resultCode != RESULT_CANCELED) {
				IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
				if (scanResult != null)
				{
					String upc = scanResult.getContents();
					URLReader.postScribble(debugger, token, upc, c);
				}
			}
			break;
		}
	}
}

