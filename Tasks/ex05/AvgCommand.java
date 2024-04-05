package ex05;
import java.util.concurrent.TimeUnit;
import ex01.Item2d;
import ex02.ViewResult;
import ex04.Command;
/** Задача, используемая
* обработчиком потока;
* шаблон Worker Thread
* @author hekku
* @version 1.0
* @see Command
* @see CommandQueue
*/
public class AvgCommand implements Command /*, Runnable */ {
/** Хранит результат обработки коллекции */
private double result = 0.0;
/** Флаг готовности результата */
private int progress = 0;
/** Обслуживает коллекцию объектов {@linkplain ex01.Item2d} */
private ViewResult viewResult;
/** Возвращает поле {@linkplain MaxCommand#viewResult}
* @return значение {@linkplain MaxCommand#viewResult}
*/
public ViewResult getViewResult() {
return viewResult;
}
/** Устанавливает поле {@linkplain MaxCommand#viewResult}
* @param viewResult значение для {@linkplain MaxCommand#viewResult}
* @return новое значение {@linkplain MaxCommand#viewResult}
*/
public ViewResult setViewResult(ViewResult viewResult) {
return this.viewResult = viewResult;
}
/** Инициализирует поле {@linkplain MaxCommand#viewResult}
* @param viewResult объект класса {@linkplain ViewResult}
*/
public AvgCommand(ViewResult viewResult) {
this.viewResult = viewResult;
}
/** Возвращает результат
* @return поле {@linkplain MaxCommand#result}
*/
public double getResult() {
return result;
}
/** Проверяет готовность результата
* @return false - если результат найден, иначе - true
* @see MaxCommand#result
*/
public boolean running() {
return progress < 100;
}
/** Используется обработчиком потока {@linkplain CommandQueue};
* шаблон Worker Thread
*/
@Override
public void execute() {
progress = 0;
System.out.println("Average executed...");
result = 0.0;
int idx = 1, size = viewResult.getItems().size();
for (Item2d item : viewResult.getItems()) {
result += item.getY();
progress = idx * 100 / size;

if (idx++ % (size / 2) == 0) {
System.out.println("Average " + progress + "%");
}
try {
TimeUnit.MILLISECONDS.sleep(2000 / size);
} catch (InterruptedException e) {
System.err.println(e);
}
}
result /= size;
System.out.println("Average done. Result = " + String.format("%.2f",result));
progress = 100;
}
/**
@Override
public void run() {
execute();
}
/**/
}