import presentation.*;
import presentation.controller.MainController;

import java.time.LocalDateTime;

/**
 * The place where everything is put together and the application can be run from
 */
public class App
{
    public static void main( String[] args ) {

        ViewMain viewMain = new ViewMain();
        ViewClient viewClient = new ViewClient();
        ViewProduct viewProduct = new ViewProduct();
        ViewOrder viewOrder = new ViewOrder();
        ViewBill viewBill = new ViewBill();
        MainController mainController = new MainController(viewMain, viewClient, viewProduct, viewOrder, viewBill);
    }
}
