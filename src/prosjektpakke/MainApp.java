// Launcher 
package prosjektpakke;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import prosjektpakke.view.HomeView;
import prosjektpakke.view.PlayerView;
import prosjektpakke.model.Playlist;

public class MainApp extends Application {

    private final BorderPane root = new BorderPane();
    private final Button hjemFane = new Button("HJEM");
    private final Button spillerFane = new Button("SPILLER");

    private HomeView homeView;
    private PlayerView playerView;

    @Override
    public void start(Stage stage) {
        homeView = new HomeView(this::visSpiller);
        Playlist playlist = new Playlist();
        playerView = new PlayerView(playlist);

        root.setTop(lagToppLinje());
        root.setBottom(lagStatusLinje());
        visHjem();

        Scene scene = new Scene(root, 1100, 720);
        scene.getStylesheets().add(getClass().getResource("resources/style.css").toExternalForm());

        stage.setTitle("MediaHub");
        stage.setMinWidth(800);
        stage.setMinHeight(500);
        stage.setScene(scene);
        stage.show();
    }

    private HBox lagToppLinje() {
        Label logo = new Label("Barman Suite");
        logo.getStyleClass().add("logo");

        hjemFane.getStyleClass().add("fane");
        spillerFane.getStyleClass().add("fane");
        hjemFane.setOnAction(e -> visHjem());
        spillerFane.setOnAction(e -> visSpiller());

        Label undertittel = new Label("SMART MULTIMEDIA HUB");
        undertittel.getStyleClass().add("mono-dim");

        Region fyll = new Region();
        HBox.setHgrow(fyll, Priority.ALWAYS);

        Label kurs = new Label("OBJ2000");
        kurs.getStyleClass().add("mono-dim");

        HBox topp = new HBox(16, logo, hjemFane, spillerFane, undertittel, fyll, kurs);
        topp.setAlignment(Pos.CENTER_LEFT);
        topp.getStyleClass().add("topplinje");
        return topp;
    }

    private HBox lagStatusLinje() {
        Label venstre = new Label("KLAR");
        venstre.getStyleClass().add("mono-dim");

        Region fyll = new Region();
        HBox.setHgrow(fyll, Priority.ALWAYS);

        Label hoyre = new Label("JAVAFX 25");
        hoyre.getStyleClass().add("mono-dim");

        HBox linje = new HBox(venstre, fyll, hoyre);
        linje.getStyleClass().add("statuslinje");
        return linje;
    }

    private void visHjem() {
        root.setCenter(homeView.getRoot());
        settAktivFane(hjemFane);
    }

    private void visSpiller() {
        root.setCenter(playerView.getRoot());
        settAktivFane(spillerFane);
    }

    private void settAktivFane(Button aktiv) {
        hjemFane.getStyleClass().remove("aktiv");
        spillerFane.getStyleClass().remove("aktiv");
        aktiv.getStyleClass().add("aktiv");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
