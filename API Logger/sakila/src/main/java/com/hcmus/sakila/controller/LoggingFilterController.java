package com.hcmus.sakila.controller;

import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/logs")
public class LoggingFilterController {
    private static final String LOG_FILE = "./logs/api-logs.log";

    @GetMapping("")
    public List<String> getLogs(@PathVariable String statusCategory) throws IOException {
        int min, max;
        switch (statusCategory) {
            case "information": min = 100; max = 199; break;
            case "success": min = 200; max = 299; break;
            case "redirection": min = 300; max = 399; break;
            case "client-error": min = 400; max = 499; break;
            case "server-error": min = 500; max = 599; break;
            default: throw new IllegalArgumentException("Invalid status category");
        }

        return getRequestResponsePairs(min, max);
    }

    private List<String> getRequestResponsePairs(int minStatus, int maxStatus) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(LOG_FILE));
        List<String> result = new ArrayList<>();

        String lastRequest = null;

        for (String line : lines) {
            if (line.contains("Incoming Request")) {
                lastRequest = line; // Store the most recent request log
            }

            if (line.contains("Outgoing Response")) {
                int status = extractStatusCode(line);
                if (status >= minStatus && status <= maxStatus) {
                    if (lastRequest != null) {
                        result.add(lastRequest);
                    }
                    result.add(line); // Add the response log
                }
            }
        }
        return result;
    }

    private int extractStatusCode(String logLine) {
        try {
            return Integer.parseInt(logLine.replaceAll(".*Status: (\\d+).*", "$1"));
        } catch (Exception e) {
            return -1; // Return -1 if parsing fails
        }
    }
}
