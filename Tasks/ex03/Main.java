package ex01;
import ex02.View;
import ex03.ViewTable;
import ex03.ViewableTable;
import ex02.ViewResult;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
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
/** Отображает меню. */
private void menu() {
String s = null;
BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
do {
do {
System.out.println("Enter command...");
System.out.print("'q'uit, 'v'iew, 'w'rite, 'c'hange, 's'ave, 'r'estore: ");
try {
s = in.readLine();
} catch(IOException e) {
System.out.println("Error: " + e);
System.exit(0);
}
} while (s.length() != 1);
switch (s.charAt(0)) {
case 'q':
System.out.println("Exit.");
break;
case 'v':
System.out.println("View current.");
view.viewShow();
break;
case 'w':
System.out.print("Enter the value of alpha (in degrees): ");
double alpha = scanner.nextDouble();
                
double sum = view.calc(alpha);
System.out.println("Sum of square and cube of 10*cos(a): " + sum);

System.out.println("Number of consecutive ones: " + view.consOnes(sum));

view.Init(sum);
view.viewShow();

break;
case 'c':
    System.out.println("Current width: " + view.getWidth());
    System.out.println("Enter new width for the table: ");
    int table = scanner.nextInt();
    System.out.println("New width is: " + table); 
    view.setWidth(table);
    System.out.println("Table changed");
    break;
case 's':
System.out.println("Save current.");
try {
view.viewSave();
} catch (IOException e) {
System.out.println("Serialization error: " + e);
}
view.viewShow();
break;
case 'r':
System.out.println("Restore last saved.");
try {
view.viewRestore();
} catch (Exception e) {
System.out.println("Serialization error: " + e);
}
view.viewShow();
break;
default:
System.out.print("Wrong command. ");
}
} while(s.charAt(0) != 'q');
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
