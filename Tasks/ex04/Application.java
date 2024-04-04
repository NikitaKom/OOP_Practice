package ex04;
import ex02.View;
import ex03.ViewableTable;
/** Формирует и отображает
* меню; реализует шаблон
* Singleton
* @author xone
* @version 1.0
*/
public class Application {
/** Ссылка на экземпляр класса Application; шаблон Singleton
* @see Application
*/
private static Application instance = new Application();
/** Закрытый конструктор; шаблон Singleton
* @see Application
*/
private Application() {}
/** Возвращает ссылку на экземпляр класса Application;
* шаблон Singleton
* @return ссылка на экземпляр класса Application;
* @see Application
*/
public static Application getInstance() {
return instance;
}
/** Объект, реализующий интерфейс {@linkplain View};
* обслуживает коллекцию объектов {@linkplain ex01.Item2d};
* инициализируется с помощью Factory Method
*/
private View view = new ViewableTable().getView();
/** Объект класса {@linkplain Menu};
* макрокоманда (шаблон Command)
*/
private Menu menu = new Menu();
/** Обработка команд пользователя
* @see Application
*/
public void run() {
menu.add(new ViewConsoleCommand(view));
menu.add(new GenerateConsoleCommand(view));
menu.add(new ChangeConsoleCommand(view));
menu.add(new SaveConsoleCommand(view));
menu.add(new RestoreConsoleCommand(view));
menu.execute();
}
}