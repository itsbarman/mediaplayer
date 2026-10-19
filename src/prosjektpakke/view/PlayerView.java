// PlayerView – spillerskjermen, bygget med BorderPane.
// App for mediaavspilling

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
import prosjektpakke.model.Playlist;
import prosjektpakke.model.Track;
import prosjektpakke.controller.PlayerController;

public class PlayerView {

    private final BorderPane root = new BorderPane();

    // Spilleliste og status
    private final ListView<Track> spilleliste = new ListView<>();
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

    // Konstruktør
    public PlayerView(Playlist playlist) {
        PlayerController controller = new PlayerController(playlist);
        controller.settStatus(status);
        controller.settFremdriftskontroller(fremdrift, tid); // slider for tid spilt
        controller.settVolumkontroll(volum); // slider for volum
        spillKnapp.setOnAction(e -> controller.spillValgtSpor()); // Startknappen skalk funke
        spilleliste.setItems(playlist.getSpor());
        stoppKnapp.setOnAction(e -> controller.stopp()); // Stopp knapp skal funke med dette

        // Neste spor knapp handle
        nesteKnapp.setOnAction(e -> {
            controller.nesteSpor();
            spilleliste.getSelectionModel().select(
                playlist.getSpor().indexOf(playlist.getValgtSpor())
            );
        });
      
        // Forrige spor knapp handle
        forrigeKnapp.setOnAction(e -> {
            controller.forrigeSpor();
            spilleliste.getSelectionModel().select(
                playlist.getSpor().indexOf(playlist.getValgtSpor())
            );
        });

        // Spill / pause knapp handle
        pauseKnapp.setOnAction(e -> {
            if (controller.pauseEllerFortsett()) {
                pauseKnapp.setText(
                    pauseKnapp.getText().equals("PAUSE") ? "FORTSETT" : "PAUSE"
                );
            }
        });

        spilleliste.getSelectionModel().selectedIndexProperty().addListener(
            (observasjon, gammelIndeks, nyIndeks) -> {
                int indeks = nyIndeks.intValue();
                if (indeks >= 0) {
                    playlist.velgSpor(indeks);
                    sporTittel.setText(playlist.getValgtSpor().getTittel());
                }
            }
        );

        root.setLeft(lagSpillelistePanel());
        root.setCenter(lagSpillesNaaPanel());
        root.setBottom(lagKontrollPanel());

        // Setter fokus på spillelisten når vinduet åpnes
        if (!playlist.getSpor().isEmpty()) {
            spilleliste.getSelectionModel().select(0);
        }
    }

    // Sender tilbake panel
    private VBox lagSpillelistePanel() {
        Label seksjon = new Label("SPILLELISTE");
        seksjon.getStyleClass().add("seksjon");

        VBox.setVgrow(spilleliste, Priority.ALWAYS);

        VBox panel = new VBox(12, seksjon, spilleliste);
        panel.getStyleClass().add("panel-venstre");
        panel.setPrefWidth(280);
        return panel;
    }

    // Lager panelet som viser hvilket spor som spilles nå
    private VBox lagSpillesNaaPanel() {
        Label seksjon = new Label("SPILLES NÅ");
        seksjon.getStyleClass().add("ikon-stor");

        Label ikon = new Label("♪");
        ikon.getStyleClass().addAll("tag", "tag-blaa");

        sporTittel.getStyleClass().add("tittel");
        status.getStyleClass().addAll("tag", "tag-blaa");


        // Innholdet i panelet som viser hvilket spor som spilles nå
        VBox innhold = new VBox(16, ikon, sporTittel, status); // boks med tre ting under hverandre m/ 16 px mellomromm
        innhold.setAlignment(Pos.CENTER);
        VBox.setVgrow(innhold, Priority.ALWAYS);

        // Panelet som inneholder seksjon og innhold
        VBox panel = new VBox(12, seksjon, innhold);
        panel.getStyleClass().add("panel");
        return panel;
    }

    // Lager panelet med kontrollknapper
    private VBox lagKontrollPanel() {
        forrigeKnapp.getStyleClass().add("knapp-omriss");
        spillKnapp.getStyleClass().add("knapp-primaer");
        pauseKnapp.getStyleClass().add("knapp-omriss");
        stoppKnapp.getStyleClass().add("knapp-omriss");
        nesteKnapp.getStyleClass().add("knapp-omriss");

        HBox knapper = new HBox(8, forrigeKnapp, spillKnapp, pauseKnapp, stoppKnapp, nesteKnapp);
        knapper.setAlignment(Pos.CENTER_LEFT);

        Label volumTekst = new Label("VOLUM");
        volumTekst.getStyleClass().add("mono-dim");
        volum.setPrefWidth(140);

        Region fyll = new Region();
        HBox.setHgrow(fyll, Priority.ALWAYS);

        HBox kontrollRad = new HBox(12, knapper, fyll, volumTekst, volum);
        kontrollRad.setAlignment(Pos.CENTER_LEFT);

        tid.getStyleClass().add("mono");
        HBox.setHgrow(fremdrift, Priority.ALWAYS);

        HBox fremdriftRad = new HBox(12, fremdrift, tid);
        fremdriftRad.setAlignment(Pos.CENTER_LEFT);

        VBox panel = new VBox(14, fremdriftRad, kontrollRad);
        panel.getStyleClass().add("panel-bunn");
        return panel;

        }


    public Parent getRoot() {
        return root;
    }
}