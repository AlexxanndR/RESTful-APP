package com.project.web.services;

import com.project.web.IO.InputParams;
import com.project.web.IO.OutputParams;
import com.project.web.enums.CharacteristicType;
import com.project.web.models.Parallelogram;
import com.project.web.logger.MyLogger;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParallelogramService {
    @Autowired
    private CacheService cacheService;

    private OutputParams outputParams = new OutputParams();

    public double calculatePerimeter(InputParams inputParams) {
        return 2 * (inputParams.getHeight() + inputParams.getLength());
    }

    public double calculateArea(InputParams inputParams) {
        return inputParams.getHeight() * inputParams.getLength();
    }

    public OutputParams calculate(InputParams inputParams, CharacteristicType chrType) {
        if (chrType == CharacteristicType.perimeter) {
            MyLogger.log(Level.INFO, "Calculating perimeter...");
            outputParams.setPerimeter(calculatePerimeter(inputParams));
            Parallelogram.setPerimeter(outputParams.getPerimeter());
        } else if (chrType == CharacteristicType.area){
            MyLogger.log(Level.INFO, "Calculating area...");
            outputParams.setArea(calculateArea(inputParams));
            Parallelogram.setArea(outputParams.getArea());
        }

        if (outputParams.getPerimeter() > 0 && outputParams.getArea() > 0) {
            MyLogger.log(Level.INFO, "Adding params to cache...");
            cacheService.add(inputParams, outputParams);
            Parallelogram.ResetCharacteristics();
            return outputParams;
        }

        return null;
    }


    public Object getCache() {
        return cacheService;
    }

}
