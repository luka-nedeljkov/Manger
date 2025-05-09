package me.manger.model.user.paymentHistory;

import me.manger.model.user.Property;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HistoryEntry {

	public Date date;
	public double amount;
	public String message;
	public Property property;

	public HistoryEntry(double amount, String message, Property source) {
		date = new Date();
		this.amount = amount;
		this.message = message;
		property = source;
	}

	public HistoryEntry(long date, double amount, String message, Property source) {
		this.date = new Date(date);
		this.amount = amount;
		this.message = message;
		property = source;
	}

	public double getAmount() {
		return amount;
	}

	public String getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
		return sdf.format(date);
	}

	public String getMessage() {
		return message;
	}

	public Property getProperty() {
		return property;
	}

}
