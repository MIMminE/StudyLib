> 디자인 패턴의 활용 분야와 디자인 패턴으로 얻을 수 있는 장점을 알아본다.  
> 몇 가지 핵심적인 객체지향 디자인 원칙을 살펴본 후, 한 가지 패턴을 정해 어떤 식으로 작동하는지 알아본다.

**오리 시뮬레이션 게임**

> 객체지향 기법을 사용하여 Duck 슈퍼 클래스를 만든 다음, 그 클래스를 확장하여 서로 다른 종류의 오리를 만들었다. 각각의 오리는 생김새와 특징도 다르고 우는 소리도 다르고, 심지어 게임에서 등장하는 오리 모양의 로봇도 Duck 클래스를 확장시켜 구현한다. 또한, 앞으로의 주기적인 패치에서 기존 오리들의 특징이 변화하기도, 새로운 오리가 추가되기도 할 것으로 예상된다. 이럴때 어떤 식으로 게임을 개발하면 좋을 지 확인해보자.

## **상속**

---

> 객체지향을 조금 공부해본 사람이라면 곧바로 상속을 생각했을 것이다. Duck이라고 하는 슈퍼 클래스를 만들고 다양한 오리에게 상속시켜 서브 클래스를 여럿 만들어서 관리하는 것이다.  
>   
> 이때, Duck 이라고 하는 슈퍼 클래스는 직접 인스턴스화하여 사용되는 것이 아닌 모든 오리들의 틀과 같은 역할을 하기 때문에 추상 클래스로 작성하고, 가지고 있어야할 메소드들을 추상 메소드로 작성하여 서브 클래스들이 해당 메소드를 직접 구현하여 사용하도록 설계한다.

```JAVA
abstract class Duck //Duck 슈퍼클래스
{
    public abstract void Quack();
    public abstract void Swim();
    public abstract void Fly();
    public abstract void Display();
}
```

> Duck 슈퍼 클래스를 이용하여 여러 종류의 클래스를 작성하면 각 오리에 대한 기능을 일정한 포맷에 맞춰 작성할 수 있게 된다. 초기 설계에서는 MallarDuck, RedheadDuck 그리고 고무 오리를 위한 RubberDuck 서브 클래스를 가지고 진행한다.

```JAVA
class MallarDuck extends Duck //Duck 클래스를 확장한 MallarDuck 클래스
{
    public void Quack() { }

    public void Swim() { }

    public void Fly() { System.out.println("Fly!"); }

    public void Display() { }
}

class RedheadDuck extends Duck //Duck 클래스를 확장한 RedheadDuck 클래스
{
    public void Quack() { }

    public void Swim() { }

    public void Fly() { }

    public void Display() { }
}

class RubberDuck extends Duck //Duck 클래스를 확장한 RubberDuck 클래스, 생물 아님!
{
    public void Quack() { }

    public void Swim() { }

    public void Fly() { }

    public void Display() { }
}
```

> **언뜻 괜찮아보이는 이 설계의 문제는 다음과 같다.**  
>   
> 1\. 패치로 인하여 새로운 오리를 추가할 때마다 추상 클래스의 모드 메소드를 오버라이딩하여 구현해야 한다. 즉, 코드 재사용에서의 문제가 발생한다.  
>   
> 2\. 모든 오리들의 공통적인 변경 사항이 발생했을 때, 모든 클래스의 해당 부분을 수정해줘야 한다. 번거롭기도 하지만 새로운 오류를 가지고 올 가능성이 높아진다.

슈퍼 클래스를 추상 클래스로 만들지 않고 공통적으로 사용되는 기능들에 대해서는 일반 메소드로 구현하여 서브 클래스들에게 그대로 사용하게끔 하고, 수정이 필요한 메소드들만 오버라이딩 하여 사용하는 방법도 생각해볼 수 있다.

하지만 그 경우에도 모든 추상 메소드를 구현하는 것보다는 줄어들 수 있겠지만, **크게 보면 결국 해결되는 것이 없다.**

결국 단순히 클래스의 상속으로 효율적으로 설계하기에는 어려움 있다는 것을 알 수 있다.

## **인터페이스** 

---

> 클래스 마다 다른 기능이 있을 것으로 예상되는 부분들을 인터페이스로 떼어내서 구현하는 것이다. 각 오리마다 울음소리와 날 수 있는지, 없는지가 달라진다하면 두 부분을 인터페이스화 시키고 각 기능을 가진 오리들만 해당 인터페이스를 상속받아 구현해서 사용하게 한다. 또한, 인터페이스 사용을 위해 슈퍼 클래스의 모양을 조금 바꿔줘야 한다.

