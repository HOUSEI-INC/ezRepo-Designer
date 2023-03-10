/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package net.sf.jasperreports.eclipse.viewer;

import java.net.URL;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.CloseWindowListener;
import org.eclipse.swt.browser.OpenWindowListener;
import org.eclipse.swt.browser.VisibilityWindowListener;
import org.eclipse.swt.browser.WindowEvent;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;

import net.sf.jasperreports.eclipse.JasperReportsPlugin;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.StringUtils;

/**
 * Utility class for operations with the SWT Browser widget.
 */
public class BrowserUtils {

	/** Constant for the property identifying the XulRunner location */
	public static final String SWT_BROWSER_XUL_RUNNER_PATH = "org.eclipse.swt.browser.XULRunnerPath"; //$NON-NLS-1$
	/** Constant for the property identifying the EDGE browser */
	public static final String SWT_BROWSER_EDGE_TYPE = "org.eclipse.swt.browser.DefaultType"; //$NON-NLS-1$
	/** Constant for the property identifying the EDGE WebView2 install location */
	public static final String SWT_BROWSER_EDGE_WEBVIEW_LOCATION = "org.eclipse.swt.browser.EdgeDir"; //$NON-NLS-1$

	/**
	 * Launches the browser to open the specified url
	 * 
	 * @param url
	 *            the url to open
	 */
	public static void openLink(String url) {
		try {
			IWebBrowser browser = JasperReportsPlugin.getDefault().getWorkbench().getBrowserSupport()
					.createBrowser(null);
			browser.openURL(new URL(url));
		} catch (Exception e) {
			UIUtils.showError(e);
		}

	}

	/**
	 * Creates a new SWT {@link Browser} widget.
	 * <p>
	 * 
	 * It takes care to detect the usage of a custom XULRunner installation, in
	 * order to create the widget with the dedicated {@link SWT#MOZILLA} style bit
	 * constant. By default the SWT browser is opened with pop-up window support.
	 * 
	 * @param parent
	 *            the browser parent
	 * @param style
	 *            the initial style
	 * @return the newly created SWT {@link Browser} widget
	 * 
	 * @see BrowserUtils#getSWTBrowserWidget(Composite, int, boolean)
	 * @see BrowserUtils#enablePopupSupport(Display, Browser)
	 */
	public static Browser getSWTBrowserWidget(Composite parent, int style) {
		return getSWTBrowserWidget(parent, style, true);
	}

	/**
	 * Creates a new SWT {@link Browser} widget.
	 * <p>
	 * 
	 * It takes care to detect the usage of a custom XULRunner installation, in
	 * order to create the widget with the dedicated {@link SWT#MOZILLA} style bit
	 * constant. Allows to specify the pop-up window support using the dedicated
	 * flag.
	 * 
	 * @param parent
	 *            the browser parent
	 * @param style
	 *            the initial style
	 * @param popupSupported
	 *            flag to specify if pop-up support should be enabled
	 * @return the newly created SWT {@link Browser} widget
	 * 
	 * @see BrowserUtils#enablePopupSupport(Display, Browser)
	 */
	public static Browser getSWTBrowserWidget(Composite parent, int style, boolean popupSupported) {
		// Detect if the information on XULRunner is set
		// If it is, then we will create the browser using SWT.MOZILLA style bit
		String xulRunnerLocation = System.getProperty(SWT_BROWSER_XUL_RUNNER_PATH);
		Browser browser = null;
		if (xulRunnerLocation != null && !xulRunnerLocation.isEmpty()) {
			browser = new Browser(parent, style | SWT.MOZILLA);
		} else {
			browser = new Browser(parent, style);
		}
		if (popupSupported) {
			enablePopupSupport(UIUtils.getDisplay(), browser);
		}
		return browser;
	}

	/**
	 * Enables the pop-up window support for the specified {@link Browser} instance.
	 * 
	 * @param display
	 *            the display used to open pop-up window(s)
	 * @param browser
	 *            the browser instance
	 * 
	 * @see {@link http://git.eclipse.org/c/platform/eclipse.platform.swt.git/tree/examples/org.eclipse.swt.snippets/src/org/eclipse/swt/snippets/Snippet270.java}
	 */
	public static void enablePopupSupport(final Display display, Browser browser) {
		browser.addOpenWindowListener(new OpenWindowListener() {
			@Override
			public void open(WindowEvent event) {
				if (!event.required)
					return; /* only do it if necessary */
				Shell shell = new Shell(display);
				shell.setText("");
				shell.setLayout(new FillLayout());
				Browser browser = new Browser(shell, SWT.NONE);
				enablePopupSupport(display, browser);
				event.browser = browser;
			}
		});
		browser.addVisibilityWindowListener(new VisibilityWindowListener() {
			@Override
			public void hide(WindowEvent event) {
				Browser browser = (Browser) event.widget;
				Shell shell = browser.getShell();
				shell.setVisible(false);
			}

			@Override
			public void show(WindowEvent event) {
				Browser browser = (Browser) event.widget;
				final Shell shell = browser.getShell();
				if (event.location != null)
					shell.setLocation(event.location);
				if (event.size != null) {
					Point size = event.size;
					shell.setSize(shell.computeSize(size.x, size.y));
				}
				shell.open();
			}
		});
		browser.addCloseWindowListener(new CloseWindowListener() {
			@Override
			public void close(WindowEvent event) {
				Browser browser = (Browser) event.widget;
				Shell shell = browser.getShell();
				shell.close();
			}
		});
	}

	/**
	 * Opens an external browser instance relying on the information set in the
	 * dedicated configuration browser page.
	 * 
	 * @param url
	 *            the URL to be opened
	 */
	public static void openExternalBrowser(final String url) {
		BusyIndicator.showWhile(null, new Runnable() {
			public void run() {
				URL helpSystemUrl = PlatformUI.getWorkbench().getHelpSystem().resolve(url, true);
				try {
					IWorkbenchBrowserSupport browserSupport = PlatformUI.getWorkbench().getBrowserSupport();
					IWebBrowser browser = browserSupport.getExternalBrowser();
					browser.openURL(helpSystemUrl);
				} catch (PartInitException ex) {
				}
			}
		});
	}
	
	
	/**
	 * Checks if the usage of the Edge WebView2 control has been set at application level.
	 * 
	 * <p>
	 * Properties that should be set (i.e Jaspersoft Studio .ini file):
	 * <ul>
	 * 	<li>-Dorg.eclipse.swt.browser.DefaultType=edge</li>
	 * 	<li>-Dorg.eclipse.swt.browser.EdgeDir=C:\\dev\\webview</li>
	 * </ul>
	 * 
	 * @return <code>true</code> if WebView (Edge) is set, <code>false</code> otherwise
	 */
	public static boolean isEdgeWebViewEnabled() {
		String pEdgeDefaultType = System.getProperty(SWT_BROWSER_EDGE_TYPE);
		String pEdgeWebViewLocation = System.getProperty(SWT_BROWSER_EDGE_WEBVIEW_LOCATION);
		return (!StringUtils.isNullOrEmpty(pEdgeDefaultType) && !StringUtils.isNullOrEmpty(pEdgeWebViewLocation));
	}

}
