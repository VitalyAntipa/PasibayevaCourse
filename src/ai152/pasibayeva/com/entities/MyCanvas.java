package ai152.pasibayeva.com.entities;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * Created by antipavitaly on 5/25/17.
 */
//Класс для настройки и инициализации холста
public class MyCanvas {

    //Необходимые для работы поля
    private Canvas canvas;
    private GraphicsContext gc;

    //Конструктор, где мы инициализируем холст и его графический контекст
    public MyCanvas(Canvas canvas, AnchorPane canvasAnchorPane) {
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();

        bindCanvas(canvasAnchorPane);
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public GraphicsContext getGc() {
        return gc;
    }

    //Методя для привязки размеров холста к панели, внутри которой он находится
    public void bindCanvas(AnchorPane canvasAnchorPane){
        canvas.widthProperty().bind(canvasAnchorPane.widthProperty());
        canvas.heightProperty().bind(canvasAnchorPane.heightProperty());
    }

    //Методя дря изменения размера холста
    public void setCanvasSize(int canvasWidth, int canvasHeight){
        canvas.setWidth(canvasWidth);
        canvas.setHeight(canvasHeight);

    }

    //Метод для добавления обработчиков событий холста
    public void addCanvasListeners( ) {

        //Обработчики событий, благодаря которым мы можем рисовать по холсту

        canvas.addEventHandler(MouseEvent.MOUSE_PRESSED,
                event -> {
                    gc.beginPath();
                    gc.moveTo(event.getX(), event.getY());
                    gc.stroke();
                });

        canvas.addEventHandler(MouseEvent.MOUSE_DRAGGED,
                event -> {
                    gc.lineTo(event.getX(), event.getY());
                    gc.stroke();
                });


    }
}
