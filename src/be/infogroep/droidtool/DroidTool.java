package be.infogroep.droidtool;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.app.AlertDialog;
import android.os.Bundle;
import android.content.Context;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;

public class DroidTool extends Activity implements OnClickListener {
	private Context c = this;
	private Activity a = this;
	//private String test = "token";
	private String token;
	//Hier gaat hij op zijnen bek, must fix!
	

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
    	String token = "blort";
    	Intent i = null;
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
            //.setView(uname)
            .setView(textEntryView)
            .create();
            login_popup.setButton("login", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                	EditText et_uname = (EditText) login_popup.findViewById(R.id.txt_name);
                	//EditText et_pw = (EditText) findViewById(R.id.txt_pw);
                	String name = et_uname.getText().toString();
                	//String pw = et_pw.getText().toString();
                	//String test = name + " " + pw;
                	
                	Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT).show();
                }
            });
            
//            login_popup.setButton("cancel", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int whichButton) {
//
//                    /* User clicked Cancel so do some stuff */
//                }
//            });
            
            
            login_popup.show();
            
    	}
    }
}

