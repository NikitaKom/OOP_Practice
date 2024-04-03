# OOP_Practice
## Завдання 1
(виконано якщо ви бачите цей репозиторій)

## Завдання 2
### Основне завдання:  Визначити найбільшу довжину послідовності 1 в подвійному поданні цілісної суми квадрата і куба 10*cos(α).

*1. Розробити клас, що серіалізується, для зберігання параметрів і результатів обчислень. Використовуючи агрегування, розробити клас для знаходження рішення задачі.*

## Calc.java:
```java
package ex01;

import java.io.Serializable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.Math;

/**
 * Клас для обчислення кількості послідовних одиниць у подвійному представленні цілісної суми квадрата і куба 10*cos(α).
 */
public class Calc {
    /** Результат обчислення. */
    private int result;
    private double sum = 0;


    /** Конструктор класу Calc. Ініціалізує результат обчислення. */
    public Calc() {
        result = 0;
    }


/**
 * Метод для обчислення кількості послідовних одиниць у подвійному представленні цілісної суми квадрата і куба 10*cos(α).
 * @param alpha кут α в градусах
 * @return кількість послідовних одиниць
 */
public int calculate(double alpha) {
    double angle = Math.toRadians(alpha);
    sum += Math.pow(10 * Math.cos(angle), 2) + Math.pow(10 * Math.cos(angle), 3);
    String binary = Integer.toBinaryString((int) sum);
    int maxLengthOfSeq = 0;
    int currentLengthOfSeq = 0;

for (char num : binary.toCharArray()) {
    if (num == '1') {
        currentLengthOfSeq++;
    }

    if (num != '1') {
        if (currentLengthOfSeq > maxLengthOfSeq) {
            maxLengthOfSeq = currentLengthOfSeq;
        }
        currentLengthOfSeq = 0;
    }
}

if (currentLengthOfSeq > maxLengthOfSeq) {
    maxLengthOfSeq = currentLengthOfSeq;
}
    result = maxLengthOfSeq;
    return result;
}





    /**
     * Метод для збереження результату обчислення у файл.
     * @throws IOException якщо виникає помилка серіалізації
     */
    public void save() throws IOException {
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("ResultData.bin"))) {
            os.writeInt(result);
        }
    }

    /**
     * Метод для відновлення результату обчислення з файлу.
     * @throws IOException якщо виникає помилка десеріалізації
     */
    public void restore() throws IOException {
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream("ResultData.bin"))) {
            result = is.readInt();
        }
    }

    /**
     * Метод для отримання результату обчислення (кількості одиниць в числі).
     * @return результат обчислення
     */
    public int getResult() {
        return result;
    }
        /**
     * Метод для отримання результату обчислення (суми квадрату і кубу числа).
     * @return результат обчислення
     */
    public double getSum() {
        return sum;
    }
}
}

```
*2. Розробити клас для демонстрації в діалоговому режимі збереження та відновлення стану об'єкта, використовуючи серіалізацію. Показати особливості використання transient полів.*

## Item2d.java:
```java
package ex01;

import java.io.Serializable;

/**
 * Клас для зберігання даних та результатів обчислення.
 */
public class Item2d implements Serializable {
    /** Аргумент функції. */
    private double x;
    /** Результат обчислення функції. */
    private double y;

    /** Згенерована версія для серіалізації. */
    private static final long serialVersionUID = 1L;

    /** 
     * Конструктор, який ініціалізує аргумент та результат обчислення.
     */
    public Item2d() {
        x = 0.0;
        y = 0.0;
    }

    /**
     * Метод для встановлення значення аргумента.
     * @param x значення аргумента
     * @return значення аргумента
     */
    public double setX(double x) {
        return this.x = x;
    }

    /**
     * Метод для отримання значення аргумента.
     * @return значення аргумента
     */
    public double getX() {
        return x;
    }

    /**
     * Метод для встановлення значення результату обчислення.
     * @param y значення результату обчислення
     * @return значення результату обчислення
     */
    public double setY(double y) {
        return this.y = y;
    }

    /**
     * Метод для отримання значення результату обчислення.
     * @return значення результату обчислення
     */
    public double getY() {
        return y;
    }

    /**
     * Метод для встановлення значення аргумента та результату обчислення.
     * @param x значення аргумента
     * @param y значення результату обчислення
     * @return поточний об'єкт Item2d
     */
    public Item2d setXY(double x, double y) {
        this.x = x;
        this.y = y;
        return this;
    }

    /**
     * Представлення об'єкту у вигляді рядка.
     * @return рядкове представлення об'єкту
     */
    @Override
    public String toString() {
        return "x = " + x + ", y = " + y;
    }

    /**
     * Перевірка на рівність об'єктів.
     * @param obj об'єкт для порівняння
     * @return true, якщо об'єкти рівні, інакше false
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        Item2d other = (Item2d) obj;
        if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
            return false;
        if (Math.abs(Math.abs(y) - Math.abs(other.y)) > .1e-10)
            return false;
        return true;
    }
}

```

