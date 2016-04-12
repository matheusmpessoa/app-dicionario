package prgdm.fatecrl.projetofinal;

/**
 * Created by Dan on 17/11/2015.
 */

public class Dicionario {
    private String palavra;
    private String significado;
    private long id;

    public String getPalavra() {
        return palavra;
    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }

    public String getSignificado() {
        return significado;
    }

    public void setSignificado(String significado) {
        this.significado = significado;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
