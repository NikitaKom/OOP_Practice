package ex04;
import java.io.IOException;
import ex02.View;
/** Консольная команда
* Save;
* шаблон Command
* @author xone
* @version 1.0
*/
public class SaveConsoleCommand implements ConsoleCommand {
/** Объект, реализующий интерфейс {@linkplain View};
* обслуживает коллекцию объектов {@linkplain ex01.Item2d}
*/
private View view;
/** Инициализирует поле {@linkplain SaveConsoleCommand#view}
* @param view объект, реализующий интерфейс {@linkplain View}
*/
public SaveConsoleCommand(View view) {
this.view = view;
}
@Override
public char getKey() {
return 's';
}
@Override
public String toString() {
return "'s'ave";
}
@Override
public void execute() {
System.out.println("Save current.");
try {
view.viewSave();
} catch (IOException e) {
System.err.println("Serialization error: " + e);
}
view.viewShow();
    }
}