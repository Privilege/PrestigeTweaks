package com.privilege.prestigetweaks.app;

import com.privilege.prestigetweaks.app.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class SyhExtrasTab extends SyhTab implements OnClickListener {
     public SyhExtrasTab(Context context, Activity activity) {
		super(context, activity);
		this.name = "Extras";
	}

	@Override
	public View getCustomView(ViewGroup parent)
	{
 		 View v = LayoutInflater.from(mContext).inflate(R.layout.syh_extrastab, parent, false);
     	 
    	 final TextView tv = (TextView) v.findViewById(R.id.textViewAppVersion);
 		 try 
 		 {
 	    	final String appVersion = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0).versionName;
			tv.setText("App Version: " + appVersion);
 		 } 
 		 catch (NameNotFoundException e) 
 		 {
			tv.setText("App Version: Not found!");
 		 }
 		 
 		 
     	 final Button button = (Button) v.findViewById(R.id.CheckForUpdates);
 		 button.setOnClickListener(this);
 		 final Button button2 = (Button) v.findViewById(R.id.FlashKernel);
 		 button2.setOnClickListener(this);
 		 final ImageButton button3 = (ImageButton) v.findViewById(R.id.imageButtonLetter);
 		 button3.setOnClickListener(this);
     	 final Button button4 = (Button) v.findViewById(R.id.ResetSettings);
 		 button4.setOnClickListener(this);
  		 
    	 final TextView tv2 = (TextView) v.findViewById(R.id.textViewKernelVersion);
    	 tv2.setText("Kernel version: " + System.getProperty("os.version"));

    	 String s = "";
    	 s += "\n 커널 버전: " + System.getProperty("os.version");
    	 s += "\n ROM 버전: " + android.os.Build.VERSION.INCREMENTAL;
       	 s += "\n ROM API 레벨: " + android.os.Build.VERSION.SDK_INT;
       	 s += "\n ROM 코드 네임: " + android.os.Build.VERSION.CODENAME;
       	 s += "\n ROM 릴리즈 버전: " + android.os.Build.VERSION.RELEASE;
       	 s += "\n 하드웨어 시리얼 넘버: " + android.os.Build.SERIAL;
      	 s += "\n 라디오 버전: " + android.os.Build.getRadioVersion();
      	 s += "\n 디바이스명: " + android.os.Build.DEVICE;
    	 s += "\n 모델명: " + android.os.Build.MODEL;   
    	 tv2.setText(s);
    	 		 
    	 
 		 return v;
	}
	
	@Override
	public void onClick(View v) {
		switch(v.getId()) {
        case R.id.CheckForUpdates:
           	Toast toast1 = Toast.makeText(mContext, R.string.prestigemod, Toast.LENGTH_LONG);
        	toast1.show();  
            break;
        case R.id.FlashKernel:
           	Toast toast2 = Toast.makeText(mContext, R.string.lechestyle, Toast.LENGTH_LONG);
        	toast2.show();  
            break;
        case R.id.imageButtonLetter:
        	mContext.startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://bluepremium.co.kr/174")));
        	break;
        case R.id.ResetSettings:
        	AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        	builder.setMessage("기본값으로 초기화 하시겠습니까?")
        	       .setPositiveButton("네", new DialogInterface.OnClickListener() {
        	           public void onClick(DialogInterface dialog, int id) {
        	               // Handle Ok
        	           		Utils.executeRootCommandInThread("/res/prestigetweaks.sh delete default");
        	           		System.exit(0);
        	           }
        	       })
        	       .setNegativeButton("아니요", new DialogInterface.OnClickListener() {
        	           public void onClick(DialogInterface dialog, int id) {
        	               // Handle Cancel
        	           }
        	       })
        	       .setTitle("주의 메시지")
        	       .create();
        	builder.show();

        	break;
		}		
	}
}
