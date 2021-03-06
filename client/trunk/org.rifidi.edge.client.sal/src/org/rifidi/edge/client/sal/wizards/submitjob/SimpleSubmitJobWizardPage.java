/*
 * SimpleSubmitJobWizardPage.java
 * 
 * Created:     July 8th, 2009
 * Project:       Rifidi Edge Server - A middleware platform for RFID applications
 *                    http://www.rifidi.org
 *                    http://rifidi.sourceforge.net
 * Copyright:   Pramari LLC and the Rifidi Project
 * License:      The software in this package is published under the terms of the EPL License
 *                    A copy of the license is included in this distribution under Rifidi-License.txt 
 */

package org.rifidi.edge.client.sal.wizards.submitjob;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

/**
 * This is a wizard page that allows a user to pick only the execution interval
 * of a command to be submitted (i.e. the user cannot pick the
 * CommandConfiguraiton)
 * 
 * @author Kyle Neumeier - kyle@pramari.com
 */
public class SimpleSubmitJobWizardPage extends WizardPage {

	/** The widget that allows the user to choose the interval */
	private IntervalChooserComposite intervalChooserComposite;

	/**
	 * Constructor
	 * 
	 * @param pageName
	 */
	protected SimpleSubmitJobWizardPage(String pageName) {
		super(pageName);
		System.out.println("SimpleSubmitJobWizardPage constructor");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets
	 * .Composite)
	 */
	@Override
	public void createControl(Composite parent) {
		System.out.println("Creating the control");
		setTitle("Job Submission Wizard");
		setDescription("Submit a New Job to a Session");
		Group bottomGroup = new Group(parent, SWT.SHADOW_ETCHED_IN);
		bottomGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		bottomGroup.setText("Scheduling Options");
		bottomGroup.setLayout(new GridLayout(1, true));
		this.intervalChooserComposite = new IntervalChooserComposite(
				bottomGroup, SWT.None);
		setControl(bottomGroup);
		setPageComplete(true);
		System.out.println("After setpagecomplete");
	}

	/** 
	 * @return The interval that the user chose
	 */
	public Long getInterval() {
		return intervalChooserComposite.getInterval();
	}

}