## Main.Java:
```java
package ex01;

import java.util.Scanner;

/**
 * Головний клас програми.
 */
public class Main {
    /**
     * Метод для запуску програми.
     * @param args параметри командного рядка
     */
    public static void main(String[] args) {
        // Створюємо об'єкт класу Calc для обчислення суми квадрата і куба 10*cos(α)
        Calc calc = new Calc();
        
        // Введення значення кута α
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the value of alpha (in degrees): ");
        double alpha = scanner.nextDouble();
        
        // Обчислюємо суму квадрата і куба 10*cos(α) та виводимо на екран
        int sum = calc.calculate(alpha);
        System.out.println("Sum of square and cube of 10*cos(a): " + calc.getSum());

        
        // Виводимо кількість послідовних одиниць
        System.out.println("Number of consecutive ones: " + calc.getResult());
        
        // Завершуємо роботу з Scanner
        scanner.close();
    }
}


```
*3. Розробити клас для тестування коректності результатів обчислень та серіалізації/десеріалізації. Використовувати докладні коментарі для автоматичної генерації документації засобами javadoc.*

## MainTest.java:
```java
package ex01;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Клас для тестування класів Main та Calc.
 */
public class MainTest {
    /**
     * Перевірка обчислення.
     */
    @Test
    public void testCalculate() {
        Calc calc = new Calc();
        // Перевірка для α = 0
        assertEquals(32, calc.calculate(0.0));
        // Перевірка для α = 90
        assertEquals(32, calc.calculate(90.0));
        // Перевірка для α = 180
        assertEquals(32, calc.calculate(180.0));
        // Перевірка для α = 270
        assertEquals(32, calc.calculate(270.0));
        // Перевірка для α = 360
        assertEquals(32, calc.calculate(360.0));
    }

    /**
     * Перевірка збереження та відновлення результату.
     */
    @Test
    public void testSaveRestore() {
        Calc calc = new Calc();
        try {
            // Обчислення для α = 90 та збереження результату
            calc.calculate(90.0);
            calc.save();
            // Відновлення результату та перевірка
            calc.restore();
            assertEquals(32, calc.getResult());
        } catch (IOException e) {
            // Викидати виняток, якщо сталася помилка при збереженні/відновленні
            throw new RuntimeException("Error during save/restore: " + e.getMessage());
        }
    }

    /**
     * Перевірка роботи програми.
     */
    @Test
    public void testMain() {
        // Встановлюємо вхідні дані через System.in
        String input = "90.0\n"; // Введення значення α = 90.0
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Запускаємо метод main з класу Main для тестування
        Main.main(null);

        // Перевіряємо результати
        assertEquals(32, Main.calc.getResult()); // Перевірка кількості послідовних одиниць
        assertEquals(32, Main.calc.getSum(), 0.01); // Перевірка суми квадрата і куба 10*cos(α)
    }
}


```
Результат:

![](images/TaskResult1.png)

## Завдання 3
*1. Як основа використовувати вихідний текст проекту попередньої лабораторної роботи. Забезпечити розміщення результатів обчислень у колекції з можливістю збереження/відновлення.*

Оновленний файл Main який викликає новий набір методів

## Main:
```java
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

```
*2. Використовуючи шаблон проектування Factory Method (Virtual Constructor), розробити ієрархію, що передбачає розширення за рахунок додавання нових відображуваних класів.*

## View.java:
```java
package ex02;
import java.io.IOException;
/** Product
* (шаблон проектирования
* Factory Method)<br>
* Интерфейс "фабрикуемых"
* объектов<br>
* Объявляет методы
* отображения объектов
* @author xone
* @version 1.0
*/
public interface View {
/** Отображает заголовок */
public void viewHeader();
/** Отображает основную часть */
public void viewBody();
/** Отображает окончание */
public void viewFooter();
/** Отображает объект целиком */
public void viewShow();
/** Выполняет инициализацию */
public void viewInit();
/** Сохраняет данные для последующего восстановления */
public void viewSave() throws IOException;
/** Восстанавливает ранее сохранённые данные */
public void viewRestore() throws Exception;
}
```
*3. Розширити ієрархію інтерфейсом "фабрикованих" об'єктів, що представляє набір методів для відображення результатів обчислень.*

