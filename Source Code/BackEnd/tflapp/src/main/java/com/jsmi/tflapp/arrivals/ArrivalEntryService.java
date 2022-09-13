package com.jsmi.tflapp.arrivals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArrivalEntryService {
    private final ArrivalEntryRepository arrivalEntryRepository;

    @Autowired
    public ArrivalEntryService(ArrivalEntryRepository arrivalEntryRepository) {
        this.arrivalEntryRepository = arrivalEntryRepository;
    }

    public List<ArrivalEntry> getAllArrivalEntries() {
        // Returns 500 most recent arrival entries from the database
        return this.arrivalEntryRepository.findMostRecent500();
    }

    public List<ArrivalEntry> getCurrentArrivalEntries() {
        // Returns only the arrival entries that were in the most recent TFL API response
        return this.arrivalEntryRepository.findAllCurrent();
    }

    public List<Optional<ArrivalEntry>> findAllByLineId(String lineId) {
        // Returns 500 most recent the arrival entries of one line
        return this.arrivalEntryRepository.findMostRecent500ByLineId(lineId);
    }
}
