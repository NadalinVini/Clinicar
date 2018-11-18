package company.clinicar.clinicar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import company.clinicar.clinicar.ActivityContrato.CarroActivity;
import company.clinicar.clinicar.ActivityContrato.OficinaActivity;
import company.clinicar.clinicar.ActivityContrato.PlanosActivity;


/**
 * Created by Vinicius on 18/11/2018.
 */

public class ContratoActivity  extends Activity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contrato);

    }

    public void IrCarro (View view){
        Intent intent = new Intent(ContratoActivity.this, CarroActivity.class);
        startActivity(intent);
    }
    public void IrContratar(View view){
        Intent intent = new Intent(ContratoActivity.this, PlanosActivity.class);
        startActivity(intent);
    }
    public void IrOficina(View view){
        Intent intent = new Intent(ContratoActivity.this, OficinaActivity.class);
        startActivity(intent);
    }
    public void IrPerfil(View view){
        Intent intent = new Intent(ContratoActivity.this, BemVindo.class);
        startActivity(intent);
    }
}
