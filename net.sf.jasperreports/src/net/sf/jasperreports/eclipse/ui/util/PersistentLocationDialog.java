/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package net.sf.jasperreports.eclipse.ui.util;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.util.Geometry;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import net.sf.jasperreports.eclipse.JasperReportsPlugin;

/**
 * A dialog that can store the size and position when it is closed, to be
 * opened in the same position the next time it is opened
 * 
 * @author Orlandin Marco
 *
 */
public class PersistentLocationDialog extends Dialog {
	
	/**
	 * Property for the position X of the dialog
	 */
	protected static final String DIALOG_X = "dialogX";

	/**
	 * Property for the position Y of the dialog
	 */
	protected static final String DIALOG_Y = "dialogY";
	
	/**
	 * Property for the position width of the dialog
	 */
	protected static final String DIALOG_WIDTH = "dialogWidth";
	
	/**
	 * Property for the position height of the dialog
	 */
	protected static final String DIALOG_HEIGHT = "dialogHeight";	
	
	/**
	 * flag used to disable the save of the settings
	 */
	private boolean saveSettings = true;
	
	/**
	 * Default width of the dialog, used  when there are not previous size stored
	 * or if the dialog is not resizable
	 */
	protected int defwidth = -1;

	/**
	 * Default height of the dialog, used  when there are not previous size stored
	 * or if the dialog is not resizable
	 */
	protected int defheight = -1;

	protected PersistentLocationDialog(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * Return the storage for the dialog. Return null if the flag to save the 
	 * settings is false 
	 */
	@Override
	protected IDialogSettings getDialogBoundsSettings() {
		if (saveSettings) {
			String dialogID = getClass().getName();
			IDialogSettings settings = JasperReportsPlugin.getDefault().getDialogSettings().getSection(dialogID);
			if (settings == null){
				settings = JasperReportsPlugin.getDefault().getDialogSettings().addNewSection(dialogID);
			}
			return settings;
			
		} else {
			return super.getDialogBoundsSettings();
		}
	}
	
	@Override
	protected void initializeBounds() {
		super.initializeBounds();
		if (resizeHasOccurred){
			Shell shell = getShell();
			Point initialLocation = getInitialLocation(shell.getSize());
			shell.setLocation(initialLocation);
		}
	}

	/**
	 * Return the initial location of the dialog, first look in the dialog settings, 
	 * if not available it return a center position for the monitor where the main application
	 * is opened. It will return the center position even if the dialog is palced into a not valid
	 * monitor
	 */
	@Override
	protected Point getInitialLocation(Point initialSize) {
		IDialogSettings settings = getDialogBoundsSettings();
		if (settings != null) {
			try {
				int x = settings.getInt(DIALOG_X);
				int y = settings.getInt(DIALOG_Y);
				Point result = new Point(x, y);
				if (isInsideMonitor(result)){
					return result;
				}
			} catch (NumberFormatException e) {
			}
		}
		return getCenteredMonitorLocation(initialSize);
	}
	
	/**
	 * Return the initial size of the dialog, first check in the stored settings,
	 * then it look in the default width and height settings. If event this are not 
	 * available it try to compute the size
	 */
	@Override
	protected Point getInitialSize() {
		if (isResizable()){
			IDialogSettings settings = getDialogBoundsSettings();
			if (settings != null) {
				try {
					int width = settings.getInt(DIALOG_WIDTH);
					int height = settings.getInt(DIALOG_HEIGHT);
					return new Point(width, height);
				} catch (NumberFormatException e) {
				}
			}
		}
		if (defwidth > 0 && defheight > 0){
			return new Point(defwidth, defheight);
		} else return super.getInitialSize();
	}
	
	/**
	 * Check if the passed position is inside a valid monitor
	 * 
	 * @param currentLocation position to check
	 * @return true if the position is trully inside an existing monitor, 
	 * false otherwise
	 */
	protected boolean isInsideMonitor(Point currentLocation){
		boolean isInside = false;
		Rectangle currentBounds = new Rectangle(0, 0, 0, 0);
		
		for(Monitor monitor : getShell().getDisplay().getMonitors()){
			Rectangle monitorBounds = monitor.getBounds();
			int endWidth = monitorBounds.x + monitorBounds.width;
			if (endWidth > currentBounds.width){
				currentBounds.width = endWidth;
			}
			
			if (monitorBounds.x < currentBounds.x){
				currentBounds.x = monitorBounds.x;
			}
			
			if (currentLocation.x > currentBounds.x && currentLocation.x < currentBounds.width){
				//check the y axis on the current monitor
				if (currentLocation.y > monitorBounds.y && currentLocation.y < monitorBounds.height){
					isInside = true;
					break;
				} else {
					isInside = false;
					break;
				}
			}
		}
		return isInside;
	}
	
	
	/**
	 * Saves the bounds of the shell in the appropriate dialog settings. The
	 * bounds are recorded relative to the parent shell, if there is one, or
	 * display coordinates if there is no parent shell.
	 *
	 * @param shell
	 *            The shell whose bounds are to be stored
	 *
	 * @since 3.2
	 */
	protected void saveDialogBounds(Shell shell) {
		IDialogSettings settings = getDialogBoundsSettings();
		if (settings != null) {
			Point shellLocation = shell.getLocation();
			Point shellSize = shell.getSize();
			settings.put(DIALOG_X, shellLocation.x);
			settings.put(DIALOG_Y, shellLocation.y);
			settings.put(DIALOG_WIDTH, shellSize.x);
			settings.put(DIALOG_HEIGHT, shellSize.y);
		}
	}
	
	/**
	 * When closed the size and position of the dialog are saved
	 */
	@Override
	public boolean close() {
		if (getShell() != null && !getShell().isDisposed()) {
			saveDialogBounds(getShell());
		}
		return super.close();
	}

	/**
	 * Return the point where the dialog should be placed to be ceneterd in the monitor of 
	 * the main application
	 * 
	 * @param initialSize the size of the dialog
	 * @return a not null point to have the dialog centered in the main application ponitor
	 */
	protected Point getCenteredMonitorLocation(Point initialSize) {
		Monitor monitor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell().getMonitor();
		Rectangle monitorBounds = monitor.getClientArea();
		Point centerPoint = Geometry.centerPoint(monitorBounds);
		
		return new Point(centerPoint.x - (initialSize.x / 2), Math.max(
				monitorBounds.y, Math.min(centerPoint.y
						- (initialSize.y * 2 / 3), monitorBounds.y
						+ monitorBounds.height - initialSize.y)));
	}
	
	/**
	 * Set if the dialog should store size and position when it is closed, they will 
	 * be reused the next time it is opened. By default this is true
	 * 
	 * @param value true if the settings should be stored, false otherwise
	 */
	protected void setSaveSettings(boolean value){
		this.saveSettings = value;
	}
	
	/**
	 * Set the default size of the dialog, this defaults will be used when there are
	 * no settings for the size or if the dialog is not resizable
	 * 
	 * @param defwidth the default width or -1 to use the default
	 * @param defheight the default height or -1 to use the default
	 */
	public void setDefaultSize(int defwidth, int defheight) {
		this.defwidth = defwidth;
		this.defheight = defheight;
	}
}
