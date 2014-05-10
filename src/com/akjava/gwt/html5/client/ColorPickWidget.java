/*
 * Copyright (C) 2011 aki@akjava.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.akjava.gwt.html5.client;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.MouseWheelEvent;
import com.google.gwt.event.dom.client.MouseWheelHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.TakesValue;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class ColorPickWidget extends Composite {

	private static ColorPickWidgetUiBinder uiBinder = GWT.create(ColorPickWidgetUiBinder.class);

	interface ColorPickWidgetUiBinder extends UiBinder<Widget, ColorPickWidget> {
	}
	private Canvas canvas;

	private int addition=17;
	
	public static final int MODE_RG=0;
	public static final int MODE_RB=1;
	public static final int MODE_GB=2;
	public static final int MODE_GREY=3;
	
	private int mode;
	private boolean picking;
	public ColorPickWidget() {
		initWidget(uiBinder.createAndBindUi(this));
		
		controls.setVisible(false);
		plus.setVisible(false);
		minus.setVisible(false);
		
		hex.setWidth("50px");
		range = InputRangeWidget.createInputRange(0, 15, 0);
		range.setWidth("160px");
		range.addMouseUpHandler(new MouseUpHandler() {
			@Override
			public void onMouseUp(MouseUpEvent event) {
				update();
			}
		});
		
		control.add(range);
		
		canvas = Canvas.createIfSupported();
		canvas.setSize("160px", "160px");
		canvas.setCoordinateSpaceHeight(160);
		canvas.setCoordinateSpaceWidth(160);
		canvas.addMouseWheelHandler(new MouseWheelHandler() {
			
			@Override
			public void onMouseWheel(MouseWheelEvent event) {
				int d=event.getDeltaY();
				if(d>0){
					plus();
				}else if(d<0){
					minus();
				}
			}
		});
		canvas.addMouseMoveHandler(new MouseMoveHandler() {
			
			@Override
			public void onMouseMove(MouseMoveEvent event) {
				int color=getColorAt(event.getX(),event.getY());
				current.setText(toCssColor(color).toUpperCase());
			}
		});
		canvas.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				
				int color=getColorAt(event.getX(),event.getY());
				setValue(color);
				
				
			}
		});
		main.add(canvas);
		draw();
		setValue(0xffffff);
	}
	public int getValue(){
		return currentColor;
	}
	public String getValueAsHex(){
		return toCssColor(currentColor);
	}
	private int getColorAt(int cx,int cy){
		if(mode!=MODE_GREY){
			
			int x=cx/gridSize;
			int y=cy/gridSize;
			int color=0;
			if(mode==MODE_RG){
				color=toColorInt(x*17,y*17,remainColor);
			}else if(mode==MODE_RB){
				color=toColorInt(x*17,remainColor,y*17);
			}else{
				color=toColorInt(remainColor,x*17,y*17);
			}
			return color;
		}else{
			int x=cx/(gridSize*4);
			int y=cy/(gridSize*4);
			int v=x+y*4;
			//GWT.log("0x"+Integer.toHexString(v));
			int g=v*17;
			int color=toColorInt(g,g,g);
			return color;
		}
	}
	
	int remainColor;
	
	int currentColor;
	public void setValue(int c){
		currentColor=c;
		String h=toCssColor(c);
		colorLabel.getElement().getStyle().setBackgroundColor(h);
		hex.setText(h.substring(1));
	}
	
	private void draw() {
		gridSize = 10;
		
		
		remainColor=range.getValue()*17;
		if(mode!=MODE_GREY){//grey mode
		for(int y=0;y<16;y++){
			for(int x=0;x<16;x++){
				int color=0;
				if(mode==MODE_RG){
					color=toColorInt(x*17,y*17,remainColor);
				}else if(mode==MODE_RB){
					color=toColorInt(x*17,remainColor,y*17);
				}else{
					color=toColorInt(remainColor,x*17,y*17);
				}
				
				
				
				
				canvas.getContext2d().setFillStyle(toCssColor(color));
				canvas.getContext2d().fillRect(x*gridSize, y*gridSize, gridSize, gridSize);
				
			}
		}
		}else{
			//00 11 -- ee ff
			
			for(int y=0;y<4;y++){
				for(int x=0;x<4;x++){
				//int v=x+y*4;
				int v=x+y*4;
				//GWT.log("0x"+Integer.toHexString(v));
				int g=v*17;
				
			
				
			int color=toColorInt(g,g,g);
			//GWT.log(""+g);
			canvas.getContext2d().setFillStyle(toCssColor(color));
			canvas.getContext2d().fillRect(x*gridSize*2*2, y*gridSize*2*2, gridSize*2*2, gridSize*2*2);
				}
			}
		}
	}
	
	private int toColorInt(int r,int g,int b){
		int c=(0xff & r)<<16| (0xff & g)<<8|(0xff & b);
		return c;
	}
	public static String toCssColor(int rgb){
		String ret=Integer.toHexString(rgb);
		while(ret.length()<6){
			ret="0"+ret;
		}
		return "#"+ret;
	}	
	
@UiField Label colorLabel,current;
@UiField TextBox hex;
@UiField Button pick,minus,plus,rg,rb,gb,grey;
@UiField VerticalPanel main,controls;
@UiField HorizontalPanel control;

private InputRangeWidget range;

private int gridSize;
	
public Label getColorLabel(){
return colorLabel;
}

public Label getCurrent(){
return current;
}

public TextBox getHex(){
return hex;
}

public Button getPick(){
return pick;
}

public Button getMinus(){
return minus;
}

public Button getPlus(){
return plus;
}

public Button getRg(){
return rg;
}

public Button getRb(){
return rb;
}
public Button getGrey(){
	return grey;
	}

public Button getGb(){
return gb;
}

public VerticalPanel getMain(){
return main;
}


	
@UiHandler("pick")
void clickPick(ClickEvent e) {
if(picking){
	controls.setVisible(false);
	picking=false;
	pick.setText("pick");
	
}else{
	controls.setVisible(true);
	picking=true;
	pick.setText("close");
}
}
private void minus(){
	remainColor-=addition;
	if(remainColor<0){
		remainColor=0;
		}
	((TakesValue<Integer>)range).setValue(remainColor/17);
	update();
}
private void plus(){
	remainColor+=addition;
	if(remainColor>255){
		remainColor=255;
		}
	((TakesValue<Integer>)range).setValue(remainColor/17);
	update();
}
@UiHandler("minus")
void clickMinus(ClickEvent e) {
	if(remainColor==-1){
		remainColor=255;
		update();
		return;
	}
	remainColor-=addition;
	if(remainColor<0){
		remainColor=-1;
		}
	update();
}
private void update() {
	draw();
}

@UiHandler("plus")
void clickPlus(ClickEvent e) {
if(remainColor==-1){
	remainColor=0;
	update();
	return;
}
remainColor+=addition;
if(remainColor>255){
	remainColor=-1;
	}
update();
}
@UiHandler("rg")
void clickRg(ClickEvent e) {
mode=MODE_RG;
update();
}
@UiHandler("rb")
void clickRb(ClickEvent e) {
	mode=MODE_RB;
	update();
}
@UiHandler("gb")
void clickGb(ClickEvent e) {
	mode=MODE_GB;
	update();
}
@UiHandler("grey")
void clickGrey(ClickEvent e) {
	mode=MODE_GREY;
	update();
}

}
