package gui;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

import Sportowcy.Trener;

public class RollerSkateTrack{

	private int sizeOfRollerSkateingTrack = 10;
	private int capacity = sizeOfRollerSkateingTrack * 2;
	private int skaters;
	private String matrix[][] = new String[sizeOfRollerSkateingTrack][sizeOfRollerSkateingTrack];
	private TopWindow window;
	private static RollerSkateTrack INSTANCE;
	private String fieldString = " - ";
	private String blankString = "   ";
	private String bench = "";
	private Queue<String> benchQueue = new LinkedList<>();
	private String entrenceExit = "";
	private Trener trener;

	
	private RollerSkateTrack(TopWindow w) {
		this();
		this.window = w;
		
	}
	private RollerSkateTrack() {
		this.skaters = 0;
		this.entrenceExit += "V_";
		for(int i=0; i<this.sizeOfRollerSkateingTrack-2; i++) {
			this.entrenceExit += "   ";
		}
		this.entrenceExit += "^_";
		initTrack();
		trener = Trener.getInstance();
	}
	
	public static RollerSkateTrack getInstance(TopWindow w) {
		if(INSTANCE == null) {
			return INSTANCE = new RollerSkateTrack(w);
		}
		
		else
			return INSTANCE;
	}
	public static RollerSkateTrack getInstance() {
		if(INSTANCE == null) {
			return INSTANCE = new RollerSkateTrack();
		}
		
		else
			return INSTANCE;
	}
	
	synchronized public void initTrack() {
		for(int i = 0; i<sizeOfRollerSkateingTrack; i++) {
			for(int j = 0; j<sizeOfRollerSkateingTrack; j++) {
				matrix[i][j] = " " + Integer.toString(i) + Integer.toString(j) + " "; 
				if((i == (sizeOfRollerSkateingTrack-1)) || (i==0) || (j==(sizeOfRollerSkateingTrack-1)) || (j==0))
					matrix[i][j] = fieldString;
				else
					matrix[i][j] = blankString;
			}
			
		}
	}
	
	synchronized public void initTrack(int i, int j, String rolkarzName) {
		this.matrix[i][j] = rolkarzName;
	}
	
	public int getRSTSize() {
		return sizeOfRollerSkateingTrack;
	}
	
	synchronized public String[][] getMatrix(){
		return this.matrix;
	}
	
	public String buildTrack() {
		buildBench();
		String s = trener.getTrenerName() + trener.getOrder() + "\n\n" + bench + "\n\n" +  entrenceExit + "\n\n";
		for(int i = 0; i<sizeOfRollerSkateingTrack; i++) {
			for(int j = 0; j<sizeOfRollerSkateingTrack; j++) {
					s += matrix[i][j];
			}
			s += "\n";
		}
		return s;
	}
	
	private void buildBench() {
		bench = "";
		for(String s : benchQueue) {
			bench += s;
		}
	}
	
	synchronized public void setMatrix(String[][] m) {
		this.matrix = m;
		this.window.getLabel().setText(buildTrack());
	}
	
	public String getFieldString() {
		return this.fieldString;
	}
	public String getBlankString() {
		return this.blankString;
	}
	
	synchronized public void pushBench(String s) {
		benchQueue.add(s);
	}
	
}
