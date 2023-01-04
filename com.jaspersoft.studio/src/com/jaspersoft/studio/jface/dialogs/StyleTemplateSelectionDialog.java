/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.jface.dialogs;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/**
 * Dialog proposed when an image needs to be selected.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 */
public class StyleTemplateSelectionDialog extends FileSelectionDialog {

	/**
	 * Expression that will be shown in the dialog once opened
	 */
	private String initialExpression = null;	
	
	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public StyleTemplateSelectionDialog(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * @return the title for the dialog
	 */
	protected String getDialogTitle() {
		return "スタイルテンプレートを選択します";
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
		return new String[] { "スタイルテンプレート·モード を選択します", "ワークスペースリソース（ワークスペース内のコンポネント）",
				"ファイルシステム内の絶対パス（クイックテスト専用で、実際のレポートでは使用しません）",
				"URL（スタイルテンプレートを参照するリモートURL。式の値になります）",
				"式をカスタマイズする（式エディタを使用してスタイルテンプレートの式を入力する）",
				"スタイルテンプレートなし(スタイルテンプレート参照は設定されません)" };
	}

	@Override
	protected String getFileExtension() {
		return "*.jrtx";
	}

	@Override
	protected String[] getFileExtensions() {
		return new String[] { "*.jrtx", "*.*" };
	}
	
	/**
	 * Set the expression that will be shown in the dialog 
	 * once opened
	 * 
	 * @param expression the expression text, can be null if 
	 * nothing should be shown
	 */
	public void setInitialExpression(String expression){
		this.initialExpression = expression;
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		Control control = super.createDialogArea(parent);
		if (initialExpression != null){
			showCustomExpression(initialExpression);	
		}
		return control;
	}
}
