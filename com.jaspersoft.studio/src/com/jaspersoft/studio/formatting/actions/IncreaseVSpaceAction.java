/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.formatting.actions;

import java.util.List;

import net.sf.jasperreports.engine.design.JRDesignElement;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.SetValueCommand;

public class IncreaseVSpaceAction extends AbstractFormattingAction {

	/** The Constant ID. */
	public static final String ID = "increasevspace"; //$NON-NLS-1$
	
	public IncreaseVSpaceAction(IWorkbenchPart part) {
		super(part);
		setText(Messages.IncreaseVSpaceAction_actionName);
		setToolTipText(Messages.IncreaseVSpaceAction_actionDescription);
		setId(ID);
		setImageDescriptor(JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/resources/elem_add_vspace_plus.png"));  //$NON-NLS-1$
	}
	
	public static JSSCompoundCommand generateCommand(List<APropertyNode> nodes){
		JSSCompoundCommand command = new JSSCompoundCommand(null);
		
		if (nodes.isEmpty()) return command;
    List<APropertyNode> sortedElements = sortYX( nodes );
    
    for (int i=1; i<sortedElements.size(); ++i)
    {
    		APropertyNode actualNode = sortedElements.get(i);
    		command.setReferenceNodeIfNull(actualNode);
        JRDesignElement element = (JRDesignElement) actualNode.getValue();
	      SetValueCommand setCommand = new SetValueCommand();
  			setCommand.setTarget(actualNode);
  			setCommand.setPropertyId(JRDesignElement.PROPERTY_Y);
  			setCommand.setPropertyValue(element.getY() + 5*i);
	      command.add(setCommand);
    }
		
		return command;
	}

	@Override
	protected Command createCommand() {
		List<APropertyNode> nodes = getOperationSet();
		JSSCompoundCommand command = null;
		if (!nodes.isEmpty()){
			command = generateCommand(nodes);
			command.setDebugLabel(getText());
		}
		return command;
	}

	@Override
	protected boolean calculateEnabled() {
		return getOperationSet().size()>1;
	}

}
