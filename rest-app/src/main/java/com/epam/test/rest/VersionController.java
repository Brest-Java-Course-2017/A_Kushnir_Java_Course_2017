package com.epam.test.rest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kushnir on 24.2.17.
 */
@RestController
public class VersionController {

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String VERSION = "1.0";

    //curl -X GET -v localhost:8088/v
    @RequestMapping(value = "/v", method = RequestMethod.GET)
    public String getVersion() {
        LOGGER.debug("/version rest");
        return VERSION;
    }

}
