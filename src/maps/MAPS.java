package maps;

import java.util.ArrayList;

public class MAPS {
	public final int numberOfMaps = 3;
	
	public int[][] map1= 
	{
		{2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
		{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
		{0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
		{0, 0, 0, 0, 3, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 3},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 3, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0},
		{1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0},
		{1, 1, 3, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 1, 1, 1, 1, 1, 1},
		{0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 1, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 1, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 1, 1, 3, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 3, 1, 1},
		{0, 0, 0, 0, 0, 0, 0, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{1, 1, 1, 1, 1, 1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
	};
	
	public int[][] map2=
	{
		{2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
		{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
		{0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0},
		{1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 1, 1, 2, 2, 2, 2, 0, 0, 0, 0, 0, 3, 0, 0, 1, 1, 1, 1},
		{2, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 4, 4, 4, 4, 4, 4, 3, 0, 0, 0, 3, 0, 0},
		{2, 1, 1, 1, 2, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 3, 0, 0},
		{2, 1, 1, 1, 2, 1, 1, 2, 1, 1, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 1, 1, 1, 3, 1, 1},
		{3, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 3, 1, 1, 1, 1, 1, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
		{3, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
		{3, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 3, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3},
		{3, 0, 0, 0, 0, 0, 3, 4, 4, 4, 4, 3, 4, 4, 4, 4, 4, 4, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0},
		{3, 1, 2, 1, 2, 1, 3, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0},
		{3, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 1, 1, 1, 1, 1, 3},
		{3, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 0, 0, 0, 0, 0, 3},
		{3, 2, 2, 1, 2, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3}
	};
	
	public int[][] map3=
	{
		{2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2},
		{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
		{0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 1, 1, 1, 3, 0, 0, 0, 0, 0, 0, 3, 0},
		{1, 1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 3, 1, 1, 1, 3, 0, 1, 1, 1, 1, 1, 3, 1},
		{1, 1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 3, 1},
		{0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 3, 4, 4, 4, 4, 3, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
		{0, 0, 0, 0, 3, 0, 0, 0, 0, 3, 1, 1, 1, 1, 1, 1, 3, 1, 1, 0, 0, 0, 0, 0, 0, 4, 4, 0},
		{1, 1, 1, 1, 3, 1, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 4, 4, 0, 0, 0},
		{0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 3, 0, 0, 4, 4, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 3, 1, 1, 1, 1, 1, 1, 3, 1, 1, 1, 1, 1, 3, 1, 1, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 3, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0},
		{1, 1, 1, 1, 1, 3, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 2, 2, 2, 2, 2, 2, 2},
		{3, 0, 0, 0, 0, 0, 0, 0, 0, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 0, 0, 0, 0, 0, 0, 0},
		{4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
	};
	
	public ArrayList<Pair<Integer,Integer> > guardMap1 = new ArrayList<>();
	public ArrayList<Pair<Integer,Integer> > guardMap2 = new ArrayList<>();
	public ArrayList<Pair<Integer,Integer> > guardMap3 = new ArrayList<>();
	
	public Pair<Integer,Integer>  playerMap1;
	public Pair<Integer,Integer>  playerMap2;
	public Pair<Integer,Integer>  playerMap3;
	
	public ArrayList<Pair<Integer,Integer> > treasureMap1 = new ArrayList<>();
	public ArrayList<Pair<Integer,Integer> > treasureMap2 = new ArrayList<>();
	public ArrayList<Pair<Integer,Integer> > treasureMap3 = new ArrayList<>();
	
	public Pair<Integer,Integer>  keyMap1;
	public Pair<Integer,Integer>  keyMap2;
	public Pair<Integer,Integer>  keyMap3;
	
	public Pair<Integer,Integer>  doorMap1;
	public Pair<Integer,Integer>  doorMap2;
	public Pair<Integer,Integer>  doorMap3;

	public MAPS() {
		///MAP1///
		guardMap1.add(new Pair<Integer, Integer>(2, 15));
		
		playerMap1 = new Pair<Integer, Integer>(15, 2);

	    treasureMap1.add(new Pair<Integer, Integer>(1, 10));
		treasureMap1.add(new Pair<Integer, Integer>(26, 13));
		treasureMap1.add(new Pair<Integer, Integer>(15, 7));
		treasureMap1.add(new Pair<Integer, Integer>(6, 15));
		treasureMap1.add(new Pair<Integer, Integer>(6, 4));
		
		keyMap1 = new Pair<Integer, Integer>(13, 15);
		
		doorMap1 = new Pair<Integer, Integer>(0, 2);
		
		
		///MAP2///
		guardMap2.add(new Pair<Integer, Integer>(4, 8));
		
		playerMap2 = new Pair<Integer, Integer>(15, 2);

		treasureMap2.add(new Pair<Integer, Integer>(1, 5));
		treasureMap2.add(new Pair<Integer, Integer>(27, 5));
		treasureMap2.add(new Pair<Integer, Integer>(3, 16));
		treasureMap2.add(new Pair<Integer, Integer>(8, 8));
		treasureMap2.add(new Pair<Integer, Integer>(2, 13));
		treasureMap2.add(new Pair<Integer, Integer>(23, 14));
		treasureMap2.add(new Pair<Integer, Integer>(18, 15));
		
		keyMap2 = new Pair<Integer, Integer>(14, 9);
		
		doorMap2 = new Pair<Integer, Integer>(2, 5);
		
		///MAP3///
		guardMap3.add(new Pair<Integer, Integer>(7, 12));
		
		playerMap3 = new Pair<Integer, Integer>(10, 2);

		treasureMap3.add(new Pair<Integer, Integer>(23, 2));
		treasureMap3.add(new Pair<Integer, Integer>(17, 4));
		treasureMap3.add(new Pair<Integer, Integer>(3, 10));
		treasureMap3.add(new Pair<Integer, Integer>(3, 14));
		treasureMap3.add(new Pair<Integer, Integer>(14, 15));
		treasureMap3.add(new Pair<Integer, Integer>(15, 12));
		treasureMap3.add(new Pair<Integer, Integer>(27, 8));
		
		keyMap3 = new Pair<Integer, Integer>(26, 14);
		
		doorMap3 = new Pair<Integer, Integer>(11, 5);
	}
}
