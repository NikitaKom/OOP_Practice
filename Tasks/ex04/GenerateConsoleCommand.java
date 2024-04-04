package ex04;
import ex02.View;
import java.util.Scanner;
/** Консольная команда
* Generate;
* шаблон Command
* @author xone
* @version 1.0
*/
public class GenerateConsoleCommand implements ConsoleCommand {
/** Объект, реализующий интерфейс {@linkplain View};
* обслуживает коллекцию объектов {@linkplain ex01.Item2d}
*/
Scanner scanner = new Scanner(System.in);
private View view;
/** Инициализирует поле {@linkplain GenerateConsoleCommand#view}
* @param view объект, реализующий интерфейс {@linkplain View}
*/
public GenerateConsoleCommand(View view) {
this.view = view;
}
@Override
public char getKey() {
return 'w';
}
@Override
public String toString() {
return "'w'rite";
}
@Override
public void execute() {
System.out.print("Enter the value of alpha (in degrees): ");
double alpha = scanner.nextDouble();
                
double sum = view.calc(alpha);
System.out.println("Sum of square and cube of 10*cos(a): " + sum);

System.out.println("Number of consecutive ones: " + view.consOnes(sum));

view.Init(sum);
view.viewShow();

}
}