package com.example.cassiamshigenaga.todofirebase;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


import com.example.cassiamshigenaga.todofirebase.modelo.Tarefa;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class AdicionarActivity extends AppCompatActivity {

    EditText editTextNome;
    private Tarefa tarefa;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar);

        tarefa = new Tarefa();
        tarefa.setUid(this.getIntent().getStringExtra("uid"));
        tarefa.setNome(this.getIntent().getStringExtra("nome"));

        editTextNome = findViewById(R.id.txtNome);
        editTextNome.setText(tarefa.getNome());
        conectarBanco();
    }

    public void conectarBanco(){
        FirebaseApp.initializeApp(AdicionarActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public void salvarTarefa(View view){
        Tarefa tarefa = new Tarefa();
        if(this.getIntent().getStringExtra("uid") ==null){
            tarefa.setUid(UUID.randomUUID().toString());
        }

        else{
            tarefa.setUid(this.getIntent().getStringExtra("uid"));
        }

        tarefa.setNome(editTextNome.getText().toString());
        databaseReference.child("Tarefa").child(tarefa.getUid()).setValue(tarefa);
        finish();

    }
}
