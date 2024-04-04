package ex04;
import ex01.Item2d;
import ex02.View;
import ex02.ViewResult;
import java.util.Scanner;
/** Консольная команда
* Change item;
* шаблон Command
* @author xone
* @version 1.0
*/
public class ChangeConsoleCommand
extends ChangeItemCommand
implements ConsoleCommand {
/** Объект, реализующий интерфейс {@linkplain View};
* обслуживает коллекцию объектов {@linkplain ex01.Item2d}
*/
Scanner scanner = new Scanner(System.in);
private View view;
/** Возвращает поле {@linkplain ChangeConsoleCommand#view}
* @return значение {@linkplain ChangeConsoleCommand#view}
*/
public View getView() {
return view;
}
/** Устанавливает поле {@linkplain ChangeConsoleCommand#view}
* @param view значение для {@linkplain ChangeConsoleCommand#view}
* @return новое значение {@linkplain ChangeConsoleCommand#view}
*/
public View setView(View view) {
return this.view = view;
}
/** Инициализирует поле {@linkplain ChangeConsoleCommand#view}
* @param view объект, реализующий интерфейс {@linkplain View}
*/
public ChangeConsoleCommand(View view) {
this.view = view;
}
@Override
public char getKey() {
return 'c';
}
@Override
public String toString() {
return "'c'hange";
}
@Override
public void execute() {
    System.out.println("Current width: " + view.getWidth());
    System.out.println("Enter new width for the table: ");
    int table = scanner.nextInt();
    System.out.println("New width is: " + table); 
    view.setWidth(table);
    System.out.println("Table changed");
}
}