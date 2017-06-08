package ai152.pasibayeva.com.entities;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 * Created by Ольга on 25.04.2017.
 */

//Класс для инициализации кнопок
public class Buttons{

    //Поля для трех кнопок-кистей
    private Button brushButton;
    private Button eraserButton;
    private Button thicknessButton;
    //Переменная, которая позволяет контролировать ширину кисти
    private int i = 1;


    //Конструктор с инициализацией кнопок
    public Buttons(Button brushButton, Button eraserButton, Button thicknessButton, GraphicsContext gc) {
        this.brushButton = brushButton;
        this.eraserButton = eraserButton;
        this.thicknessButton = thicknessButton;

    }

    //Метод, добавляющий обработчики событий для кнопок
    public void addButtonsListeners(GraphicsContext gc, Slider rSlider, Slider gSlider, Slider bSlider,Slider opacitySlider, int red, int green, int blue){
        //Обработчик событий для кнопки, которая инициализирует кисть-резинку
        eraserButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

            //Открючение слайдеров, чтобы нельзя было контролировать цвет и прозрачность кисти
            rSlider.setDisable(true);
            gSlider.setDisable(true);
            bSlider.setDisable(true);
            opacitySlider.setDisable(true);

            //Задаем цвет кисти(для резинки он принимает значение нашего фона)
            gc.setStroke(Color.rgb(244, 244, 244));
        });

        //Обработчик событий для кнопки, которая инициализирует ксить для рисования
        brushButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

            //Включаем слайдеры, которые позволяют нам контролировать цве и прозрачность кисти
            rSlider.setDisable(false);
            gSlider.setDisable(false);
            bSlider.setDisable(false);
            opacitySlider.setDisable(false);

            //Задаем цвет кисти(берем значения из переменных, где сохраняются значения цвета)
            gc.setStroke(Color.rgb(red, green, blue));
        });


        //Обработчик событий для кнопки, которая позвляет контролировать ширину кисти
        thicknessButton.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {

            i+=2;

            if(i>=10){
                i=1;
            }

            //Задаем ширину кисти
            gc.setLineWidth(i);
        });



    }


}
