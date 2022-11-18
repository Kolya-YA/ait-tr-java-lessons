> *_Java, mon amour!_* — авторский курс по лучшему языку програмирования ;-)

# Comparable и Comparator

Вспомним класс **TreeSet** и интерфейс **SortedSet**.
С их помощью мы научились создавать и манипулирвать сортирванными коллекциями. 

## Как сравнить не сравнимое

```java
class Car {
    private String brand;
    private int maxSpeed;
    private String cute;

    Car(String brand, int maxSpeed, String cute) {
        this.brand = brand;
        this.maxSpeed = maxSpeed;
        this.cute = cute;
    }

    String getBrand() {
        return brand;
    }

    int getMaxSpeed() {
        return maxSpeed;
    }

    String getCute() {
        return cute;
    }
}
```

### Интерфейс _Comparable_

У интерфейса _Comparable_ есть только один метод ```int compareTo(E item)```.
Он сравнивает текущий объект с передаваемым в качестве параметра и возвращает `int`.  
Если этот `int`:
- int < 0 — то текущий объект будет расположен перед передаваемым;
- int > 0 — то наоброт, текущий объект будет после передаваемого;
- int == 0 — то значит оба объекта равны.

#### Сортируем по имени бренда
```java
class Car implements Comparable<Car> {

    ...

    public int compareTo(Car c) {
        return brand.compareTo(c.getBrand());
    }
}
```

#### Сортируем по убыванию максимальной скорости
```java
class Car implements Comparable<Car> {

    ...

    public int compareTo(Car c) {
        return c.getMaxSpeed() - maxSpeed;
    }
}
```

#### Сортируем по убыванию ~~милости няшности~~ внешней привлекательности
```java
class Car implements Comparable<Car> {

    ...

    public int compareTo(Car c) {
        return c.getCute().length() - cute.length();
    }
}
```

### Интерфейс Comparable

Достаточно часто мы должны взаимодествовать с классами, в которых нет реализованного интерфейса _Comparable_
или его функциональность не отвечает нашим потребностям. В этом случае, мы можем воздействовать снаружи
применяя для их сортировки/упорядочивания интерфейс _Comparator_ и его основной метод `int compare()`.
Этот метод принимает два сравниваемых объекта `a` и `b`, а затем возвращает `int`. Если этот `int` отрицательный,
то объект `a` продшествует объекту `b`, если наоборот, то наоборот. Если объекты равны, метод возвращает `0`.
Для использования интерфейса _Comarator_ необходимо создать метод, который его реализует.

#### Сравнение по имени бренда
```java
class CarByBrandComparator implements Comparator<Car> {
    public int compare(Car carA, Car carB) {
        return carA.getBrand().compareTo(carB.getBrand());
    }
}
```

#### Сравнение по максимальной скорости (убывание)
```java
class CarByMaxSpeedComparator implements Comparator<Car> {
    public int compare(Car carA, Car carB) {
        int mSpeedA = carA.getMaxSpeed();
        int mSpeedB = carB.getMaxSpeed();
        return mSpeedA < mSpeedB ? 1 : mSpeedA > mSpeedB ? -1 :0;
    }
}
```
#### Сравнение по внешней привлекательности (убывание)
```java
class CarByCuteComparator implements Comparator<Car> {
    public int compare(Car carA, Car carB) {
        int cuteQtyA = carA.getCute().length();
        int cuteQtyB = carB.getCute().length();

        if (cuteQtyA < cuteQtyB ) { return 1; }
        if (cuteQtyA > cuteQtyB) { return -1; }
        return 0;
    }
}
```

Теперь отсортируем наши машины с их помощью
- ```java
   cars02.sort(new CarByBrandComparator());
   ```
- ```java
  cars02.sort(new CarByMaxSpeedComparator());
   ```
- ```java
  cars02.sort(new CarByCuteComparator());
   ```
На этих примерах мы передаем в метод `sort` экземпляр/инстанс компаратора, для сортировки по тому или иному принципу.

В случае коллекции TreeSet, которая не имеет метода `sort`, сортировка происходит в процессе добавления объектов в коллекцию.
Мы используем немного другой подход  
```java
TreeSet<Car> cars03 = new TreeSet<Car>(new CarByCuteComparator());
```
Мы передаем компаратор в качестве параметра конструктора и он применяется при создании новой коллекции.
> Мы еще вернемся к _Comparator_ когда будем изучать Лямбда-выражения.

## Документация, примеры и дополнительные материалы

- [Интерфейс Comparable](https://docs.oracle.com/en/java/javase/14/docs/api/java.base/java/lang/Comparable.html)
- [Интерфейс Comparator](https://docs.oracle.com/en/java/javase/14/docs/api/java.base/java/util/Comparator.html)
- https://www.happycoders.eu/java/comparator-comparable-compareto/
- https://habr.com/ru/company/otus/blog/670630/
- https://metanit.com/java/tutorial/5.6.php

### Домашнее задание

1. Создайте коллекцию содержащую описание прямоугольников в виде пары цельночисленных величин длинны и ширины.  
Имплементируйте<sup>*</sup> интерфейсы _Comparator_, позволяющие сортировать коллекцию:
   - по площадям прямоугольников;
   - по периметрам прямоугольников.

   <sup>*</sup>Вычислять площади и периметры будем в самом _Comparator_.

2. Создайте коллекцию содержащую описание 
[торов](https://en.wikipedia.org/wiki/Torus) в виде пары цельночисленных величин
радиуса оси вращения тора и радиуса образующей окружности.  
Имплементируйте интерфейс _Comparable_, позволяющий сортировать коллекцию по объему тора.
