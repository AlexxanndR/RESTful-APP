package com.project.web.controllers;

import com.project.web.IO.InputParams;
import com.project.web.enums.CharacteristicType;
import com.project.web.models.Parallelogram;
import com.project.web.services.ParallelogramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ParallelogramController {
    @Autowired
    private ParallelogramService parallelogramService;

    @GetMapping("/calculate")
    public String calculate(
            @RequestParam(value = "height", required = false, defaultValue = "0.0") double height,
            @RequestParam(value = "length", required = false, defaultValue = "0.0") double length,
            @RequestParam(value = "areaClick", required = false, defaultValue = "false") Boolean btnArea,
            @RequestParam(value = "perimeterClick", required = false, defaultValue = "false") Boolean btnPerimeter,
            Model model) {
        if (height < 0 && length < 0)
            throw new IllegalArgumentException("Wrong params provided!");

        var in = new InputParams(height, length);

        if (btnPerimeter == Boolean.TRUE)
           parallelogramService.calculate(in, CharacteristicType.perimeter);
        else if (btnArea == Boolean.TRUE)
           parallelogramService.calculate(in, CharacteristicType.area);

        //Synchronization.semaphore.release();

        model.addAttribute("height", height);
        model.addAttribute("length", length);
        model.addAttribute("perimeter", Parallelogram.getPerimeter());
        model.addAttribute("area", Parallelogram.getArea());

        return "calculate";
    }

    @GetMapping("/cache")
    public ResponseEntity<Object> getCache() {
       return new ResponseEntity<>(parallelogramService.getCache(), HttpStatus.OK);
    }

//    @GetMapping("/calculate/{action}")
//    public ResponseEntity<Map<String, Object>> calculatorJSON(
//            @PathVariable String action,
//            @RequestParam(value = "height", required = false, defaultValue = "0.0") double height,
//            @RequestParam(value = "length", required = false, defaultValue = "0.0") double length) {
//        Map<String, Object> parallelogramParams = new HashMap<>();
//
//        if (height >= 0 && length >= 0) {
//            parallelogram.setHeight(height);
//            parallelogram.setLength(length);
//        }
//
//        if (action.equals("perimeter"))
//            parallelogram.calculatePerimeter();
//        else if (action.equals("area"))
//            parallelogram.calculateArea();
//
//        parallelogramParams.put("area", parallelogram.getArea());
//        parallelogramParams.put("perimeter", parallelogram.getPerimeter());
//        parallelogramParams.put("height", height);
//        parallelogramParams.put("length", length);
//
//        logger.info("GetMapping was Successful!");
//        return new ResponseEntity<>(parallelogramParams, HttpStatus.OK);
//    }
}
