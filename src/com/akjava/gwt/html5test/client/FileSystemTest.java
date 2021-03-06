package com.akjava.gwt.html5test.client;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.akjava.gwt.html5.client.file.Blob;
import com.akjava.gwt.html5.client.file.DirectoryReader;
import com.akjava.gwt.html5.client.file.File;
import com.akjava.gwt.html5.client.file.FileError;
import com.akjava.gwt.html5.client.file.FileHandler;
import com.akjava.gwt.html5.client.file.FileIOUtils;
import com.akjava.gwt.html5.client.file.FileIOUtils.GetFileSystemListener;
import com.akjava.gwt.html5.client.file.FileIOUtils.MakeDirectoryCallback;
import com.akjava.gwt.html5.client.file.FileIOUtils.ReadStringCallback;
import com.akjava.gwt.html5.client.file.FileIOUtils.RequestPersitentFileQuotaListener;
import com.akjava.gwt.html5.client.file.FileIOUtils.WriteCallback;
import com.akjava.gwt.html5.client.file.FileReader;
import com.akjava.gwt.html5.client.file.FileSystem;
import com.akjava.gwt.html5.client.file.FileUploadForm;
import com.akjava.gwt.html5.client.file.FileUtils;
import com.akjava.gwt.html5.client.file.FileUtils.DataURLListener;
import com.akjava.gwt.html5.client.file.FileWriter;
import com.akjava.gwt.html5.client.file.ProgressEvent;
import com.akjava.gwt.html5.client.file.RequestFileSystem;
import com.akjava.gwt.html5.client.file.RequestFileSystem.FileSystemCallback;
import com.akjava.gwt.html5.client.file.callback.FileEntryCallback;
import com.akjava.gwt.html5.client.file.callback.FileErrorCallback;
import com.akjava.gwt.html5.client.file.callback.FileWriterCallback;
import com.akjava.gwt.html5.client.file.callback.ProgressEventCallback;
import com.akjava.gwt.html5.client.file.callback.VoidCallback;
import com.akjava.gwt.html5.client.file.webkit.DirectoryCallback;
import com.akjava.gwt.html5.client.file.webkit.FileCallback;
import com.akjava.gwt.html5.client.file.webkit.FileEntry;
import com.akjava.gwt.html5.client.file.webkit.WebkitStorageInfo;
import com.akjava.gwt.html5.client.file.webkit.WebkitStorageInfo.RequestQuotaCallback;
import com.akjava.gwt.html5.client.file.webkit.WebkitStorageInfo.StorageInfoUsageCallback;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArray;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionChangeEvent.Handler;
import com.google.gwt.view.client.SingleSelectionModel;

public class FileSystemTest  extends VerticalPanel{

	public FileSystemTest(){
		this.setSpacing(16);
		
		createSimpleButton(this);
		createQuotaButton(this);
		createQuotaInfoButton(this);
		createWriterButton(this);
		
		createImageUploadButton(this);
		
		createListButton(this);
		
		
		
		
		createFileIOButton(this);
	}
	
