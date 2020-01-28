package raja.interview.mobioticsencryption;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Encryptanddecryptscreen extends AppCompatActivity {

    EditText stringToCode;
    Button functionButton;
    TextView labelToDisplay;
    Intent intent;
    String Sample="",encryptedString="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encryptanddecryptscreen);
        stringToCode=findViewById(R.id.stringtocode);
        functionButton=findViewById(R.id.functionButton);
        labelToDisplay=findViewById(R.id.labeltodisplay);
        functionButton.setEnabled(false);

        stringToCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                labelToDisplay.setText("");
              /* if(count > 0) {

                }
                else {
                    functionButton.setBackgroundTintList(getApplication().getResources().getColorStateList(R.color.greyedColor));
                    functionButton.setEnabled(false);
                }*/
                if(s.length() > 0) {
                    functionButton.setEnabled(true);
                    functionButton.setBackgroundTintList(getApplication().getResources().getColorStateList(R.color.colorPrimaryDark));
                }
                else {
                    functionButton.setBackgroundTintList(getApplication().getResources().getColorStateList(R.color.greyedColor));
                    functionButton.setEnabled(false);
                }
            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        intent=getIntent();
        //functionButton.setText(intent.getStringExtra("Function"));
    }


    public void functionButtonClick(View view)
    {
        encryptedString="";
        Sample=stringToCode.getText().toString();
        if(intent.getStringExtra("Function").equals("Encrypt"))
        {
            EncryptTheCode();
        }
        else if(intent.getStringExtra("Function").equals("Decrypt"))
        {
            DecryptTheCode();
        }
    }
    public void EncryptTheCode()
    {

        try {
            char previousChar=Sample.toCharArray()[0];
            int totalNumber=1;
            if(Sample.length()<=1) {
                encryptedString = previousChar + "" + totalNumber + "";
            }
            else {
                for (int i = 1; i < Sample.length(); i++) {

                    if (previousChar == Sample.charAt(i)) {
                        totalNumber++;
                    } else {
                        encryptedString = encryptedString + previousChar + totalNumber;
                        totalNumber = 1;
                    }
                    if (i == Sample.length() - 1) {
                        previousChar = Sample.charAt(i);
                        encryptedString = encryptedString + previousChar + totalNumber;
                    }
                    previousChar = Sample.charAt(i);
                }
            }
            labelToDisplay.setText(encryptedString);
        } catch (Exception e) {
            Toast.makeText(this, "Corrupted String", Toast.LENGTH_SHORT).show();
        }
    }


    public void DecryptTheCode()
    {

        try {
            char previousChar=Sample.toCharArray()[0];
            boolean isNextDigit=false;
            int totalNumber=0;

            for(int i=1;i<Sample.length();i++)
            {
                if(Character.isDigit(Sample.charAt(i))  )
                {
                    if(!isNextDigit)
                    {
                        totalNumber=Integer.parseInt("" + Sample.charAt(i));
                    }
                    else {

                        String TOT=""+totalNumber+ Sample.charAt(i);
                       int temp =Integer.parseInt(TOT)-totalNumber;
                        totalNumber=temp;
                    }
                    for(int j=0;j<totalNumber;j++)
                    {
                        encryptedString=encryptedString+previousChar;
                    }
                    isNextDigit=true;
                    //Toast.makeText(this, ""+encryptedString, Toast.LENGTH_SHORT).show();
                }
                else if(Character.isLetter(Sample.charAt(i)) || Character.isSpaceChar(Sample.charAt(i)))
                {
                    isNextDigit=false;
                    previousChar=Sample.charAt(i);
                }
                else
                {
                    isNextDigit=false;
                    previousChar=Sample.charAt(i);
                    encryptedString=encryptedString+Sample.charAt(i);
                }
            }
            labelToDisplay.setText(encryptedString);
        } catch (Exception e) {
            Toast.makeText(this, "Corrupted String unable to Decrypt", Toast.LENGTH_SHORT).show();
        }
    }


}
