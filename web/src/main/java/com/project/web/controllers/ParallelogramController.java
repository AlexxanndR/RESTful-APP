package com.project.web.controllers;

import com.project.web.IO.InputParams;
import com.project.web.IO.OutputParams;
import com.project.web.enums.CharacteristicType;
import com.project.web.logger.MyLogger;
import com.project.web.models.Parallelogram;
import com.project.web.services.ParallelogramService;
import com.project.web.services.RequestCounter;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;


@Controller
public class ParallelogramController {
    @Autowired
    private ParallelogramService parallelogramService;

    @GetMapping("/calculate")
    public String calculate(@RequestParam(value = "height", required = false, defaultValue = "0.0") Double height,
                            @RequestParam(value = "length", required = false, defaultValue = "0.0") Double length,
                            @RequestParam(value = "areaClick", required = false, defaultValue = "false") Boolean btnArea,
                            @RequestParam(value = "perimeterClick", required = false, defaultValue = "false") Boolean btnPerimeter,
                            Model model) {
        if (height < 0 || length < 0)
            throw new IllegalArgumentException("Wrong params provided!");

        var inputParams = new InputParams(height, length);

        if (btnPerimeter == Boolean.TRUE)
            parallelogramService.calculate(inputParams, CharacteristicType.perimeter);
        else if (btnArea == Boolean.TRUE)
            parallelogramService.calculate(inputParams, CharacteristicType.area);

        RequestCounter.increase();

        model.addAttribute("height", height);
        model.addAttribute("length", length);
        model.addAttribute("perimeter", Parallelogram.getPerimeter());
        model.addAttribute("area", Parallelogram.getArea());

        return "calculate";
    }

    @PostMapping("/calculate")
    public ResponseEntity<Object> calculateBulk(@Valid @RequestBody List<InputParams> inputList) {
        List<OutputParams> outputList = new LinkedList<>();

        inputList.forEach((inputParams) -> {
            try {
                outputList.add(parallelogramService.calculate(inputParams, CharacteristicType.perimeter_area));
                MyLogger.log(Level.INFO, "Output list - " + outputList);
            } catch (IllegalArgumentException e) {
                MyLogger.log(Level.ERROR, "POST error!");
            }
        });
        MyLogger.log(Level.ERROR, "POST success!");

        DecimalFormat dec = new DecimalFormat("#0.00");

        double heightAverage = parallelogramService.calculateAverage(inputList, CharacteristicType.height);
        double lengthAverage = parallelogramService.calculateAverage(inputList,  CharacteristicType.length);
        double perimeterAverage = parallelogramService.calculateAverage(outputList,  CharacteristicType.perimeter);
        double areaAverage = parallelogramService.calculateAverage(outputList,  CharacteristicType.area);

        double heightMin = parallelogramService.findMin(inputList, CharacteristicType.height);
        double lengthMin = parallelogramService.findMin(inputList, CharacteristicType.length);
        double perimeterMin = parallelogramService.findMin(outputList, CharacteristicType.perimeter);
        double areaMin = parallelogramService.findMin(outputList, CharacteristicType.area);

        double heightMax = parallelogramService.findMax(inputList, CharacteristicType.height);
        double lengthMax = parallelogramService.findMax(inputList, CharacteristicType.length);
        double perimeterMax = parallelogramService.findMax(outputList, CharacteristicType.perimeter);
        double areaMax = parallelogramService.findMax(outputList, CharacteristicType.area);

        return new ResponseEntity<>("Height average - " + dec.format(heightAverage)  + "; length average - " + dec.format(lengthAverage)
                                    + "; area average - " + dec.format(areaAverage) + "; perimeter average - " + dec.format(perimeterAverage) + ".\n"
                                    + "Height min - " + dec.format(heightMin)  + "; length min - " + dec.format(lengthMin)
                                    + "; area min - " + dec.format(areaMin) + "; perimeter min - " + dec.format(perimeterMin) + ".\n"
                                    + "Height max - " + dec.format(heightMax)  + "; length max - " + dec.format(lengthMax)
                                    + "; area max - " + dec.format(areaMax) + "; perimeter max - " + dec.format(perimeterMax) + ".\n", HttpStatus.OK);
    }

    @GetMapping("/cache")
    public ResponseEntity<Object> getCache() {
        return new ResponseEntity<>(parallelogramService.getCache(), HttpStatus.OK);
    }
}