package com.mariaeduarda.meucopo.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

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
import java.util.List;
public class HistoricoActivity extends AppCompatActivity {
    ListView listView;
    TextView textHistorico;
    Button btnVoltar;
    ArrayAdapter<CopoModel> adapter;
    private List<CopoModel> list;
    CoposController controller;



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
        listView = findViewById(R.id.listView);
        //textHistorico = findViewById(R.id.txtHistorico);
        btnVoltar = findViewById(R.id.btnVoltar);


        MeuCopoDB db = new MeuCopoDB(this);
        CopoModel model = new CopoModel(db);

        List<Registro> list = model.getHistorico();
        StringBuilder text = new StringBuilder();
        for (Registro log : list) {
            text.append(log.getData()).append(" - ").append(log.getTotalMl()).append(" ml\n");
        }
        textHistorico.setText(text.toString());
    }
//    private void mostrarContatos(){
//        list = controller.mostrarTodosContatos();
//        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
//        listView.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
//    }


}

