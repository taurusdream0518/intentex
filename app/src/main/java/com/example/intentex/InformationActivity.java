package com.example.intentex;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;

public class InformationActivity extends AppCompatActivity {

    private InformationActivity context;
    private EditText editTextName;
    private EditText editTextId;
    private RadioGroup radioGroupAnimal;
    private CheckBox cheakBoxRice,cheakBoxRiceDumpling,cheakBoxSteak,cheakBoxChickSteak;
    private boolean koalaflag,rabbitflag,squirrelflag,riceflag,ricedumplingflag,steakflag,chickstealflag;
    private Button buttonCancel,buttonOK;
    private String animal;
    private final int ResultCode=20;
    private String food;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        context = this;

        editTextName = (EditText) findViewById(R.id.editText_infoname);
        editTextId = (EditText) findViewById(R.id.editText_infoid);

        intent = getIntent();
        editTextId.setText(intent.getStringExtra("id"));

        editTextName.setText(intent.getStringExtra("name"));


        radioGroupAnimal = (RadioGroup) findViewById(R.id.radioGroup_animal);

        cheakBoxRice = (CheckBox) findViewById(R.id.checkBox_rice);
        cheakBoxRiceDumpling = (CheckBox) findViewById(R.id.checkBox_ricedumpling);
        cheakBoxSteak = (CheckBox) findViewById(R.id.checkBox_steak);
        cheakBoxChickSteak = (CheckBox) findViewById(R.id.checkBox_chickensteak);

        koalaflag = false;
        rabbitflag = false;
        squirrelflag = false;

        riceflag = false;
        ricedumplingflag = false;
        steakflag = false;
        chickstealflag = false;

        radioGroupAnimal.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButton_koala:
                        koalaflag = true;
                        rabbitflag = false;
                        squirrelflag = false;
                        break;
                    case R.id.radioButton_rabbit:
                        koalaflag = false;
                        rabbitflag = true;
                        squirrelflag = false;
                        break;
                    case R.id.radioButton_squirrel:
                        koalaflag = false;
                        rabbitflag = false;
                        squirrelflag = true;
                        break;
                }
            }
        });

        cheakBoxRice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                riceflag = isChecked;
            }
        });
        cheakBoxRiceDumpling.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ricedumplingflag = isChecked;
            }
        });
        cheakBoxSteak.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                steakflag = isChecked;
            }
        });
        cheakBoxChickSteak.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                chickstealflag = isChecked;
            }
        });

        buttonCancel = (Button) findViewById(R.id.button_infcancel);
        buttonOK = (Button) findViewById(R.id.button_infok);

        buttonCancel.setOnClickListener(new MyButton());
        buttonOK.setOnClickListener(new MyButton());

        animal = "";


    }

    private class MyButton implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.button_infcancel:
                    radioGroupAnimal.clearCheck();
                    koalaflag = false;
                    rabbitflag = false;
                    squirrelflag = false;

                    cheakBoxRice.setChecked(false);
                    cheakBoxRiceDumpling.setChecked(false);
                    cheakBoxSteak.setChecked(false);
                    cheakBoxChickSteak.setChecked(false);

                    break;
                case R.id.button_infok:
                    animal = "";
                    if(koalaflag){
                        animal += "無尾熊";
                    } else if(rabbitflag) {
                        animal +="兔子";
                    } else if (squirrelflag) {
                        animal += "松鼠";
                    }

                    food = "";
                    if(riceflag){
                        food += "白米飯 ";
                    }
                    if(ricedumplingflag){
                        food += "肉粽 ";
                    }
                    if(steakflag){
                        food += "牛排 ";
                    }
                    if(chickstealflag){
                        food += "雞排 ";
                    }


                    intent = new Intent();
                    intent.putExtra("animal",animal);
                    intent.putExtra("food",food);
                    setResult(ResultCode,intent);

                    finish();


                    break;
            }

        }
    }
}