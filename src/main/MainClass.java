package main;
import javax.swing.SwingUtilities;

import Sportowcy.Rolkarz;
import Sportowcy.Trener;
import gui.TopWindow;



public class MainClass {
	
	private static TopWindow window;
	
	
	
	public static void main(String[] args) throws InterruptedException {

		
		Trener trener = Trener.getInstance();
		window = new TopWindow();
		trener.start();
		Rolkarz t1 = new Rolkarz(2);
		t1.start();	
		Rolkarz t2 = new Rolkarz(6);
		t2.start();
		//if(t1.getAtBench())	t1.wait();
		Rolkarz t3 = new Rolkarz(4);
		t3.start();
		Rolkarz t4 = new Rolkarz(9);
		t4.start();
		Rolkarz t5 = new Rolkarz(9);
//		
		
	}
	
	
	
	private static void repaint() {
		SwingUtilities.updateComponentTreeUI(window);
	}
	
}