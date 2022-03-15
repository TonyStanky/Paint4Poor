import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.paint.*;
import javafx.scene.control.*;
import javafx.event.*;
import javafx.scene.input.*;
import javafx.scene.paint.Color;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Paint4Poor2022 extends Application {
  // Anfang Attribute
  private Pane root;                // global definiert für späteren Zugriff
  private Pixel[][] leinwand;       // Ein Array aus erweiterten Buttons
  private Color[][] bild;           // Ein reines Farb-Array
  private String grundStyle = "-fx-border-width: 0;-fx-background-radius: 0;-fx-border-color:LIGHTGRAY;-fx-border-insets: 0;-fx-border-radius: 0;"; // für alle Pixel 
  private Color aktuelleFarbe = Color.BLACK; 
  private ToggleGroup toggleGroup1 = new ToggleGroup();
  private ColorPicker colorPicker1 = new ColorPicker();
  private Label label1 = new Label();
  public boolean mouse = false;
  // Ende Attribute
  
  
  // Belegt die beiden Arrays leinwand und bild mit Startwerten
  public void generiereLeinwand(int spalten, int zeilen, int pixelbreite) {
    int linkerRand = 10;
    int obererRand = 10;
    String pixelStyle;
    leinwand = new Pixel[spalten][zeilen];
    bild = new Color[spalten][zeilen];
    for (int y = 0; y < zeilen; y++) {
      for (int x = 0; x < spalten; x++) {
        bild[x][y] = Color.WHITE;
        leinwand[x][y] = new Pixel(x, y);
        leinwand[x][y].setLayoutX(linkerRand + x * pixelbreite);
        leinwand[x][y].setLayoutY(obererRand + y * pixelbreite);
        leinwand[x][y].setPrefHeight(pixelbreite);
        leinwand[x][y].setPrefWidth(pixelbreite);
        pixelStyle = grundStyle + "-fx-background-color: #" + leinwand[x][y].getFarbe().toString().substring(2)+";";
        leinwand[x][y].setStyle(pixelStyle);                      
        if(mouse){
          leinwand[x][y].setOnMouseEntered(
          (event) -> {leinwand_Action(event);} 
          );
          root.getChildren().add(leinwand[x][y]);
        }
      } 
    }
  }
  
  
  // Das wird alles einmal beim Starten ausgeführt
  public void start(Stage primaryStage) { 
    root = new Pane();
    Scene scene = new Scene(root, 640, 508);
    // Anfang Komponenten
    colorPicker1.setLayoutX(493);
    colorPicker1.setLayoutY(49);
    colorPicker1.setPrefHeight(25);
    colorPicker1.setPrefWidth(133);
    root.getChildren().add(colorPicker1);
    label1.setLayoutX(492);
    label1.setLayoutY(22);
    label1.setPrefHeight(20);
    label1.setPrefWidth(110);
    label1.setText("Farbwahl:");
    root.getChildren().add(label1);
    // Ende Komponenten
    
    
   
    // erzeugen des "Array of Button" sowie initialiseren von bild[][]
    generiereLeinwand(16,16,30);
    
    primaryStage.setOnCloseRequest(e -> System.exit(0));
    primaryStage.setTitle("Paint4Poor2022");
    primaryStage.setScene(scene);
    primaryStage.show();
    
    scene.setOnMousePressed(mouse = true);
    scene.setOnMouseReleased(mouse = false);
    
  } 
  // Anfang Methoden

  // wenn irgendein Button der Leinwand gedrückt wird
  public void leinwand_Action(Event evt) {
    int x = ((Pixel) evt.getSource()).getX();
    int y = ((Pixel) evt.getSource()).getY();
    aktuelleFarbe = colorPicker1.getValue();
    leinwand[x][y].setFarbe(aktuelleFarbe);
    leinwand[x][y].setStyle(grundStyle + "-fx-background-color: #" + leinwand[x][y].getFarbe().toString().substring(2)+";"); 
    bild[x][y] = aktuelleFarbe;
  } // end of button1_Action
  
  // Hauptprogramm
  public static void main(String[] args) {
    launch(args);
  } 
  // Ende Methoden
   
} 

