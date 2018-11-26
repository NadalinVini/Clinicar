package company.clinicar.clinicar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends Activity {

    private EditText editLogin;
    private EditText editSenha;
    private FirebaseAuth mAuth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editLogin = findViewById(R.id.editLogin);
        editSenha = findViewById(R.id.editSenha);
        mAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
    }

    public void Logar(View view) {
        String sLogin = editLogin.getText().toString();
        String sSenha = editSenha.getText().toString();
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (!sLogin.isEmpty() && !sSenha.isEmpty()) {
            mAuth.signInWithEmailAndPassword(sLogin, sSenha)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(MainActivity.this, "Logado Sucesso", Toast.LENGTH_SHORT).show();
                                if (user.getDisplayName() == null) {
                                    Intent intent = new Intent(MainActivity.this, BemVindo.class);
                                    startActivity(intent);
                                } else {
                                    Intent intent = new Intent(MainActivity.this, ContratoActivity.class);
                                    startActivity(intent);
                                }
                            } else {
                                Toast.makeText(MainActivity.this, "Erro ao Logar", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else {
            Toast.makeText(MainActivity.this, "Preenche essa disgraça ai", Toast.LENGTH_SHORT).show();
        }
    }

    public void Registrar(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }


}
