package com.jsmi.tflapp.arrivals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/api/arrivals")
public class ArrivalEntryController {
    private final ArrivalEntryService arrivalEntryService;

    @Autowired
    public ArrivalEntryController(ArrivalEntryService arrivalEntryService) {
        this.arrivalEntryService = arrivalEntryService;
    }

    @GetMapping(path="all")
    @CrossOrigin(origins = "*")
    public List<ArrivalEntry> getAllArrivalEntries() {
        // This endpoint responds with the most recent 500
        // entries, so that the response size does not get out of hand.
        return arrivalEntryService.getAllArrivalEntries();
    }

    @GetMapping(path="line/{lineId}")
    @CrossOrigin(origins = "*")
    public List<Optional<ArrivalEntry>> getAllArrivalEntriesOfLine(@PathVariable("lineId") String lineId) {
        // This endpoint responds with 500 most recent ArrivalEntries of one specific line
        return arrivalEntryService.findAllByLineId(lineId);
    }

    @GetMapping(path = "current")
    @CrossOrigin(origins = "*")
    public List<ArrivalEntry> getAllCurrentArrivalEntries() {
        // This endpoint responds with the entries that came from the most recent API call
        return arrivalEntryService.getCurrentArrivalEntries();
    }
}
