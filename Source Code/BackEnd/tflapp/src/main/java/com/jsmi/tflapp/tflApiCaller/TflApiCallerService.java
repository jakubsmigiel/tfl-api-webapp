package com.jsmi.tflapp.tflApiCaller;

import com.jsmi.tflapp.arrivals.ArrivalEntry;
import com.jsmi.tflapp.arrivals.ArrivalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TflApiCallerService {
    private final ArrivalEntryService arrivalEntryService;
    private final WebClient webClient;

    @Autowired
    public TflApiCallerService(WebClient.Builder webClientBuilder, ArrivalEntryService arrivalEntryService) {
        this.webClient = webClientBuilder.baseUrl("https://api.tfl.gov.uk/StopPoint/490000254W/arrivals").build();
        this.arrivalEntryService = arrivalEntryService;
    }

    public List<ArrivalEntry> call() {
        Mono<ArrivalEntry[]> response = webClient.get()
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(ArrivalEntry[].class);

        ArrivalEntry[] readers = response.block();

        return Arrays.stream(readers).collect(Collectors.toList());
    }
}
