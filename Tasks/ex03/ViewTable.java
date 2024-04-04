package ex03;

import java.util.Formatter;
import ex01.Item2d;
import ex02.ViewResult;
/** ConcreteProduct
* (шаблон проектирования
* Factory Method)<br>
* Вивод в виде таблицы
* @author hekku
* @version 1.0
* @see ViewResult
*/

public class ViewTable extends ViewResult {
/** Визначає ширину таблиці за замовчуванням */
private static final int DEFAULT_WIDTH = 20;
/** Поточна ширина таблиці */
private int width;
/** Устанавливает {@linkplain ViewTable#width width}
* значеннием {@linkplain ViewTable#DEFAULT_WIDTH DEFAULT_WIDTH}<br>
* Вызывает конструктор суперкласса {@linkplain ViewResult#ViewResult() ViewResult()}
*/
public ViewTable() {
width = DEFAULT_WIDTH;
}
/** Устанавливает {@linkplain ViewTable#width} значение <b>width</b><br>
* Вызывает конструктор суперкласса {@linkplain ViewResult#ViewResult() ViewResult()}
* @param width визначает ширину таблицы
*/
public ViewTable(int width) {
this.width = width;
}

/** Встановлює {@linkplain ViewTable#width} значенням <b>width</b><br>
* Викликає конструктор суперкласу {@linkplain ViewResult#ViewResult(int n) ViewResult(int
* @param width ширина таблицы
* @param n количество елементов коллекции; передается суперконструктору
*/
public ViewTable(int width, int n) {
super(n);
this.width = width;
}
/** Устанавливает поле {@linkplain ViewTable#width} значением <b>width</b>
* @param width новая ширина таблицы
* заданная параметром <b>width</b> ширина таблицы
*/
@Override
public void setWidth(int width) {
this.width = width;
}
/** Возвращает значение поля {@linkplain ViewTable#width}
* @return текущая ширина таблицы
*/
@Override
public int getWidth() {
return width;
}
/** Выводит вертикальный разделитель шириной {@linkplain ViewTable#width} символов */
private void outLine() {
for(int i = width; i > 0; i--) {
System.out.print('-');
}
}
/** Вызывает {@linkplain ViewTable#outLine()}; завершает вывод разделителем рядов */
private void outDelimiter() {
outLine();
System.out.println();
}
/** Виводит заголовок таблицы */
@Override
public void viewHeader() {
outDelimiter();
}
/** Виводит тело таблицы */
@Override
public void viewBody() {
    Formatter fmt = new Formatter();
    for(Item2d item : getItems()) {
        fmt.format("| %" + width + ".0f | %" + width + ".3f |\n",
                    item.getX(), item.getY());
    }
    System.out.print(fmt.toString());
    fmt.close();
}

/** Выводит окончание таблицы */
@Override
public void viewFooter() {
outDelimiter();
}
/** Перегрузка (совмещение, overloading) метода суперкласса;
* устанавливает поле {@linkplain ViewTable#width} значением <b>width</b><br>
* Вызывает метод {@linkplain ViewResult#viewInit() viewInit()}
* @param width новая ширина таблицы
*/
public final void Init(int width) { // method overloading
this.width = width;
viewInit();
}
/** Перегрузка метода суперкласса;
* устанавливает поле {@linkplain ViewTable#width} значением <b>width</b><br>
* Для объекта {@linkplain ViewTable} вызывает метод {@linkplain ViewTable#init(double
* @param width новая ширина таблицы.
* @param stepX передается методу <b>init(double)</b>
*/
public final void Init(int width, double stepX) { // method overloading
this.width = width;
Init(stepX);
}
/** Переопределение (замещение, overriding) метода суперкласса;
* выводит информационное сообщение и вызывает метод суперкласса
* {@linkplain ViewResult#init(double stepX) init(double stepX)}<br>
* {@inheritDoc}
*/
@Override
public void Init(double stepX) { // method overriding
System.out.print("Initialization... ");
super.Init(stepX);
System.out.println("done. ");
}
}
