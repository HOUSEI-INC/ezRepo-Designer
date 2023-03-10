/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package net.sf.jasperreports.eclipse.classpath.container;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.sf.jasperreports.eclipse.JasperReportsPlugin;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaModelException;

public class ClasspathContainerManager {

	private static List<IClasspathContainerFactory> factoryByNodeType = new ArrayList<>();

	public void init() {
		IConfigurationElement[] config = Platform.getExtensionRegistry()
				.getConfigurationElementsFor(JasperReportsPlugin.PLUGIN_ID, "classpathcontainer"); //$NON-NLS-1$
		for (IConfigurationElement e : config) {
			try {
				Object o = e.createExecutableExtension("ClassFactory"); //$NON-NLS-1$
				if (o instanceof IClasspathContainerFactory) {
					IClasspathContainerFactory compFactory = (IClasspathContainerFactory) o;
					factoryByNodeType.add(compFactory);
				}
			} catch (CoreException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}

	public void createJRClasspathContainer(IProgressMonitor monitor, List<IClasspathEntry> centries,
			IJavaProject javaProject) throws JavaModelException {
		for (IClasspathContainerFactory f : factoryByNodeType)
			f.createJRClasspathContainer(monitor, centries, javaProject);
	}

	public Set<Path> getRemovableContainers() {
		Set<Path> set = new HashSet<>();
		for (IClasspathContainerFactory f : factoryByNodeType) {
			Set<Path> s = f.isRemovable();
			if (s != null && !s.isEmpty())
				set.addAll(s);
		}
		return set;
	}
}
