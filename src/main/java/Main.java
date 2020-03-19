import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main extends Application {

    public static void main(String[] args) {
         launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Входные данные из:");
        System.out.println("1 - файла");
        System.out.println("2 - консоли");

        int inpWay = scanner.nextInt();

        double a = 0;
        double b = 0;
        double e = 0;

        if (inpWay == 2) { // input from console
            System.out.println("Введите начало интервала, конец интервала и погрешность через пробел:");
            a = scanner.nextDouble();
            b = scanner.nextDouble();
            e = scanner.nextDouble();


        } else if (inpWay == 1) { //input from file
            System.out.println("Введите путь к файлу:");

            //input-format of file: <start of the interval> <end of the interval> <epsilon>
            //use dot in fractional number in file
            String path = scanner.next().trim(); // file's path
            String line = null;
            try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
                line = reader.readLine();
            } catch (FileNotFoundException e1) {
                System.out.println("Файл не найден!");
            } catch (IOException e2) {
                System.out.println("Не удалось открыть файл!");
            }

            a = Double.parseDouble(line.split(" ")[0]);
            b = Double.parseDouble(line.split(" ")[1]);
            e = Double.parseDouble(line.split(" ")[2]);
        } else {
            System.out.println("Некорректный выбор!");
        }

        System.out.println("Записать ответ в:");
        System.out.println("1 - файл");
        System.out.println("2 - консоль");
        int outWay = scanner.nextInt();

        if (outWay == 2) { //consoles output

            System.out.println("Meтод Хорд: " + Methods.horda(a, b, e));
            System.out.println("Meтод Секущих: " + Methods.sekushie(a, b, e));
            System.out.println("Meтод Простой итерации: " + Methods.iteraz(a, b, e));

        } else if (outWay == 1) { // files output
            System.out.println("Введите путь к файлу:");
            String path = scanner.next().trim(); // file's path

            try (FileWriter writer = new FileWriter(path, true)) {

                writer.write("\nMeтод Хорд: " + Methods.horda(a, b, e) + "\n");
                writer.write("Meтод Секущих: " + Methods.sekushie(a, b, e) + "\n");
                writer.write("Meтод Простой итерации: " + Methods.iteraz(a, b, e) + "\n");

                writer.flush();
            } catch (IOException ex) {
                System.out.println("Файл не найден!");
            }
        } else {
            System.out.println("Некорректный выбор!");
        }


        //вывод графика
        stage.setTitle("График");

        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("X");
        yAxis.setLabel("Y");

        final LineChart<Number, Number> lineChart =
                new LineChart<Number, Number>(xAxis, yAxis);

        XYChart.Series series = new XYChart.Series();
        double x = a - 15;
        while(x < b + 10) {
            series.getData().add(new XYChart.Data(x, Methods.f(x)));
            x = x + 1;
        }

        Scene scene = new Scene(lineChart, 1000, 800);
        lineChart.getData().add(series);

        stage.setScene(scene);
        stage.show();
    }

}
