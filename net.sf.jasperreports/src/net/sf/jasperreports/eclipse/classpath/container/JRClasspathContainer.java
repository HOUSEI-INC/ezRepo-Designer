/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package net.sf.jasperreports.eclipse.classpath.container;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import net.sf.jasperreports.eclipse.JasperReportsPlugin;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.osgi.framework.Bundle;

public class JRClasspathContainer implements IClasspathContainer {
	public final static Path ID = new Path("net.sf.jasperreports.JR_CONTAINER"); //$NON-NLS-1$

	// path string that uniquiely identifies this container instance
	private IPath path;

	public JRClasspathContainer(IPath path, IJavaProject project) {
		this.path = path;
	}

	public IClasspathEntry[] getClasspathEntries() {
		List<IClasspathEntry> entryList = new ArrayList<>();

		Bundle bundle = JasperReportsPlugin.getDefault().getBundle();
		Enumeration<URL> urls = bundle.findEntries("lib/", "jasperreports*.jar", true); //$NON-NLS-1$ //$NON-NLS-2$
		while (urls.hasMoreElements()) {
			URL url = urls.nextElement();
			try {
				URL fileURL = FileLocator.toFileURL(url);
				URI uri = new URI(fileURL.getProtocol(), fileURL.getUserInfo(), fileURL.getHost(), fileURL.getPort(),
						fileURL.getPath(), fileURL.getQuery(), null);
				Path binpath = new Path(new File(uri).getAbsolutePath());
				Path srcpath = binpath;
				entryList.add(JavaCore.newLibraryEntry(binpath, srcpath, new Path("/"))); //$NON-NLS-1$
			} catch (URISyntaxException | IOException e) {
				e.printStackTrace();
			}
		}
		// convert the list to an array and return it
		return entryList.toArray(new IClasspathEntry[entryList.size()]);
	}

	public IClasspathEntry[] getAllClasspathEntries() {
		List<IClasspathEntry> entryList = new ArrayList<>();

		Bundle bundle = JasperReportsPlugin.getDefault().getBundle();
		Enumeration<URL> urls = bundle.findEntries("lib/", "*.jar", true); //$NON-NLS-1$ //$NON-NLS-2$
		while (urls.hasMoreElements()) {
			URL url = urls.nextElement();
			try {
				URL fileURL = FileLocator.toFileURL(url);
				URI uri = new URI(fileURL.getProtocol(), fileURL.getUserInfo(), fileURL.getHost(), fileURL.getPort(),
						fileURL.getPath(), fileURL.getQuery(), null);
				Path binpath = new Path(new File(uri).getAbsolutePath());
				Path srcpath = binpath;
				entryList.add(JavaCore.newLibraryEntry(binpath, srcpath, new Path("/"))); //$NON-NLS-1$
			} catch (URISyntaxException | IOException e) {
				e.printStackTrace();
			}
		}
		// convert the list to an array and return it
		return entryList.toArray(new IClasspathEntry[entryList.size()]);
	}

	public String getDescription() {
		return "Runtime Library"; //$NON-NLS-1$
	}

	public int getKind() {
		return IClasspathContainer.K_APPLICATION;
	}

	public IPath getPath() {
		return path;
	}
}
