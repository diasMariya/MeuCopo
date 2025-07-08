package com.mariaeduarda.meucopo.View;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mariaeduarda.meucopo.Model.CopoModel;
import com.mariaeduarda.meucopo.R;

public class ConfiguracoesActivity extends AppCompatActivity {

    EditText edtMeta, edtCopo;
    Button btnSalvar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_configuracoes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edtMeta = findViewById(R.id.edtMeta);
        edtCopo = findViewById(R.id.edtCopo);
        btnSalvar = findViewById(R.id.btnSalvar);

        CopoModel dados = CopoModel.getInstancia();

        edtMeta.setText((String.valueOf(dados.getMeta())));
        edtCopo.setText((String.valueOf(dados.getTamanhoCopo())));

      btnSalvar.setOnClickListener(v -> {
          int metaNova = Integer.parseInt(edtMeta.getText().toString());
          int copoNovo = Integer.parseInt(edtCopo.getText().toString());

          dados.setMeta(metaNova);
          dados.setTamanhoCopo(copoNovo);
          finish();
      });
    }
}

