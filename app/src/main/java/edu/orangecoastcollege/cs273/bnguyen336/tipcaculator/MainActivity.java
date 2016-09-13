package edu.orangecoastcollege.cs273.bnguyen336.tipcaculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Associate the controller with needed views
    private EditText amountEditText;
    private TextView amountTextView;
    private TextView tipTextView;
    private TextView percentTextView;
    private TextView totalTextView;
    private SeekBar percentSeekBar;

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

        amountEditText.addTextChangedListener(amountTextChangedListener);

        percentSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                percentTextView.setText(String.valueOf(i) + "%");
                currentBill.setTipPercent((double)i / 100);
                tipTextView.setText("$" + String.format("%.2f", currentBill.getTipAmount() ));
                totalTextView.setText("$" + String.format("%.2f", currentBill.getTotalAmount()));
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
                    amountTextView.setText("$" + String.format("%.2f", currentBill.getAmount()));
                    tipTextView.setText("$" + String.format("%.2f", currentBill.getTipAmount()));
                    totalTextView.setText("$" + String.format("%.2f", currentBill.getTotalAmount()));
                } catch (NumberFormatException e) {
                    amountEditText.setText("");
                }
        }

        @Override
        public void afterTextChanged(Editable editable) {
            //Do nothing
        }
    };
}
