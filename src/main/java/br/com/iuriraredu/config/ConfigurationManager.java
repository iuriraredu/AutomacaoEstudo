package br.com.iuriraredu.config;

import org.aeonbits.owner.ConfigCache;

/**
 *  A classe ConfigurationManager serve para iniciar as configurações
 *  da interface Configuration
 */
public class ConfigurationManager{

    private ConfigurationManager() {
    }

    public static Configuration configuration() {
        return ConfigCache.getOrCreate(Configuration.class);
    }

}
