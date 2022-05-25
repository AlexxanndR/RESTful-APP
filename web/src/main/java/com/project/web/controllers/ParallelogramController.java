package com.project.web.controllers;

import com.project.web.IO.InputParams;
import com.project.web.IO.OutputParams;
import com.project.web.enums.CharacteristicType;
import com.project.web.enums.ParamsType;
import com.project.web.logger.MyLogger;
import com.project.web.models.ParallelogramView;
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
import java.util.Collections;
import java.util.HashMap;
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
        model.addAttribute("perimeter", ParallelogramView.getPerimeter());
        model.addAttribute("area", ParallelogramView.getArea());

        return "calculate";
    }

    @PostMapping("/calculate")
    public ResponseEntity<Object> calculateBulk(@Valid @RequestBody List<InputParams> inputList) {
        List<OutputParams> outputList = new LinkedList<>();

        inputList.forEach((inputParams) -> {
            OutputParams outputParams = new OutputParams();
            try {
                outputParams = parallelogramService.calculate(inputParams, CharacteristicType.area);
                outputParams = parallelogramService.calculate(inputParams, CharacteristicType.perimeter);
                outputList.add(outputParams);
                MyLogger.log(Level.INFO, "Value " + inputParams + "@" + outputParams + " added to output list!");
            } catch (IllegalArgumentException e) {
                MyLogger.log(Level.ERROR, "POST error!");
            }
        });
        MyLogger.log(Level.ERROR, "POST success!");

        double heightAverage = parallelogramService.calculateAverage(inputList, ParamsType.input, CharacteristicType.height);
        double lengthAverage = parallelogramService.calculateAverage(inputList, ParamsType.input, CharacteristicType.length);
        double perimeterAverage = parallelogramService.calculateAverage(outputList, ParamsType.output, CharacteristicType.perimeter);
        double areaAverage = parallelogramService.calculateAverage(outputList, ParamsType.output, CharacteristicType.area);

        double heightMin = parallelogramService.findMin(inputList, ParamsType.input, CharacteristicType.height);
        double lengthMin = parallelogramService.findMin(inputList, ParamsType.input, CharacteristicType.length);
        double perimeterMin = parallelogramService.findMin(outputList, ParamsType.output, CharacteristicType.perimeter);
        double areaMin = parallelogramService.findMin(outputList, ParamsType.output, CharacteristicType.area);

        double heightMax = parallelogramService.findMax(inputList, ParamsType.input, CharacteristicType.height);
        double lengthMax = parallelogramService.findMax(inputList, ParamsType.input, CharacteristicType.length);
        double perimeterMax = parallelogramService.findMax(outputList, ParamsType.output, CharacteristicType.perimeter);
        double areaMax = parallelogramService.findMax(outputList, ParamsType.output, CharacteristicType.area);

        return new ResponseEntity<>("Height average - " + heightAverage  + "; length average - " + lengthAverage
                                    + "; area average - " + areaAverage + "; perimeter average - " + perimeterAverage + ".\n"
                                    + "Height min - " + heightMin  + "; length min - " + lengthMin
                                    + "; area min - " + areaMin + "; perimeter min - " + perimeterMin + ".\n"
                                    + "Height max - " + heightMax  + "; length max - " + lengthMax
                                    + "; area max - " + areaMax + "; perimeter max - " + perimeterMax + ".\n", HttpStatus.OK);
    }

    @GetMapping("/cache")
    public ResponseEntity<Object> getCache() {
        return new ResponseEntity<>(parallelogramService.getCache(), HttpStatus.OK);
    }
}