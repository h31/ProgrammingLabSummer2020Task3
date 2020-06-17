module fillword {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;

    opens ru.nikiens.fillword.controller to javafx.fxml;
    opens ru.nikiens.fillword.model to javafx.fxml;
    opens ru.nikiens.fillword to javafx.fxml;

    exports ru.nikiens.fillword.controller;
    exports ru.nikiens.fillword.model;
    exports ru.nikiens.fillword;
}