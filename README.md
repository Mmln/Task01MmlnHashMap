### Описание 

#### _Внимание!!! Проект нормально загружается из GitHub в IntelliJ IDEA(Community Edition)_

1. Part 1.
   1. Создан класс Account;
   2. Создан именованный тип CurTypes;
   3. Для хранения валюты использован TreeMap, чтобы была сортировка. 
      Во время реализации с использованием HashMap было замечено,
      что HashMap выводит значения в случайном порядке и «валит» тесты;
   4. Проверки ограничений реализованы для полей класса Account
      при помощи поведенческого паттерна (скорее всего это Startegy).
      Лямбда-выражения записаны в переменные и размещены на уровне исполняемых
      модулей. 
      Из соображений рациольнальности, лямбды можно было бы поместить прямо 
      в интерфейсы, но это бы означало, что при необходимости внесения изменений, 
      придется исправлять сами интерфейсы. Это сводит на нет саму идею 
      применения такого приема.
   5. Создано необходимое количество геттеров/сеттеров;
2. Part 2.
   1. При помощи паттерна Command реализован метод **undo** для класса Account;
   2. Использована коллекция Deque для создания стека сохранений; 
   3. Добавлено поле **type**, для проверки, что реализация соответствует 
      требованиям ООП;
3. Part 3.
   1. При помощи паттерна Memento реализован метод **save** для класса Account;
4. Реализованы модульные тесты.

