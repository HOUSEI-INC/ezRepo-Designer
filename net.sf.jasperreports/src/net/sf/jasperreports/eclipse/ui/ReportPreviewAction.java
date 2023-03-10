/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package net.sf.jasperreports.eclipse.ui;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

/*
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: ShowDesignPreviewAction.java 26 2007-03-16 18:19:06Z lucianc $
 */
public class ReportPreviewAction implements IObjectActionDelegate {
	private ISelection selection;
	private IWorkbenchPart part;

	public ReportPreviewAction() {
	}

	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
		this.part = targetPart;
	}

	public void run(IAction action) {
		if (selection instanceof IStructuredSelection) {
			IStructuredSelection strSel = (IStructuredSelection) selection;
			if (strSel.size() != 1) {
				return;
			}
			final Object sel = strSel.getFirstElement();
			if (sel instanceof IFile) {
				try {
					ReportPreviewView view = (ReportPreviewView) part.getSite().getPage().showView(ReportPreviewView.ID);
					ReportPreviewUtil.loadFileIntoViewer((IFile) sel, view.getReportViewer(), view.getSite().getShell().getDisplay());
				} catch (CoreException e) {
					UIUtils.showError(e);
				}
			}
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {
		this.selection = selection;
	}

}
