// HomeView – startskjermen.
// - Viser tittel og en kort velkomsttekst.
// - Knapp for å åpne spilleren.

package prosjektpakke.view;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


// Startskjermen for mediespilleren
public class HomeView {
    private final VBox root = new VBox(16);

    public HomeView(Runnable onApneSpiller) {
        Label seksjon = new Label("01 / MEDIEHUB");
        seksjon.getStyleClass().add("seksjon");
        
        root.getChildren().addAll(seksjon, lagKort(onApneSpiller));
        root.getStyleClass().add("side");
    
    }


    // Lager kortet som vises på startskjermen.
    private VBox lagKort(Runnable onApneSpiller) {
        Label nummer = new Label("01");
        nummer.getStyleClass().addAll("badge", "badge-blaa");

        Label ikon = new Label("♪");
        ikon.getStyleClass().addAll("ikon", "ikon-blaa");

        Label tittelHvit = new Label("Media");
        Label tittelFarge = new Label("Hub");
        tittelHvit.getStyleClass().add("tittel");
        tittelFarge.getStyleClass().addAll("tittel", "tekst-blaa");
        HBox tittel = new HBox(tittelHvit, tittelFarge);

        // Tekstseksjon med tittel, beskrivelse og hint
        Label beskrivelse = new Label("Musikk- og videospiller");
        beskrivelse.getStyleClass().add("beskrivelse");

        // Hint for brukeren
        Label hint = new Label("Spilleliste, avspilling og volumkontroll");
        hint.getStyleClass().add("hint");

        VBox tekst = new VBox(2, tittel, beskrivelse, hint);
        HBox hode = new HBox(14, ikon, tekst);
        hode.setAlignment(Pos.CENTER_LEFT);

        Label status = new Label("KLAR TIL AVSPILLING");
        status.getStyleClass().addAll("tag", "tag-groenn");

        // Knapp for å åpne spilleren
        Button apne = new Button("ÅPNE SPILLER");
        apne.getStyleClass().add("knapp-primaer");
        apne.setMaxWidth(Double.MAX_VALUE);
        apne.setOnAction(e -> onApneSpiller.run());

        // Legger til alle elementene i kortet
        VBox kort = new VBox(12, hode, status, apne);
        kort.getStyleClass().add("kort");
        kort.setMaxWidth(380);
        return kort;
    }

    public Parent getRoot() {
        return root;
    }

}

