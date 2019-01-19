package currency.home.com.currency;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private final double EXCHANGE_RATE = 30.9; // 1:30.9 (USD:NTD)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText edNtd = findViewById(R.id.ntd);
        Button btnGo = findViewById(R.id.button);
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlert(edNtd.getText().toString());
            }
        });
    }

    private void showAlert(String currency) {

        String title = getResources().getString(R.string.alert_title_problem);
        String message = getResources().getString(R.string.alert_amount_empty);
        if (validation(currency)) {
            title = getResources().getString(R.string.alert_title_result);
            message = "USD is " + (float)exchange(currency);
        }

        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK",null)
                .show();
    }

    private boolean validation(String currency) {
        return !currency.isEmpty();
    }

    private double exchange(String currency) {
        double ntd = Double.valueOf(currency);
        return ntd / EXCHANGE_RATE;
    }
}