## ViewResult:
```java
package ex02;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import ex01.Item2d;
/** ConcreteProduct
* (Шаблон проектирования
* Factory Method)<br>
* Вычисление функции,
* сохранение и отображение
* результатов
* @author xone
* @version 1.0
* @see View
*/
public class ViewResult implements View {
/** Имя файла, используемое при сериализации */
private static final String FNAME = "items.bin";
/** Определяет количество значений для вычисления по умолчанию */
private static final int DEFAULT_NUM = 10;
/** Коллекция аргументов и результатов вычислений */
private ArrayList<Item2d> items = new ArrayList<Item2d>();
/** Вызывает {@linkplain ViewResult#ViewResult(int n) ViewResult(int n)}
* с параметром {@linkplain ViewResult#DEFAULT_NUM DEFAULT_NUM}
*/
public ViewResult() {
this(DEFAULT_NUM);
}
/** Инициализирует коллекцию {@linkplain ViewResult#items}
* @param n начальное количество элементов
*/
public ViewResult(int n) {
for(int ctr = 0; ctr < n; ctr++) {
items.add(new Item2d());
}
}
/** Получить значение {@linkplain ViewResult#items}
* @return текущее значение ссылки на объект {@linkplain ArrayList}
*/
public ArrayList<Item2d> getItems() {
return items;
}
/** Вычисляет значение функции
* @param x аргумент вычисляемой функции
* @return результат вычисления функции
*/
private double calc(double x) {
return Math.sin(x * Math.PI / 180);
}
/** Вычисляет значение функции и сохраняет
* результат в коллекции {@linkplain ViewResult#items}
* @param stepX шаг приращения аргумента
*/
public void init(double stepX) {
double x = 0.0;
for(Item2d item : items) {
item.setXY(x, calc(x));
x += stepX;
}
}
/** Вызывает <b>init(double stepX)</b> со случайным значением аргумента<br>
* {@inheritDoc}
*/
@Override
public void viewInit() {
init(Math.random() * 360.0);
}
/** Реализация метода {@linkplain View#viewSave()}<br>
* {@inheritDoc}
*/
@Override
public void viewSave() throws IOException {
ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(FNAME));
os.writeObject(items);
os.flush();
os.close();
}
/** Реализация метода {@linkplain View#viewRestore()}<br>
* {@inheritDoc}
*/
@SuppressWarnings("unchecked")
@Override
public void viewRestore() throws Exception {
ObjectInputStream is = new ObjectInputStream(new FileInputStream(FNAME));
items = (ArrayList<Item2d>) is.readObject();
is.close();
}
/** Реализация метода {@linkplain View#viewHeader()}<br>
* {@inheritDoc}
*/
@Override
public void viewHeader() {
System.out.println("Results:");
}
/** Реализация метода {@linkplain View#viewBody()}<br>
* {@inheritDoc}
*/
@Override
public void viewBody() {
for(Item2d item : items) {
System.out.printf("(%.0f; %.3f) ", item.getX(), item.getY());
}
System.out.println();
}
/** Реализация метода {@linkplain View#viewFooter()}<br>
* {@inheritDoc}
*/
@Override
public void viewFooter() {
System.out.println("End.");
}
/** Реализация метода {@linkplain View#viewShow()}<br>
* {@inheritDoc}
*/
@Override
public void viewShow() {
viewHeader();
viewBody();
viewFooter();
}
}
```
*4. Реалізувати ці методи виведення результатів у текстовому виде.*

Реалізовано в файлі Main

*5. Розробити та реалізувати інтерфейс для "фабрикуючого" методу*

Реалізовано в файлі Main

Результат:

![](images/TaskResult2.png)