```JAVA
interface IFlyable //날 수 있는 오리들에게 상속할 인터페이스
{
    void Fly();
}

interface IQuackable //소리를 낼 수 있는 오리들에게 상속할 인터페이스
{
    void Quack();
}
```

```JAVA
abstract class Duck //Duck 슈퍼클래스
{
    public void Swim(); //모든 오리들이 가지고 있는 기능이므로 추상화를 하지 않는다.
    public abstract void Display();
}

//인터페이스로 빠진 부분들을 제외해준다.
```

인터페이스와 Duck 클래스를 이용하여 서브 클래스를 수정해보자.

```JAVA
class MallarDuck : Duck, IQuackable, IFlyable
{
    public void Quack() { }
    public void Fly() { }

    public override void Display() { }
}
```

> 인터페이스를 사용하게 되면 날지 못하는 오리, 소리를 내지 못하는 오리 등과 같은 일부 문제에 대해서는 충분히 해결할 여지가 있어보인다. 하지만 인터페이스를 상속받는 여러 클래스들에서 각 코드 구현을 또 해줘야하므로 여전히 코드 재사용에 대한 이슈를 해결하기에는 어려워 보인다.

인터페이스를 이용하더라도 코드의 재사용성이 크게 개선되지는 않아보인다.

```JAVA
class MallarDuck extends Duck implements IFlyable, IQuackable
{
    public void Quack() { System.out.println("MallarDuck Quack!"); }

    public void Fly() { System.out.println("MallarDuck Fly!"); }

    public void Display() { System.out.println("MallarDuck Display!"); }
}

class RedheadDuck extends Duck implements IFlyable, IQuackable
{
    public void Quack() { System.out.println("RedheadDuck Quack!"); }

    public void Fly() { System.out.println("RedheadDuck Fly!"); }

    public void Display() { System.out.println("RedheadDuck Display!"); }
}

class RubberDuck extends Duck
{
    public void Display() { System.out.println("RedheadDuck Display!"); }
}
```

## **전략 패턴**

---

> 유연성이 좋은 프로그램 디자인을 위한 몇 가지 원칙이 있다.   
>   
> **디자인 원칙 1**  
> _: 애플리케이션에서 달라지는 부분을 찾아내고, 달라지지 않는 부분과 분리한다._  
> 이는 객체지향 프로그래밍에서 캡슐화를 하는 것과 유사하며, 이렇게 함으로 바뀌지 않아도 될 부분에 대한 영향이 없애고 특정 부분에 대한 변경이 가능하다.  
>   
> **디자인 원칙 2**  
> _: 구현보다는 인터페이스에 맞춰서 프로그래밍한다._  
> 이것은 코드를 구체적인 구현에 의존하지말고, 추상화된 인터페이스나 추상 클래스에 의존하도록 하라는 말이다. 이렇게 하면 여러 클래스가 하나의 인터페이스(또는 추상클래스)에 마주처지기에 재사용과 유지보수가 쉬워진다.

두 가지 디자인 원칙에 맞춰 달라지는 부분을 분리하는 과정은 다음과 같다.

> **1\. 전략 인터페이스(Strategy Interface) 정의**  
> : 인터페이스로 구현된 것들(기능이 달라질 수 있는 부분)을 추상화하기 위한 인터페이스를 정의한다.  
>   
> **2\. 전략 클래스(Concrete Strategies) 구현**  
> : 각각의 전략을 구현한 구체 클래스들을 만든다. 이 클래스들은 전략 인터페이스를 구현한다.  
>   
> **3\. 컨텍스트 클래스(Context Class) 생성**  
> : 컨텍스트 클래스는 전략 인터페이스 타입의 인스턴스 변수를 가지며, 이 변수를 초기화하고 사용한다. 컨텍스트 클래스는 클라이언트가 사용하는 인터페이스를 제공하고, 전략 객체를 변경할 수 있는 메서드(주로 Setter 메서드 또는 생성자)를 제공한다.  
>   
> **4\. 전략 변경**  
> : 클라이언트는 컨텍스트 클래스를 통해 필요한 전략 객체를 설정하거나 변경할 수 있으며, 이를 통해 컨텍스트 클래스의 동작을 동적으로 변경할 수 있다.

