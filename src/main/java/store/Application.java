package store;

import store.common.config.StoreConfig;

public class Application {
    public static void main(String[] args) {
        StoreConfig storeConfig = new StoreConfig();
        MainController mainController = storeConfig.mainController();
        mainController.run();
    }
}
