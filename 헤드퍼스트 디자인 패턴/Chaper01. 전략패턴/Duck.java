
abstract public class Duck {
	protected IFlyable iFlyable;
	protected IQuackable iQuackable;
    public void Swim() { System.out.println("Duck Swin!"); };
    public abstract void Display();
    public void PerformQuack() { 
    	iQuackable.Quack();
    }
    public void PerformFly() {
    	iFlyable.Fly();
    }
    
    public void SetFlyBehavior(IFlyable IF) {
    	iFlyable = IF;
    }
    
    public void SetQuackBehavior(IQuackable IQ) {
    	iQuackable = IQ;
    }
}


//Duck 클래스를 확장한 MallarDuck 클래스
class MallarDuck extends Duck
{
	public MallarDuck() {
		iFlyable = new FlyBehavior();
		iQuackable = new QuackBehavior();
	}
	
    public void Display() { System.out.println("MallarDuck Display!"); }
}

//Duck 클래스를 확장한 RedheadDuck 클래스
class RedheadDuck extends Duck
{
	public RedheadDuck() {
		iFlyable = new FlyBehavior();
		iQuackable = new QuackBehavior();
	}
	
    public void Display() { System.out.println("RedheadDuck Display!"); }
}

//Duck 클래스를 확장한 RubberDuck 클래스, 생물 아님!
class RubberDuck extends Duck
{
	public RubberDuck() {
		iFlyable = new NoFlyBehavior();
		iQuackable = new NoQuackBehavior();
	}
    public void Display() { System.out.println("RedheadDuck Display!"); }
}
