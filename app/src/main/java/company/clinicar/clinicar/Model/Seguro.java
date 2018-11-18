package company.clinicar.clinicar.Model;

import java.util.Date;

/**
 * Created by User on 18/11/2018.
 */

public class Seguro {

    private int ID;
    private Date DataContrato;
    private String Usuario;

    public Seguro() {
    }

    public Seguro(int ID, Date dataContrato, String usuario) {
        this.ID = ID;
        DataContrato = dataContrato;
        Usuario = usuario;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Date getDataContrato() {
        return DataContrato;
    }

    public void setDataContrato(Date dataContrato) {
        DataContrato = dataContrato;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }
}
