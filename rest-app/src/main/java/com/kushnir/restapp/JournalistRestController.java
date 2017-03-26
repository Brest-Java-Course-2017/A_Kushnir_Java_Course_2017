package com.kushnir.restapp;

import com.kushnir.model.Journalist;
import com.kushnir.service.JournalistService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Journalist Rest Controller
 */
@CrossOrigin
@RestController
public class JournalistRestController {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    JournalistService journalistService;

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler({IllegalArgumentException.class})
    public String incorrectDataError() {
        return "{  \"response\" : \"Incorrect Data Error\" }";
    }

    //curl -X GET -v localhost:8088/journalists?birthDateStart=yyyy.MM.dd&birthDateEnd=yyyy.MM.dd
    @RequestMapping(value = "/journalists", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public List<Journalist> getAllJournalist (
            @RequestParam(value = "birthDateStart", required = false)
                @DateTimeFormat(pattern = "yyyy.MM.dd") LocalDate birthDateStart,
            @RequestParam(value = "birthDateEnd", required = false)
                @DateTimeFormat(pattern = "yyyy.MM.dd")LocalDate birthDateEnd)
            throws IllegalArgumentException {
        LOGGER.debug("getAllJournalist(birthDateStart: {}, birthDateEnd: {})", birthDateStart , birthDateEnd);
        return journalistService.getAllJournalists(birthDateStart, birthDateEnd);
    }

    //curl -X GET -v localhost:8088/journalists/journalist/1
    @RequestMapping(value = "/journalists/journalist/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.FOUND)
    @ResponseBody
    public Journalist getJournalistById (
            @PathVariable(value = "id") Integer id)
            throws IllegalArgumentException {
        LOGGER.debug("getJournalistById(id: {})", id);
        return journalistService.getJournalistById(id);
    }

    //curl -X GET -v localhost:8088/journalists/journalist?name=...
    @RequestMapping(value = "/journalists/journalist", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.FOUND)
    @ResponseBody
    public Journalist getJournalistByName (
            @RequestParam(value = "name") String name)
            throws IllegalArgumentException {
        LOGGER.debug("getJournalistByName(name: {})", name);
        return journalistService.getJournalistByName(name);
    }

    /*
    * -H "Content-Type: application/json" -X POST -d '{"name":"...","birthDate":[yyyy,MM,dd],"rating":0}'
    * -v localhost:8088/journalists/journalist
    */
    @RequestMapping(value = "/journalists/journalist", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    @ResponseBody
    public Integer addJournalist (
            @RequestBody Journalist journalist)
            throws IllegalArgumentException {
        LOGGER.debug("addJournalist(journalist: {})", journalist);
        return journalistService.addJournalist(journalist);
    }

    /*
    * -H "Content-Type: application/json" -X PUT -d '{"id":1, "name":"...", "birthDate":[yyyy,MM,dd], "rating":10}'
    * -v localhost:8088/journalists/journalist
    */
    @RequestMapping(value = "/journalists/journalist", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @ResponseBody
    public Integer updateJournalist (
            @RequestBody Journalist journalist)
            throws IllegalArgumentException {
        LOGGER.debug("updateJournalist(journalist: {})", journalist);
        return journalistService.updateJournalist(journalist);
    }

    //curl -X DELETE -v localhost:8088/journalists/journalist/1
    @RequestMapping(value = "/journalists/journalist/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public Integer deleteJournalistById (
            @PathVariable(value = "id") Integer id)
            throws IllegalArgumentException {
        LOGGER.debug("deleteJournalistById(id: {})", id);
        return journalistService.deleteJournalistById(id);
    }

}
