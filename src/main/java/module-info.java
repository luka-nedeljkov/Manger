module me.manger {
    requires javafx.controls;
    requires javafx.fxml;
    requires datetime.picker.javafx;

    exports me.manger;
    exports me.manger.controller;
    exports me.manger.controller.owner;
    exports me.manger.controller.manager;
    opens me.manger to javafx.fxml;
    opens me.manger.controller to javafx.fxml;
    opens me.manger.controller.owner to javafx.fxml;
    opens me.manger.model.user.notifications to javafx.base;
    opens me.manger.controller.manager to javafx.fxml;
    opens me.manger.model.user.paymentHistory to javafx.base;
    opens me.manger.model.ledger to javafx.base;
    opens me.manger.model.building to javafx.base;
    exports me.manger.controller.manager.dialog.home;
    opens me.manger.controller.manager.dialog.home to javafx.fxml;
    exports me.manger.controller.manager.dialog.reclamation;
    opens me.manger.controller.manager.dialog.reclamation to javafx.fxml;
}