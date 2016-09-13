package edu.orangecoastcollege.cs273.bnguyen336.tipcaculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    //Associate the controller with needed views
    private EditText amountEditText;
    private TextView amountTextView;
    private TextView tipTextView;
    private TextView percentTextView;
    private TextView totalTextView;
    private TextView taxTextView;
    private SeekBar percentSeekBar;

    private static NumberFormat currency = NumberFormat.getCurrencyInstance();

    //Associate the controller with the needed model
    RestaurantBill currentBill = new RestaurantBill(0.0, 0.15);

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
        taxTextView = (TextView) findViewById(R.id.taxTextView);

        amountEditText.addTextChangedListener(amountTextChangedListener);

        percentSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                percentTextView.setText(String.valueOf(i) + "%");
                currentBill.setTipPercent((double)i / 100);
                calculateBill();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //Do nothing
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //Do nothing
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
                    double amount;
                    if (charSequence.length() < 1) {
                        amount = 0.0;
                    } else {
                        amount = Double.parseDouble(charSequence.toString()) / 100.0;
                    }
                    currentBill.setAmount(amount);
                    amountTextView.setText(currency.format(currentBill.getAmount()));
                    calculateBill();
                } catch (NumberFormatException e) {
                    amountEditText.setText("");
                }
        }

        @Override
        public void afterTextChanged(Editable editable) {
            //Do nothing
        }
    };

    private void calculateBill(){
        //Update tip amount text change
        tipTextView.setText(currency.format(currentBill.getTipAmount()));
        //Update total amount text change
        totalTextView.setText(currency.format(currentBill.getTotalAmount()));
        //Update total amount with tax
        taxTextView.setText(currency.format(currentBill.getTotalWithTax()));
    };
}
