package com.jsmi.tflapp.tflApiCaller;

import com.jsmi.tflapp.arrivals.ArrivalEntry;
import com.jsmi.tflapp.arrivals.ArrivalEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
@EnableScheduling
public class TflApiCallerConfiguration {

    // How often the API will be called in seconds
    private final int API_CALL_INTERVAL_SECONDS = 60;

    @Autowired
    private TflApiCallerService tflApiCaller;
    @Autowired
    private ArrivalEntryRepository repository;

    @Scheduled(fixedRate = API_CALL_INTERVAL_SECONDS * 1000)
    public void scheduleFixedRateTask() {
        LocalDateTime now = LocalDateTime.now();
        List<ArrivalEntry> result = tflApiCaller.call();
        for (ArrivalEntry arrivalEntry : result) {
            arrivalEntry.setTimestamp(now);

            // Adding one hour to adjust the timezone
            // Done this way due to the development time constraint. It should be done using a specialised
            // library to deal with the time zone adjustments robustly.
            arrivalEntry.setExpectedArrival(arrivalEntry.getExpectedArrival().plusHours(1));
        }
        repository.saveAll(result);
    }
}
