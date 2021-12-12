package Sportowcy;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import gui.RollerSkateTrack;

public class Rolkarz extends Thread implements Runnable{
	private static final AtomicInteger count = new AtomicInteger(0);
	 private int labs;
	 private String name;
	 private final char nameID;
	 private int posX;
	 private int posY;
	 private int speed;
	 private int trackSize;
	 private Boolean atBench;
	 private RollerSkateTrack track;
	 private Trener trener;
	 
	 public Rolkarz(int labs){
		 this.atBench = false;
		 this.setLabs(labs);
		 this.nameID = (char)(count.incrementAndGet() + 64);
		 this.setName();
		 this.setSpeed();
		 this.posX = 0;
		 this.posY = 0;
		 track = RollerSkateTrack.getInstance();
		 this.trackSize = track.getRSTSize()-1;
		 this.trener = Trener.getInstance();
	 }
	 
	 public char getNameID() {
		 return this.nameID;
	 }
	 private void setName() {
		 this.name = " " + nameID + Integer.toString(labs);
	 }
	 //TODO  
	 @Override
	 public void run(){
		 
		 //poruszanie siê po torze
		 while(true) {
				 try {
					 for(int i=0; i<trackSize; i++) {
						 String[][] m = track.getMatrix();
						 if(m[posX+1][posY].equals(track.getFieldString())) {
							 m[posX][posY] = track.getFieldString();
							 posX++;
							 m[posX][posY] = name;
							 track.setMatrix(m);
						 }else {
							 i--;
						 }
						 Thread.sleep(1000/this.speed);
					 }
					 
					 for(int i=0; i<trackSize; i++) {
						 String[][] m = track.getMatrix();
						 if(m[posX][posY+1].equals(track.getFieldString())) {
							 m[posX][posY] = track.getFieldString();
							 posY++;
							 m[posX][posY] = name;
							 track.setMatrix(m);
						 }else {
							 i--;
						 }
						 Thread.sleep(1000/this.speed);
					 }
					 
					 for(int i=trackSize; i>0; i--) {
						 String[][] m = track.getMatrix();
						 if(m[posX-1][posY].equals(track.getFieldString())) {
							 m[posX][posY] = track.getFieldString();
							 posX--;
							 m[posX][posY] = name;
							 track.setMatrix(m);
						 }else {
							 i++;
						 }
						 Thread.sleep(1000/this.speed);
					 }
					 //if(labs == 0 && posX==0 && posY == trackSize-1) 
					 if(labs != 0) {
						 for(int i=trackSize; i>0; i--) {
							 String[][] m = track.getMatrix();
							 if(m[posX][posY-1].equals(track.getFieldString())) {
								 m[posX][posY] = track.getFieldString();
								 posY--;
								 m[posX][posY] = name;
								 track.setMatrix(m);
							 }else {
								 i++;
							 }
							 Thread.sleep(1000/this.speed);
						 }
						 labs--;
						 this.setName();
					 }else {
						 String [][]m = track.getMatrix();
						 m[posX][posY] = track.getFieldString();
						 track.pushBench(name);
						 track.buildTrack();
						 trener.pushBench(this);
						 track.setMatrix(m);
						 atBench = true;
						 trener.pushBench(this);
						 try {
							 synchronized(this) {
								 this.wait();
							 }
						 }catch(InterruptedException e) {
							 e.printStackTrace();
						 }
					 }
					 
				 }catch (InterruptedException e) {
		  				e.printStackTrace();
		  			}
			 }
		 
	}
	 
	 public void setAtBench() {
		 this.atBench = false;
	 }
	 public Boolean getAtBench() {
		 return this.atBench;
	 }
	 private static String str(int i) {
		    return str((i / 26) - 1) + (char)(65 + i % 26);
		}
	 
	 private void setSpeed() {
		 Random rand = new Random();
		 this.speed = rand.nextInt(10) + 1;
	 }
	 public int getSpeed() {
		 return speed;
	 }
	 public void setLabs(int labs) {
		 this.labs = labs;
	 }
	 public int getLabs() {
		 return labs;
	 }
	
	 public String getRolkarzName() {
		 String result = this.name + this.labs;
		 return result;
	 }

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}
	 
}
