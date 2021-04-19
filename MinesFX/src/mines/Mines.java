package mines;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Mines {
	
	protected String Mat_Close[][];			// " " "." "F"
	protected String Mat_Open[][];			//
	protected int height;
	protected int width;
	protected int numMines;
	protected boolean showAll;
	

	public Mines(int height, int width, int numMines) {
		this.height = height;
		this.width = width;
		if(numMines<= height*width) {
		this.numMines = numMines;} else System.out.println("error");
		
		Mat_Close = new String[height][width];
		Mat_Open = new String[height][width];
		
		for(int i=0; i<height; i++)
			for(int j=0; j<width; j++) {
				Mat_Close[i][j] = ".";
				Mat_Open[i][j] = " ";
			}
		
		Random rand = new Random(); 
		int K=0;
		int num;
		List<My_Point> list_Friends = new ArrayList<>();
		while (K != numMines) {
			int i = rand.nextInt(height);
			int j = rand.nextInt(width);
			if(!Mat_Open[i][j].equals("X")) {		//if in place there is X
					Mat_Open[i][j] = "X";
					K++;
					list_Friends = list_Adj(i, j);		//בשביל לעלות את כל התאים מסביבו
					
					for(My_Point c: list_Friends) {
						if(Mat_Open[c.i][c.j].equals(" ")) {
							Mat_Open[c.i][c.j] = "1";
						}else if(!Mat_Open[c.i][c.j].equals(" ") && !Mat_Open[c.i][c.j].equals("X")) {
						num = Integer.parseInt(Mat_Open[c.i][c.j]);	//convert string to integer in order to add 1; 
						num++;
						Mat_Open[c.i][c.j]=num + "";}				//+ "" because num is type integer 
					}
				}
		}
	}
	
	public boolean addMine(int i, int j) {
		List<My_Point> list_Friends = new ArrayList<>();
		int num;
		
		if(!Mat_Open[i][j].equals("X")) {
			Mat_Open[i][j] = "X";
			numMines++;
			list_Friends = list_Adj(i, j);
			
			for(My_Point c: list_Friends) {
				if(Mat_Open[c.i][c.j].equals(" ")) {
					Mat_Open[c.i][c.j] = "1";
				}else if(!Mat_Open[c.i][c.j].equals(" ") && !Mat_Open[c.i][c.j].equals("X")) {
				num = Integer.parseInt(Mat_Open[c.i][c.j]);	//convert string to integer in order to add 1; 
				num++;
				Mat_Open[c.i][c.j]=num + "";}				//+ "" because num is type integer 
			}return true;
		}
		System.out.println("Plese try new place");
		return false;
	}
	
	
	
	public boolean open(int i, int j) {
	List<My_Point> list_Friends = new ArrayList<>();

	

		if(Mat_Open[i][j].equals("X") || Mat_Close[i][j].equals("F"))
			return false;
		
		
		
		Mat_Close[i][j] = " ";			//sign open place
		
		list_Friends = list_Adj(i, j);
		
		if(check_Adj(i, j)) {
		for(int k=0; k<list_Friends.size(); k++) {
			My_Point n = list_Friends.get(k);
			if(n!=null && Mat_Close[n.i][n.j]!= " ")
							open(n.i,n.j);
				
			}
		}
		return true;
	}
	

	@SuppressWarnings("unused")
	private List<My_Point> list_Adj (int i, int j) {			//Create list points of friends 
		
		List<My_Point> list = new ArrayList<>();
	
		
		for(int x=-1; x<2; x++)
			for(int y=-1; y<2; y++) {
				
				if( (x+i)<height && (x+i)>= 0)
					if((y+j)<width && (y+j)>=0)
						list.add(new My_Point(i+x, j+y));
			}
		return list;
		
	}
	
	
	private boolean check_Adj(int i, int j) {
		
		for(int x=-1; x<2; x++)
			for(int y=-1; y<2; y++) {
				
				if( (x+i)<height && (x+i)>= 0)
					if((y+j)<width && (y+j)>=0)
						if(Mat_Open[x+i][y+j].equals("X"))
							return false;
			}
		return true;
	}
	
	
	public void toggleFlag(int i, int j) {
	
		if(!Mat_Close[i][j].equals(" ")){
			
			if(!Mat_Close[i][j].equals("F")) {
				Mat_Close[i][j] = "F";
			}else Mat_Close[i][j] = ".";
			
		}
	}
	
	public boolean isDone() {
		
		for(int i=0; i<height; i++)
			for(int j=0; j<width; j++) {
				
				if(!Mat_Open[i][j].equals("X"))
					if(!Mat_Close[i][j].equals(" "))
					return false;
			}
		return true;
	}
	
	public String get(int i, int j) {
		
		if(showAll)return Mat_Open[i][j];
		
		if(!Mat_Close[i][j].equals(" ")) { return Mat_Close[i][j];
		
		}else  return Mat_Open[i][j];
		
			
	}
	
	public void setShowAll(boolean showAll) {
		
		this.showAll = showAll;
	}
	
	public String toString() {
		String str="";
		
		for(int i=0; i<height; i++) {
			for(int j=0; j<width; j++) {
				
				str += get(i, j);
			}
			str += "\n";
		}
		return str;
	}
	
	
	
	private class My_Point{
		int i;
		int j;
		
		My_Point(int i, int j){
			this.i=i;
			this.j=j;
		}
		
	}

}
