module HelloWorld {
	requires javafx.controls;
	
	opens Ejercicios to javafx.graphics, javafx.fxml;
	opens model to java.base;
}
