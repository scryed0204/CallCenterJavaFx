package com.liam.callcenter.util;

import javafx.scene.control.TextArea;

public class TextAreaPrinter {

	private static TextAreaPrinter textAreaPrinter;
	private TextArea textArea = null;

	private TextAreaPrinter(TextArea textArea) {
		this.textArea = textArea;
	}

	public static void init(TextArea textArea) {
		textAreaPrinter = new TextAreaPrinter(textArea);
	}

	public static TextAreaPrinter getInstance() {
		if (null == textAreaPrinter) {
			textAreaPrinter = new TextAreaPrinter(new TextArea());
		}

		return textAreaPrinter;
	}

	public void printText(String msg) {
		if (null != textArea) {
			if (!textArea.getText().isEmpty()) {
				msg = System.getProperty("line.separator") + msg;
			}
			textArea.appendText(msg);
		}
	}
}
