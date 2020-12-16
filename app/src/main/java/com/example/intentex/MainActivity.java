package com.example.intentex;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Context context;
    private EditText editTextId;
    private EditText editTextName;
    private TextView textViewResult;
    private Button buttonCancel,buttonOK;
    private Intent intent;
    private final int RequsetCode = 20;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        editTextId = (EditText) findViewById(R.id.editText_infoid);
        editTextName = (EditText) findViewById(R.id.editText_infoname);

        textViewResult = (TextView) findViewById(R.id.textView_result);
        textViewResult.setText("");

        buttonCancel = (Button) findViewById(R.id.button_infcancel);
        buttonOK = (Button) findViewById(R.id.button_ok);

        buttonCancel.setOnClickListener(new MyButton());
        buttonOK.setOnClickListener(new MyButton());





    }

    private class MyButton implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.button_infcancel:
                    editTextId.setText("");
                    editTextName.setText("");
                    textViewResult.setText("");
                    break;
                case R.id.button_ok:
                    if(editTextId.length() == 0 | editTextName.length() ==0){
                        Toast.makeText(context,"請輸入名字或ID",Toast.LENGTH_SHORT).show();
                    } else {
                        intent = new Intent(context,InformationActivity.class);
                        intent.putExtra("name",editTextName.getText().toString());
                        intent.putExtra("id",editTextId.getText().toString());
                        startActivityForResult(intent,RequsetCode);
                    }

                    break;

            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RequsetCode){
            textViewResult.setText("最喜歡的動物是：\n\t\t\t"+data.getStringExtra("animal")+"\n");
            textViewResult.append("喜歡的食物是：\n\t\t\t"+data.getStringExtra("food"));

        }
    }
}