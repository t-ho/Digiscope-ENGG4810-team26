package gui;

import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileView;

/**
 * @author ToanHo
 *
 */
public class FileChooserUI extends JFileChooser {
	private static final long serialVersionUID = 1L;

	/**
	 * Construct FileChooserUI
	 * 
	 * @param fileSelectionMode
	 *            (e.g. JFileChooser.FILES_AND_DIRECTORIES)
	 * @param fileExtension
	 *            file extension (e.g. csv)
	 * @param iconPath
	 *            Path to icon
	 * @param decription
	 *            File description (e.g. "Comma-separated values file (*.csv)")
	 */
	public FileChooserUI(int fileSelectionMode, final String fileExtension, final String iconPath,
			final String decription) {
		super();
		this.setFileSelectionMode(fileSelectionMode);
		this.setAcceptAllFileFilterUsed(false);
		this.addChoosableFileFilter(new FileFilter() {

			@Override
			public String getDescription() {
				return decription;
			}

			@Override
			public boolean accept(File f) {
				if (f.isDirectory()) {
					return true;
				}
				String extension = getFileExtension(f);
				if (extension == null) {
					return false;
				} else {
					if (extension.equals(fileExtension)) {
						return true;
					} else {
						return false;
					}
				}
			}
		});

		this.setFileView(new FileView() {
			ImageIcon fileIcon = new ImageIcon(getClass().getResource(iconPath));

			public String getTypeDescription(File f) {
				String extension = getFileExtension(f);
				String type = null;
				if (extension != null) {
					if (extension.equals(fileExtension)) {
						type = decription;
					}
				}
				return type;
			}

			public Icon getIcon(File f) {
				String extension = getFileExtension(f);
				Icon icon = null;
				if (extension != null) {
					if (extension.equals(fileExtension)) {
						icon = fileIcon;
					}
				}
				return icon;
			}
		});
	}

	/**
	 * Get the file extension
	 * 
	 * @return file extension (lower case), otherwise null
	 */
	private String getFileExtension(File f) {
		String extension = null;
		if (f != null) {
			String fileName = f.getName();
			int i = fileName.lastIndexOf(".");
			if (i > 0 && i < fileName.length() - 1) {
				extension = fileName.substring(i + 1).toLowerCase();
			}
		}
		return extension;
	}

}
