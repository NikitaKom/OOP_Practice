package ex02;
/** Creator
* (шаблон проектирования
* Factory Method)<br>
* Объявляет метод,
* "фабрикующий" объекты
* @author hekku
* @version 1.0
* @see Viewable#getView()
*/
public interface Viewable {
/** Создаёт объект, реализующий {@linkplain View}
     * @return  метод View*/
public View getView();
}
