// PlayerView – spillerskjermen, bygget med BorderPane.
// - Venstre: spilleliste (ListView), sporet som spilles uthevet.
// - Midten: «Spilles nå»-tekst, og eventuelt video (MediaView).
// - Bunn: knapper (spill av, pause, stopp, forrige, neste),
//   fremdriftsslider, tidsetikett og volumslider.
// - Topp: knapp for å gå tilbake til hjemskjermen.
// - Skal tilpasse seg når vinduet endrer størrelse.

package prosjektpakke.view;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class PlayerView {

    private final BorderPane root = new BorderPane();

    // Spilleliste og status
    private final ListView<String> spilleliste = new ListView<>();
    private final Label sporTittel = new Label("Ingen spor valgt");
    private final Label status = new Label("STOPPET");

    //Knapper
    private final Button forrigeKnapp = new Button("FORRIGE");
    private final Button spillKnapp = new Button("SPILL");
    private final Button pauseKnapp = new Button("PAUSE");
    private final Button stoppKnapp = new Button("STOPP");
    private final Button nesteKnapp = new Button("NESTE");

    // Fremdrift og volum
    private final Slider fremdrift = new Slider(0,1,0); //mist verdi, størst verdi, verdi slider starter på
    private final Label tid = new Label("00:00 / 00:00");
    private final Slider volum = new Slider(0,1,0.5); // minimum verdi, maksimum verdi, startverdi

    public PlayerView() {
        root.setLeft(lagSpillelistePanel());
        root.setCenter(lagSpillesNaaPanel());
        root.setBottom(lagKontrollPanel());
    }

    private VBox lagSpillelistePanel() {
        root.setLeft(lagSpillelistePanel());
        root.setCenter(lagSpillesNaaPanel());
        root.setBottom(lagKontrollPanel());
    }

    private VBox lagSpillelistePanel() {
        Label seksjon = new Label("01 / SPILLELISTE");
        seksjon.getStyleClass().add("seksjon");

        // Plassholdere, byttes ut med Trtack objekter i milepæl 3
        spilleliste.getItems().addAll("Spor 1", "Spor 2", "Spor 3");
        VBox.setVgrow(spilleliste, Priority.ALWAYS);

        VBox panel = new VBox(12, seksjon, spilleliste);
        return panel;
    }

    private VBox lagSpillesNaaPanel() {
        Label seksjon = new Label("02 / SPILLES NÅ");
    
    }







}