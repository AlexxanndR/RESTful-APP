package com.project.web.controllers;

import com.project.web.models.Parallelogram;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parallelogram")
public class ParallelogramController {
    private final Parallelogram parallelogram = new Parallelogram();

    @GetMapping("/calculate/{action}")
    public Parallelogram calculate(@PathVariable String action,
                              @RequestParam(value = "height", required = false, defaultValue = "0.0") double height,
                              @RequestParam(value = "length", required = false, defaultValue = "0.0") double length) {
        parallelogram.setHeight(height);
        parallelogram.setLength(length);
        if (action.equals("perimeter"))
            parallelogram.CalculPerimeter();
        else if (action.equals("area"))
            parallelogram.CalculArea();
        return parallelogram;
    }
}
