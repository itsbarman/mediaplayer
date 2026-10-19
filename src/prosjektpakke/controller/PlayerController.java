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
import javafx.scene.control.Slider;
import javafx.util.Duration;
import javafx.scene.control.Label;

public class PlayerController {
    private final Playlist playlist;
    private MediaPlayer mediaPlayer;
    private Slider fremdrift;
    private Label tid;
    private Slider volum;

    public PlayerController(Playlist playlist) {
        this.playlist = playlist;
    }

    //Metode for slider med tid spilt osv
    public void settFremdriftskontroller(Slider fremdrift, Label tid) {
        this.fremdrift = fremdrift;
        this.tid = tid;

        fremdrift.setMin(0);
        fremdrift.setMax(0);

        // Når du slipper slideren hopper du til tid valgt
        fremdrift.setOnMouseReleased(e -> {
            if (mediaPlayer != null && mediaPlayer.getTotalDuration().toMillis() > 0) {
                mediaPlayer.seek(Duration.seconds(fremdrift.getValue()));
            }
        });
    }

    //Volum
    public void settVolumkontroll(Slider volum) {
        this.volum = volum;

        volum.valueProperty().addListener((observasjon, gammelVerdi, nyVerdi) -> {
            if (mediaPlayer != null) {
                mediaPlayer.setVolume(nyVerdi.doubleValue());
            }
        });
    }

    private String formaterTid(Duration varighet) {
        int sekunder = Math.max(0, (int) varighet.toSeconds());
        return String.format("%02d:%02d", sekunder / 60, sekunder % 60);
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
        nySpiller.setVolume(volum.getValue()); // Volum

        nySpiller.setOnError(() -> {
            Throwable feil = nySpiller.getError();
            String melding = feil == null ? "Ukjent avspillingsfeil." : feil.getMessage();
            visFeil(melding);
        });

        // Oppdater slider og tid mens sporet spilles
        nySpiller.setOnReady(() -> {
            Duration varighet = nySpiller.getTotalDuration();
            fremdrift.setMax(varighet.toSeconds());
            tid.setText("00:00 / " + formaterTid(varighet));
        });

        nySpiller.currentTimeProperty().addListener((observasjon, gammelTid, nyTid) -> {
            if (!fremdrift.isValueChanging()) {
                fremdrift.setValue(nyTid.toSeconds());
            }

            tid.setText(
                formaterTid(nyTid) + " / " +
                formaterTid(nySpiller.getTotalDuration())
            );
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

    // Forrige og neste spor
    public void nesteSpor() {
        if (playlist.nesteSpor() != null) {
            spillValgtSpor();
        }
    }

    public void forrigeSpor() {
        if (playlist.forrigeSpor() != null) {
            spillValgtSpor();
        }
    }

    //Pause og fortsett
    public boolean pauseEllerFortsett() {
        if (mediaPlayer == null) {
            return false;
        }
        if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            mediaPlayer.pause();
            return true;          
        }
        if (mediaPlayer.getStatus() == MediaPlayer.Status.PAUSED) {
            mediaPlayer.play();
            return true;
        }
        return false;  
    }

    private void visFeil(String melding) {
        Alert dialog = new Alert(Alert.AlertType.ERROR);
        dialog.setTitle("Avspillingsfeil");
        dialog.setHeaderText("Kunne ikke spille av sporet");
        dialog.setContentText(melding);
        dialog.showAndWait();
    }
}