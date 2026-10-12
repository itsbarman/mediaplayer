// MainApp – startpunktet for programmet.
// - Starter JavaFX og åpner hovedvinduet.
// - Lager spillelisten, kontrolleren og visningene.
// - Bytter mellom hjemskjerm og spillerskjerm.

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

public class MainApp extends Application {

    private final BorderPane root = new BorderPane();
    private final Button hjemFane = new Button("HJEM");
    private final Button spillerFane = new Button("SPILLER");

    private HomeView homeView;
    private PlayerView playerView;

    @Override 
    public void start(Stage stage) {

        homeView = new HomeView(this::visSpiller);
        playerView = new PlayerView();
    }


}
