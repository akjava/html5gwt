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
package com.akjava.gwt.html5.client.file;


public class File extends Blob{

	protected File(){}
	
	public  final native String getFileName()/*-{
    return this.name;
  }-*/;
	
	public  final native int getFileSize()/*-{
    return this.size;
  }-*/;
	

	
	public  final native String getFileType()/*-{
    return this.type;
  }-*/;
	
	public  final native Blob slice(int start,int length)/*-{
    return this.slice(start,length);
  }-*/;
}
