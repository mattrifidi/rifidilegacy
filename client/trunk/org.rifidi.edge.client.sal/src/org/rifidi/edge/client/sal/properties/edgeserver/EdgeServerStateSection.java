/*
 * EdgeServerStateSection.java
 * 
 * Created:     July 8th, 2009
 * Project:       Rifidi Edge Server - A middleware platform for RFID applications
 *                    http://www.rifidi.org
 *                    http://rifidi.sourceforge.net
 * Copyright:   Pramari LLC and the Rifidi Project
 * License:      The software in this package is published under the terms of the EPL License
 *                    A copy of the license is included in this distribution under Rifidi-License.txt 
 */
package org.rifidi.edge.client.sal.properties.edgeserver;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.forms.widgets.FormText;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.rifidi.edge.client.model.sal.RemoteEdgeServer;

/**
 * A Section that displays the current connection state of the Edge Server in
 * the Tabbed Properties View
 * 
 * @author Kyle Neumeier - kyle@pramari.com
 */
public class EdgeServerStateSection extends AbstractPropertySection implements
		PropertyChangeListener {

	/** The Control to display the text */
	private FormText stateText = null;
	/** The Remote Edge server */
	private RemoteEdgeServer server;

	/**
	 * Constructor.
	 */
	public EdgeServerStateSection() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#createControls
	 * (org.eclipse.swt.widgets.Composite,
	 * org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	@Override
	public void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);
		Section section = super.getWidgetFactory().createSection(parent,
				Section.EXPANDED | Section.TITLE_BAR);
		section.setText("Edge Server State");
		Composite composite = super.getWidgetFactory().createFlatFormComposite(
				section);
		composite.setLayout(new TableWrapLayout());
		stateText = super.getWidgetFactory().createFormText(composite, false);
		TableWrapData layoutData = new TableWrapData(TableWrapData.FILL_GRAB);
		stateText.setLayoutData(layoutData);
		stateText.setEnabled(false);
		section.setClient(composite);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#setInput
	 * (org.eclipse.ui.IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);
		Object input = ((IStructuredSelection) selection).getFirstElement();
		RemoteEdgeServer server = (RemoteEdgeServer) input;
		if (server != null && this.server != server) {
			stateText.setText(server.getState().toString(), false, false);
			server.addPropertyChangeListener(this);
			this.server = server;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seejava.beans.PropertyChangeListener#propertyChange(java.beans.
	 * PropertyChangeEvent)
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(RemoteEdgeServer.STATE_PROPERTY)) {
			stateText.setText(server.getState().toString(), false, false);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.views.properties.tabbed.AbstractPropertySection#dispose()
	 */
	@Override
	public void dispose() {
		super.dispose();
		this.server.removePropertyChangeListener(this);
		stateText.dispose();
	}

}
