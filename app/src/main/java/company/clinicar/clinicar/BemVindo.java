package company.clinicar.clinicar;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.firebase.database.Query;

import company.clinicar.clinicar.ActivityContrato.CarroActivity;
import company.clinicar.clinicar.Model.Carro;
import company.clinicar.clinicar.Model.DadosComplementares;
import me.iwf.photopicker.PhotoPicker;

import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BemVindo extends Activity {

    private ImageView imgSelected;
    private StorageReference mStorageRef;
    private ArrayList<String> photos;
    private TextView textView;
    private EditText TextNome;
    private EditText TextEmail;
    private EditText TextCPF;
    private EditText TextTelefone;
    private EditText TextEndereco;
    private EditText TextSexo;
    private EditText TextSenha;
    private DatabaseReference mDatabase;
    private StorageReference photoRef;
    String nome = null;
    String email = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bem_vindo);

        photos = new ArrayList<>();
        imgSelected = findViewById(R.id.imgSelected);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        textView = findViewById(R.id.textView);
        TextNome = findViewById(R.id.TextNome);
        TextEmail = findViewById(R.id.TextEmail);
        TextCPF = findViewById(R.id.TextCPF);
        TextTelefone = findViewById(R.id.TextTelefone);
        TextEndereco = findViewById(R.id.TextEndereco);
        TextSexo = findViewById(R.id.TextSexo);
        TextSenha = findViewById(R.id.TextSenha);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            textView.setText("Bem vindo, " + user.getDisplayName());
        }

        CarregarInformacoes(user.getUid());
    }

    public void photoPickerFunction(View view) {
        PhotoPicker.builder()
                .setPhotoCount(1)
                .setShowCamera(true)
                .setShowGif(true)
                .setPreviewEnabled(false)
                .start(this, PhotoPicker.REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE) {
            if (data != null) {
                photos = data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                imgSelected.setImageURI(Uri.parse(photos.get(0)));
            }
        }
    }

    private void resetForm() {
        photos.clear();
        imgSelected.setImageResource(0);
    }

    public void sendPhotoFunction(View view) {
        if (photos.size() > 0) {
            Uri file = Uri.fromFile(new File(photos.get(0)));
            photoRef = mStorageRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid());
            GetUser();
            UpdateUser();
            AdicionarRegistroCompleto();
            photoRef.putFile(file).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(BemVindo.this, "Arquivo Enviado com sucesso!", Toast.LENGTH_SHORT).show();
                    textView.setText("Bem vindo, " + nome);
                    CleanUser();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(BemVindo.this, "Falha ao enviar arquivo.", Toast.LENGTH_SHORT).show();
                }
            });
            resetForm();
        } else {
            Toast.makeText(this, "Nenhum arquivo carregado.", Toast.LENGTH_SHORT).show();
        }
    }

    private void GetUser() {
        nome = TextNome.getText().toString();
        email = TextEmail.getText().toString();

    }

    private void UpdateUser() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (!email.equals("")) {
            user.updateEmail(email);
            Uri image = Uri.parse(photos.get(0));
            user.updateProfile(new UserProfileChangeRequest.Builder().setDisplayName(nome).setPhotoUri(image).build());
        }
    }

    private void CleanUser() {
        TextNome.setText(null);
        TextEmail.setText(null);
        TextEndereco.setText((null));
        TextTelefone.setText(null);
        TextSexo.setText(null);
        TextCPF.setText(null);
    }

    private void AdicionarRegistroCompleto() {

        DadosComplementares dados = new DadosComplementares();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        String cpf;
        cpf = TextCPF.getText().toString();
        if (TextCPF.getText().toString().toCharArray().length > 11 || TextCPF.getText().toString().toCharArray().length < 11) {
            Toast.makeText(this, "CPF ficará vazio, pois está incorreto", Toast.LENGTH_SHORT).show();
            dados.setCPF(null);
        } else {
            dados.setCPF(cpf);
        }

        if (TextTelefone.getText().toString().toCharArray().length > 11 || TextCPF.getText().toString().toCharArray().length < 9) {
            Toast.makeText(this, "Telefone ficará vazio, pois está incorreto", Toast.LENGTH_SHORT).show();
            dados.setTelefone(0L);
        } else {
            dados.setTelefone(Long.parseLong(TextTelefone.getText().toString()));
        }

        if (!TextSenha.getText().toString().isEmpty()) {
            user.updatePassword(TextSenha.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {

                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(BemVindo.this, "Senha alterada com sucesso", Toast.LENGTH_SHORT).show();

                }

            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(BemVindo.this, "Erro ao alterar senha", Toast.LENGTH_SHORT).show();

                }

            });
        }

        dados.setEndereco(TextEndereco.getText().toString());

        String sexo = TextSexo.getText().toString();
        dados.setSexo(sexo);

        dados.setUsuario(user.getEmail());

        SalvarDadosComplementares(dados, user.getUid());
        goHome();
    }

    private void SalvarDadosComplementares(DadosComplementares dados, final String user) {

        mDatabase.child("dados").child(user).setValue(dados)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(BemVindo.this, "Dados adicionado", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(BemVindo.this, "Erro ao realizar operação ", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void CarregarInformacoes(String user) {

        Query mQuery = mDatabase.child("dados").child(user);

        mQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                DadosComplementares dados = dataSnapshot.getValue(DadosComplementares.class);

                TextEmail.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
                TextNome.setText(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());

                if (dados != null) {
                    photoRef = mStorageRef.child(FirebaseAuth.getInstance().getCurrentUser().getUid());

                    TextCPF.setText(dados.getCPF());

                    if (!dados.getEndereco().equals(null))
                        TextEndereco.setText(dados.getEndereco());

                    if (!dados.getSexo().equals(null)) TextSexo.setText(dados.getSexo());
                    if (!dados.getTelefone().equals(null))
                        TextTelefone.setText(dados.getTelefone().toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    public void goHome() {
        Intent intent = new Intent(this, ContratoActivity.class);
        startActivity(intent);
    }
}
