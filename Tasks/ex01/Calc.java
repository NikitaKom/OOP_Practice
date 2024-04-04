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
