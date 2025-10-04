package testdata;

import com.google.gson.JsonObject;
import utils.JsonUtil;

public class testdatamanager {

    private static final String USER_JSON = "test-data/userData.json";
    private static final String PRODUCT_JSON = "test-data/productData.json";

    public static JsonObject getUserData() {
        return JsonUtil.getJsonObject(USER_JSON);
    }

    public static JsonObject getProductData() {
        return JsonUtil.getJsonObject(PRODUCT_JSON);
    }
}
