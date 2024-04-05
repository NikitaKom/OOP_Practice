package ex01;
import ex02.View;
import ex02.ViewableResult;
import ex03.ViewableTable;
import ex04.ChangeConsoleCommand;
import ex04.GenerateConsoleCommand;
import ex04.Menu;
import ex04.ViewConsoleCommand;
import ex05.ExecuteConsoleCommand;
/** Вычисление и отображение
* результатов; содержит реализацию
* статического метода main()
* @author xone
* @version 5.0
* @see Main#main
*/
public class Main {
/** Объект, реализующий интерфейс {@linkplain View};
* обслуживает коллекцию объектов {@linkplain ex01.Item2d};
* инициализируется с помощью Factory Method
*/
private View view = new ViewableTable().getView();
/** Объект класса {@linkplain Menu};
* макрокоманда (шаблон Command)
*/
private Menu menu = new Menu();
/** Обработка команд пользователя */
public void run() {
menu.add(new ViewConsoleCommand(view));
menu.add(new GenerateConsoleCommand(view));
menu.add(new ChangeConsoleCommand(view));
menu.add(new ExecuteConsoleCommand(view));
menu.execute();
}
/** Выполняется при запуске программы
* @param args параметры запуска программы
*/
public static void main(String[] args) {
Main main = new Main();
main.run();
}
}
