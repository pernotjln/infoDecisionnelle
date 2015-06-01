/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pernotpeyetsainthillier.infodecisionnelle;

import java.io.File;

import weka.classifiers.bayes.NaiveBayesUpdateable;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

/**
 * 
 * @author peyet pernot sainthillier
 */
public class Decideur {
	public static void main(String args[]) throws Throwable {

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
	}
}
