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
		readFileAsString(path,persitent,callback,encoding);
	}
		
		public static void readFileAsString(final String path,boolean persitent,final ReadStringCallback callback,final String encoding){
		
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
	

	public static void makeDirectory(boolean persitent,final String path,final MakeDirectoryCallback callback){
		
		int type=RequestFileSystem.TEMPORARY;
		if(persitent){
			type=RequestFileSystem.PERSISTENT;
		}
		RequestFileSystem.requestFileSystem(type,0,new FileSystemCallback() {
			@Override
			public void fileSystemCallback(FileSystem fileSystem) {
				fileSystem.getRoot().getDirectory(path,true,false, new FileEntryCallback(){
					@Override
					public void fileEntryCallback(final FileEntry file) {
						callback.onMakeDirectory(file);
					}
				}
				, new MessageErrorCallback("getDirectory",callback));
			}
		}
		,new MessageErrorCallback("requestFileSystem",callback)
		);
	}
	public static void writeFile(boolean persitent,final String path,final String text,final WriteCallback callback,final boolean append){
		writeFile(persitent,path,text,callback,append,"UTF-8");
	}
	
	public static String getErrorCodeValue(int code){
		String value=""+code;
		switch(code){
		case 1:
			value="NOT_FOUND_ERR";
			break;
		case 2:
			value="SECURITY_ERR";
			break;
		case 3:
			value="ABORT_ERR";
			break;
		case 4:
			value="NOT_READABLE_ERR";
			break;
		case 5:
			value="ENCODING_ERR";
			break;
		case 6:
			value="NO_MODIFICATION_ALLOWED_ERR";
			break;
		case 7:
			value="INVALID_STATE_ERR";
			break;
		case 8:
			value="SYNTAX_ERR";
			break;
		case 9:
			value="INVALID_MODIFICATION_ERR";
			break;
		case 10:
			value="QUOTA_EXCEEDED_ERR";
			break;
		case 11:
			value="TYPE_MISMATCH_ERR";
			break;
		case 12:
			value="PATH_EXISTS_ERR";
			break;	
		}
		return value;
	}
	public static void writeFile(boolean persitent,final String path,final String text,final WriteCallback callback,final boolean append,final String encoding){
		final String mimeType="text/plain;charset="+encoding;
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
									
									
									
									
									fileWriter.truncate(0);//FUTURE blob support length	
								}else{
									
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
	//use 1 for detect not granted
		WebkitStorageInfo.requestQuota(type, 1, new RequestQuotaCallback(){
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
	public interface MakeDirectoryCallback extends ErrorCallback{
		public void onMakeDirectory(FileEntry file);
	}
}
