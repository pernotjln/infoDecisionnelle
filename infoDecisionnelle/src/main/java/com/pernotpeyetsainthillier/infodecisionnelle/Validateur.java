package com.pernotpeyetsainthillier.infodecisionnelle;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import weka.classifiers.bayes.NaiveBayesUpdateable;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

/**
 * 
 * @author peyet pernot sainthillier
 */
public class Validateur {
	private static Validateur instance = null;
	
	/*
	 * Constructeur privé du singleton
	 */
	private Validateur() {
		try {
			// load data
			ArffLoader loader = new ArffLoader();
			loader.setFile(new File("./dataSimple.arff"));
			Instances structure = loader.getStructure();
			structure.setClassIndex(structure.numAttributes() - 1);
	
			// train NaiveBayes
			NaiveBayesUpdateable nb = new NaiveBayesUpdateable();
			nb.buildClassifier(structure);
			Instance current;
			while ((current = loader.getNextInstance(structure)) != null) {
				nb.updateClassifier(current);
			}
	
			// object
			Instance person = new Instance(8);
			person.setValue(structure.attribute(0), ">=200");
			person.setValue(structure.attribute(1), "all paid");
			person.setValue(structure.attribute(2), "radio/tv");
			person.setValue(structure.attribute(3), 1169.0);
			person.setValue(structure.attribute(4), "<100");
			person.setValue(structure.attribute(5), "bank");
			person.setValue(structure.attribute(6), "skilled");
	
			person.setDataset(structure);
			nb.updateClassifier(person);
	
			// result
			System.out.println(nb);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/*
	 * Récupération de l'instance unique
	 */
	public static Validateur getInstance() {
		if (Validateur.instance == null) {
			Validateur.instance = new Validateur();
		}
		
		return Validateur.instance;
	}
	
	/*
	 * Permet d'évaluer une personne (à implémenter !)
	 */
	public HashMap<String, Double> evaluer(Person personne) {
		HashMap<String, Double> results = new HashMap<String, Double>();
		
		Random rd = new Random();
		Double n = new Double(rd.nextInt(100)+1);
		
		results.put("bon", n);
		results.put("mauvais", 100 - n);
		
		return results;
	}
}
