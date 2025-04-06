package com.example.investioncalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    EditText pocetniIznos;
    EditText kamatnaStopa;
    EditText brojGodina;
    Button dugme;
    TextView rezultat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        pocetniIznos = findViewById(R.id.pocetniIznosID);
        kamatnaStopa = findViewById(R.id.kamatnaStopaID);
        brojGodina = findViewById(R.id.brojGodinaID);
        dugme = findViewById(R.id.dugmeID);
        rezultat = findViewById(R.id.rezultatID);

        dugme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputPocetniIznos = pocetniIznos.getText().toString();
                String inputKamatnaStopa = kamatnaStopa.getText().toString();
                String inputBrojGodina = brojGodina.getText().toString();

                if (inputPocetniIznos.isEmpty() || inputKamatnaStopa.isEmpty() || inputBrojGodina.isEmpty()) {
                    rezultat.setText("Popuni sva polja!");
                    return;
                }

                try {
                    double P = Double.parseDouble(inputPocetniIznos);
                    double r = Double.parseDouble(inputKamatnaStopa);
                    int n = Integer.parseInt(inputBrojGodina);

                    double A = P * Math.pow(1 + r / 100, n);
                    double ukupnaKamata = A - P;

                    rezultat.setText(String.format(Locale.getDefault(),"Konačni iznos: %.2f\nUkupna kamata: %.2f", A, ukupnaKamata));
                } catch (NumberFormatException e) {
                    rezultat.setText("Greška :(");
                }

            }
        });
    }
}