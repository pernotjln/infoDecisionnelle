package com.pernotpeyetsainthillier.infodecisionnelle;

import java.io.File;
import java.util.HashMap;
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
	private Instances structure;
	private NaiveBayesUpdateable nb;
	
	/*
	 * Constructeur privé du singleton
	 */
	private Validateur() {
		try {
			// load data
			ArffLoader loader = new ArffLoader();
			loader.setFile(new File("./dataSimple.arff"));
			structure = loader.getStructure();
			structure.setClassIndex(structure.numAttributes() - 1);
	
			// train NaiveBayes
			nb = new NaiveBayesUpdateable();
			nb.buildClassifier(structure);
			Instance current;
			while ((current = loader.getNextInstance(structure)) != null) {
				nb.updateClassifier(current);
			}
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

		// object
		Instance person = new Instance(8);
		person.setValue(structure.attribute(0), personne.getChecking_status());
		person.setValue(structure.attribute(1), personne.getCredit_history());
		person.setValue(structure.attribute(2), personne.getPurpose());
		person.setValue(structure.attribute(3), personne.getCredit_amount());
		person.setValue(structure.attribute(4), personne.getSavings_status());
		person.setValue(structure.attribute(5), personne.getOther_payment_plans());
		person.setValue(structure.attribute(6), personne.getJob());

		person.setDataset(structure);
		double[] returned_values = null;
		try {
			returned_values = nb.distributionForInstance(person);
			nb.updateClassifier(person);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		results.put("good", returned_values[0] * 100);
		results.put("bad", returned_values[1] * 100);
		
		return results;
	}
}