	private void createFileIOButton(Panel panel){
		VerticalPanel main=new VerticalPanel();
		
		HorizontalPanel requestButtons=new HorizontalPanel();
		panel.add(main);
		main.add(requestButtons);
		final Label label=new Label();
		main.add(label);
		requestButtons.add(new Label("FileIOUtils Test"));
		Button request=new Button("request",new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				FileIOUtils.requestPersitentFileQuota(1000, new RequestPersitentFileQuotaListener() {
					
					@Override
					public void onError(String message, Object option) {
						HTML5Test.log("error:"+message);
						if(option instanceof FileError){
							int code=((FileError)option).getCode();
							label.setText("error:"+FileIOUtils.getErrorCodeValue(code));
						}
					}
					
					@Override
					public void onAccepted(FileSystem fileSystem, double acceptedSize) {
						label.setText("accepted:"+acceptedSize);
						
					}
				});
			}
		});
		requestButtons.add(request);
		
		
		Button getRoot=new Button("getRoot",new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				FileIOUtils.getFileSystem(true, new GetFileSystemListener() {
					
					@Override
					public void onError(String message, Object option) {
						HTML5Test.log("error:"+message);
						if(option instanceof FileError){
							int code=((FileError)option).getCode();
							label.setText("error:"+FileIOUtils.getErrorCodeValue(code));
						}
					}
					
					@Override
					public void onGetFileSystem(FileSystem fileSystem) {
						label.setText("getRoot done:"+fileSystem.getRoot().getFullPath());
					}
				});
			}
		});
		requestButtons.add(getRoot);
		
		Button mkdir=new Button("mkdir",new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				FileIOUtils.makeDirectory(true, "dir", new MakeDirectoryCallback() {
					
					@Override
					public void onError(String message, Object option) {
						HTML5Test.log("error:"+message);
						if(option instanceof FileError){
							int code=((FileError)option).getCode();
							label.setText("error:"+FileIOUtils.getErrorCodeValue(code));
						}
					}
					
					@Override
					public void onMakeDirectory(FileEntry file) {
						label.setText("make done:"+file.getFullPath());
					}
				});
			}
		});
		requestButtons.add(mkdir);
		
		
		
		
		
		Button write=new Button("write",new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				FileIOUtils.writeFile(true, "dir/a.txt", "fileio", new WriteCallback() {
					
					@Override
					public void onError(String message, Object option) {
						HTML5Test.log("error:"+message);
						if(option instanceof FileError){
							int code=((FileError)option).getCode();
							label.setText("error:"+FileIOUtils.getErrorCodeValue(code));
						}
					}
					
					@Override
					public void onWriteEnd(FileEntry file) {
						label.setText("write done:"+file.getFullPath());
					}
				}, false);
			}
		});
		requestButtons.add(write);
		
		Button read=new Button("read",new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				FileIOUtils.readFileAsString(true, "dir/a.txt",  new ReadStringCallback() {
					
					@Override
					public void onError(String message, Object option) {
						HTML5Test.log("error:"+message);
						if(option instanceof FileError){
							int code=((FileError)option).getCode();
							label.setText("error:"+FileIOUtils.getErrorCodeValue(code));
						}
					}

					@Override
					public void onReadString(String text, FileEntry file) {
						label.setText("read done:"+file.getFullPath());
						previewTextArea.setText(text);
					}
					
					
				});
			}
		});
		requestButtons.add(read);

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
						
					}
				}
				,
				new FileErrorCallback(){

					@Override
					public void fileErrorCallback(FileError fileError) {
						label.setText("[error]"+fileError.getCode()+" "+FileIOUtils.getErrorCodeValue(fileError.getCode()));
						
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
					public void requestQuotaCallback(double grantedBytes) {
						final long size=(long) grantedBytes;
						
						RequestFileSystem.requestFileSystem(type,size,new FileSystemCallback() {
							
							@Override
							public void fileSystemCallback(FileSystem fileSystem) {
								label.setText("[file]"+fileSystem.getName()+" size="+size);
								
							}
						}
						,
						new FileErrorCallback(){

							@Override
							public void fileErrorCallback(FileError fileError) {
								label.setText("[error]"+fileError.getCode());
								label.setText("[error]"+fileError.getCode()+" "+FileIOUtils.getErrorCodeValue(fileError.getCode()));
								
							}});
					}
				});
				
				
				
			}
		});
		
		requestButtons.add(simpleRequest);
		requestButtons.add(label);
	}
	
	

	private void createImageUploadButton(Panel panel){
		HorizontalPanel requestButtons=new HorizontalPanel();
		panel.add(requestButtons);
		
		requestButtons.add(new Label("Image Upload"));
		
		final CheckBox check=new CheckBox("PERSISTENT");
		requestButtons.add(check);
		final Label label=new Label();
		
		final FileErrorCallback errorCallback=new FileErrorCallback(){

			@Override
			public void fileErrorCallback(FileError fileError) {
				label.setText("[error]"+fileError.getCode());
				label.setText("[error]"+fileError.getCode()+" "+FileIOUtils.getErrorCodeValue(fileError.getCode()));
				
			}};
			
		FileUploadForm form=FileUtils.createSingleFileUploadForm(new DataURLListener(){

			@Override
			public void uploaded(final File uploadFile, String value) {
				HTML5Test.doneDrop();//this panel 100% converd background change border
				int type=RequestFileSystem.TEMPORARY;
				if(check.getValue()){
					type=RequestFileSystem.PERSISTENT;
				}
				RequestFileSystem.requestFileSystem(type,100,new FileSystemCallback() {
					
					@Override
					public void fileSystemCallback(FileSystem fileSystem) {
						//if already exists call code: 9
						fileSystem.getRoot().getFile("test.png", true, false, new FileEntryCallback(){

							@Override
							public void fileEntryCallback(final FileEntry file) {
								
								file.createWriter(new FileWriterCallback() {
									
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
												Image img=new Image(file.toURL());
												add(img);
											}
										});
										
										
										//DownloadBlobBuilder blobBuilder=new HTML5Download().BlobBuilder();
										//blobBuilder.append("hello world");
										fileWriter.write(uploadFile);
										
									}
								}, errorCallback);
								
								label.setText(file.getFullPath());
								
							}}, errorCallback);
					}
				}
				,errorCallback
				);
				
			}},false);	
		form.setShowDragOverBorder(true);
		
		requestButtons.add(form);
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
				label.setText("[error]"+fileError.getCode()+" "+FileIOUtils.getErrorCodeValue(fileError.getCode()));
				
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
						fileSystem.getRoot().getFile("test.txt", true, false, new FileEntryCallback(){

							@Override
							public void fileEntryCallback(FileEntry file) {
								
								file.createWriter(new FileWriterCallback() {
									
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
										
										
										
										fileWriter.write(Blob.createBlob("hello world hello world"));
										
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
	
	
	
	
	
	private Map<String,FileEntry> fileEntryList=new HashMap<String,FileEntry>();
	private CellList<FileEntry> cellList;
	private SingleSelectionModel<FileEntry> selectionModel;
	private Handler selectionChangeHandler;
	private FileEntry currentSelectionFileEntry;
	private TextArea previewTextArea;
	private void createListButton(final Panel panel){
		
		VerticalPanel main=new VerticalPanel();
		HorizontalPanel requestButtons=new HorizontalPanel();
		panel.add(main);
		main.add(requestButtons);
		requestButtons.add(new Label("File List"));
		
		final CheckBox check=new CheckBox("PERSISTENT");
		requestButtons.add(check);
		final Label label=new Label();
		main.add(label);
		
		previewTextArea = new TextArea();
		
		final FileErrorCallback errorCallback=new FileErrorCallback(){

			@Override
			public void fileErrorCallback(FileError fileError) {
				label.setText("[error]"+fileError.getCode()+" "+FileIOUtils.getErrorCodeValue(fileError.getCode()));
				
				
			}};
			
			FileEntryCell cell=new FileEntryCell();
			cellList = new CellList<FileEntry>(cell);
			cellList.setStylePrimaryName("table");
			panel.add(cellList);
			
			selectionModel = new SingleSelectionModel<FileEntry>();
			cellList.setSelectionModel(selectionModel);
			selectionChangeHandler = new Handler() {
				@Override
				public void onSelectionChange(SelectionChangeEvent event) {
					
					FileEntry fileEntry=selectionModel.getSelectedObject();
					HTML5Test.log("selection-changed:"+fileEntry.getFullPath());
					
					currentSelectionFileEntry=fileEntry;
					
					fileEntry.file(new FileCallback() {
						
						@Override
						public void callback(File file) {
							final FileReader reader=FileReader.createFileReader();
							reader.setOnLoad(new FileHandler() {
								@Override
								public void onLoad() {
									previewTextArea.setValue(reader.getResultAsString());
								}
							});
							reader.readAsText(file, "UTF-8");
							
						}
					});
				}
			};
			selectionModel.addSelectionChangeHandler(selectionChangeHandler);
			
			
			
		Button simpleRequest=new Button("Update",new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				int type=RequestFileSystem.TEMPORARY;
				if(check.getValue()){
					type=RequestFileSystem.PERSISTENT;
				}
				RequestFileSystem.requestFileSystem(type,100,new FileSystemCallback() {
					
					@Override
					public void fileSystemCallback(FileSystem fileSystem) {
						
						fileEntryList.clear();
						
						
						
						DirectoryReader reader=fileSystem.getRoot().getReader();
						reader.readEntries(new DirectoryCallback() {
							@Override
							public void callback(JsArray<FileEntry> entries) {
								for(int i=0;i<entries.length();i++){
									fileAdd(entries.get(i),fileEntryList,cellList);
									
								}
								
								 updateCellList();
							}
						});
						
						
					}
				}
				,errorCallback
				);
			}
		});
		
Button removeButton=new Button("Remove",new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				final FileEntry entry=getCurrentSelection();
				if(entry==null){
					return;
				}
				entry.remove(new VoidCallback() {
					
					@Override
					public void callback() {
						fileEntryList.remove(entry.getFullPath());
						updateCellList();
						HTML5Test.log("removed:"+fileEntryList.size());
					}
				}, errorCallback);
			}
		});
Button mkdirButton=new Button("Create Dir",new ClickHandler() {
	
	@Override
	public void onClick(ClickEvent event) {
		
		FileEntry entry=getCurrentSelection();
		
		
		
		
		
		
		
		if(entry!=null && entry.isFile()){
			return;
		}
		
		
		if(entry==null){
			int type=RequestFileSystem.TEMPORARY;
			if(check.getValue()){
				type=RequestFileSystem.PERSISTENT;
			}
			RequestFileSystem.requestFileSystem(type,100,new FileSystemCallback() {
				@Override
				public void fileSystemCallback(FileSystem fileSystem) {
					
					createDir(fileSystem.getRoot(),"dir",errorCallback);
				}
			}
			,errorCallback
			);
			
			
		}else{
			createDir(entry,"dir",errorCallback);
		}
		
	}
});

Button writeButton=new Button("Add File",new ClickHandler() {
	
	@Override
	public void onClick(ClickEvent event) {
		FileEntry entry=getCurrentSelection();
		
	
		
		final JavaScriptObject blob=Blob.createBlob("hello world", "text/plain");
		
		final FileEntryCallback onwriteend=new FileEntryCallback() {
			@Override
			public void fileEntryCallback(FileEntry fileEntry) {
				fileEntryList.put(fileEntry.getFullPath(),fileEntry);
				updateCellList();
				HTML5Test.log("write end");
			}
		};
		
		final FileEntryCallback onerror=new FileEntryCallback() {
			@Override
			public void fileEntryCallback(FileEntry fileEntry){
				HTML5Test.log("error-on write:");
				fileEntry.remove(new VoidCallback() {
					@Override
					public void callback() {
						HTML5Test.log("write error & removed");
					}
				}, errorCallback);
			}
		};
		
		if(entry==null || entry.isFile()){
			
			int type=RequestFileSystem.TEMPORARY;
			if(check.getValue()){
				type=RequestFileSystem.PERSISTENT;
			}
			
			
			
			
			RequestFileSystem.requestFileSystem(type,100,new FileSystemCallback() {
				@Override
				public void fileSystemCallback(FileSystem fileSystem) {
					
					//how to overwrite?
					writeFile(fileSystem.getRoot(),"hello.txt",true,false,blob,false,onwriteend,onerror,errorCallback);
					
					
				}
			}
			,errorCallback
			);
			
			
		}else{
			writeFile(entry,"hello.txt",true,false,blob,false,onwriteend,onerror,errorCallback);
		}
		
	}
});
		
		requestButtons.add(simpleRequest);
		
		requestButtons.add(removeButton);
		requestButtons.add(mkdirButton);
		requestButtons.add(writeButton);
		add(previewTextArea);
	}
	
	protected FileEntry getCurrentSelection() {
		//selectionModel.getSelectedObject();
		return currentSelectionFileEntry;
	}

	private void fileAdd(FileEntry entry,final Map<String,FileEntry> container,final CellList<FileEntry> list){
		container.put(entry.getFullPath(),entry);
		if(entry.isDirectory()){
			DirectoryReader reader=entry.getReader();
			reader.readEntries(new DirectoryCallback() {
				@Override
				public void callback(JsArray<FileEntry> entries) {
					for(int i=0;i<entries.length();i++){
						fileAdd(entries.get(i),container,list);
					}
				}
			});
		}
		
		 list.setRowCount(fileEntryList.size());
		 
		 List<FileEntry> entries=new ArrayList<FileEntry>();
		 entries.addAll(fileEntryList.values());
		 list.setRowData(0, entries);
	}
	
	private void createDir(FileEntry parent,String name,FileErrorCallback fileErrorCallback){
		parent.getDirectory(name, true, false, new FileEntryCallback() {
			@Override
			public void fileEntryCallback(FileEntry file) {
				fileEntryList.put(file.getFullPath(),file);
				HTML5Test.log("directory created:"+fileEntryList.size());
				updateCellList();
			}
		}, fileErrorCallback);
	}
	
	private void updateCellList(){
		currentSelectionFileEntry=null;
		  cellList.setRowCount(fileEntryList.size());
		 List<FileEntry> entries=new ArrayList<FileEntry>();
		 entries.addAll(fileEntryList.values());
		 cellList.setRowData(0, entries);
		 //TODO test selectionModel.clear()
		 previewTextArea.setText("");
		 /*
		 selectionModel=new SingleSelectionModel<FileEntry>();
		 selectionModel.addSelectionChangeHandler(selectionChangeHandler);
		cellList.setSelectionModel(selectionModel);
		*/
	}
	
	private void writeFile(FileEntry parent,String name,boolean create,boolean exclusive,final JavaScriptObject blob,final boolean append,final FileEntryCallback onwriteend,final FileEntryCallback onerror,final FileErrorCallback errorCallback){
		parent.getFile(name,create,exclusive, new FileEntryCallback(){

			@Override
			public void fileEntryCallback(final FileEntry file) {
				
				file.createWriter(new FileWriterCallback() {
					
					@Override
					public void createWriterCallback(final FileWriter fileWriter) {
						
						
						fileWriter.setOnError(new ProgressEventCallback() {
							
							@Override
							public void progressEventCallback(ProgressEvent progressEvent) {
								onerror.fileEntryCallback(file);
							}
						});//maybe should remove it
						
						
						if(fileWriter.length()>0 &&!append){
							fileWriter.setOnWriteEnd(new ProgressEventCallback() {
								@Override
								public void progressEventCallback(ProgressEvent progressEvent) {
									fileWriter.setOnWriteEnd(new ProgressEventCallback() {
										@Override
										public void progressEventCallback(ProgressEvent progressEvent) {

											onwriteend.fileEntryCallback(file);
										}
									});
									fileWriter.write(blob);
								}
							});
							HTML5Test.log("truncated");
							
							
							
							fileWriter.truncate(0);//TODO blob support length	
						}else{
							HTML5Test.log("empty ust write");
							fileWriter.setOnWriteEnd(new ProgressEventCallback() {
								@Override
								public void progressEventCallback(ProgressEvent progressEvent) {

									onwriteend.fileEntryCallback(file);
								}
							});
							if(fileWriter.length()>0){
								fileWriter.seek(fileWriter.length());
							}
							fileWriter.write(blob);
						}
						
					}
				}, errorCallback);
				
			}}, errorCallback);
	}
	
	
}
