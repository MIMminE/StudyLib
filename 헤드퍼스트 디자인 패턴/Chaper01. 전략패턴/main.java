
public class main {

	public static void main(String[] args) {

		MallarDuck mallarDuck = new MallarDuck();
		System.out.println("mallarDuck");
		
		mallarDuck.SetFlyBehavior(new NoFlyBehavior());
		mallarDuck.PerformFly();
		mallarDuck.PerformQuack();
		
		System.out.println("-------------------");
		RubberDuck rubberDuck = new RubberDuck();
		System.out.println("rubberDuck");
		rubberDuck.PerformFly();
		rubberDuck.PerformQuack();
	}
}
