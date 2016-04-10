package Lab3;

public class MainClass {

	public static void main(String[] args) {
		
		Monitor monitor = new Monitor();
		/*
		 Version for only one producer and one consumer:
		Producent p = new Producent(monitor);
		Consument c = new Consument(monitor);
		
		Thread t1 = new Thread(p);
		Thread t2 = new Thread(c);
		
		t1.start();
		t2.start();
		*/
		
		//Version for many producers and consumers:
		
		Producent [] p = new Producent[10];
		Consument [] c = new Consument[10];
		
		for(int i = 0; i < 10; i++){
			p[i] = new Producent(monitor);
			c[i] = new Consument(monitor);
			
			Thread t1 = new Thread(p[i]);
			t1.start();
			Thread t2 = new Thread(c[i]);
			t2.start();
		}
		
	}

}
