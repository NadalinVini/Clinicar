package company.clinicar.clinicar.Model;

/**
 * Created by Vinicius on 18/11/2018.
 */

public class Carro {

    private int ID;
    private String Fabricante;
    private String Modelo;
    private int ano;
    private double valor;
    private String Usuario;

    public Carro() {
    }

    public Carro(String fabricante, String modelo, int ano, double valor, int id, String usuario) {
        Fabricante = fabricante;
        Modelo = modelo;
        this.ano = ano;
        this.valor = valor;
        this.ID = id;
        Usuario = usuario;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFabricante() {
        return Fabricante;
    }

    public void setFabricante(String fabricante) {
        Fabricante = fabricante;
    }

    public String getModelo() {
        return Modelo;
    }

    public void setModelo(String modelo) {
        Modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }
}
