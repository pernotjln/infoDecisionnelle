package com.pernotpeyetsainthillier.infodecisionnelle;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class FenetrePrincipale extends JFrame implements ActionListener {
	private JComboBox<String> combo_checking_status;
	private JComboBox<String> combo_credit_history;
	private JComboBox<String> combo_purpose;
	private JComboBox<String> combo_credit_amount;
	private JComboBox<String> combo_savings_status;
	private JComboBox<String> combo_other_payment_plans;
	private JComboBox<String> combo_job;

	public FenetrePrincipale() {
		// Définition des propriétés principales de la fenêtre
		this.setTitle("Décideur");
		this.setSize(500, 300);
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
		combo_credit_amount = new JComboBox<String>(); 
		combo_credit_amount.addItem("mean");
		combo_credit_amount.addItem("std. dev.");
		combo_credit_amount.addItem("weight sum");
		combo_credit_amount.addItem("precision");
		
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
		liste_combos.add(combo_credit_amount);
		
		liste_combos.add(label_savings_status);
		liste_combos.add(combo_savings_status);
		
		liste_combos.add(label_other_payment_plans);
		liste_combos.add(combo_other_payment_plans);
		
		liste_combos.add(label_job);
		liste_combos.add(combo_job);
		
		// On crée le bouton de soumission
		JButton button_submit = new JButton("Vérifier ce client");
		button_submit.addActionListener(this);
		
		JPanel panel_submit = new JPanel();
		panel_submit.setLayout(new BoxLayout(panel_submit, BoxLayout.LINE_AXIS));
		panel_submit.add(button_submit);
		
		// On assemble le formulaire
		JPanel top = new JPanel();
		top.setLayout(new BoxLayout(top, BoxLayout.PAGE_AXIS));
		top.add(liste_combos);
		top.add(panel_submit);
		
		this.setContentPane(top);
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String checking_status = this.combo_checking_status.getSelectedItem().toString();
		String credit_amount = this.combo_credit_amount.getSelectedItem().toString();
		String credit_history = this.combo_credit_history.getSelectedItem().toString();
		String job = this.combo_job.getSelectedItem().toString();
		String other_payment_plans = this.combo_other_payment_plans.getSelectedItem().toString();
		String purpose = this.combo_purpose.getSelectedItem().toString();
		String savings_status = this.combo_savings_status.getSelectedItem().toString();
		
		JOptionPane jop1 = new JOptionPane();
		String contenu = "checking status : " + checking_status + "\n" +
				"credit_amount : " + credit_amount + "\n" +
				"credit_history : " + credit_history + "\n" +
				"job : " + job + "\n" +
				"other_payment_plans : " + other_payment_plans + "\n" +
				"purpose : " + purpose + "\n" +
				"savings_status : " + savings_status + "\n";
		jop1.showMessageDialog(null, contenu, "Résultat", JOptionPane.INFORMATION_MESSAGE);
	}
}
