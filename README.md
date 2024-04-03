# OOP_Practice
## Завдання 1
(виконано якщо ви бачите цей репозиторій)

## Завдання 2

# Я зараз зрозумів наскільки все перемудрив і запоров. Почав спочатку, у мене буде до вас декілька питань в п'ятницю с приводу коду (це завдання вже змінене, 3 та 4 можете навіть не читати)

### Основне завдання:  Визначити найбільшу довжину послідовності 1 в подвійному поданні цілісної суми квадрата і куба 10*cos(α).

*1. Розробити клас, що серіалізується, для зберігання параметрів і результатів обчислень. Використовуючи агрегування, розробити клас для знаходження рішення задачі.*

## Calc.java:
```java
package ex01;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/** Содержит реализацию методов для вычисления и отображения результатов.
* @author xone
* @version 1.0
*/
public class Calc {
/** Имя файла, используемое при сериализации. */
private static final String FNAME = "Item2d.bin";
/** Сохраняет результат вычислений. Объект класса {@linkplain Item2d} */
private Item2d result;
/** Инициализирует {@linkplain Calc#result} */
public Calc() {
result = new Item2d();
}
/** Установить значение {@linkplain Calc#result}
* @param result - новое значение ссылки на объект {@linkplain Item2d}
*/
public void setResult(Item2d result) {
this.result = result;
}
/** Установить значение {@linkplain Calc#alpha}
* @param alpha - новое значение ссылки на объект {@linkplain Item2d}
*/

/** Получить значение {@linkplain Calc#result}
* @return текущее значение ссылки на объект {@linkplain Item2d}
*/
public Item2d getResult() {
return result;
}
/** Получить значение {@linkplain Calc#alpha}
* @return текущее значение ссылки на объект {@linkplain Item2d}
*/

/** Вычисляет значение функции.
* @param alpha - аргумент вычисляемой функции.
* @return результат вычисления функции.
*/
public double calc(double alpha) {
return Math.pow(10 * Math.cos(alpha), 2) + Math.pow(10 * Math.cos(alpha), 3);
}
/** Из вычесленого значения функции находит количество последовательных "1" в нём.
* @param num - число в котором ищутся последовательные "1".
* @return количество последовательных "1" в числе.
*/
public int consOnes(double num) {
    int maxCount = 0;
        int currentCount = 0;
        while (num != 0) {
            if (num % 2 == 1) {
                currentCount++; 
                maxCount = Math.max(maxCount, currentCount);
            } else {
                currentCount = 0; 
            }
            num = Math.floor(num / 2); 
        }
        return maxCount;
    }
/** Вычисляет значение функции и сохраняет
* результат в объекте {@linkplain Calc#result}
* @param alpha - аргумент вычисляемой функции.
     * @return 
*/
public double init(double alpha) {
result.setX(alpha);
return result.setY(consOnes(calc(alpha)));
}
/** Выводит результат вычислений. */
public void show() {
System.out.println(result);
}
/** Сохраняет {@linkplain Calc#result} в файле {@linkplain Calc#FNAME}
* @throws IOException
*/
public void save() throws IOException {
ObjectOutputStream os = new ObjectOutputStream(new
FileOutputStream(FNAME));
os.writeObject(result);
os.flush();
os.close();
}

/** Восстанавливает {@linkplain Calc#result} и {@linkplain Calc#alpha} из файла {@linkplain Calc#FNAME}
* @throws Exception
*/
public void restore() throws Exception {
ObjectInputStream is = new ObjectInputStream(new FileInputStream(FNAME));
result = (Item2d)is.readObject();
is.close();
}
}
```
*2. Розробити клас для демонстрації в діалоговому режимі збереження та відновлення стану об'єкта, використовуючи серіалізацію. Показати особливості використання transient полів.*

