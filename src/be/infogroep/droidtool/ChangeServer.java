package be.infogroep.droidtool;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.app.AlertDialog;

public class ChangeServer {
	
	public static boolean tryLogin(final Context c){
		String name = StorageInterface.Get("name", c);
		String pw = StorageInterface.Get("pw", c);
		try {
			String token = URLReader.getToken(name, pw, c);
			StorageInterface.Save("token", token, c);
			return true;
			
		} catch (Exception e) {
			return false;						
		}
		
	}
	
	public static AlertDialog makeServerPopup(final Context c){
		
		LayoutInflater factory = LayoutInflater.from(c);
		final View textEntryView = factory.inflate(R.layout.change_server, null);

		final AlertDialog server_popup = new AlertDialog.Builder(c)
		.setTitle("Change server")
		.setView(textEntryView)
		.create();

		server_popup.setButton("Set", new DialogInterface.OnClickListener() {

			public void onClick(DialogInterface dialog, int whichButton) {
				EditText et_server_address = (EditText) server_popup.findViewById(R.id.txt_server_address);
				String server = et_server_address.getText().toString();
				try {
					StorageInterface.Save("server", server, c);					
				} catch (Exception e) {
					Toast.makeText(c, e.getMessage(), Toast.LENGTH_SHORT).show();						
				}

			}
		});

		server_popup.setButton2("cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				Toast.makeText(c, "CANCEL", Toast.LENGTH_SHORT).show();
			}
		});
		
		return server_popup;
		
	}
}
