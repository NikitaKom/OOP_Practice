# OOP_Practice

## Завдання 1
### Основне завдання:  Визначити найбільшу довжину послідовності 1 в подвійному поданні цілісної суми квадрата і куба 10*cos(α).

**1. Розробити клас, що серіалізується, для зберігання параметрів і результатів
обчислень. Використовуючи агрегування, розробити клас для знаходження рішення
задачі.**
Calc.java:
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
2. Розробити клас для демонстрації в діалоговому режимі збереження та
відновлення стану об'єкта, використовуючи серіалізацію. Показати особливості
використання transient полів.
Item2d.java:
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
Main.Java:
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
3. Розробити клас для тестування коректності результатів обчислень та
серіалізації/десеріалізації. Використовувати докладні коментарі для автоматичної генерації
документації засобами javadoc.
MainTest.java:
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
