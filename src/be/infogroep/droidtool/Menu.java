package be.infogroep.droidtool;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Menu extends Activity
{
   public void onCreate(Bundle icicle)
   {
      super.onCreate(icicle);
      setContentView(R.layout.menu);
      Button b = (Button) findViewById(R.id.btn_quick);
      b.setOnClickListener(new View.OnClickListener() {
         public void onClick(View arg0) {
         setResult(RESULT_OK);
         finish();
         } 
      });
   }
}

