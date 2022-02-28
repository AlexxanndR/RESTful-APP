package com.project.web.controllers;

import com.project.web.models.Parallelogram;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ParallelogramController {
    private final Parallelogram parallelogram = new Parallelogram();

    @GetMapping("/calculate")
    public String calculatorHTML(Model model, @RequestParam(value = "height", required = false, defaultValue = "0.0") double height,
                       @RequestParam(value = "length", required = false, defaultValue = "0.0") double length,
                       @RequestParam(value = "areaClick", required = false, defaultValue = "false") Boolean btnArea,
                       @RequestParam(value = "perimeterClick", required = false, defaultValue = "false") Boolean btnPerimeter) {
        parallelogram.setHeight(height);
        parallelogram.setLength(length);

        if (btnPerimeter == Boolean.TRUE)
            parallelogram.CalculPerimeter();
        else if (btnArea == Boolean.TRUE)
            parallelogram.CalculArea();

        double area = parallelogram.getArea(), perimeter = parallelogram.getPerimeter();

        model.addAttribute("height", height);
        model.addAttribute("length", length);
        model.addAttribute("area", area);
        model.addAttribute("perimeter", perimeter);
        return  "calculate";
    }

    @GetMapping("/calculate/{action}")
    @ResponseBody
    public Map<String, Object> calculatorJSON(@PathVariable String action,
                                   @RequestParam(value = "height", required = false, defaultValue = "0.0") double height,
                                   @RequestParam(value = "length", required = false, defaultValue = "0.0") double length) {
        Map<String, Object> prllgramParams = new HashMap<>();

        parallelogram.setHeight(height);
        parallelogram.setLength(length);

        if (action.equals("perimeter"))
            parallelogram.CalculPerimeter();
        else if (action.equals("area"))
            parallelogram.CalculArea();

        prllgramParams.put("area", parallelogram.getArea());
        prllgramParams.put("perimeter", parallelogram.getPerimeter());
        prllgramParams.put("height", height);
        prllgramParams.put("length", length);

        return prllgramParams;
    }
}
