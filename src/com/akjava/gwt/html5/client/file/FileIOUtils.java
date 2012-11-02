package com.akjava.gwt.html5.client.file;

import com.akjava.gwt.html5.client.file.RequestFileSystem.FileSystemCallback;
import com.akjava.gwt.html5.client.file.callback.FileEntryCallback;
import com.akjava.gwt.html5.client.file.callback.FileErrorCallback;
import com.akjava.gwt.html5.client.file.callback.FileWriterCallback;
import com.akjava.gwt.html5.client.file.callback.ProgressEventCallback;
import com.akjava.gwt.html5.client.file.webkit.FileCallback;
import com.akjava.gwt.html5.client.file.webkit.FileEntry;
import com.akjava.gwt.html5.client.file.webkit.WebkitStorageInfo;
import com.akjava.gwt.html5.client.file.webkit.WebkitStorageInfo.RequestQuotaCallback;
import com.akjava.gwt.html5.client.file.webkit.WebkitStorageInfo.StorageInfoUsageCallback;
import com.akjava.gwt.html5test.client.HTML5Test;

public class FileIOUtils {
private FileIOUtils(){}


	public static void readFileAsString(final String path,boolean persitent,final ReadStringCallback callback){
		final String encoding="UTF8";
		int type=RequestFileSystem.TEMPORARY;
		if(persitent){
			type=RequestFileSystem.PERSISTENT;
		}
		RequestFileSystem.requestFileSystem(type,0,new FileSystemCallback() {
			
			@Override
			public void fileSystemCallback(FileSystem fileSystem) {
				//if already exists call code: 9
				fileSystem.getRoot().getFile(path, false, false, new FileEntryCallback(){

					@Override
					public void fileEntryCallback(final FileEntry fileEntry) {
						
						fileEntry.file(new FileCallback(){
							@Override
							public void callback(File file) {
								final FileReader reader=FileReader.createFileReader();
								reader.setOnLoad(new FileHandler() {
									@Override
									public void onLoad() {
										callback.onReadString(reader.getResultAsString(),fileEntry);
									}
								});
								reader.readAsText(file, encoding);
							}});
						
						
					}}, new MessageErrorCallback("getFile",callback));
			}
		}
		,new MessageErrorCallback("requestFileSystem",callback)
		);
	}
	
	public static void writeFile(final String path,boolean persitent,final String text,final WriteCallback callback,final boolean append){
		final String mimeType="text/plain;charset=UTF-8";
		int type=RequestFileSystem.TEMPORARY;
		if(persitent){
			type=RequestFileSystem.PERSISTENT;
		}
		RequestFileSystem.requestFileSystem(type,0,new FileSystemCallback() {
			@Override
			public void fileSystemCallback(FileSystem fileSystem) {
				
				final Blob blob=Blob.createBlob(text, mimeType);
				
				fileSystem.getRoot().getFile(path,true,false, new FileEntryCallback(){

					@Override
					public void fileEntryCallback(final FileEntry file) {
						
						file.createWriter(new FileWriterCallback() {
							
							@Override
							public void createWriterCallback(final FileWriter fileWriter) {
								
								
								fileWriter.setOnError(new ProgressEventCallback() {
									@Override
									public void progressEventCallback(ProgressEvent progressEvent) {
										callback.onError("onWrite",file);
									}
								});//maybe should remove it
								
								
								if(fileWriter.length()>0 &&!append){
									fileWriter.setOnWriteEnd(new ProgressEventCallback() {
										@Override
										public void progressEventCallback(ProgressEvent progressEvent) {
											fileWriter.setOnWriteEnd(new ProgressEventCallback() {
												@Override
												public void progressEventCallback(ProgressEvent progressEvent) {
													callback.onWriteEnd(file);
													
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

											callback.onWriteEnd(file);
										}
									});
									if(fileWriter.length()>0){
										fileWriter.seek(fileWriter.length());
									}
									fileWriter.write(blob);
								}
								
							}
						}, new MessageErrorCallback("getFileWriter",callback));
						
					}}, new MessageErrorCallback("getFile",callback));
			}
		}
		,new MessageErrorCallback("requestFileSystem",callback)
		);
	}
	
	private static class MessageErrorCallback implements FileErrorCallback{
		private String message;
		private ErrorCallback callback;
		
		public MessageErrorCallback(String message,ErrorCallback callback){
			this.message=message;
			this.callback=callback;
		}
		
		@Override
		public void fileErrorCallback(FileError fileError) {
			callback.onError(message, fileError);
		}
	}
	
	
	
	public static void getFileQuataAndUsage(boolean persistent,FileQuataAndUsageListener listener){
		WebkitStorageInfo.queryUsageAndQuota(persistent?RequestFileSystem.PERSISTENT:RequestFileSystem.TEMPORARY, listener);
	}
	
	
	
public static void getFileSystem(boolean persitent,final GetFileSystemListener listener){
	int type=RequestFileSystem.TEMPORARY;
	if(persitent){
		type=RequestFileSystem.PERSISTENT;
	}
		WebkitStorageInfo.requestQuota(type, 0, new RequestQuotaCallback(){
			@Override
			public void requestQuotaCallback(final double grantedBytes) {
				
				if(grantedBytes==0){
					listener.onError("zero quota", null);
					return;
				}
				
				RequestFileSystem.requestFileSystem(RequestFileSystem.PERSISTENT,grantedBytes,new FileSystemCallback(){
					@Override
					public void fileSystemCallback(FileSystem fileSystem) {
						listener.onGetFileSystem(fileSystem);
					}}
				
				,new FileErrorCallback() {
					@Override
					public void fileErrorCallback(FileError fileError) {
						listener.onError("onRequestFileSystem", fileError);
					}
				}
						);
				
				
			}
		});
	}

	public static void requestPersitentFileQuota(double size,final RequestPersitentFileQuotaListener listener){
		
		WebkitStorageInfo.requestQuota(RequestFileSystem.PERSISTENT, size, new RequestQuotaCallback(){
			@Override
			public void requestQuotaCallback(final double grantedBytes) {
				
				if(grantedBytes==0){
					listener.onError("zero quota", null);
					return;
				}
				
				RequestFileSystem.requestFileSystem(RequestFileSystem.PERSISTENT,grantedBytes,new FileSystemCallback(){
					@Override
					public void fileSystemCallback(FileSystem fileSystem) {
						listener.onAccepted(fileSystem, grantedBytes);
					}}
				
				,new FileErrorCallback() {
					@Override
					public void fileErrorCallback(FileError fileError) {
						listener.onError("onRequestFileSystem", fileError);
					}
				}
						);
				
				
			}
		});
	}
	
	public interface RequestPersitentFileQuotaListener extends ErrorCallback{
		public void onAccepted(FileSystem fileSystem,double acceptedSize);
	}
	
	public interface FileQuataAndUsageListener extends StorageInfoUsageCallback{
		
	}
	
	

	public interface GetFileSystemListener extends ErrorCallback{
		public void onGetFileSystem(FileSystem fileSystem);
	}
	
	public interface ErrorCallback{
		public void onError(String message,Object option);
	}
	
	public interface ReadStringCallback extends ErrorCallback{
		public void onReadString(String text,FileEntry file);
	}
	
	public interface WriteCallback extends ErrorCallback{
		public void onWriteEnd(FileEntry file);
	}
}
