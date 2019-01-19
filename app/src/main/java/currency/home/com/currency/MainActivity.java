package currency.home.com.currency;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final double USD_EXCHANGE_RATE = 30.9; // 1:30.9 (USD:NTD)
    private final double JPY_EXCHANGE_RATE = 0.28124; // 1:0.28124 (JYP:NTD)

    private TextView tvValueUSD;
    private TextView tvValueJPY;

    private enum Currency {
        JPY,
        USD
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        tvValueUSD = findViewById(R.id.tvValueUSD);
        tvValueUSD.setText(R.string.common_none);
        tvValueJPY = findViewById(R.id.tvValueJPY);
        tvValueJPY.setText(R.string.common_none);

        final EditText edNtd = findViewById(R.id.ntd);
        Button btnGo = findViewById(R.id.button);
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ntd = edNtd.getText().toString();
                calculate(ntd);
                showAlert(ntd);
            }
        });

    }

    private void showAlert(String currency) {

        String title = getResources().getString(R.string.alert_title_problem);
        String message = getResources().getString(R.string.alert_amount_empty);
        if (validation(currency)) {
            title = getResources().getString(R.string.alert_title_result);
            message = getResources().getString(R.string.currency_usd) + " "
                    + getResources().getString(R.string.common_is) + " "
                    + (float)exchange(currency,Currency.USD);
        }

        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(getResources().getText(R.string.common_ok),null)
                .show();
    }

    private boolean validation(String currency) {
        return !currency.isEmpty();
    }

    private double exchange(String currency, Currency type) {
        double ntd = Double.valueOf(currency);
        if (type == Currency.USD) {
            return ntd / USD_EXCHANGE_RATE;
        } else {
            return ntd / JPY_EXCHANGE_RATE;
        }
    }

    private void calculate(String currency) {
        tvValueUSD.setText( String.valueOf((float)exchange(currency,Currency.USD)) );
        tvValueJPY.setText( String.valueOf((float)exchange(currency,Currency.JPY)) );
    }
}
