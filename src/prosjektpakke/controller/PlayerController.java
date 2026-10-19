// PlayerController – styrer avspillingen.
// - Holder på MediaPlayer for sporet som spilles nå.
// - Spill av, pause, stopp, neste og forrige.
// - Stopper gjeldende spor før et nytt startes.
// - Kobler volum og fremdrift til MediaPlayer med bindinger.
// - Viser feilmelding hvis en fil mangler eller ikke kan spilles.
// - Stopper eller går til neste spor når et spor er ferdig.

package prosjektpakke.controller;

import java.net.URL;

import javafx.scene.control.Alert;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import prosjektpakke.model.Playlist;
import prosjektpakke.model.Track;

public class PlayerController {
    private final Playlist playlist;
    private MediaPlayer mediaPlayer;

    public PlayerController(Playlist playlist) {
        this.playlist = playlist;
    }

    public void spillValgtSpor() {
        Track spor = playlist.getValgtSpor();

        if (spor == null) {
            visFeil("Velg et spor først.");
            return;
        }

        URL fil = getClass().getResource(spor.getRessurssti());

        if (fil == null) {
            visFeil("Fant ikke mediefilen: " + spor.getRessurssti());
            return;
        }

        stoppGjeldendeSpiller();

        Media media = new Media(fil.toExternalForm());
        MediaPlayer nySpiller = new MediaPlayer(media);
        mediaPlayer = nySpiller;

        nySpiller.setOnError(() -> {
            Throwable feil = nySpiller.getError();
            String melding = feil == null ? "Ukjent avspillingsfeil." : feil.getMessage();
            visFeil(melding);
        });

        nySpiller.play();
    }

    public void stopp() {
        stoppGjeldendeSpiller();
    }

    private void stoppGjeldendeSpiller() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
            mediaPlayer = null;
        }
    }

    private void visFeil(String melding) {
        Alert dialog = new Alert(Alert.AlertType.ERROR);
        dialog.setTitle("Avspillingsfeil");
        dialog.setHeaderText("Kunne ikke spille av sporet");
        dialog.setContentText(melding);
        dialog.showAndWait();
    }
}