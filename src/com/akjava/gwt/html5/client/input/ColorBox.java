package com.akjava.gwt.html5.client.input;

/*
 * based on Hidden,Checkbox,ListBox
 * 
 * Copyright 2008 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */


import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.InputElement;
import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.editor.client.adapters.TakesValueEditor;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasName;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;


public class ColorBox extends Widget implements HasName,HasValue<String>, IsEditor<LeafValueEditor<String>> {

  /**
   * Creates a color widget that wraps an existing &lt;input type='color'&gt;
   * element.
   * 
   * This element must already be attached to the document. If the element is
   * removed from the document, you must call
   * {@link RootPanel#detachNow(Widget)}.
   * 
   * @param element the element to be wrapped
   */
  public static ColorBox wrap(Element element) {
    // Assert that the element is attached.
    assert Document.get().getBody().isOrHasChild(element);

    ColorBox color = new ColorBox(element);

    // Mark it attached and remember it for cleanup.
    color.onAttach();
    RootPanel.detachOnWindowClose(color);

    return color;
  }
  
  protected void ensureDomEventHandlers() {
	  addChangeHandler(new ChangeHandler() {
		@Override
		public void onChange(ChangeEvent event) {
			 ValueChangeEvent.fire(ColorBox.this, getValue());
		}
	});
	  
	 
	  }
  
  protected HandlerRegistration addChangeHandler(ChangeHandler handler) {
	    return addDomHandler(handler, ChangeEvent.getType());
	  }
  
  private LeafValueEditor<String> editor;

  /**
   * Constructor for <code>color</code>.
   */
  public ColorBox() {
	  InputElement input=Document.get().createHiddenInputElement();
	  input.setAttribute("type", "color");
	  setElement(input);
  }

  /**
   * Constructor for <code>Hidden</code>.
   * 
   * @param name name of the hidden field
   */
  public ColorBox(String name) {
    this();
    setName(name);
  }

  /**
   * Constructor for <code>Hidden</code>.
   * 
   * @param name name of the hidden field
   * @param value value of the hidden field
   */
  public ColorBox(String name, String value) {
    this(name);
    setValue(value);
  }

  /**
   * This constructor may be used by subclasses to explicitly use an existing
   * element. This element must be an &lt;input&gt; element whose type is
   * 'hidden'.
   * 
   * @param element the element to be used
   */
  protected ColorBox(Element element) {
    assert InputElement.as(element).getType().equalsIgnoreCase("color");
    setElement(element);
  }

  public LeafValueEditor<String> asEditor() {
    if (editor == null) {
      editor = TakesValueEditor.of(this);
    }
    return editor;
  }

  /**
   * Gets the default value of the color field.
   * 
   * @return the default value
   */
  public String getDefaultValue() {
    return getInputElement().getDefaultValue();
  }

  /**
   * Gets the id of the color field.
   * 
   * @return the id
   */
  public String getID() {
    return getElement().getId();
  }

  /**
   * Gets the name of the color field.
   * 
   * @return the name
   */

  public String getName() {
    return getInputElement().getName();
  }

  /**
   * Gets the value of the color field.
   * must start # & not empty
   * @return the value
   */
  public String getValue() {
	String value=getInputElement().getValue();
	if(value.isEmpty()||!value.startsWith("#")){
		return "#000000";
	}
    return value;
  }
  
  /**
   * get raw value
   * @return
   */
  public String getInputValue() {
	    return getInputElement().getValue();
	  }

  /**
   * Sets the default value of the color field.
   * 
   * @param defaultValue default value to set
   */
  public void setDefaultValue(String defaultValue) {
    getInputElement().setDefaultValue(defaultValue);
  }

  /**
   * Sets the id of the color field.
   * 
   * @param id id to set
   */
  public void setID(String id) {
    getElement().setId(id);
  }

  /**
   * Sets the name of the color field.
   * 
   * @param name name of the field
   */
  public void setName(String name) {
    if (name == null) {
      throw new NullPointerException("Name cannot be null");
    } else if (name.equals("")) {
      throw new IllegalArgumentException("Name cannot be an empty string.");
    }

    getInputElement().setName(name);
  }

  /**
   * Sets the value of the color field.
   * 
   * @param value value to set
   */
  public void setValue(String value) {
    getInputElement().setValue(value);
  }

  private InputElement getInputElement() {
    return getElement().cast();
  }

  /*
   * i have no idea so far
@Override
public HandlerRegistration addValueChangeHandler(ValueChangeHandler<String> handler) {
	// TODO Auto-generated method stub
	return null;
}
*/

@Override
public void setValue(String value, boolean fireEvents) {
	setValue(value);
}

private boolean valueChangeHandlerInitialized;
@Override
public HandlerRegistration addValueChangeHandler(ValueChangeHandler<String> handler) {
    // Is this the first value change handler? If so, time to add handlers
    if (!valueChangeHandlerInitialized) {
      ensureDomEventHandlers();
      valueChangeHandlerInitialized = true;
    }
    return addHandler(handler, ValueChangeEvent.getType());
}

/*
 don't wrok
  public HandlerRegistration addValueChangeHandler(
	      ValueChangeHandler<String> handler) {
	    return addHandler(handler, ValueChangeEvent.getType());
	  }
	  */
  

}

