package no.triki.demoapp2;

public class SlideBilde {

    private int ressursId;
    private String tittel;

    public SlideBilde(int ressursId, String tittel) {
        this.ressursId = ressursId;
        this.tittel = tittel;
    }

    public int getRessursId() {
        return ressursId;
    }

    public String getTittel() {
        return tittel;
    }
}
