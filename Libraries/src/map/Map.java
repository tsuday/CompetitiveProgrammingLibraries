package map;

public class Map {

//	int[] dx = {1, 0,-1};
//	int[] dy = {1, 0,-1};
//	int[] dz = {1, 0,-1};

	int[] d  = { 0, -1, 0, 1};
	//int x=d[i];int y=d[i^1];


	int[] dx = { 1, 0,-1, 0};
	int[] dy = { 0, 1, 0,-1};
	 
	static String[] map;

	static boolean isIn(int x, int y) {
		return x>=0 && x<map[0].length() && y>=0 && y<map.length;
	}
	
	static boolean isOut(int x, int y) {
		return !isIn(x, y);
	}
	
	static char get(int x, int y) {
		return map[y].charAt(x);
	}
	
	static void set(int x, int y, char c) {
		String s = map[y];
		map[y] = s.substring(0, x) + c + s.substring(x+1, s.length());
	}
	


//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}

}
