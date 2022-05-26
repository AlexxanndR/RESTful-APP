package com.project.web.services;

import com.project.web.IO.InputParams;
import com.project.web.IO.OutputParams;
import com.project.web.enums.CharacteristicType;
import com.project.web.models.Parallelogram;
import com.project.web.logger.MyLogger;
import org.apache.logging.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParallelogramService {
    @Autowired
    private CacheService cacheService;

    private double area      = 0.0;
    private double perimeter = 0.0;

    public double calculatePerimeter(InputParams inputParams) {
        return 2 * (inputParams.getHeight() + inputParams.getLength());
    }

    public double calculateArea(InputParams inputParams) {
        return inputParams.getHeight() * inputParams.getLength();
    }

    public OutputParams calculate(InputParams inputParams, CharacteristicType chrType) {
        if (chrType == CharacteristicType.perimeter || chrType == CharacteristicType.perimeter_area) {
            MyLogger.log(Level.INFO, "Calculating perimeter...");
            perimeter = calculatePerimeter(inputParams);
            Parallelogram.setPerimeter(perimeter);
        }
        if (chrType == CharacteristicType.area || chrType == CharacteristicType.perimeter_area) {
            MyLogger.log(Level.INFO, "Calculating area...");
            area = calculateArea(inputParams);
            Parallelogram.setArea(area);
        }

        if (perimeter > 0 && area > 0) {
            MyLogger.log(Level.INFO, "Adding params to cache...");
            OutputParams outputParams = new OutputParams(perimeter, area);
            cacheService.add(inputParams, outputParams);
            perimeter = area = 0;
            return outputParams;
        }

        return null;
    }

   public <T> double calculateAverage(List<T> paramsList, CharacteristicType chrType) {
        double average = 0;
        if (!paramsList.isEmpty()) {
            MyLogger.log(Level.INFO, "Calculating average...");
            if (chrType == CharacteristicType.length) {
                average = paramsList.stream()
                      .map(element -> ((InputParams)element).getLength())
                      .mapToDouble(Double::doubleValue)
                      .average()
                      .getAsDouble();
            } else if (chrType == CharacteristicType.height) {
                average = paramsList.stream()
                        .map(element -> ((InputParams)element).getHeight())
                        .mapToDouble(Double::doubleValue)
                        .average()
                        .getAsDouble();
            } else if (chrType == CharacteristicType.perimeter) {
                average = paramsList.stream()
                        .map(element -> ((OutputParams)element).getPerimeter())
                        .mapToDouble(Double::doubleValue)
                        .average()
                        .getAsDouble();
            } else if (chrType == CharacteristicType.area) {
                average = paramsList.stream()
                        .map(element -> ((OutputParams)element).getArea())
                        .mapToDouble(Double::doubleValue)
                        .average()
                        .getAsDouble();
            }
        }
        MyLogger.log(Level.INFO, "Average - " + average);
        return average;
    }

    public <T> double findMin(List<T> paramsList, CharacteristicType chrType) {
        double min = 0;
        if (!paramsList.isEmpty()) {
            MyLogger.log(Level.INFO, "Calculating min...");
            if (chrType == CharacteristicType.length) {
                min  =   paramsList.stream()
                        .map(element -> ((InputParams)element).getLength())
                        .mapToDouble(Double::doubleValue)
                        .min()
                        .getAsDouble();
            } else if (chrType == CharacteristicType.height) {
                min  =   paramsList.stream()
                        .map(element -> ((InputParams) element).getHeight())
                        .mapToDouble(Double::doubleValue)
                        .min()
                        .getAsDouble();
            } else if (chrType == CharacteristicType.perimeter) {
                min  =   paramsList.stream()
                        .map(element -> ((OutputParams)element).getPerimeter())
                        .mapToDouble(Double::doubleValue)
                        .min()
                        .getAsDouble();
            } else if (chrType == CharacteristicType.area) {
                min  =   paramsList.stream()
                        .map(element -> ((OutputParams)element).getArea())
                        .mapToDouble(Double::doubleValue)
                        .min()
                        .getAsDouble();
            }
        }
        MyLogger.log(Level.INFO, "Min - " + min);
        return min;
    }

    public <T> double findMax(List<T> paramsList, CharacteristicType chrType) {
        double max = 0;
        if (!paramsList.isEmpty()) {
            MyLogger.log(Level.INFO, "Calculating max...");
            if (!paramsList.isEmpty()) {
                if (chrType == CharacteristicType.length) {
                    max  =   paramsList.stream()
                            .map(element -> ((InputParams)element).getLength())
                            .mapToDouble(Double::doubleValue)
                            .max()
                            .getAsDouble();
                } else if (chrType == CharacteristicType.height) {
                    max  =   paramsList.stream()
                            .map(element -> ((InputParams) element).getHeight())
                            .mapToDouble(Double::doubleValue)
                            .max()
                            .getAsDouble();
                } else if (chrType == CharacteristicType.perimeter) {
                    max  =   paramsList.stream()
                            .map(element -> ((OutputParams)element).getPerimeter())
                            .mapToDouble(Double::doubleValue)
                            .max()
                            .getAsDouble();
                } else if (chrType == CharacteristicType.area) {
                    max  =   paramsList.stream()
                            .map(element -> ((OutputParams)element).getArea())
                            .mapToDouble(Double::doubleValue)
                            .max()
                            .getAsDouble();
                }
            }
        }
        MyLogger.log(Level.INFO, "Max - " + max);
        return max;
    }

    public String getCache() {
        return cacheService.toString();
    }

}
