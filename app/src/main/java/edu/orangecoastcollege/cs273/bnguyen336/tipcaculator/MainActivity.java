package edu.orangecoastcollege.cs273.bnguyen336.tipcaculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextClock;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //Associate the controller with needed views
    private EditText amountEditText;
    private TextView amountTextView;
    private TextView tipTextView;
    private TextView percentTextView;
    private TextView totalTextView;
    private SeekBar percentSeekBar;
    private double amount;
    private double tipPercent;

    //Associate the controller with the needed model
    RestaurantBill currentBill = new RestaurantBill();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Connect the controller with the widgets in our app
        amountEditText = (EditText) findViewById(R.id.amountEditText);
        amountTextView = (TextView) findViewById(R.id.amountTextView);
        tipTextView = (TextView) findViewById(R.id.tipTextView);
        percentTextView = (TextView) findViewById(R.id.percentTextView);
        totalTextView = (TextView) findViewById(R.id.totalTextView);
        percentSeekBar = (SeekBar) findViewById(R.id.percentSeekBar);

        amountEditText.addTextChangedListener(amountTextChangedListener);
        percentSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                percentTextView.setText(String.valueOf(i)+"%");
                currentBill.setTipPercent((double)i/100.0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private TextWatcher amountTextChangedListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            //Do nothing
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            //Try to get the amount from amountEditText
            try {
                double amount = Double.parseDouble(charSequence.toString()) / 100.0;
                currentBill.setAmount(amount);
                amountTextView.setText("$" + String.valueOf(currentBill.getAmount()));
                tipTextView.setText("$" + String.valueOf(currentBill.getTipAmount()));
                totalTextView.setText("$" + String.valueOf(currentBill.getTotalAmount()));
            }
            catch (NumberFormatException e) {
                amountEditText.setText("");
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {
            //Do nothing
        }
    };
}