## Item2d.java:
```java
package ex01;
import java.io.Serializable;
/** Хранит исходные данные и результат вычислений.
* @author xone
* @version 1.0
*/
public class Item2d implements Serializable {
/** Аргумент вычисляемой функции. */
// transient
private double x;
/** Результат вычисления функции. */
private double y;
/** Автоматически сгенерированная константа */
private static final long serialVersionUID = 1L;
/** Инициализирует поля {@linkplain Item2d#x}, {@linkplain Item2d#y} */
public Item2d() {
x = .0;
y = .0;
}
/** Устанавливает значения полей: аргумента
* и результата вычисления функции.
* @param x - значение для инициализации поля {@linkplain Item2d#x}
* @param y - значение для инициализации поля {@linkplain Item2d#y}
*/
public Item2d(double x, double y) {
this.x = x;
this.y = y;
}
/** Установка значения поля {@linkplain Item2d#x}
* @param x - значение для {@linkplain Item2d#x}
* @return Значение {@linkplain Item2d#x}
*/
public double setX(double x) {
return this.x = x;
}
/** Получение значения поля {@linkplain Item2d#x}
* @return Значение {@linkplain Item2d#x}
*/
public double getX() {
return x;
}
/** Установка значения поля {@linkplain Item2d#y}
* @param y - значение для {@linkplain Item2d#y}
* @return Значение {@linkplain Item2d#y}
*/
public double setY(double y) {
return this.y = y;
}
/** Получение значения поля {@linkplain Item2d#y}
* @return значение {@linkplain Item2d#y}
*/
public double getY() {
return y;
}
/** Установка значений {@linkplain Item2d#x} и {@linkplain Item2d#y}
* @param x - значение для {@linkplain Item2d#x}
* @param y - значение для {@linkplain Item2d#y}
* @return this
*/
public Item2d setXY(double x, double y) {
this.x = x;
this.y = y;
return this;
}
/** Представляет результат вычислений в виде строки.<br>{@inheritDoc} */
@Override
public String toString() {
return "alpha = " + x + ", Number of consecutive ones = " + y;
}
/** Автоматически сгенерированный метод.<br>{@inheritDoc} */
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
// изменено сравнение результата вычисления функции
if (Math.abs(Math.abs(y) - Math.abs(other.y)) > .1e-10)
return false;
return true;
}
}

```

## Main.Java:
```java
package ex01;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
/** Вычисление и отображение результатов.
* Содержит реализацию статического метода main().
* @author xone
* @version 1.0
* @see Main#main
*/
public class Main {
/** Объект класса {@linkplain Calc}.<br>Решает задачу инд. задания. */
private Calc calc = new Calc();
Scanner scanner = new Scanner(System.in);
boolean running = true;
/** Отображает меню. */
private void menu() {
String s = null;
BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
do {
do {
System.out.println("Enter command...");
System.out.print("'q'uit, 'v'iew, 'w'rite, 's'ave, 'r'estore: ");
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
calc.show();
break;
case 'w':
System.out.print("Enter the value of alpha (in degrees): ");
double alpha = scanner.nextDouble();
                
double sum = calc.calc(alpha);
System.out.println("Sum of square and cube of 10*cos(a): " + sum);

System.out.println("Number of consecutive ones: " + calc.consOnes(sum));
calc.init(sum);

break;

case 's':
System.out.println("Save current.");
try {
calc.save();
} catch (IOException e) {
System.out.println("Serialization error: " + e);
}
calc.show();
break;
case 'r':
System.out.println("Restore last saved.");
try {
calc.restore();
} catch (Exception e) {System.out.println("Serialization error: " + e);
}
calc.show();
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
Main main = new Main();
main.menu();
}
}

```
*3. Розробити клас для тестування коректності результатів обчислень та серіалізації/десеріалізації. Використовувати докладні коментарі для автоматичної генерації документації засобами javadoc.*

## MainTest.java:
```java
package ex01;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import junit.framework.Assert;
import java.io.IOException;

/** Выполняет тестирование разработанных классов.
* @author hekku
* @version 1.0
*/
public class MainTest {
/** Проверка основной функциональности класса {@linkplain Calc} */
@Test
public void testCalc() {
Calc calc = new Calc();
calc.init(0.0);
assertEquals(0.0, calc.getResult().getY(), .1e-10);
calc.init(90.0);
assertEquals(1.0, calc.getResult().getY(), .1e-10);
calc.init(180.0);
assertEquals(0.0, calc.getResult().getY(), .1e-10);
calc.init(270.0);
assertEquals(-1.0, calc.getResult().getY(), .1e-10);
calc.init(360.0);
assertEquals(0.0, calc.getResult().getY(), .1e-10);
}
/** Проверка сериализации. Корректность восстановления данных. */
@Test
public void testRestore() {
Calc calc = new Calc();
double x, y;
for(int ctr = 0; ctr < 1000; ctr++) {
x = Math.random() * 360.0;
y = calc.init(x);
try {
calc.save();
} catch (IOException e) {
Assert.fail(e.getMessage());
}
calc.init(Math.random() * 360);
try {
calc.restore();
} catch (Exception e) {
Assert.fail(e.getMessage());
}
assertEquals(y, calc.getResult().getY(), .1e-10);
assertEquals(x, calc.getResult().getX(), .1e-10);
}
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
