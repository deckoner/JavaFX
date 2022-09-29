module HelloWorld {
	requires javafx.controls;
	requires javafx.web;
	requires javafx.fxml;
	requires javafx.swing;
	requires javafx.media;
	requires javafx.graphics;
	requires javafx.base;
	
	opens Ejercicios to javafx.graphics, javafx.base;
	opens model to javafx.graphics, javafx.base;
}
