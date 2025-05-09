package me.manger.model.user.paymentHistory;

import me.manger.model.user.Property;

import java.util.ArrayList;

public class HistoryHolder {

	public ArrayList<HistoryEntry> entries;

	public HistoryHolder(ArrayList<HistoryEntry> entries) {
		this.entries = entries;
	}

	public HistoryHolder() {
		entries = new ArrayList<>();
	}

	public void addEntry(double amount, String message, Property property) {
		entries.add(new HistoryEntry(amount, message, property));
	}

	@Override
	public String toString() {
		return "HistoryHolder{" +
				"entries=" + entries +
				'}';
	}

}
