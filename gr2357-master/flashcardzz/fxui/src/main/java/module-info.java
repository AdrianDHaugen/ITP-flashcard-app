module fxui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;

    requires core;

    opens fxui to javafx.graphics, javafx.fxml;
}
