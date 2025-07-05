package com.AverageCalculator.AverageCalculator.controller;
import com.AverageCalculator.AverageCalculator.model.NumbersRequest;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AverageController {

    @PostMapping("/average")
    public Map<String, Double> calculateAverage(@RequestBody NumbersRequest request) {
        List<Double> numbers = request.getNumbers();
        double sum = 0.0;
        for (double num : numbers) {
            sum += num;
        }
        double average = numbers.isEmpty() ? 0.0 : sum / numbers.size();
        Map<String, Double> response = new HashMap<>();
        response.put("average", average);
        return response;
    }
}
