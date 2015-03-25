package com.chartadvisor.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import com.chartadvisor.view.*;
import com.chartadvisor.model.*;

public class Controller implements ActionListener {
	
	private MainView view;
	
	public Controller(MainView view) {
		this.view = view;
		this.addListeners();
	}
	
	public void addListeners(){
		view.getJbtn_Open().addActionListener(this);
		view.getJbtn_Generate().addActionListener(this);
		view.getJbtn_Cancel().addActionListener(this);
		view.getJmenu_About().addActionListener(this);
		view.getJmenu_Dictionary().addActionListener(this);
		view.getJmenu_Exit().addActionListener(this);
		view.getJmenu_Generate().addActionListener(this);
		view.getJmenu_Open().addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == view.getJbtn_Open() || e.getSource() == view.getJmenu_Open()) {
	    	JFileChooser fileChooser = new JFileChooser();
	        int returnValue = fileChooser.showOpenDialog(null);
	        if (returnValue == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				String path = selectedFile.getAbsolutePath().replace("\\", "/");
				System.out.println(path);
				view.getJtxt_FilePath().setText(path);
				//view.setCheckBoxList(DataSets.getProperties(path));
	        }
		}
		if (e.getSource() == view.getJbtn_Generate() || e.getSource() == view.getJmenu_Generate()) {
			view.getCheckedItems();
		}
		if (e.getSource() == view.getJbtn_Cancel() || e.getSource() == view.getJmenu_Exit()) {
			view.dispose();
			System.exit(0);
		}
		if (e.getSource() == view.getJmenu_About()) {
			AboutView aboutview = new AboutView();
			aboutview.pack();
			aboutview.setVisible(true);
		}
		if (e.getSource() == view.getJmenu_Dictionary()) {
			DictionaryView dictionaryView = new DictionaryView();
			dictionaryView.pack();
			dictionaryView.setVisible(true);
		}
	}
}
