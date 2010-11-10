package be.infogroep.droidtool;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.app.AlertDialog;

public class Login {
	
	public static boolean tryLogin(final Context c){
		String name = StorageInterface.Get("name", c);
		String pw = StorageInterface.Get("pw", c);
		try {
			String token = URLReader.getToken(name, pw);
			StorageInterface.Save("token", token, c);
			return true;
			
		} catch (Exception e) {
			return false;						
		}
		
	}
	
	public static AlertDialog makeLogin(final Context c){
		
		LayoutInflater factory = LayoutInflater.from(c);
		final View textEntryView = factory.inflate(R.layout.login, null);

		final AlertDialog login_popup = new AlertDialog.Builder(c)
		.setTitle("Login")
		.setView(textEntryView)
		.create();

		login_popup.setButton("login", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				EditText et_uname = (EditText) login_popup.findViewById(R.id.txt_name);
				EditText et_pw = (EditText) login_popup.findViewById(R.id.txt_pw);
				String name = et_uname.getText().toString();
				String token;
				String pw = et_pw.getText().toString();
				String test = name + " " + pw;
				try {
					token = URLReader.getToken(name, pw);
					StorageInterface.Save("token", token, c);
					StorageInterface.Save("name", name, c);
					StorageInterface.Save("pw", pw, c);
					
				} catch (Exception e) {
					Toast.makeText(c, e.getMessage(), Toast.LENGTH_SHORT).show();						
				}

			}
		});

		login_popup.setButton2("cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				Toast.makeText(c, "CANCEL", Toast.LENGTH_SHORT).show();
			}
		});
		
		return login_popup;
		
	}
}
