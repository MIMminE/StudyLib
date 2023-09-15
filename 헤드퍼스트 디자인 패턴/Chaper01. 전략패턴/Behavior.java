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