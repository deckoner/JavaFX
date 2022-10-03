module Calculadora {
	requires javafx.controls;
	requires javafx.fxml;
	requires org.kordamp.ikonli.javafx;
	requires com.jfoenix;
	
	opens application to javafx.graphics, javafx.fxml;
	opens controller to javafx.graphics, javafx.fxml;
	opens model to javafx.graphics, javafx.fxml;
}
