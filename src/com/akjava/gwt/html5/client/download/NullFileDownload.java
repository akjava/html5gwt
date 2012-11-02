package com.akjava.gwt.html5.client.download;

import com.akjava.gwt.html5.client.file.Blob;
import com.google.gwt.user.client.ui.Anchor;

public class NullFileDownload implements FileDownload{

	@Override
	public DownloadURL URL() {
		return null;
	}

	

	@Override
	public Anchor generateTextDownloadLink(String text, String fileName,
			String anchorText) {
		return null;
	}

	@Override
	public Anchor generateTextDownloadLink(String text, String fileName,
			String anchorText, boolean autoRemove) {
		return null;
	}

	@Override
	public Anchor generateBase64DownloadLink(String urlData, String mimeType,
			String fileName, String anchorText, boolean autoRemove) {
		return null;
	}

	@Override
	public Anchor generateDownloadLink(Blob blob, String mimeType,
			String fileName, String anchorText) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
