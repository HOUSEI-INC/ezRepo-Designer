/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.jface.dialogs;

import org.eclipse.swt.widgets.Shell;

import com.jaspersoft.studio.messages.Messages;

import net.sf.jasperreports.eclipse.util.FileExtension;

/**
 * Dialog proposed when an image needs to be selected.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 */
public class SubreportSelectionDialog extends FileSelectionDialog {

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public SubreportSelectionDialog(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * @return the title for the dialog
	 */
	protected String getDialogTitle() {
		return Messages.SubreportSelectionDialog_0;
	}

	/**
	 * Returns an array of strings containing the title for the modes section, plus the title of every mode.
	 * <p>
	 * 
	 * Default implementation would return 6 strings, including 1 title and the following 5 modes:
	 * <ol>
	 * <li>workspace resource;</li>
	 * <li>absolute path in filesystem;</li>
	 * <li>URL;</li>
	 * <li>no image;</li>
	 * <li>custom expression</li>
	 * </ol>
	 * 
	 * @return the title and labels for the group of modes
	 */
	protected String[] getImageModesAndHeaderTitles() {
		return new String[] { Messages.SubreportSelectionDialog_1, Messages.SubreportSelectionDialog_2,
				Messages.SubreportSelectionDialog_3,
				Messages.SubreportSelectionDialog_4,
				Messages.SubreportSelectionDialog_5,
				Messages.SubreportSelectionDialog_6 };
	}

	@Override
	protected String getFileExtension() {
		return Messages.SubreportSelectionDialog_7;
	}

	@Override
	protected String[] getFileExtensions() {
		return new String[] { "*"+FileExtension.JRXML, "*.*" }; //$NON-NLS-1$ //$NON-NLS-2$
	}
}
