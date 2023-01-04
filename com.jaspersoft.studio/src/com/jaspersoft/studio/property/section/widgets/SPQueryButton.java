/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.widgets;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.editor.SimpleJRXMLEditor;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.MQuery;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.model.dataset.MDataset;
import com.jaspersoft.studio.property.dataset.dialog.DatasetDialog;
import com.jaspersoft.studio.property.descriptor.pattern.dialog.PatternEditor;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.utils.SelectionHelper;

/**
 * A button that when clicked open the edit query dialog
 * 
 * @author Orlandin Marco
 * 
 */
public class SPQueryButton<T extends IPropertyDescriptor> extends ASPropertyWidget<T> {

	/**
	 * The button control
	 */
	private Button editQueryButton;

	/**
	 * The query of the report
	 */
	private MQuery mquery;

	/**
	 * The main dataset of the report
	 */
	private MDataset mdataset;

	/**
	 * 
	 * @param parent
	 * @param section
	 * @param pDescriptor
	 * @param buttonText
	 *          text on the button
	 */
	public SPQueryButton(Composite parent, AbstractSection section, T pDescriptor, String buttonText) {
		super(parent, section, pDescriptor);
		createButton(parent, buttonText);
	}

	@Override
	protected void createComponent(Composite parent) {
	}

	/**
	 * Build the button
	 * 
	 * @param parent
	 *          composite where is placed
	 * @param buttonText
	 *          text on the button
	 */
	protected void createButton(Composite parent, String buttonText) {
		editQueryButton = section.getWidgetFactory().createButton(parent, buttonText, SWT.PUSH);
		editQueryButton.setToolTipText(pDescriptor.getDescription());
		editQueryButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (SelectionHelper.getActiveJRXMLEditor() instanceof SimpleJRXMLEditor){
					UIUtils.showInformation(Messages.SPQueryButton_editorNotSuppoertedMessage);
				} else {
					PatternEditor wizard = new PatternEditor();
					Object queryText = mquery.getPropertyValue(JRDesignQuery.PROPERTY_TEXT);
					wizard.setValue(queryText != null ? queryText.toString() : ""); //$NON-NLS-1$
					new DatasetDialog(editQueryButton.getShell(), mdataset, mquery.getJasperConfiguration(), section
							.getEditDomain().getCommandStack()).open();
				}
			}
		});
	}

	@Override
	public void setData(APropertyNode pnode, Object value) {
		if (pnode instanceof MDataset)
			mdataset = (MDataset) pnode;
		else if (pnode instanceof MReport)
			mdataset = (MDataset) pnode.getPropertyValue(JasperDesign.PROPERTY_MAIN_DATASET);
		mquery = (MQuery) mdataset.getPropertyValue(JRDesignDataset.PROPERTY_QUERY);
	}

	@Override
	public Control getControl() {
		return editQueryButton;
	}

}