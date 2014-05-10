package com.akjava.gwt.html5.client.media;

import com.akjava.gwt.html5.client.file.Uint8Array;

public class AnalyserNode extends AudioNode {
protected AnalyserNode(){}

public final  native void setSmoothingTimeConstant(double time)/*-{
this.smoothingTimeConstant =time;
}-*/;

public final  native void setFftSize(int size)/*-{
this.fftSize =size;
}-*/;

public final  native int getFftSize()/*-{
return this.fftSize;
}-*/;

/**
 * half of fft-size
 * @return
 */
public final  native int getFrequencyBinCount()/*-{
return this.frequencyBinCount;
}-*/;

public final  native void getByteFrequencyData(Uint8Array array)/*-{
this.getByteFrequencyData(array);
}-*/;

}
