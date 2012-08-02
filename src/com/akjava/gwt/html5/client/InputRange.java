package com.akjava.gwt.html5.client;


public interface InputRange {
public void setMax(int max);
public void setMin(int min);
public void setValue(int value);
public int getValue();
public void setEnabled(boolean bool);
public  void addListener(InputRangeListener listener);
public  void removeListener(InputRangeListener listener);
}
