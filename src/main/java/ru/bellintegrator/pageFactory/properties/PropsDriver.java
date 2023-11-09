package ru.bellintegrator.pageFactory.properties;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "system:env",
        "file:driver.properties"
})
public interface PropsDriver extends Config {

    @Key("default.timeout")
    int defaultTimeout();

    @Key("address.chrome.driver")
    String addressChromeDriver();
}
