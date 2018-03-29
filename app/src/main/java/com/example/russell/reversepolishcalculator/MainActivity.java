package com.example.russell.reversepolishcalculator;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    //define variables for the widgets
    private Button buttonNumber0, buttonNumber1, buttonNumber2,
        buttonNumber3, buttonNumber4, buttonNumber5, buttonNumber6,
        buttonNumber7, buttonNumber8, buttonNumber9, buttonAddition,
        buttonSubtraction, buttonMultiplication, buttonDivision,
        buttonDecimal, buttonDelete, buttonDrop, buttonEnter;

    private TextView labelStack, label4InStack, label3InStack,
        label2InStack, labelTopInStack, labelDisplay;

    //stack for reverse polish notation
    private Deque<String> theStack = new ArrayDeque<>();

    //define the shared preferences object
    private SharedPreferences savedValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get references to widgets
        buttonNumber0 = (Button) findViewById(R.id.button_number_0);
        buttonNumber1 = (Button) findViewById(R.id.button_number_1);
        buttonNumber2 = (Button) findViewById(R.id.button_number_2);
        buttonNumber3 = (Button) findViewById(R.id.button_number_3);
        buttonNumber4 = (Button) findViewById(R.id.button_number_4);
        buttonNumber5 = (Button) findViewById(R.id.button_number_5);
        buttonNumber6 = (Button) findViewById(R.id.button_number_6);
        buttonNumber7 = (Button) findViewById(R.id.button_number_7);
        buttonNumber8 = (Button) findViewById(R.id.button_number_8);
        buttonNumber9 = (Button) findViewById(R.id.button_number_9);

        buttonAddition = (Button) findViewById(R.id.button_addition);
        buttonSubtraction = (Button) findViewById(R.id.button_subtraction);
        buttonMultiplication = (Button) findViewById(R.id.button_multiplication);
        buttonDivision = (Button) findViewById(R.id.button_division);
        buttonDecimal = (Button) findViewById(R.id.button_decimal);
        buttonDelete =(Button) findViewById(R.id.button_delete);
        buttonDrop = (Button) findViewById(R.id.button_drop);
        buttonEnter = (Button) findViewById(R.id.button_enter);

        labelStack = (TextView) findViewById(R.id.label_stack);
        label4InStack = (TextView) findViewById(R.id.label_4_in_stack);
        label3InStack = (TextView) findViewById(R.id.label_3_in_stack);
        label2InStack = (TextView) findViewById(R.id.label_2_in_stack);
        labelTopInStack = (TextView) findViewById(R.id.label_top_in_stack);
        labelDisplay = (TextView) findViewById(R.id.label_display);

        //set the listeners
        buttonNumber0.setOnClickListener(buttonListener);
        buttonNumber1.setOnClickListener(buttonListener);
        buttonNumber2.setOnClickListener(buttonListener);
        buttonNumber3.setOnClickListener(buttonListener);
        buttonNumber4.setOnClickListener(buttonListener);
        buttonNumber5.setOnClickListener(buttonListener);
        buttonNumber6.setOnClickListener(buttonListener);
        buttonNumber7.setOnClickListener(buttonListener);
        buttonNumber8.setOnClickListener(buttonListener);
        buttonNumber9.setOnClickListener(buttonListener);

        buttonAddition.setOnClickListener(buttonListener);
        buttonSubtraction.setOnClickListener(buttonListener);
        buttonMultiplication.setOnClickListener(buttonListener);
        buttonDivision.setOnClickListener(buttonListener);
        buttonDecimal.setOnClickListener(buttonListener);
        buttonDelete.setOnClickListener(buttonListener);
        buttonDrop.setOnClickListener(buttonListener);
        buttonEnter.setOnClickListener(buttonListener);

        savedValues = getSharedPreferences("SavedVal", MODE_PRIVATE);

        theStack.clear();
    }

    @Override
    public void onResume() {
        super.onResume();
        labelDisplay.setText(savedValues.getString("labelDisplay",""));
        theStack.clear();
        for (Integer i = savedValues.getInt("stackSize",-1); i >= 0; i--){
            if(!savedValues.getString(i.toString(),"").equals("")) {
                theStack.push(savedValues.getString(i.toString(), ""));
            }
        }
        updateStackLabels();

    }

    @Override
    public void onPause() {
        //save instance variables then pause
        Editor editor = savedValues.edit();
        editor.putString("labelDisplay", labelDisplay.getText().toString());
        Integer i = 0;
        while(!theStack.isEmpty()){
            editor.putString(i.toString(),theStack.pop());
            i++;
        }
        editor.putInt("stackSize",i);
        editor.commit();

        super.onPause();
    }

    public void updateStackLabels(){
        Iterator<String> iter = theStack.iterator();
        if (iter.hasNext()) {
            labelTopInStack.setText(iter.next());
        }
        else{
            labelTopInStack.setText("");
        }
        if (iter.hasNext()) {
            label2InStack.setText(iter.next());
        }
        else{
            label2InStack.setText("");
        }
        if (iter.hasNext()) {
            label3InStack.setText(iter.next());
        }
        else{
            label3InStack.setText("");
        }
        if (iter.hasNext()) {
            label4InStack.setText(iter.next());
        }
        else{
            label4InStack.setText("");
        }


    }

    public void calculate(String s){
        Float a,b, numberAnswer;
        String stringAnswer;
        if(theStack.size() >= 2) {
            a = Float.parseFloat(theStack.pop());
            b = Float.parseFloat(theStack.pop());

            switch (s) {
                case "+":
                    numberAnswer = a + b;
                    stringAnswer = numberAnswer.toString();
                    theStack.push(stringAnswer);
                    break;
                case "-":
                    numberAnswer = a - b;
                    stringAnswer = numberAnswer.toString();
                    theStack.push(stringAnswer);
                    break;
                case "*":
                    numberAnswer = a * b;
                    stringAnswer = numberAnswer.toString();
                    theStack.push(stringAnswer);
                    break;
                case "/":
                    numberAnswer = a / b;
                    stringAnswer = numberAnswer.toString();
                    theStack.push(stringAnswer);
                    break;
            }

            updateStackLabels();
        }
    }

    private OnClickListener buttonListener = new OnClickListener(){
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button_number_0:
                    if (labelDisplay.length() <= 10) {
                        labelDisplay.append("0");
                        break;
                    } else {break;}
                case R.id.button_number_1:
                    if (labelDisplay.length() <= 10) {
                        labelDisplay.append("1");
                        break;
                    } else {break;}

                case R.id.button_number_2:
                    if (labelDisplay.length() <= 10) {
                        labelDisplay.append("2");
                        break;
                    } else {break;}
                case R.id.button_number_3:
                    if (labelDisplay.length() <= 10) {
                        labelDisplay.append("3");
                        break;
                    } else {break;}
                case R.id.button_number_4:
                    if (labelDisplay.length() <= 10) {
                        labelDisplay.append("4");
                        break;
                    } else {break;}
                case R.id.button_number_5:
                    if (labelDisplay.length() <= 10) {
                        labelDisplay.append("5");
                        break;
                    } else {break;}
                case R.id.button_number_6:
                    if (labelDisplay.length() <= 10) {
                        labelDisplay.append("6");
                        break;
                    } else {break;}
                case R.id.button_number_7:
                    if (labelDisplay.length() <= 10) {
                        labelDisplay.append("7");
                        break;
                    } else {break;}
                case R.id.button_number_8:
                    if (labelDisplay.length() <= 10) {
                        labelDisplay.append("8");
                        break;
                    } else {break;}
                case R.id.button_number_9:
                    if (labelDisplay.length() <= 10) {
                        labelDisplay.append("9");
                        break;
                    } else {break;}
                case R.id.button_addition:
                    calculate("+");
                    break;
                case R.id.button_subtraction:
                    calculate("-");
                    break;
                case R.id.button_multiplication:
                    calculate("*");
                    break;
                case R.id.button_division:
                    calculate("/");
                    break;
                case R.id.button_decimal:
                    String display = labelDisplay.getText().toString();
                    if(display.contains(".")){
                        break;
                    }
                    else{
                        labelDisplay.append(".");
                        break;
                    }
                case R.id.button_delete:
                    if (labelDisplay.length()>0) {
                        labelDisplay.setText(labelDisplay.getText().toString().substring(0, labelDisplay.length() - 1));
                        break;
                    }
                    else{break;}
                case R.id.button_drop:
                    if(!theStack.isEmpty()) {
                        theStack.pop();
                        updateStackLabels();
                        break;
                    }
                    else{
                        break;
                    }
                case R.id.button_enter:
                    if(labelDisplay.getText().toString().equals("") ||
                            labelDisplay.getText().toString().equals(".")) {
                        break;
                    }
                    else{
                        theStack.push(labelDisplay.getText().toString());
                        labelDisplay.setText("");
                        updateStackLabels();
                        break;
                    }
            }
        }
    };


}
