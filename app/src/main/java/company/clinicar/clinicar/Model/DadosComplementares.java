package company.clinicar.clinicar.Model;

import com.google.firebase.auth.FirebaseUser;

import java.util.Date;

/**
 * Created by Vinicius on 18/11/2018.
 */

public class DadosComplementares {

    private String Usuario;
    private String CPF;
    private Long Telefone;
    private String Endereco;
    private Date Nascimento;
    private String Sexo;
    private FirebaseUser user;
    private String img;

    public DadosComplementares() {
    }

    public DadosComplementares(String CPF, Long telefone, String endereco, Date nascimento, String sexo, String usuario) {
        this.CPF = CPF;
        Telefone = telefone;
        Endereco = endereco;
        Nascimento = nascimento;
        Sexo = sexo;
        Usuario = usuario;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public FirebaseUser getUser() {
        return user;
    }

    public void setUser(FirebaseUser user) {
        this.user = user;
    }

    public String getCPF() {
        return this.CPF;

    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public Long getTelefone() {
        return Telefone;
    }

    public void setTelefone(Long telefone) {
        Telefone = telefone;
    }

    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String endereco) {
        Endereco = endereco;
    }

    public Date getNascimento() {
        return Nascimento;
    }

    public void setNascimento(Date nascimento) {
        Nascimento = nascimento;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String sexo) {
        Sexo = sexo;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }
}
