package com.akjava.gwt.html5.client;


public interface IInputRange {
public void setMax(int max);
public void setMin(int min);
public int getMin();
public int getMax();
public void setValue(Integer value);
public Integer getValue();
public void setWidth(int width);
public void setHeight(int height);
public void setEnabled(boolean bool);
public  void addInputRangeListener(InputRangeListener listener);
public  void removeInputRangeListener(InputRangeListener listener);
}
