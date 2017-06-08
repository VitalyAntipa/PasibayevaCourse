package ai152.pasibayeva.com.entities;

import javafx.geometry.Insets;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

/**
 * Created by antipavitaly on 5/25/17.
 */

//Класс для инициализации и настройки слайдеров
public class Sliders{
    //Необходимые для работы поля
    private  Slider rSlider;
    private  Slider gSlider;
    private Slider bSlider;
    private Slider opacitySlider;
    private Label colorLabel;

    private int red;
    private int green;
    private int blue;
    private double opacity;


    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }

    //Конструктор, в котором происзодиз инициализация полей
    public Sliders(Slider rSlider, Slider gSlider, Slider bSlider, Slider opacitySlider, Label colorLabel, GraphicsContext gc) {
        this.rSlider = rSlider;
        this.gSlider = gSlider;
        this.bSlider = bSlider;
        this.opacitySlider = opacitySlider;

        this.colorLabel = colorLabel;

        this.red = 0;
        this.green = 0;
        this. blue = 0;
        this.opacity = 1.0;

        initSlider(colorLabel);


    }

    public Slider getOpacitySlider() {
        return opacitySlider;
    }

    public Slider getrSlider() {

        return rSlider;
    }

    public Slider getgSlider() {
        return gSlider;
    }

    public Slider getbSlider() {
        return bSlider;
    }

    /*
    Метод, в котором происходит инициализация минимальных, максимальных,
    а так же текущих значений слайдеров. А так же привязка нашего результирующего
    цвета к colorLabel, чтобы пользователь смог увидеть какой цвет у него получился.
     */
    private  void initSlider(Label colorLabel){

        rSlider.setMin(0);
        rSlider.setMax(255);
        rSlider.setValue(red);


        gSlider.setMin(0);
        gSlider.setMax(255);
        gSlider.setValue(green);


        bSlider.setMin(0);
        bSlider.setMax(255);
        bSlider.setValue(blue);

        opacitySlider.setMin(0.0);
        opacitySlider.setMax(1.0);
        opacitySlider.setValue(opacity);

        colorLabel.setBackground(new Background(new BackgroundFill(Color.rgb(red,green,blue), CornerRadii.EMPTY, Insets.EMPTY)));

    }

    //Метод для добавления обработчиков событий для слайдеров
    public void addSliderListeners(GraphicsContext gc){

        //Обработчик событий изменения значения слайдера, отвечающего за красный цвет
        rSlider.valueProperty().addListener((observable, oldValue, newValue) ->
        {
            //Заносим новое значение слайдера в переменную
            red = newValue.intValue();
            //Изменяем цвет кисти
            gc.setStroke(Color.rgb(red,green,blue,opacity));
            //Заливаем фон colorLabel цветом, что получился в результате
            colorLabel.setBackground(new Background(new BackgroundFill(Color.rgb(red,green,blue), CornerRadii.EMPTY, Insets.EMPTY)));
        });

        //Обработчик событий изменения значения слайдера, отвечающего за зеленый цвет
        gSlider.valueProperty().addListener((observable, oldValue, newValue) ->
        {
            green = newValue.intValue();
            gc.setStroke(Color.rgb(red,green,blue,opacity));
            colorLabel.setBackground(new Background(new BackgroundFill(Color.rgb(red,green,blue), CornerRadii.EMPTY, Insets.EMPTY)));
        });

        //Обработчик событий изменения значения слайдера, отвечающего за синий цвет
        bSlider.valueProperty().addListener((observable, oldValue, newValue) ->
        {
            blue = newValue.intValue();
            gc.setStroke(Color.rgb(red,green,blue,opacity));
            colorLabel.setBackground(new Background(new BackgroundFill(Color.rgb(red,green,blue), CornerRadii.EMPTY, Insets.EMPTY)));
        });

        //Обработчик событий изменения значения слайдера, отвечающего за прозрачность кисти
        opacitySlider.valueProperty().addListener((observable, oldValue, newValue) ->
        {
            //Заносим новое значение слайдера в переменную
            opacity = newValue.doubleValue();
            //Изменяем прозрачность кисти
            gc.setStroke(Color.rgb(red,green,blue,opacity));
        });

    }
}
