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
