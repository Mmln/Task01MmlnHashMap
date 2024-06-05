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


#### Ответ на замечание «_категорически непонятно почему для мапы выбрана TreeMap_»

Первоначально была сделана реализация с _HashMap_ (см. коммит 22652fff), 
а потом (см. коммит f94d0f27) я переделал на _TreeMap_. 

Причина - был создан тест _MementoAbleTest_ и обнаружилось, что если запускать только _repeatedLoad()_, 
то тест проходит нормально, а вот, если запустить его полностью, с точки _MementoAbleTest_,
то тест падает.
Выяснилось, что при запуске с точки _MementoAbleTest_ этот _acc.toString()_ выводит коды валют в ином порядке. 
Да, обе валюты присутствуют, но порядок вывода в стороку иной.
Я не стал _заморачиваться_ и решил проблему кардинально - применил _TreeMap_. 

Куски кода приведены внизу. Я написал об этом в п.4 описания.

Оверрайд для toString из _Account.java_:

    @Override
    public String toString() {
        return "Account{" + "name='" + name + '\'' + ", currency=" + currencies + ", type=" + type + '}';
    }

Тест _MementoAbleTest.java_:

    public class MementoAbleTest {
    NameRuleAble nameRule = (x) -> x == null || x.isEmpty();
    CurrRuleAble currRule = (y) -> (y < 0) || y == null;
       
       @Test
       void load() {
           Account acc = new Account(nameRule,  currRule);
           acc.setName("initName");
           acc.putCurrency(CurTypes.RUB, 100);
           acc.setType("SuperAccount");
           MementoAble qs1 = acc.save();
           String savedAcc = acc.toString();
           acc.putCurrency(CurTypes.USD, 100);
           acc.setName("modifiedName");
           acc.setType("SimpleAccount");
           qs1.load();
           Assert.assertEquals(savedAcc, acc.toString());
       }
   
       @Test
       void repeatedLoad() {
           Account acc = new Account(nameRule,  currRule);
           acc.setName("initName");
           acc.putCurrency(CurTypes.RUB, 100);
           acc.setType("SuperAccount");
           MementoAble qs1 = acc.save();
           String savedAcc1 = acc.toString();
   
           acc.setName("modifiedName");
           acc.putCurrency(CurTypes.USD, 100);
           acc.setType("SimpleAccount");
           MementoAble qs2 = acc.save();
           String savedAcc2 = acc.toString();
   
           qs1.load();
           Assert.assertEquals(savedAcc1, acc.toString());
   
           qs2.load();
           Assert.assertEquals(savedAcc2, acc.toString());
   
       } 
    }






