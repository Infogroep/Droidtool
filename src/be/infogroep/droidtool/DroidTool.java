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
	private String debugger = "tstrickx"; //TODO debugging purposes, moet gelezen worden van file samen met token!!
	

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //StorageInterface.Save(test, getApplicationContext());
        token = StorageInterface.Get(c);
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
            
            LayoutInflater factory = LayoutInflater.from(this);
            final View textEntryView = factory.inflate(R.layout.login, null);
            
            final AlertDialog login_popup = new AlertDialog.Builder(DroidTool.this)
            .setTitle("Login")
            .setView(textEntryView)
            .create();
            
            login_popup.setButton("login", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                	EditText et_uname = (EditText) login_popup.findViewById(R.id.txt_name);
                	EditText et_pw = (EditText) login_popup.findViewById(R.id.txt_pw);
                	String name = et_uname.getText().toString();
                	String pw = et_pw.getText().toString();
                	String test = name + " " + pw;
                	try {
						token = URLReader.getToken(name, pw);
						StorageInterface.Save(token, getApplicationContext());
					} catch (Exception e) {
						Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();						
					}
                	
                	Toast.makeText(getApplicationContext(), test, Toast.LENGTH_SHORT).show();
                }
            });
            
            login_popup.setButton2("cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                	Toast.makeText(getApplicationContext(), "CANCEL", Toast.LENGTH_SHORT).show();
                }
            });
          
            
            
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
					// Barcode hieronder is om te testen, kzit thuis, en geen blikjes, (Dit is cola)
					URLReader.postScribble(debugger, token, "5449000000996");
				}
    		}
    		break;
    	}
    }
}

