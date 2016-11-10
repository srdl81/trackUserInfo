package se.ams.track.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.ams.track.model.JobAdvertisement;
import se.ams.track.service.TrackService;

import java.util.List;


@Api(value = "Track User", description = "Track User Activites")
@RestController
public class TrackUserController {

    @Autowired
    private TrackService trackService;

    @ApiOperation(value = "asd", nickname = "dasd", produces = "application/json")
    @RequestMapping(value = "/track/{id}", method = RequestMethod.GET, produces = "text/html")
    public String getHelloMessage(@PathVariable("id") String id) {
        trackService.restoreDefaultBooks();
        List<JobAdvertisement> all = trackService.findAll();
        return String.format("Hello id: '%s'", id);
    }


}
