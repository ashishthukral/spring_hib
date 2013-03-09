package com.tbone.springBeans;

public class Point {
	
	private int _x;
	private int _y;
	
	@Override
	public String toString(){
		return "x="+_x+", y="+_y;
	}
	
	public int getX() {
		return _x;
	}
	public void setX(int iX) {
		_x = iX;
	}
	public int getY() {
		return _y;
	}
	public void setY(int iY) {
		_y = iY;
	}

}
