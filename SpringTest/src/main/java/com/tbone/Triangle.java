package com.tbone;


public class Triangle {
	
	private String _type;
	private int _height;
	private Point _pointA;
	
	public Triangle(int iHeight){
		_height=iHeight;
	}
	
	public Triangle(int iHeight,String iType){
		_height=iHeight;
		_type=iType;
	}

	public void draw(){
		System.out.println("Triangle drawn !!!");
	}
	
	@Override
	public String toString(){
		return "type="+_type+", height="+_height+", point="+_pointA;
	}

	public String getType() {
		return _type;
	}

	public void setType(String iType) {
		_type = iType;
	}

	public int getHeight() {
		return _height;
	}

	public void setHeight(int iHeight) {
		_height = iHeight;
	}

	public Point getPointA() {
		return _pointA;
	}

	public void setPointA(Point iPointA) {
		_pointA = iPointA;
	}
}
