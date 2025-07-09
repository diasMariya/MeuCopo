package com.mariaeduarda.meucopo.View;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mariaeduarda.meucopo.Controller.CoposController;
import com.mariaeduarda.meucopo.Model.CopoModel;
import com.mariaeduarda.meucopo.Model.MeuCopoDB;
import com.mariaeduarda.meucopo.R;

public class ConfiguracoesActivity extends AppCompatActivity implements CoposController.TelaListener {

    EditText edtMeta, edtCopo;
    Button btnSalvar;
    CopoModel model;
    //TextView txtConsumo;
    CoposController controller;

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

        MeuCopoDB db = new MeuCopoDB(this);
        model = new CopoModel(db);

        controller = new CoposController(this, this);

        edtMeta = findViewById(R.id.edtMeta);
        edtCopo = findViewById(R.id.edtCopo);
        btnSalvar = findViewById(R.id.btnSalvar);
        //txtConsumo = findViewById(R.id.txtConsumo);

        edtMeta.setText(String.valueOf(model.getMeta()));
        edtCopo.setText(String.valueOf(model.getTamanhoCopo()));
//        txtConsumo.setText(String.valueOf(model.getConsumo()));

        btnSalvar.setOnClickListener(v -> {
            String metaStr = edtMeta.getText().toString().trim();
            String copoStr = edtCopo.getText().toString().trim();

            if (metaStr.isEmpty() || copoStr.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                int metaNova = Integer.parseInt(metaStr);
                int copoNovo = Integer.parseInt(copoStr);

                model.setMeta(metaNova);
                model.setTamanhoCopo(copoNovo);

                controller.salvarObj(model);

                Toast.makeText(this, "Configurações salvas com sucesso!", Toast.LENGTH_SHORT).show();

                finish();

            } catch (NumberFormatException e) {
                Toast.makeText(this, "Por favor, insira números válidos.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void atualizarProgresso(int ml, int meta, double percentual) {

    }
}