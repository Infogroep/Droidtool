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
import android.view.View;
import android.view.View.OnClickListener;

public class DroidTool extends Activity implements OnClickListener {
	private Context c = this;
	private Activity a = this;
	private String token;
	private String debugger;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		token = StorageInterface.Get("token", c);
		debugger = StorageInterface.Get("name", c);
		setContentView(R.layout.main);
		setupViews();
	}
	private void setupViews() {
		View scanButton = findViewById(R.id.scan_button); 
		scanButton.setOnClickListener(this); 

		View inputButton = findViewById(R.id.input_button); 
		inputButton.setOnClickListener(this);

		View aboutButton = findViewById(R.id.about_button); 
		aboutButton.setOnClickListener(this); 

		View exitButton = findViewById(R.id.exit_button); 
		exitButton.setOnClickListener(this);
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
				if (URLReader.checkToken(token)) {
					IntentIntegrator.initiateScan(a);
				}
				break;
			case R.id.input_button:
				if (URLReader.checkToken(token)) {
					//Manual Input view here!
				}
				break;
			case R.id.exit_button:
				finish();
				break;
			}
		}
		catch (Exception e) {
			
			final AlertDialog login_popup = Login.makeLogin(c);
			login_popup.show();

		}
	}
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case IntentIntegrator.REQUEST_CODE:
			if (resultCode != RESULT_CANCELED) {
				IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
				if (scanResult != null)
				{
					String upc = scanResult.getContents();
					URLReader.postScribble(debugger, token, upc);
				}
			}
			break;
		}
	}
}

