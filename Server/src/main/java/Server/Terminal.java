package Server;

/**
 * Created by Fiser on 4/10/16.
 */
public class Terminal {
    private long id;
    private String marca;
    private String modelo;
    private double precio;
    private String promoOro;
    private String promoPlata;
    private String promoBronce;

    public Terminal() {
    }

    public Terminal(long id, String marca, String modelo, double precio) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.precio = precio;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getPromoOro() {
        return promoOro;
    }

    public void setPromoOro(String promoOro) {
        this.promoOro = promoOro;
    }

    public String getPromoPlata() {
        return promoPlata;
    }

    public void setPromoPlata(String promoPlata) {
        this.promoPlata = promoPlata;
    }

    public String getPromoBronce() {
        return promoBronce;
    }

    public void setPromoBronce(String promoBronce) {
        this.promoBronce = promoBronce;
    }
}
