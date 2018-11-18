package company.clinicar.clinicar.ActivityContrato;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import company.clinicar.clinicar.Model.Carro;
import company.clinicar.clinicar.R;

/**
 * Created by Vinicius on 18/11/2018.
 */

public class CarroActivity extends Activity {

    private EditText EditFabricante;
    private EditText EditModelo;
    private EditText EditAno;
    private EditText EditValor;
    private DatabaseReference mDatabase;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carro);

        EditFabricante = findViewById(R.id.fabricanteText);
        EditModelo = findViewById(R.id.modeloText);
        EditAno = findViewById(R.id.anoText);
        EditValor = findViewById(R.id.valorText);
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }
    public void AdicionarCarro (View view){

        String fabricante = "";
        String modelo = "";
        int ano = 0;
        Double valor = 0.0;

        Carro carro = new Carro();

        try
        {
            fabricante = EditFabricante.getText().toString();
            modelo = EditModelo.getText().toString();
            ano = Integer.parseInt(EditAno.getText().toString());
            valor = Double.parseDouble(EditValor.getText().toString());

        }catch (NullPointerException e){

            Toast.makeText(CarroActivity.this, "Não foi possível ler os dados do carro", Toast.LENGTH_SHORT).show();
        }

        carro.setFabricante(fabricante);
        carro.setModelo(modelo);
        carro.setAno(ano);
        carro.setValor(valor);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        carro.setUsuario(user.getEmail());
        carro.setID(1);

        if(ano < 1990 || ano > 2020){
            Toast.makeText(CarroActivity.this, "ano do carro não pode ser menor que 1990 e maior que 2020", Toast.LENGTH_SHORT).show();
        }
        else{
            SalvarCarro(carro, carro.getID());
        }

    }

    private void SalvarCarro(Carro carro, long id){

        mDatabase.child("carro").child("ID" + id).setValue(carro)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(CarroActivity.this, "Carro adicionado", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener(){
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(CarroActivity.this, "Erro ao tentar adicionar carro ", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
