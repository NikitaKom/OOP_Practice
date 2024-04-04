package ex01;
import ex02.View;
import java.util.Scanner;
import ex04.Application;


/** Вычисление и отображение результатов.
* Содержит реализацию статического метода main().
* @author hekku
* @version 1.0
* @see Main#main
*/
public class Main {
Scanner scanner = new Scanner(System.in);
boolean running = true;


/** Объект, реализующий интерфейс {@linkplain View};
* обслуживает коллекцию объектов {@linkplain ex01.Item2d}
*/
private View view;

/** Инициализирует поле {@linkplain Main#view view}.
     * @param view - переменная для вывода коллекции*/
public Main(View view) {
this.view = view;
}

/** Выполняется при запуске программы.
* Вычисляется значение функции для различных аргументов.
* Результаты вычислений выводятся на экран.
* @param args - параметры запуска программы.
*/
public static void main(String[] args) {
Application app = Application.getInstance();
app.run();
}
}
