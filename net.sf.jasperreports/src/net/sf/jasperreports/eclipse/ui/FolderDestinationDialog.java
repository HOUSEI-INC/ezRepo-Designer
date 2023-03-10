/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package net.sf.jasperreports.eclipse.ui;

import java.io.File;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import net.sf.jasperreports.eclipse.messages.Messages;

/**
 * The dialog for selecting the external folder
 * 
 */
public class FolderDestinationDialog extends TitleAreaDialog {

	/**
	 * The final path selected in the dialog
	 */
	private String fDirectory = null;
	
	/**
	 * The control for the text
	 */
	private Text fDirText;
	
	/**
	 * The title of the dialog
	 */
	private String title;
	
	/**
	 * The message of the dialog
	 */
	private String message;

	/**
	 * Creates a dialog to select file system folder.
	 *  
	 * @param shell the shell, must be not null
	 * @param title the title of the dialog, must be not null
	 * @param message the message of the dialog, must be not null
	 */
	public FolderDestinationDialog(Shell shell, String title, String message) {
		super(shell);
		setShellStyle(getShellStyle() | SWT.RESIZE );
		this.title = title;
		this.message = message;
	}
	
	/**
	 * Returns the result of the dialog.open() operation
	 * 
	 * @return the dialog.open() result, null if the dialog was not 
	 * opened or the cancel button was pressed
	 */
	public String getDirectory() {
		return fDirectory;
	}

	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.TitleAreaDialog#createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		setTitle(title);
		setMessage(message);
		Composite parentComposite = (Composite)super.createDialogArea(parent);
		Font font = parentComposite.getFont();
		Composite composite = new Composite(parentComposite, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginHeight = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_MARGIN);
		layout.marginWidth = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_MARGIN);
		layout.verticalSpacing = convertVerticalDLUsToPixels(IDialogConstants.VERTICAL_SPACING);
		layout.horizontalSpacing = convertHorizontalDLUsToPixels(IDialogConstants.HORIZONTAL_SPACING);
		composite.setLayout(layout);
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		composite.setFont(font);

    Composite dirComposite = new Composite(composite, SWT.NONE);
    layout = new GridLayout(3, false);
		dirComposite.setLayout(layout);
		dirComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		dirComposite.setFont(font);

    Label label = new Label(dirComposite, SWT.NONE);
    label.setText(Messages.common_path);
    label.setFont(font);
        
    fDirText = new Text(dirComposite, SWT.BORDER);
    GridData data = new GridData(GridData.FILL_HORIZONTAL);
    data.horizontalSpan = 1;
    fDirText.setLayoutData(data);
    fDirText.setFont(font);
    fDirText.addModifyListener(new ModifyListener() {
    	@Override
    	public void modifyText( ModifyEvent e ) {
				validate();
			}        	
    });

    Button button = new Button(dirComposite, SWT.PUSH);
    button.setText(Messages.common_browse);
    data = new GridData();
    int widthHint = convertHorizontalDLUsToPixels(IDialogConstants.BUTTON_WIDTH);
    Point minSize = button.computeSize(SWT.DEFAULT, SWT.DEFAULT, true);
    data.widthHint = Math.max(widthHint, minSize.x);
    button.setLayoutData(data);
    button.setFont(JFaceResources.getDialogFont());
    button.addSelectionListener(new SelectionAdapter() {
        @Override
        public void widgetSelected(SelectionEvent event) {
        	browse();
        }
    });
    return parentComposite;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
	 */
	@Override
	protected void configureShell(Shell newShell) {
		String title = "Select Destination Dialog";
		newShell.setText(title);
		super.configureShell( newShell );
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.TitleAreaDialog#createContents(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createContents(Composite parent) {
		Control c = super.createContents(parent);
		fDirText.setText("");
		validate();
		return c;
	}
	
	/**
	 * When the ok button is pressed the path in the 
	 * text area is stored and can be retrieved with getDirectory() method
	 */
	@Override
	protected void okPressed() {
		fDirectory = fDirText.getText().trim();
		super.okPressed();
	}

	/**
	 * Open the file browse dialog
	 */
	private void browse() {
		String last = fDirText.getText().trim();
		DirectoryDialog dialog = new DirectoryDialog(getShell(), SWT.SINGLE);
		dialog.setFilterPath(last);
		String result = dialog.open();
		if (result == null) {
			return;
		}
		fDirText.setText(result);
	}

	/**
	 * Validate the dialog and enable the ok button only if the path on 
	 * the text area point to an existing directory
	 */
	private void validate() {
		File file = new File(fDirText.getText().trim());
		getButton(IDialogConstants.OK_ID).setEnabled(file.isDirectory() && file.exists());
	}
}
