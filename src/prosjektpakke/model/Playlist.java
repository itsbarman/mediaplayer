package prosjektpakke.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Playlist {
    private final ObservableList<Track> spor = FXCollections.observableArrayList();
    private int valgtIndeks = -1;

    public Playlist() {
        spor.addAll(
            new Track("Spor 1", "/prosjektpakke/resources/media/Spor1.mp3"),
            new Track("Spor 2", "/prosjektpakke/resources/media/Spor2.mp4"),
            new Track("Spor 3", "/prosjektpakke/resources/media/Spor3.mp4")
        );

        if (!spor.isEmpty()) {
            valgtIndeks = 0;
        }
    }

    public ObservableList<Track> getSpor() {
        return spor;
    }

    public Track getValgtSpor() {
        if (valgtIndeks < 0 || valgtIndeks >= spor.size()) {
            return null;
        }
        return spor.get(valgtIndeks);
    }

    public void velgSpor(int indeks) {
        if (indeks >= 0 && indeks < spor.size()) {
            valgtIndeks = indeks;
        }
    }

    public Track nesteSpor() {
        if (spor.isEmpty()) {
            return null;
        }

        valgtIndeks = (valgtIndeks + 1) % spor.size();
        return getValgtSpor();
    }

    public Track forrigeSpor() {
        if (spor.isEmpty()) {
            return null;
        }

        valgtIndeks = (valgtIndeks - 1 + spor.size()) % spor.size();
        return getValgtSpor();
    }
}
