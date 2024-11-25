package com.apptware.interview.stream.impl;

import com.apptware.interview.stream.DataReader;
import com.apptware.interview.stream.PaginationService;
import jakarta.annotation.Nonnull;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;

@Slf4j
@Service
class DataReaderImpl implements DataReader {

  @Autowired private PaginationService paginationService;

  @Override
  public Stream<String> fetchLimitadData(int limit) {
    return fetchPaginatedDataAsStream().limit(limit);
  }

  @Override
  public Stream<String> fetchFullData() {
    return fetchPaginatedDataAsStream();
  }

  /**
   * This method is where the candidate should add the implementation. Logs have been added to track
   * the data fetching behavior. Do not modify any other areas of the code.
   */
  private @Nonnull Stream<String> fetchPaginatedDataAsStream() {
    log.info("Fetching paginated data as stream.");

    // Placeholder for paginated data fetching logic
    // The candidate will add the actual implementation here

    Integer pageSize = 50;
    int totalPages = (int) Math.ceil((double) PaginationService.FULL_DATA_SIZE / pageSize);
    
    List<String> allData = new ArrayList<>();
    
    for (int page = 1; page <= totalPages; page++) {
        log.info("Fetching data for page {}", page);
    
        // Fetch data for the current page
        List<String> pageData = paginationService.getPaginatedData(page, pageSize);
    
        log.info("Page {} contains {} items", page, pageData.size());
        allData.addAll(pageData); // Add page data to the main list
    }
    
    // Convert all items into a stream and log each fetched item
    return allData.stream()
                  .peek(item -> log.info("Fetched Item: {}", item));
  }
}
