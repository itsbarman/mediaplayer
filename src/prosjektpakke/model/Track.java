// Track – ett spor (lyd eller video).
// - Tittel og filsti.
// - Varighet når den er kjent.
// - Om sporet spilles nå (brukes til å utheve det i listen).

package prosjektpakke.model;

public class Track {
    private final String tittel;
    private final String ressurssti;

    // Lager et nytt spor med gitt tittel og ressurssti.
    public Track(String tittel, String ressurssti) {
        this.tittel = tittel;
        this.ressurssti = ressurssti;
    }

    // Returnerer tittelen til sporet.
    public String getTittel() {
        return tittel;
    }

    // Returnerer ressursstien til sporet.
    public String getRessurssti() {
        return ressurssti;
    }

    // Returnerer tittelen som representasjon av sporet.
    @Override
    public String toString() {
        return tittel;
    }
}

