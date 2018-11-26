package company.clinicar.clinicar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class RegisterActivity extends Activity {

    EditText editsEmail;
    EditText editsSenha;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editsEmail = findViewById(R.id.editsEmail);
        editsSenha = findViewById(R.id.editsSenha);
        mAuth = FirebaseAuth.getInstance();
    }

    public void Registrar(View view) {
        String sEmail = editsEmail.getText().toString();
        String sSenha = editsSenha.getText().toString();

        if (!sEmail.isEmpty() && !sSenha.isEmpty()) {
                mAuth.createUserWithEmailAndPassword(sEmail, sSenha).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "Registrado, Pau no cu", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this, BemVindo.class);
                            startActivity(intent);
                            /**/

                        } else {
                            Toast.makeText(RegisterActivity.this, "Escreve certo ae FDP", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
        } else {
            Toast.makeText(RegisterActivity.this, "Preeche os campos ai caralho", Toast.LENGTH_SHORT).show();
        }
    }
}
