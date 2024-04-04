package ex02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Обчислення і відображення результатів
 * Містить реалізацію статичного методу main ()
 * @author xone
 * @version 2.0
 * @see Main#main
 */
public class Main {
    /** Об'єкт, що реалізує інтерфейс {@linkplain View};
     * обслуговує колекцію об'єктів {@linkplain ex01.Item2d}
     */
    private View view;
    /** Об'єкт для обчислення */
    private Calc calc;

    /** Ініціалізує поле {@linkplain Main#view view} та об'єкт Calc. */
    public Main(View view) {
        this.view = view;
        this.calc = new Calc();
    }

    /** Виводить меню. */
    protected void menu() {
        String s = null;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        do {
            do {
                System.out.println("Enter command...");
                System.out.println("1 - view");
                System.out.println("2 - generate");
                System.out.println("3 - save");
                System.out.println("4 - restore");
                System.out.println("5 - quit");
                System.out.print("> ");
                try {
                    s = in.readLine();
                } catch(IOException e) {
                    System.out.println("Error: " + e);
                    System.exit(0);
                }
            } while (s.length() != 1);
            switch (s.charAt(0)) {
                case '5':
                    System.out.println("Exit.");
                    break;
                case '1':
                    System.out.println("View current.");
                    view.viewShow();
                    break;
                case '2':
                    System.out.println("Random generation.");
                    double alpha = Math.random() * 360.0;
                    int result = calc.calculate(alpha);
                    view.viewInit();
                    view.viewShow();
                    System.out.println("Calculation result: " + result);
                    break;
                case '3':
                    System.out.println("Save current.");
                    try {
                        view.viewSave();
                        calc.save(); // Зберігаємо результати обчислення
                    } catch (IOException e) {
                        System.out.println("Serialization error: " + e);
                    }
                    view.viewShow();
                    break;
                case '4':
                    System.out.println("Restore last saved.");
                    try {
                        view.viewRestore();
                        calc.restore(); // Відновлюємо результати обчислення
                    } catch (Exception e) {
                        System.out.println("Serialization error: " + e);
                    }
                    view.viewShow();
                    break;
                default:
                    System.out.println("Wrong command.");
            }
        } while(!s.equals("5"));
    }

    /**
     * Виконується при запуску програми;
     * викликає метод {@linkplain Main#menu() menu()}
     * @param args - параметри запуску програми.
     */
    public static void main(String[] args) {
        Main main = new Main(new ViewableResult().getView());
        main.menu();
    }
}
