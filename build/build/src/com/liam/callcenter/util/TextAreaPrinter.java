package com.liam.callcenter.util;

import javafx.scene.control.TextArea;

public class TextAreaPrinter {
	
	public static void printText(TextArea textArea, String msg) {
		if (null != textArea) {
			textArea.appendText(msg);
		}
	}
}
