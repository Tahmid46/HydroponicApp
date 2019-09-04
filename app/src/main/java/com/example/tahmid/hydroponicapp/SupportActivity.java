package com.example.tahmid.hydroponicapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SupportActivity extends AppCompatActivity {

    private EditText mEditTextTo,mEditTextSubject,mEditTextMessage;
    private Button sendBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        mEditTextTo=findViewById(R.id.edit_text_to);
        mEditTextSubject=findViewById(R.id.edit_text_subject);
        mEditTextMessage=findViewById(R.id.edit_text_message);

        sendBtn=findViewById(R.id.button_send);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMail();
            }
        });
    }

    public void sendMail()
    {
        //String[] to=mEditTextTo.getText().toString();
        String to = mEditTextTo.getText().toString().trim();
        String [] recepient=to.split(",");
        String subject=mEditTextSubject.getText().toString().trim();
        String message=mEditTextMessage.getText().toString().trim();

        Intent intent=new Intent(Intent.ACTION_SEND);

        intent.putExtra(Intent.EXTRA_EMAIL,recepient);
        intent.putExtra(Intent.EXTRA_SUBJECT,subject);
        intent.putExtra(Intent.EXTRA_TEXT,message);

        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent,"Choose an email client"));
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);

    }
}
