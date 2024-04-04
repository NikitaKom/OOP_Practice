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
