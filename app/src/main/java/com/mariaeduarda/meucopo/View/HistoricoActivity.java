package com.mariaeduarda.meucopo.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mariaeduarda.meucopo.Model.CopoModel;
import com.mariaeduarda.meucopo.Model.Registro;
import com.mariaeduarda.meucopo.R;

import java.util.ArrayList;
import java.util.List;

public class HistoricoActivity extends AppCompatActivity {

    TextView textHistorico;
    List<Registro> list;
    Button btnVoltar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_historico);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textHistorico = findViewById(R.id.txtHistorico);
         list = CopoModel.getInstancia().getHistorico();
         btnVoltar = findViewById(R.id.btnVoltar);

         StringBuilder text = new StringBuilder();

         for (Registro log : list){
             text.append(log.getData()).append("-").append(log.getTotalMl()).append("ml\n");
         }
         textHistorico.setText(text.toString());

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HistoricoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


}
