package com.chartadvisor.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JFileChooser;

import com.chartadvisor.view.*;
import com.chartadvisor.model.*;
import com.hp.hpl.jena.util.FileManager;

public class Controller implements ActionListener {
	
	private MainView view;
	private OutputManager outputManager; 
	List<String[]> propertiesList; 
	List<String[]> selectedPropertiesList;
	
	public Controller(MainView view) {
		this.view = view;
		this.outputManager = new OutputManager();
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
				this.propertiesList = DataSets.get_properties(DataSets.create_model(path), 0);
				view.getJtxt_FilePath().setText(path);
				view.setCheckBoxList(this.getArrayFromList(propertiesList));
	        }
		}
		if (e.getSource() == view.getJbtn_Generate() || e.getSource() == view.getJmenu_Generate()) {
			getSelectedPropList();
			JFileChooser fileChooser = new JFileChooser();
	        int returnValue = fileChooser.showSaveDialog(null);
	        if (returnValue == JFileChooser.APPROVE_OPTION) {
				outputManager.generateRDFOutput(this.findCharts(), new File(fileChooser.getSelectedFile().toString() + ".rdf"));
	        }
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
	
	public String[] getArrayFromList(List<String[]> list) {
		ArrayList<String> arrayStr = new ArrayList<String>();
		for (String[] temp : list) {
			arrayStr.add(temp[2] + "  (" + temp[4]+")");
			//System.out.println(temp[2] + "  -  " + temp[4]);
		}
		return arrayStr.toArray(new String[arrayStr.size()]); 
	}
	
	//Get the arrayList of properties selected by user with the type, complete name, local name, complete datatype and short name of datatype for Literals
	public List<String[]> getSelectedPropList() {
		selectedPropertiesList = propertiesList;
		ArrayList<String> checkedItems = view.getCheckedItems();
		List<String[]>	elemetsToDelete = new ArrayList<String[]>();
		for (String[] temp : propertiesList) {
			for (String tempStr : checkedItems) {
				if (temp[2].equalsIgnoreCase(tempStr.split("\\s+")[0])) {
					elemetsToDelete.add(temp);
				}
			}
		}
		for (String[] temp : elemetsToDelete) {
			System.out.println("Pruning list of properties...");
			selectedPropertiesList.remove(temp);
		}
		return selectedPropertiesList; 
	}
	
	//temporal method that emulates the actual findChart!
	private List<String[]> findCharts(){
		List<String[]> list = new ArrayList<String[]>();
		list.add(new String[]{"ColumnChart","75"});
		list.add(new String[]{"BarChart","50"});
		list.add(new String[]{"PointChart","50"});
		list.add(new String[]{"GeoChart","25"});
		return list;
	}
}
