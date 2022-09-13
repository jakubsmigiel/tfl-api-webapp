package com.jsmi.tflapp.arrivals;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ArrivalEntryRepository extends JpaRepository<ArrivalEntry, Long> {

    @Query(value="SELECT * FROM Arrival_Entry WHERE timestamp = (SELECT timestamp FROM Arrival_Entry ORDER BY timestamp DESC LIMIT 1)", nativeQuery = true)
    List<ArrivalEntry> findAllCurrent();

    @Query(value="SELECT * FROM Arrival_Entry WHERE line_id = :line_id ORDER By entry_id DESC LIMIT 500", nativeQuery = true)
    List<Optional<ArrivalEntry>> findMostRecent500ByLineId(@Param("line_id") String lineId);

    @Query(value="SELECT * FROM Arrival_Entry ORDER By entry_id DESC LIMIT 500", nativeQuery = true)
    List<ArrivalEntry> findMostRecent500();
}
