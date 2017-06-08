package ai152.pasibayeva.com.entities;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by antipavitaly on 5/25/17.
 */
//Класс для инициализации и настройки меню бара и его пунктов
public class MyMenuBar {

    //Обьявление необходимых для работы полей
    private MenuItem clearCase;
    private MenuItem loadCase;
    private MenuItem saveCase;
    private MenuItem aboutCase;

    private Image img;
    private double imgHeight;
    private double imgWidth;

    private Canvas canvas;

    private GraphicsContext gc;

    //Конструктор для инициализации полей
    public MyMenuBar(MenuItem clearCase, MenuItem loadCase, MenuItem saveCase, MenuItem aboutCase, Canvas canvas) {
        this.clearCase = clearCase;
        this.loadCase = loadCase;
        this.saveCase = saveCase;
        this.aboutCase = aboutCase;

        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();

    }

    //Метод, по средством которого мы загружаем и рисуем картинку
    private void initDrawImage(){

        double canvasWidth = gc.getCanvas().getWidth();
        double canvasHeight = gc.getCanvas().getHeight();

        //Заносим результат работы FileChooser(картинку) в переменную img
        img =  initFileChooser();

        //Проверяем не является ли она пустой
        if (img != null){

            imgHeight = img.getHeight();
            imgWidth = img.getWidth();

            //Рисуем картинку на холста
            gc.drawImage(img, 0, 0, canvasWidth, canvasHeight);
            gc.scale(imgWidth,imgHeight);

        } else {

            //Выводим окно ошибки
            Alert dialog = new Alert(Alert.AlertType.INFORMATION);
            dialog.setHeaderText("Error");
            dialog.setContentText("Cannot resolve the file");
            dialog.showAndWait();

        }
    }

    //Метод для очистик канваса
    private void clearCanvas(){

        gc.clearRect(0,              //x of the upper left corner
                0,              //y of the upper left corner
                canvas.getWidth(),    //width of the rectangle
                canvas.getHeight());
    }

    //Метод для сохранения картикни, которую нарисова пользователь
    private void saveImg() {
        //Объект, в который мы заносим размеры холста
        WritableImage wim = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
        //Делаем "снимок" канваса
        canvas.snapshot(null, wim);

        //Создаем файл для записи
        File file = new File("CanvasImage.png");


        try {
            //Инициируем запись картинки по средствам потока
            ImageIO.write(SwingFXUtils.fromFXImage(wim, null), "png", file);
        } catch (Exception s) {
        }
    }

    //Метод, который добавляет обработчики событий для пунктов меню
    public void addMenuBarListeners(){

        loadCase.setOnAction(event -> {

            initDrawImage();

        });

        saveCase.setOnAction(event -> {

            saveImg();

        });

        clearCase.setOnAction(e->{

            clearCanvas();

        });

        aboutCase.setOnAction(e->{

            try {
                openModal();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });



    }

    //Метод для открытия модального окна с описанием приложения
    private void openModal() throws IOException {

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        //Путь к необходимому файлу
        String fxmlFile = "/ai152/pasibayeva/res/xml/aboutWindow.fxml";
        //Загружаем fxml файл
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
        Parent root = loader.load();
        //Задаем его заголовок
        stage.setTitle("About");
        //Задаём сцену
        stage.setScene(new Scene(root));
        //Отображаем окно
        stage.showAndWait();
    }

    /*
    Методя, который инициализируе обьект FileChooser,
    который возвращает нам картинку из системы, которую мы выбрали.
     */
    private Image initFileChooser(){

        //Создаем объект FileChooser
        FileChooser fChooser = new FileChooser();

        fChooser.setTitle("Choose a picture!");

        //Создаем два фильтра(для PNG и JPEG файлов)
        fChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PNG Files", "*.png"), new FileChooser.ExtensionFilter("JPEG Files", "*.jpg"));

        //Открываем FileChooser и по выполению записываем выбранный файл
        File selectedFile = fChooser.showOpenDialog(null);

        //Проверяем не является ли он пустым
        if (selectedFile != null) {
            try {
                //Считываем его
                BufferedImage bufferedImage = ImageIO.read(selectedFile);
                //Конвертируем в обьект Image
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                //Возвращаем
                return image;
            } catch (IOException ex) {

            }

        }

        return null;

    }

}
