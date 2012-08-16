package org.taptwo.android.widget.viewflow.example;

public class Point implements Comparable <Point> {
	private double dist;
	private int index;
	

	public Point(double dist, int index) {
	// set values on attributes
		super();
		dist=this.dist;
		index=this.index;
	}
	
	public double getDist(){
		return dist;
	}
	
	public int getIndex(){
		
		return index;
	}
	
	public void setDist(double dist){
		
		this.dist=dist;
	}
	
public void setIndex(int index){
		
		this.index=index;
	}

@Override
public int compareTo(Point arg0) {
	// TODO Auto-generated method stub
	int compare=  (int) (this.dist - arg0.dist) ;
	 
	 return compare;
}
}
