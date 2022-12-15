package org.example.utils;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public interface ConfigProvider {
    Config config = readConfig();

    static Config readConfig() {
        return ConfigFactory.systemProperties().hasPath("testProfile")
                ? ConfigFactory.load(ConfigFactory.systemProperties().getString("testProfile"))
                : ConfigFactory.load("data.conf");
    }

    String URL_BASE = readConfig().getString("url_base");

    String EMAIL_VALID = readConfig().getString("usersParams.valid.email");
    String PASSWORD_VALID = readConfig().getString("usersParams.valid.password");
    String CARD_VALID = readConfig().getString("usersParams.valid.card");
    String CARDDATE_VALID = readConfig().getString("usersParams.valid.cardDate");
    String CVV_VALID = readConfig().getString("usersParams.valid.cvv");
    Integer ZIP_VALID = readConfig().getInt("usersParams.valid.zip");


    String EMAIL_NOT_VALID = readConfig().getString("usersParams.notValid.email");
    String PASSWORD_NOT_VALID = readConfig().getString("usersParams.notValid.password");
    String CARD_NOT_VALID = readConfig().getString("usersParams.notValid.card");
    String CARDDATE_NOT_VALID = readConfig().getString("usersParams.notValid.cardDate");
    String CVV_NOT_VALID = readConfig().getString("usersParams.notValid.cvv");
    Integer ZIP_NOT_VALID = readConfig().getInt("usersParams.notValid.zip");

}
