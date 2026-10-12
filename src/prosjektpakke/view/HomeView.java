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

public class HomeView {
    private final VBox root = new VBox(16);

    public HomeView(Runnable onApneSpiller) {
        Label seksjon = new Label("01 / MEDIEHUB");
        seksjon.getStyleClass().add("seksjon");
        
        root.getChildren().addAll(seksjon, lagKort(onApneSpiller));
        root.getStyleClass().add("side");
    
    }

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

        Label beskrivelse = new Label("Musikk- og videospiller");
        beskrivelse.getStyleClass().add("beskrivelse");

        Label hint = new Label("Spilleliste, avspilling og volumkontroll");
        hint.getStyleClass().add("hint");

        





    }

}