### **Duck 슈퍼클래스** 

핵심은 클래스의 특정 메소드의 기능을 본인 또는 부모 클래스에서 구현하지 않고 **전략 클래스에 위임**하는 것이다. 

```JAVA
abstract public class Duck {
	IFlyable iFlyable; //특정 구상 클래스의 인스턴스를 할당하지 않은 상태
	IQuackable iQuackable; //특정 구상 클래스의 인스턴스를 할당하지 않은 상태

    public void PerformQuack() { 
    	iQuackable.Quack(); //Quack 행동을 직접 처리하는 대신, 객체에게 행동을 위임한다.
    }
    public void PerformFly() {
    	iFlyable.Fly(); //Fly행동을 직접 처리하는 대신, 객체에게 행동을 위임한다.
    }
}
```

인터페이스를 이용하여 행동을 위임받아 실행할 전략 클래스 집합을 구현한다.

```JAVA
interface IFlyable{ 
	public void Fly();
}

interface IQuackable{ 
	public void Quack();
}

class FlyBehavior implements IFlyable{
	public void Fly() {
		System.out.println("Fly!");
	}
}

class NoFlyBehavior implements IFlyable{
	public void Fly() {
		System.out.println("NoFly!");
	}
}

class QuackBehavior implements IQuackable{
	public void Quack() {
		System.out.println("Quack!");
	}
}

class NoQuackBehavior implements IQuackable{
	public void Quack() {
		System.out.println("NoQuack!");
	}
}
```

> 정리하자면,  
>   
> 변할 여지가 있는 행동들의 집합 클래스를 인터페이스로 구현하고, 해당 **인터페이스 인스턴스 변수**를 가진 클래스에 선언한다.  
> 클래스의 메소드는 해당 인터페이스 인스턴스 변수를 실행하는 것으로 대체한다. 이것은 행동을 위임하는 것이다.  
> 프로그램 실행 중 동적으로 메소드의 동작을 바꾸기 위해서는 클래스의 인터페이스 인스턴스 변수의 레퍼런스를 변경해줘야 한다.

```JAVA
abstract public class Duck {
	IFlyable iFlyable;
	IQuackable iQuackable;
    public void Swim() { System.out.println("Duck Swin!"); };
    public abstract void Display();
    public void PerformQuack() { 
    	iQuackable.Quack();
    }
    public void PerformFly() {
    	iFlyable.Fly();
    }
    
    public void SetFlyBehavior(IFlyable IF) { //인스턴스 변수의 레퍼런스 변경을 위한 메소드
    	iFlyable = IF;
    }
    
    public void SetQuackBehavior(IQuackable IQ) { //인스턴스 변수의 레퍼런스 변경을 위한 메소드
    	iQuackable = IQ;
    }
}
```

슈퍼클래스를 위와 같은 써주면 상속받은 모든 서브클래스들은 PerformQuack메소드와 PerformFly 메소드를 사용하여 위임 실행할 수 있다.

### **구상 클래스 구현**

```JAVA
class RubberDuck extends Duck
{
	public RubberDuck() {
		iFlyable = new NoFlyBehavior();  
		iQuackable = new NoQuackBehavior();
	}
}

class MallarDuck extends Duck
{
	public MallarDuck() {
		iFlyable = new FlyBehavior();
		iQuackable = new QuackBehavior();
	}
}
```

> RubberDuck은 날 수도, 소리도 낼 수 없고 MallarDuck은 날 수도, 소리도 낼 수 있으므로 각각 해당 동작을 하는 클래스의 인스턴스를 할당하는 코드를 생성자에 입력해준다.

### **컨택스트 클래스 동적 변경** 

```JAVA
public static void main(String[] args) {
    MallarDuck mallarDuck = new MallarDuck();
    System.out.println("mallarDuck");
    
    mallarDuck.SetFlyBehavior(new NoFlyBehavior());
    //행동 객체를 동적으로 변경해준다.
    
    mallarDuck.PerformFly();
    mallarDuck.PerformQuack();
}
```

> 생성자에 의해 FlyBehavior 인스턴스와 QuackBehavior 인스턴스가   
> 동적으로 변경되어 다른 결과를 출력하고 있는것을 확인할 수 있다.

> < 헤드퍼스트 디자이패턴 개정판 > 도서 공부를 하며 작성한 학습노트입니다.