## Завдання 4 (Я хуже аутиста тому-що до кінця це виконати не вийшло, а ще я не уявляю чому таблиця видає незрозумілі значення замість згенерованого кута і обчислень)
*1.За основу використовувати вихідний текст проекту попередньої лабораторної роботи Використовуючи шаблон проектування Factory Method (Virtual Constructor), розширити ієрархію похідними класами, реалізують методи для подання результатів у вигляді текстової таблиці. Параметри відображення таблиці мають визначатися користувачем.
2.Продемонструвати заміщення (перевизначення, overriding), поєднання (перевантаження, overloading), динамічне призначення методів (Пізнє зв'язування, поліморфізм, dynamic method dispatch).
3. Забезпечити діалоговий інтерфейс із користувачем.
4. Розробити клас для тестування основної функціональності.
5. Використати коментарі для автоматичної генерації документації засобами javadoc.*

Змінені файли: 
## Main:
```java
package ex03;

import ex02.Calc;
import ex02.View;
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

    /** Ініціалізує поле {@linkplain Main#view view} та об'єкт Calc.
     * @param view */
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

    /** Выполняется при запуске программы;
    * вызывает метод {@linkplain ex02.Main#menu menu()}
    * @param args - параметры запуска программы
    */
    public static void main(String[] args) {
        Main main = new Main(new ViewableTable().getView());
        main.menu();
    }
}

```

## ViewTable:
```java
package ex03;

import java.util.Formatter;
import ex01.Item2d;
import ex02.ViewResult;
/** ConcreteProduct
* (шаблон проектирования
* Factory Method)<br>
* Виведення у вигляді таблиці
* @author hekku
* @version 1.0
* @see ViewResult
*/

public class ViewTable extends ViewResult {
/** Визначає ширину таблиці за замовчуванням */
private static final int DEFAULT_WIDTH = 20;
/** Поточна ширина таблиці */
private int width;
/** Встановлює {@linkplain ViewTable#width width}
* значенням {@linkplain ViewTable#DEFAULT_WIDTH DEFAULT_WIDTH}<br>
* Викликає конструктор суперкласу {@linkplain ViewResult#ViewResult() ViewResult()}
*/
public ViewTable() {
width = DEFAULT_WIDTH;
}
/** Встановлює {@linkplain ViewTable#width} значенням <b>width</b><br>
* Викликає конструктор суперкласу {@linkplain ViewResult#ViewResult() ViewResult()}
* @param width визначає ширину таблиці
*/
public ViewTable(int width) {
this.width = width;
}

/** Встановлює {@linkplain ViewTable#width} значенням <b>width</b><br>
* Викликає конструктор суперкласу {@linkplain ViewResult#ViewResult(int n) ViewResult(int
* @param width визначає ширину таблиці
* @param n кількість елементів колекції; передається суперконструктору
*/
public ViewTable(int width, int n) {
super(n);
this.width = width;
}
/** Встановлює поле {@linkplain ViewTable#width} значенням <b>width</b>
* @param width нова ширина таблиці
* @return задана параметром <b>width</b> ширина таблиці
*/
public int setWidth(int width) {
return this.width = width;
}
/** Повертає значення поля {@linkplain ViewTable#width}
* @return поточна ширина таблиці
*/
public int getWidth() {
return width;
}
/** Виводить вертикальний роздільник шириною {@linkplain ViewTable#width} символів */
private void outLine() {
for(int i = width; i > 0; i--) {
System.out.print('-');
}
}
/** Викликає {@linkplain ViewTable#outLine()}; завершує вивід роздільником рядків */
private void outDelimiter() {
outLine();
System.out.println();
}
/** Виводить заголовок таблиці */
@Override
public void viewHeader() {
outDelimiter();
}
/** Виводить тіло таблиці */
@Override
public void viewBody() {
    Formatter fmt = new Formatter();
    for(Item2d item : getItems()) {
        fmt.format("| %" + width + ".0f | %" + width + ".3f |\n",
                    item.geta(), item.getCons1());
    }
    System.out.print(fmt.toString()); // Виводимо сформатований рядок
    fmt.close();
}

/** Виводить кінець таблиці */
@Override
public void viewFooter() {
outDelimiter();
}
}
```

## ViewableTable:
```java
package ex03;

import ex02.ViewableResult;
import ex02.View;
/** ConcreteCreator
* (шаблон проектирования
* Factory Method)<br>
* Объявляет метод,
* "фабрикующий" объекты
* @author xone
* @version 1.0
* @see ViewableResult
* @see ViewableTable#getView()
*/
public class ViewableTable extends ViewableResult {
/** Создаёт отображаемый объект {@linkplain ViewTable}
     * @return  */
@Override
public View getView() {
    return new ViewTable();
    }
}
```
