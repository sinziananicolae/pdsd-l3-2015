package ro.pub.cs.systems.pdsd.lab03.phonedialer;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class PhoneDialerActivity extends Activity {

	final protected int[] buttonIds = { R.id.Button0, R.id.Button1,
			R.id.Button2, R.id.Button3, R.id.Button4, R.id.Button5,
			R.id.Button6, R.id.Button7, R.id.Button8, R.id.Button9,
			R.id.ButtonStar, R.id.ButtonDiez };

	final protected int[] imageButtonIds = { R.id.imageButton1,
			R.id.imageButton2, R.id.imageButton3, R.id.imageButton4 };
	
	static final int CONTACTS_MANAGER_REQUEST_CODE = 99;

	protected MyButtonListener buttonListener = new MyButtonListener();
	protected MyImageButtonListener imageButtonListener = new MyImageButtonListener();

	protected class MyButtonListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			if (v instanceof Button) {
				EditText phoneNumberEditText = (EditText) findViewById(R.id.editText1);
				phoneNumberEditText.setText(phoneNumberEditText.getText()
						.toString() + ((Button) v).getText().toString());
			}
		}

	}

	protected class MyImageButtonListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			EditText phoneNumberEditText = (EditText) findViewById(R.id.editText1);
			String phoneNb = phoneNumberEditText.getText().toString();

			if (v instanceof ImageButton) {
				switch (v.getId()) {
				case R.id.imageButton1:
					Log.d("mytag", "am apasat back");

					if (phoneNb.length() > 0) {
						phoneNumberEditText.setText(phoneNb.substring(0,
								phoneNb.length() - 1));
					}
					break;
				case R.id.imageButton2:
					Log.d("mytag", "am apasat call");
					EditText phoneNumberEditText1 = (EditText) findViewById(R.id.editText1);
					if (phoneNb.length() > 0) {
						Intent intent = new Intent(Intent.ACTION_CALL);
						intent.setData(Uri.parse("tel:"
								+ phoneNumberEditText1.getText().toString()));
						startActivity(intent);
					}
					break;
				case R.id.imageButton3:
					Log.d("mytag", "am apasat hangup");
					finish();
					break;
				
				case R.id.imageButton4:
					Log.d("mytag", "am apasat contacts " + phoneNb);
					if (phoneNb.length() > 0) {
						  Intent intent = new Intent("ro.pub.cs.systems.pdsd.lab04.contactsmanager.intent.action.ContactsManagerActivity");
						  intent.putExtra("ro.pub.cs.systems.pdsd.lab04.contactsmanager.PHONE_NUMBER_KEY", phoneNb);
						  startActivityForResult(intent, CONTACTS_MANAGER_REQUEST_CODE);
						} else {
						  Toast.makeText(getApplication(), getResources().getString(R.string.phone_error), Toast.LENGTH_LONG).show();
						}
					break;
					
				}
			}
		}

	}

	@Override
	protected void onCreate(Bundle state) {
		super.onCreate(state);
		setContentView(R.layout.activity_phone_dialer);
		// BLOCK View to portrait: setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		for (int k = 0; k < buttonIds.length; k++) {
			Button button = (Button) findViewById(buttonIds[k]);
			button.setOnClickListener(buttonListener);
		}
		for (int k = 0; k < imageButtonIds.length; k++) {
			ImageButton imageButton = (ImageButton) findViewById(imageButtonIds[k]);
			imageButton.setOnClickListener(imageButtonListener);
		}
	}

}
