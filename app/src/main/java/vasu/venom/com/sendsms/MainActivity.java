package vasu.venom.com.sendsms;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText num, msg;
    Button btn1,btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num = findViewById(R.id.num);
        msg = findViewById(R.id.msg);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!msg.getText().toString().isEmpty() && !num.getText().toString().isEmpty()) {
                    String message = msg.getText().toString();
                    String number = num.getText().toString();

                    SmsManager sm = SmsManager.getDefault();

                    sm.sendTextMessage(number, null, message, null, null);

                    Toast.makeText(MainActivity.this, "Message send successfully...", Toast.LENGTH_SHORT).show();
                } else if (msg.getText().toString().isEmpty() && num.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Both are Empty...", Toast.LENGTH_SHORT).show();
                } else if (num.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Number is Empty...", Toast.LENGTH_SHORT).show();
                } else if (msg.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Message is Empty...", Toast.LENGTH_SHORT).show();
                }
            }

        });



        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!num.getText().toString().isEmpty()) {

                    String number = num.getText().toString();

                    if(ActivityCompat.checkSelfPermission(MainActivity.this,
                            Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }

                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:"+number));
                    startActivity(callIntent);

                    Toast.makeText(MainActivity.this, "Call dialed successfully...", Toast.LENGTH_SHORT).show();
                } else if (num.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Number is Empty...", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
