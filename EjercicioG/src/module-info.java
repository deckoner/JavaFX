module EjercicioG {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	
	opens application to javafx.graphics, javafx.fxml;
	opens model to javafx.graphics, javafx.fxml;
	opens controller to javafx.graphics, javafx.fxml;
}