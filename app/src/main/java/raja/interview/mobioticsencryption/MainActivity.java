package raja.interview.mobioticsencryption;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void Encrypt(View v) {

    Intent i=new Intent(this,Encryptanddecryptscreen.class);
    i.putExtra("Function","Encrypt");
    startActivity(i);
    }

    public void Decrypt(View view)
    {
        Intent i=new Intent(this,Encryptanddecryptscreen.class);
        i.putExtra("Function","Decrypt");
        startActivity(i);
    }
}
