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
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == view.getJbtn_Open()) {
	    	JFileChooser fileChooser = new JFileChooser();
	        int returnValue = fileChooser.showOpenDialog(null);
	        if (returnValue == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				String path = selectedFile.getAbsolutePath().replace("\\", "/");
				System.out.println(path);
				view.getJtxt_FilePath().setText(path);
				view.setCheckBoxList(DataSets.getProperties(path));
	        }
		}
		
		if (e.getSource() == view.getJbtn_Generate()) {
			view.getCheckedItems();
		}
	}
	
}
