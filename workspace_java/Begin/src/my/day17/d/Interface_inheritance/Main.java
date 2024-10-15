package my.day17.d.Interface_inheritance;

public class Main {

	public static void main(String[] args) {
		// TODO Main
		
		Me_imple me = new Me_imple();
		
		me.work();
		me.cook();
		me.play();
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~\n");
		
		Father me2 = new Me_imple(); 
		me2.work();
		
		Mother me3 = new Me_imple();
		me3.cook();
		
		Me me4 = new Me_imple();
		me4.work();
		me4.cook();
		me4.play();
		
		System.out.println("\n~~~~~~~~~~~~~~~~~~~~~~\n");

		
		
		
	}

}
