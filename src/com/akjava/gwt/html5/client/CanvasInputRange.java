package com.akjava.gwt.html5.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.user.client.ui.FocusPanel;

public class CanvasInputRange extends FocusPanel implements InputRange{
private Canvas canvas;
private int width;
private int height;
private int min=0;
private int max=100;
private int value=0;
private boolean mousePressed;
private boolean mouseObscure;
private int pointX;
private int margin=8;
private String pressedColor="#eee";
private String defaultColor="#fff";
private boolean enabled=true;
	public void setSize(int w,int h){
		width=w;
		height=h;
		
		canvas.setCoordinateSpaceWidth(w);
		canvas.setCoordinateSpaceHeight(h);
		canvas.setSize(w+"px", h+"px");
	}

	public CanvasInputRange(int min,int max,int current){
		this.width=130;
		this.height=26;
		this.min=min;
		this.max=max;
		this.value=current;
		canvas=Canvas.createIfSupported();
		canvas.setCoordinateSpaceWidth(width);
		canvas.setCoordinateSpaceHeight(height);
		canvas.setSize(width+"px", height+"px");
		add(canvas);
		/*
		canvas.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				if(!enabled){
					return;
				}
				pointX=event.getX();
				update();
			}
		});
		*/
		
		canvas.addMouseUpHandler(new MouseUpHandler() {
			
			@Override
			public void onMouseUp(MouseUpEvent event) {
				if(!enabled){
					return;
				}
				mousePressed=false;
				mouseObscure=false;
				canvas.setFocus(false);
			}
		});

		canvas.addMouseDownHandler(new MouseDownHandler() {
			@Override
			public void onMouseDown(MouseDownEvent event) {
				if(!enabled){
					return;
				}
				
				mousePressed=true;
				pointX=event.getX();
				update();//for value change before mouse up;
			}
		});
		canvas.addMouseMoveHandler(new MouseMoveHandler() {
			@Override
			public void onMouseMove(MouseMoveEvent event) {
				if(!enabled){
					return;
				}
				if(!mousePressed){
					int x=event.getX();
					if(mouseObscure && (x<8 ||x>canvas.getCoordinateSpaceWidth()-8)){
						return;
					}else{
						return;
					}
					
				}
				
				pointX=event.getX();
				update();
				if(pointX<margin){
					mousePressed=false;
					mouseObscure=false;
					canvas.setFocus(false);
				}
				if(pointX>canvas.getCoordinateSpaceWidth()-margin){
					mousePressed=false;
					mouseObscure=false;
					canvas.setFocus(false);
				}
				
				
				
			}
		});
		canvas.addMouseOutHandler(new MouseOutHandler() {
			
			@Override
			public void onMouseOut(MouseOutEvent event) {
				if(!enabled){
					return;
				}
				
				mousePressed=false;
				mouseObscure=true;
				canvas.setFocus(false);
			}
		});
		lastValue=current+1;
		update(current);
	}
	
	int lastValue;
	private void update(int value){
		
		this.value=value;
		int vwidth=canvas.getCoordinateSpaceWidth()-margin*2;
		int p=(int) (vwidth*getParsent(value));
		pointX=margin+p;
		updateCanvas();
		
		if(value!=lastValue){
		
		fireEvent();
		lastValue=value;
		}
	}
	public double getParsent(int value){
		int total=max-min;
		return (double)(value-min)/total;
	}
	
	private int getValueByXPoint(int point){
		
		if(max-min<(width-margin*2)){
		int mx=point-margin;
		double perpixel=(getRealWidth())/(getRealMax()+1);
		//GWT.log(min+" - "+max+",per:"+perpixel+" "+",realwidth="+getRealWidth()+",realMax="+getRealMax());
		int pt=(int) (mx/perpixel);
	
		return pt+min;
		}else{
		point-=margin;
		int maxPoint=canvas.getCoordinateSpaceWidth()-margin*2;
		double parsent=(double)point/maxPoint;
		return (int) (parsent*(max-min)+min);
		}
	}
	private int getRealWidth(){
		return width-margin*2;
	}
	private int getRealMax(){
		return max-min;
	}
	
	private void update(){
		//updateCanvas();
		
		value=getValueByXPoint(pointX);
		value=Math.max(value,min);
		value=Math.min(value,max);
		update(value);
		
	}
	private void updateCanvas(){
canvas.getContext2d().clearRect(0, 0, width, height);
		
		
		drawLine("#ccc");
		
		String color=enabled?"#000":"#ccc";
		drawValue(color,pointX);
	}
	private void drawLine(String color){
		double middle=canvas.getCoordinateSpaceHeight()/2;
		canvas.getContext2d().save();
		canvas.getContext2d().setStrokeStyle(color);
		canvas.getContext2d().beginPath();
		canvas.getContext2d().moveTo(margin, middle);
		canvas.getContext2d().lineTo(canvas.getCoordinateSpaceWidth()-margin, middle);
		canvas.getContext2d().closePath();
		canvas.getContext2d().stroke();
		canvas.getContext2d().restore();
	}
	
	private void drawValue(String color,double ptx){
		if(ptx<margin){
			ptx=margin;
		}
		if(ptx>canvas.getCoordinateSpaceWidth()-margin){
			ptx=canvas.getCoordinateSpaceWidth()-margin;
		}
		
		double middle=canvas.getCoordinateSpaceHeight()/2;
		canvas.getContext2d().save();
		if(mousePressed){
			canvas.getContext2d().setFillStyle(pressedColor);	
		}else{
		canvas.getContext2d().setFillStyle(defaultColor);
		}
		canvas.getContext2d().setStrokeStyle(color);
		canvas.getContext2d().beginPath();
		canvas.getContext2d().moveTo(ptx, middle+12);
		canvas.getContext2d().lineTo(ptx-4, middle+4);
		canvas.getContext2d().lineTo(ptx-4, middle-4);
		canvas.getContext2d().lineTo(ptx+4, middle-4);
		canvas.getContext2d().lineTo(ptx+4, middle+4);
		canvas.getContext2d().lineTo(ptx, middle+12);
		canvas.getContext2d().closePath();
		canvas.getContext2d().fill();
		canvas.getContext2d().stroke();
		canvas.getContext2d().restore();
	}

	@Override
	public void setMax(int max) {
		this.max=max;
		update();
	}

	@Override
	public void setMin(int min) {
		this.min=min;
		update();
	}

	@Override
	public void setValue(int value) {
		 if(value<getMin()){
			 value=getMin();
		 }
		 if(value>getMax()){
			 value=getMax();
		 }
		update(value);
	}

	@Override
	public int getValue() {
		return value;
	}


	 private List<InputRangeListener> listeners=new ArrayList<InputRangeListener>();
	 public synchronized void addInputRangeListener(InputRangeListener listener){
		 listeners.add(listener);
	 }
	 public synchronized void removeInputRangeListener(InputRangeListener listener){
		 listeners.remove(listener);
	 }
	 
	 public synchronized void fireEvent(){
		 for(InputRangeListener listener:listeners){
			 listener.changed(getValue());
		 }
	 }

	@Override
	public void setEnabled(boolean bool) {
		enabled=bool;
		updateCanvas();
	}

	@Override
	public void setWidth(int width) {
		this.width=width;
		canvas.setWidth(width+"px");
		canvas.setCoordinateSpaceWidth(width);
		update(getValue());
	}

	@Override
	public int getMin() {
		return min;
	}

	@Override
	public int getMax() {
		return max;
	}


	
	

}
