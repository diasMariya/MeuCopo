package com.mariaeduarda.meucopo.View;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mariaeduarda.meucopo.Controller.CoposController;
import com.mariaeduarda.meucopo.Model.CopoModel;
import com.mariaeduarda.meucopo.Model.MeuCopoDB;
import com.mariaeduarda.meucopo.Model.Registro;
import com.mariaeduarda.meucopo.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements CoposController.TelaListener {

    CopoModel model;
    CoposController controller;
    TextView txtConsumo, txtMeta;
    ProgressBar barra;
    Button btnConfiguracoes, btnHistorico, btnBeber, btnSalvar;
    EditText edtMeta, edtCopo;

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
        txtConsumo = findViewById(R.id.txtConsumo);
        txtMeta = findViewById(R.id.txtMeta);
        barra = findViewById(R.id.barraProgresso);
        btnBeber = findViewById(R.id.btnBeber);
        btnConfiguracoes = findViewById(R.id.btnConfiguracoes);
        btnHistorico = findViewById(R.id.btnHistorico);
        btnSalvar = findViewById(R.id.btnSalvar);
        edtMeta = findViewById(R.id.edtMeta);
        edtCopo = findViewById(R.id.edtCopo);

        MeuCopoDB db = new MeuCopoDB(this);
        model = new CopoModel(db);

        controller = new CoposController(this, this);

        controller.salvarObj(model);

        controller.atualizarTela();

        btnBeber.setOnClickListener(view -> {
            String dataAtual = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

            controller.beber();

            int consumoAtual = model.getConsumo();

            ContentValues valores = new ContentValues();
            valores.put("data", dataAtual);
            valores.put("totalMl", consumoAtual);

            SQLiteDatabase banco = db.getWritableDatabase();
            Cursor cursor = banco.rawQuery("SELECT * FROM Registro WHERE data = ?", new String[]{dataAtual});

            if (cursor.moveToFirst()) {
                db.atualizar("Registro", valores, "data = ?", new String[]{dataAtual});
            } else {
                db.salvar("Registro", valores);
            }

            cursor.close();
            controller.atualizarTela();
            Toast.makeText(this, "Consumo salvo com sucesso!", Toast.LENGTH_SHORT).show();
        });

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
    @SuppressLint("SetTextI18n")
    @Override
    public void atualizarProgresso(int ml, int meta, double percentual) {
        txtConsumo.setText("Hoje: " + ml + "ml");
        txtMeta.setText("Meta: " + meta + "ml");
        barra.setProgress((int) percentual);
    }
}
