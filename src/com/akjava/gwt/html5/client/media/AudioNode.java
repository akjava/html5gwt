package com.akjava.gwt.html5.client.media;

import com.google.gwt.core.client.JavaScriptObject;

public class AudioNode extends JavaScriptObject{
protected AudioNode(){}

public final  native void connect(AudioNode node)/*-{
this.connect(node);
}-*/;

public final  native void disconnect(AudioNode node)/*-{
this.disconnect(node);
}-*/;


}
