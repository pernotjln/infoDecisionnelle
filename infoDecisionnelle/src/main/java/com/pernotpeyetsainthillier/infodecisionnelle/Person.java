package com.pernotpeyetsainthillier.infodecisionnelle;

import weka.core.Instance;

public class Person extends Instance {

	String checking_status;
	String credit_history;
	String purpose;
	String credit_amount;
	String savings_status;
	String other_payment_plans;
	String job;

	public Person(String checking_status, String credit_history, String purpose,
			String credit_amount, String savings_status, String other_payment_plans, String job) {
		this.checking_status = checking_status;
		this.credit_history = credit_history;
		this.purpose = purpose;
		this.credit_amount = credit_amount;
		this.savings_status = savings_status;
		this.other_payment_plans = other_payment_plans;
		this.job = job;
	}

	public String getChecking_status() {
		return this.checking_status;
	}
	public void setChecking_status(String checking_status) {
		this.checking_status = checking_status;
	}
	public String getCredit_history() {
		return this.credit_history;
	}
	public void setCredit_history(String credit_history) {
		this.credit_history = credit_history;
	}
	public String getPurpose() {
		return this.purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getCredit_amount() {
		return this.credit_amount;
	}
	public void setCredit_amount(String credit_amount) {
		this.credit_amount = credit_amount;
	}
	public String getSavings_status() {
		return this.savings_status;
	}
	public void setSavings_status(String savings_status) {
		this.savings_status = savings_status;
	}
	public String getOther_payment_plans() {
		return this.other_payment_plans;
	}
	public void setOther_payment_plans(String other_payment_plans) {
		this.other_payment_plans = other_payment_plans;
	}
	public String getJob() {
		return this.job;
	}
	public void setJob(String job) {
		this.job = job;
	}

	@Override
	public String toString() {
		return "Person [checking_status=" + this.checking_status
				+ ", credit_history=" + this.credit_history + ", purpose=" + this.purpose
				+ ", credit_amount=" + this.credit_amount + ", savings_status="
				+ this.savings_status + ", other_payment_plans="
				+ this.other_payment_plans + ", job=" + this.job + "]";
	}

}
