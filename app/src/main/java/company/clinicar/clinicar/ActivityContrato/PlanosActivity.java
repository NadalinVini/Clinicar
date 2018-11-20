package company.clinicar.clinicar.ActivityContrato;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import company.clinicar.clinicar.Model.Carro;
import company.clinicar.clinicar.Model.DadosComplementares;
import company.clinicar.clinicar.Model.Seguro;
import company.clinicar.clinicar.R;

/**
 * Created by User on 18/11/2018.
 */

public class PlanosActivity extends Activity {

    private String plano;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private DatabaseReference mDatabase;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plano);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        mDatabase = FirebaseDatabase.getInstance().getReference();

    }
    Seguro seguro = new Seguro();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    Date hora = Calendar.getInstance().getTime();
    String dataFormatada = sdf.format(hora);


    public void PlanoEco(View view){

        button2.setEnabled(false);
        button3.setEnabled(false);
        button4.setEnabled(false);
        seguro.setID(1);
        seguro.setUsuario(user.getEmail());
        seguro.setDataContrato(dataFormatada);
        AddSeguro(seguro);

    }
    public void PlanoBplus(View view){

        button1.setEnabled(false);
        button3.setEnabled(false);
        button4.setEnabled(false);
        seguro.setID(2);
        seguro.setUsuario(user.getEmail());
        seguro.setDataContrato(dataFormatada);
        AddSeguro(seguro);
    }
    public void PlanoA(View view){
        button2.setEnabled(false);
        button1.setEnabled(false);
        button4.setEnabled(false);
        seguro.setID(3);
        seguro.setUsuario(user.getEmail());
        seguro.setDataContrato(dataFormatada);
        AddSeguro(seguro);
    }
    public void PlanoGold(View view){
        button2.setEnabled(false);
        button3.setEnabled(false);
        button1.setEnabled(false);
        seguro.setID(4);
        seguro.setUsuario(user.getEmail());
        seguro.setDataContrato(dataFormatada);
        AddSeguro(seguro);
    }

    private void AddSeguro(Seguro seguro){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase.child("seguro").child(user.getUid()).setValue(seguro)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Toast.makeText(PlanosActivity.this, "Seguro adicionado", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener(){
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(PlanosActivity.this, "Erro ao tentar adicionar o seguro ", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
