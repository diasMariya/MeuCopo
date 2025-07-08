package com.mariaeduarda.meucopo.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mariaeduarda.meucopo.Controller.CoposController;
import com.mariaeduarda.meucopo.Model.CopoModel;
import com.mariaeduarda.meucopo.Model.Registro;
import com.mariaeduarda.meucopo.R;

public class MainActivity extends AppCompatActivity implements CoposController.TelaListener {

    CopoModel model;
    CoposController controller;
    private TextView txtConsumo, txtMeta;
    private ProgressBar barra;
    Button btnConfiguracoes, btnHistorico;
    @SuppressLint("MissingInflatedId")
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

       model = new CopoModel();
        controller = new CoposController(this);


            txtConsumo = findViewById(R.id.txtConsumo);
            txtMeta = findViewById(R.id.txtMeta);
            barra = findViewById(R.id.barraProgresso);
            Button btnBeber = findViewById(R.id.btnBeber);
            Button btnConfiguracoes = findViewById(R.id.btnConfiguracoes);
            Button btnHistorico = findViewById(R.id.btnHistorico);

           controller.atualizarTela();

            btnBeber.setOnClickListener(v -> controller.beber());

        btnConfiguracoes.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, ConfiguracoesActivity.class);
            startActivity(intent);
        }
     });
        btnHistorico.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, HistoricoActivity.class);
            startActivity(intent);
        }
     });
    }

        public void atualizarProgresso ( int ml, int meta, double percentual){
            txtConsumo.setText("Hoje: " + ml + "ml");
            txtMeta.setText("Meta: " + meta + "ml");
            barra.setProgress((int) percentual);
        }
    }
