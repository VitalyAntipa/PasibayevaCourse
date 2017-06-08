package ai152.pasibayeva.com.controllers;

import ai152.pasibayeva.com.entities.Buttons;
import ai152.pasibayeva.com.entities.MyCanvas;
import ai152.pasibayeva.com.entities.MyMenuBar;
import ai152.pasibayeva.com.entities.Sliders;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


/**
 * Created by Ольга on 07.04.2017.
 */
public class Controller  {

    //Ссылки на View объекты, которые мы описали в fxml файле
    @FXML
    private Canvas canvas;

    @FXML
    private AnchorPane canvasAnchorPane;

    @FXML
    private BorderPane pane;

    @FXML
    private Slider sliderR;

    @FXML
    private Slider sliderG;

    @FXML
    private  Slider sliderB;

    @FXML
    private Slider sliderOpacity;

    @FXML
    private Button brushButton;

    @FXML
    private Button eraserButton;

    @FXML
    private  Button thicknessButton;

    @FXML
    private  Label colorLabel;

    @FXML
    private MenuItem saveCase;

    @FXML
    private  MenuItem loadCase;

    @FXML
    private  MenuItem clearCase;

    @FXML
    private  MenuItem aboutCase;

    //Необходимые для работы поля
    private MyCanvas myCanvas;
    private Sliders sliders;
    private Buttons btns;
    private MyMenuBar menuBar;

    @FXML
    public void initialize(){

        //Инициализация объектов классов, в которых происходит инициализация и настройка View обьектов приложения
         myCanvas = new MyCanvas(canvas, canvasAnchorPane);
        GraphicsContext gc = myCanvas.getGc();
         sliders = new Sliders(sliderR, sliderG, sliderB,sliderOpacity ,colorLabel, gc );
         btns = new Buttons(brushButton, eraserButton, thicknessButton,gc );
         menuBar = new MyMenuBar(clearCase,loadCase,saveCase,aboutCase,myCanvas.getCanvas());

         //инициализация обработчиков событий
        myCanvas.addCanvasListeners();
        sliders.addSliderListeners(gc);
        btns.addButtonsListeners(gc,sliders.getrSlider(), sliders.getgSlider(),sliders.getbSlider(),sliders.getOpacitySlider(),sliders.getRed(), sliders.getGreen(), sliders.getBlue());
        menuBar.addMenuBarListeners();

    }








}

