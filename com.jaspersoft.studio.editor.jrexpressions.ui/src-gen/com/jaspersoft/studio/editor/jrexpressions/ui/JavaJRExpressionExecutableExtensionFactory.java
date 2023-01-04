/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
/*
 * generated by Xtext
 */
package com.jaspersoft.studio.editor.jrexpressions.ui;

import org.eclipse.xtext.ui.guice.AbstractGuiceAwareExecutableExtensionFactory;
import org.osgi.framework.Bundle;

import com.google.inject.Injector;

import com.jaspersoft.studio.editor.jrexpressions.ui.JRExpressionsActivator;

/**
 * This class was generated. Customizations should only happen in a newly
 * introduced subclass. 
 */
public class JavaJRExpressionExecutableExtensionFactory extends AbstractGuiceAwareExecutableExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return JRExpressionsActivator.getInstance().getBundle();
	}
	
	@Override
	protected Injector getInjector() {
		return JRExpressionsActivator.getInstance().getInjector(JRExpressionsActivator.COM_JASPERSOFT_STUDIO_EDITOR_JREXPRESSIONS_JAVAJREXPRESSION);
	}
	
}