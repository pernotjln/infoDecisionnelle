package com.pernotpeyetsainthillier.infodecisionnelle;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.*;

import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.PiePlot;
import de.erichseifert.gral.plots.PiePlot.PieSliceRenderer;
import de.erichseifert.gral.plots.colors.LinearGradient;
import de.erichseifert.gral.ui.InteractivePanel;
import de.erichseifert.gral.util.Insets2D;

public class FenetrePrincipale extends JFrame implements ActionListener, KeyListener {
	private JComboBox<String> combo_checking_status;
	private JComboBox<String> combo_credit_history;
	private JComboBox<String> combo_purpose;
	private JComboBox<String> combo_savings_status;
	private JComboBox<String> combo_other_payment_plans;
	private JComboBox<String> combo_job;
	private JPanel resultPanel;
	private JTextField text_credit_amount;
	private JButton button_submit;

	public FenetrePrincipale() {
		// Définition des propriétés principales de la fenêtre
		this.setTitle("Décideur");
		this.setSize(500, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
		/*
		 * On crée les différents combobox		
		 */
		// Select checking_status
		JLabel label_checking_status = new JLabel("Cheking Status : ", SwingConstants.RIGHT);
		combo_checking_status = new JComboBox<String>();
		combo_checking_status.addItem("no checking");
		combo_checking_status.addItem("<0");
		combo_checking_status.addItem("0<=X<200");
		combo_checking_status.addItem(">=200");
		
		// Select credit_history
		JLabel label_credit_history = new JLabel("Credit History : ", SwingConstants.RIGHT);
		combo_credit_history = new JComboBox<String>(); 
		combo_credit_history.addItem("no credits/all paid");
		combo_credit_history.addItem("all paid");
		combo_credit_history.addItem("existing paid");
		combo_credit_history.addItem("delayed previously");
		combo_credit_history.addItem("critical/other existing credit");
		
		// Select purpose
		JLabel label_purpose = new JLabel("Purpose : ", SwingConstants.RIGHT);
		combo_purpose = new JComboBox<String>();
		combo_purpose.addItem("new car");
		combo_purpose.addItem("used car");
		combo_purpose.addItem("furniture/equipment");
		combo_purpose.addItem("radio/tv");
		combo_purpose.addItem("domestic appliance");
		combo_purpose.addItem("repairs");
		combo_purpose.addItem("education");
		combo_purpose.addItem("vacation");
		combo_purpose.addItem("retraining");
		combo_purpose.addItem("business");
		combo_purpose.addItem("other");
		
		// Select credit_amount
		JLabel label_credit_amount = new JLabel("Credit Amount : ", SwingConstants.RIGHT);
		text_credit_amount = new JTextField();
		text_credit_amount.addKeyListener(this);
		
		// Select savings_status
		JLabel label_savings_status = new JLabel("Savings Status : ", SwingConstants.RIGHT);
		combo_savings_status = new JComboBox<String>();
		combo_savings_status.addItem("<100");
		combo_savings_status.addItem("100<=X<500");
		combo_savings_status.addItem("500<=X<1000");
		combo_savings_status.addItem(">=1000");
		combo_savings_status.addItem("no known savings");
		
		// Select other_payment_plans
		JLabel label_other_payment_plans = new JLabel("Other Payment Plans : ", SwingConstants.RIGHT);
		combo_other_payment_plans = new JComboBox<String>();
		combo_other_payment_plans.addItem("bank");
		combo_other_payment_plans.addItem("stores");
		combo_other_payment_plans.addItem("none");
		
		// Select job
		JLabel label_job = new JLabel("Job : ", SwingConstants.RIGHT);
		combo_job = new JComboBox<String>();
		combo_job.addItem("unemp/unskilled non res");
		combo_job.addItem("unskilled resident");
		combo_job.addItem("skilled");
		combo_job.addItem("high qualif/self emp/mgmt");
		
		// On regroupe tous les combos dans un panel
		JPanel liste_combos = new JPanel();
		liste_combos.setLayout(new GridLayout(7,2));
		
		liste_combos.add(label_checking_status);
		liste_combos.add(combo_checking_status);
		
		liste_combos.add(label_purpose);
		liste_combos.add(combo_purpose);
		
		liste_combos.add(label_credit_history);
		liste_combos.add(combo_credit_history);
		
		liste_combos.add(label_credit_amount);
		liste_combos.add(text_credit_amount);
		
		liste_combos.add(label_savings_status);
		liste_combos.add(combo_savings_status);
		
		liste_combos.add(label_other_payment_plans);
		liste_combos.add(combo_other_payment_plans);
		
		liste_combos.add(label_job);
		liste_combos.add(combo_job);
		
		// On crée le bouton de soumission
		button_submit = new JButton("Vérifier ce client");
		button_submit.addActionListener(this);
		button_submit.setEnabled(false);
		
		JPanel panel_submit = new JPanel();
		panel_submit.setLayout(new BoxLayout(panel_submit, BoxLayout.LINE_AXIS));
		panel_submit.add(button_submit);
		
		// On crée la zone de résultat
		this.resultPanel = new JPanel();
		this.resultPanel.setLayout(new BorderLayout());

		// On assemble le formulaire et le résultat
		JPanel top = new JPanel();
		top.setLayout(new BoxLayout(top, BoxLayout.PAGE_AXIS));
		top.add(liste_combos);
		top.add(panel_submit);
		top.add(this.resultPanel);
		
		this.setContentPane(top);
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String checking_status = this.combo_checking_status.getSelectedItem().toString();
		int credit_amount = Integer.parseInt(this.text_credit_amount.getText());
		String credit_history = this.combo_credit_history.getSelectedItem().toString();
		String job = this.combo_job.getSelectedItem().toString();
		String other_payment_plans = this.combo_other_payment_plans.getSelectedItem().toString();
		String purpose = this.combo_purpose.getSelectedItem().toString();
		String savings_status = this.combo_savings_status.getSelectedItem().toString();
		
		Person personne = new Person(checking_status, credit_history, purpose, 
				credit_amount, savings_status, other_payment_plans, job);
		
		HashMap<String, Double> results = Validateur.getInstance().evaluer(personne);
		
		// Create data
		DataTable data = new DataTable(Double.class);
		data.add(results.get("good"));
		data.add(results.get("bad"));

		// Create new pie plot
		PiePlot plot = new PiePlot(data);

		// Format plot
		plot.getTitle().setText("Résultat de l'évaluation : ");
		// Change relative size of pie
		plot.setRadius(0.9);
		// Add some margin to the plot area
		plot.setInsets(new Insets2D.Double(20.0, 40.0, 40.0, 40.0));

		PieSliceRenderer pointRenderer =
				(PieSliceRenderer) plot.getPointRenderer(data);
		// Change relative size of inner region
		pointRenderer.setInnerRadius(0.4);
		// Change the width of gaps between segments
		pointRenderer.setGap(0.2);
		// Change the colors
		LinearGradient colors = new LinearGradient(Color.green, Color.red);
		pointRenderer.setColor(colors);
		// Show labels
		pointRenderer.setValueVisible(true);
		pointRenderer.setValueColor(Color.WHITE);
		pointRenderer.setValueFont(Font.decode(null).deriveFont(Font.BOLD));
		
		this.resultPanel.removeAll();
		this.resultPanel.add(new InteractivePanel(plot), BorderLayout.CENTER);
		
		this.resultPanel.revalidate();
		this.resultPanel.repaint();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if (this.text_credit_amount.getText().length() == 0) {
			this.button_submit.setEnabled(false);
		} else {
			this.button_submit.setEnabled(true);
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {}
}
