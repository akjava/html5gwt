package com.akjava.gwt.html5test.client;

import com.akjava.gwt.html5.client.download.DownloadBlobBuilder;
import com.akjava.gwt.html5.client.download.HTML5Download;
import com.akjava.gwt.html5.client.file.FileError;
import com.akjava.gwt.html5.client.file.FileSystem;
import com.akjava.gwt.html5.client.file.FileWriter;
import com.akjava.gwt.html5.client.file.ProgressEvent;
import com.akjava.gwt.html5.client.file.RequestFileSystem;
import com.akjava.gwt.html5.client.file.RequestFileSystem.FileSystemCallback;
import com.akjava.gwt.html5.client.file.callback.CreateWriterCallback;
import com.akjava.gwt.html5.client.file.callback.FileErrorCallback;
import com.akjava.gwt.html5.client.file.callback.GetFileCallback;
import com.akjava.gwt.html5.client.file.callback.ProgressEventCallback;
import com.akjava.gwt.html5.client.file.webkit.FileEntry;
import com.akjava.gwt.html5.client.file.webkit.WebkitStorageInfo;
import com.akjava.gwt.html5.client.file.webkit.WebkitStorageInfo.RequestQuotaCallback;
import com.akjava.gwt.html5.client.file.webkit.WebkitStorageInfo.StorageInfoUsageCallback;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class FileSystemTest  extends VerticalPanel{

	public FileSystemTest(){
		this.setSpacing(16);
		
		createSimpleButton(this);
		createQuotaButton(this);
		createQuotaInfoButton(this);
		createWriterButton(this);
	}
	
	private void createSimpleButton(Panel panel){
		HorizontalPanel requestButtons=new HorizontalPanel();
		panel.add(requestButtons);
		
		requestButtons.add(new Label("Simple Request"));
		
		final CheckBox check=new CheckBox("PERSISTENT");
		requestButtons.add(check);
		final Label label=new Label();
		
		Button simpleRequest=new Button("simple request",new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				int type=RequestFileSystem.TEMPORARY;
				if(check.getValue()){
					type=RequestFileSystem.PERSISTENT;
				}
				RequestFileSystem.requestFileSystem(type,1000,new FileSystemCallback() {
					
					@Override
					public void fileSystemCallback(FileSystem fileSystem) {
						label.setText("[file]"+fileSystem.getName());
						HTML5Test.log(fileSystem);
						
					}
				}
				,
				new FileErrorCallback(){

					@Override
					public void fileErrorCallback(FileError fileError) {
						label.setText("[error]"+fileError.getCode());
						HTML5Test.log(fileError);
					}});
			}
		});
		
		requestButtons.add(simpleRequest);
		requestButtons.add(label);
	}
	
	private void createQuotaInfoButton(Panel panel){
		HorizontalPanel requestButtons=new HorizontalPanel();
		panel.add(requestButtons);
		
		requestButtons.add(new Label("Quota Info"));
		
		final CheckBox check=new CheckBox("PERSISTENT");
		requestButtons.add(check);
		final Label label=new Label();
		
		Button simpleRequest=new Button("quota info request",new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				final int type=check.getValue()?RequestFileSystem.PERSISTENT:RequestFileSystem.TEMPORARY;
				
				//WebkitStorageInfo.test();
				
				
				WebkitStorageInfo.queryUsageAndQuota(type, new StorageInfoUsageCallback() {
					@Override
					public void storageInfoUsageCallback(double usage,double quota) {
						label.setText("[info]usage="+(long)usage+" quota="+(long)quota);
					}
				});
				
				
				
			}
		});
		
		requestButtons.add(simpleRequest);
		requestButtons.add(label);
	}
	
	private void createQuotaButton(Panel panel){
		HorizontalPanel requestButtons=new HorizontalPanel();
		panel.add(requestButtons);
		
		requestButtons.add(new Label("Quota Request"));
		
		final CheckBox check=new CheckBox("PERSISTENT");
		requestButtons.add(check);
		final Label label=new Label();
		
		Button simpleRequest=new Button("quota request",new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				final int type=check.getValue()?RequestFileSystem.PERSISTENT:RequestFileSystem.TEMPORARY;
				
				//WebkitStorageInfo.test();
				
				
				WebkitStorageInfo.requestQuota(type, 10, new RequestQuotaCallback() {
					@Override
					public void requestQuotaCallback(int grantedBytes) {
						final int size=grantedBytes;
						
						RequestFileSystem.requestFileSystem(type,size,new FileSystemCallback() {
							
							@Override
							public void fileSystemCallback(FileSystem fileSystem) {
								label.setText("[file]"+fileSystem.getName()+" size="+size);
								HTML5Test.log(fileSystem);
							}
						}
						,
						new FileErrorCallback(){

							@Override
							public void fileErrorCallback(FileError fileError) {
								label.setText("[error]"+fileError.getCode());
								HTML5Test.log(fileError);
							}});
					}
				});
				
				
				
			}
		});
		
		requestButtons.add(simpleRequest);
		requestButtons.add(label);
	}
	
	private void createWriterButton(Panel panel){
		HorizontalPanel requestButtons=new HorizontalPanel();
		panel.add(requestButtons);
		
		requestButtons.add(new Label("Write Test"));
		
		final CheckBox check=new CheckBox("PERSISTENT");
		requestButtons.add(check);
		final Label label=new Label();
		
		final FileErrorCallback errorCallback=new FileErrorCallback(){

			@Override
			public void fileErrorCallback(FileError fileError) {
				label.setText("[error]"+fileError.getCode());
				HTML5Test.log(fileError);
			}};
		Button simpleRequest=new Button("Write",new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				int type=RequestFileSystem.TEMPORARY;
				if(check.getValue()){
					type=RequestFileSystem.PERSISTENT;
				}
				RequestFileSystem.requestFileSystem(type,100,new FileSystemCallback() {
					
					@Override
					public void fileSystemCallback(FileSystem fileSystem) {
						//if already exists call code: 9
						fileSystem.getRoot().getFile("test.txt", true, false, new GetFileCallback(){

							@Override
							public void getFileCallback(FileEntry file) {
								
								file.createWriter(new CreateWriterCallback() {
									
									@Override
									public void createWriterCallback(FileWriter fileWriter) {
										
										fileWriter.setOnError(new ProgressEventCallback() {
											
											@Override
											public void progressEventCallback(ProgressEvent progressEvent) {
												HTML5Test.log("error:"+progressEvent.getType());
											}
										});
										
										fileWriter.setOnWriteEnd(new ProgressEventCallback() {
											
											@Override
											public void progressEventCallback(ProgressEvent progressEvent) {
												HTML5Test.log("done:"+progressEvent.getType());
											}
										});
										
										
										DownloadBlobBuilder blobBuilder=new HTML5Download().BlobBuilder();
										blobBuilder.append("hello world");
										fileWriter.write(blobBuilder.getBlob("text/plain"));
										
									}
								}, errorCallback);
								
								label.setText(file.getFullPath());
								
							}}, errorCallback);
					}
				}
				,errorCallback
				);
			}
		});
		
		requestButtons.add(simpleRequest);
		requestButtons.add(label);
	}
}
