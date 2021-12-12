package Sportowcy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

import gui.RollerSkateTrack;

public class Trener extends Thread{

	private RollerSkateTrack track;
	private Queue<Rolkarz> bench = new LinkedList<>();
	private static Trener INSTANCE;
	private String name = "t";
	private String order ="";
	
	public static Trener getInstance() {
		if(INSTANCE == null) {
			return new Trener();
		}
		return INSTANCE;
	}
	private Trener () {
		
	}

	public String getTrenerName() {
		return this.name;
	}
	@Override
	public void run() {
		while(true) {
			System.out.print(" " + bench.size());
			if(bench.size() > 0) {
				Rolkarz r = bench.poll();
				System.out.println("W¹tek : " + r.getName());
				Random rand = new Random();
				int labs = rand.nextInt(9) + 1;
				this.order = r.getName() + Integer.toString(labs);
				r.setPosX(0);
				r.setPosY(0);
				r.setLabs(labs);
				r.setAtBench();
				r.notify();
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	synchronized public Queue getBench() {
		return bench;
	}
	
	 public void pushBench(Rolkarz r) {
		
		bench.add(r);
		for(Rolkarz e : bench) {
			System.out.println(e.getName());
		}
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}


	
	
}
