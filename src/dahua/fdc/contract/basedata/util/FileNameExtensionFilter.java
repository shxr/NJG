package com.kingdee.eas.fdc.basedata.util;

import java.io.File;
import java.util.Locale;

import javax.swing.filechooser.FileFilter;

public final class FileNameExtensionFilter extends FileFilter {
	// Description of this filter.
	private final String description;
	// Known extensions.
	private final String[] extensions;
	// Cached ext
	private final String[] lowerCaseExtensions;

	public FileNameExtensionFilter(String description, String[] extensions) {
		if (extensions == null || extensions.length == 0) {
			throw new IllegalArgumentException(
					"Extensions must be non-null and not empty");
		}
		this.description = description;
		this.extensions = new String[extensions.length];
		this.lowerCaseExtensions = new String[extensions.length];
		for (int i = 0; i < extensions.length; i++) {
			if (extensions[i] == null || extensions[i].length() == 0) {
				throw new IllegalArgumentException(
						"Each extension must be non-null and not empty");
			}
			this.extensions[i] = extensions[i];
			lowerCaseExtensions[i] = extensions[i].toLowerCase(Locale.ENGLISH);
		}
	}

	public boolean accept(File f) {
		if (f != null) {
			if (f.isDirectory()) {
				return true;
			}
			String fileName = f.getName();
			int i = fileName.lastIndexOf('.');
			if (i > 0 && i < fileName.length() - 1) {
				String desiredExtension = fileName.substring(i + 1)
						.toLowerCase(Locale.ENGLISH);
				for (int j = 0; j < lowerCaseExtensions.length; j++) {
					String extension = lowerCaseExtensions[j];
					if (desiredExtension.equals(extension)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public String getDescription() {
		return description;
	}

	public String[] getExtensions() {
		String[] result = new String[extensions.length];
		System.arraycopy(extensions, 0, result, 0, extensions.length);
		return result;
	}

	public String toString() {
		return super.toString() + "[description=" + getDescription()
				+ " extensions=" + java.util.Arrays.asList(getExtensions())
				+ "]";
	}
}
