package org.swrlapi.ui.action;

import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.swrlapi.ui.dialog.SWRLAPIDialogManager;
import org.swrlapi.ui.model.FileBackedOWLOntologyModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SaveAction implements ActionListener
{
	private final Component parent;
	private final SWRLAPIDialogManager dialogManager;
	private final FileBackedOWLOntologyModel ontologyModel;

	public static final String TITLE = "Save";

	public SaveAction(Component parent, FileBackedOWLOntologyModel ontologyModel, SWRLAPIDialogManager dialogManager)
	{
		this.parent = parent;
		this.dialogManager = dialogManager;
		this.ontologyModel = ontologyModel;
	}

	@Override public void actionPerformed(ActionEvent e)
	{
		save();
	}

	public void save()
	{
		try {
			this.ontologyModel.save();
		} catch (OWLOntologyStorageException e) {
			this.dialogManager.showErrorMessageDialog(this.parent, e.getMessage(), "Error");
		}
	}
}
