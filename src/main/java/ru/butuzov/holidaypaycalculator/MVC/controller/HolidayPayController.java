package ru.butuzov.holidaypaycalculator.MVC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.butuzov.holidaypaycalculator.service.HolidayPayService;

import java.util.HashMap;
import java.util.Map;

@Controller
public class HolidayPayController {

    @Autowired
    private HolidayPayService holidayPayService;

    /* Возвращает домашнюю страницу */
    @GetMapping("/")
    public String home (Model model)  {
        return "holidayPayCalculator";
    }

/* Возвращает результат расчета отпускных в виде JSON-объекта*/
    @GetMapping("/calculate")
    @ResponseBody
    public Map<String, Double> calculateVacation(@RequestParam int salary,
                                                 @RequestParam int days,
                                                 @RequestParam(required = false) String vacationDates) {
        double result = holidayPayService.calculateHolidayPay(salary, days, vacationDates);
        Map<String, Double> response = new HashMap<>();
        response.put("result", result);
        return response;
    }

}