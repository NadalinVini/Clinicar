package company.clinicar.clinicar.Model;

import java.util.Date;

/**
 * Created by Vinicius on 18/11/2018.
 */

public class DadosComplementares {

    private String Usuario;
    private String CPF;
    private long Telefone;
    private String Endereco;
    private Date Nascimento;
    private String Sexo;

    public DadosComplementares() {
    }

    public DadosComplementares(String CPF, long telefone, String endereco, Date nascimento, String sexo, String usuario) {
        this.CPF = CPF;
        Telefone = telefone;
        Endereco = endereco;
        Nascimento = nascimento;
        Sexo = sexo;
        Usuario = usuario;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public long getTelefone() {
        return Telefone;
    }

    public void setTelefone(long telefone) {
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
