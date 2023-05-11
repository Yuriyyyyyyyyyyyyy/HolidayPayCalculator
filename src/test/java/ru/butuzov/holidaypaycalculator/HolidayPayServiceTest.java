package ru.butuzov.holidaypaycalculator;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.butuzov.holidaypaycalculator.service.HolidayPayService;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class HolidayPayServiceTest {

    /*Вызывают метод calculateHolidayPay с разными параметрами,
    чтобы проверить корректность расчета отпускных*/

    /* 1.Проверяет расчет отпускных с зарплатой 100000
    и 10 днями отпуска без указания дат отпуска */
    @Test
    public void testCalculateHolidayPay() {
        HolidayPayService holidayPayService = new HolidayPayService();
        double result = holidayPayService.calculateHolidayPay(100000, 10, null);
        assertEquals(34129.69, result);
    }

    /* 2.Проверяет расчет отпускных с зарплатой 150000
    и 10 днями отпуска без указания дат отпуска*/
    @Test
    public void testCalculateHolidayPayWithDifferentSalary() {
        HolidayPayService holidayPayService = new HolidayPayService();
        double result = holidayPayService.calculateHolidayPay(150000, 10, null);
        assertEquals(51194.54, result);
    }

    /*
    3.Проверяет расчет отпускных с зарплатой 100000
   и 15 днями отпуска без указания дат отпуска
   */
    @Test
    public void testCalculateHolidayPayWithDifferentDays() {
        HolidayPayService holidayPayService = new HolidayPayService();
        double result = holidayPayService.calculateHolidayPay(100000, 15, null);
        assertEquals(51194.54, result);
    }

    /* 4.Проверяет расчет отпускных с зарплатой 100000
    и 3 днями отпуска с указанием дат отпуска в январе,
    которые включают только праздничные дни*/
    @Test
    public void testCalculateHolidayPayWithVacationDates() {
        HolidayPayService holidayPayService = new HolidayPayService();
        double result = holidayPayService.calculateHolidayPay(100000, 3,
                "2023-01-01," +
                        "2023-01-02," +
                        "2023-01-03");
        assertEquals(0, result);
    }

    /*5.Проверяет расчет отпускных с зарплатой 100000
    и 7 днями отпуска с указанием дат отпуска в феврале*/
    @Test
    public void testCalculateHolidayPayWithSevenVacationDates() {
        HolidayPayService holidayPayService = new HolidayPayService();
        double result = holidayPayService.calculateHolidayPay(100000, 7,
                "2023-02-01," +
                        "2023-02-02," +
                        "2023-02-03," +
                        "2023-02-04," +
                        "2023-02-05," +
                        "2023-02-06," +
                        "2023-02-07");
        assertEquals(23890.78, result);
    }

    /*6. Проверяет расчет отпускных с зарплатой 100000
    и 30 днями отпуска с указанием дат отпуска в январе,
    которые включают праздничные дни*/
    @Test
    public void testCalculateHolidayPayWithThirtyVacationDates() {
        HolidayPayService holidayPayService = new HolidayPayService();
        double result = holidayPayService.calculateHolidayPay(100000, 30,
                "2023-01-01," +
                        "2023-01-02," +
                        "2023-01-03," +
                        "2023-01-04," +
                        "2023-01-05," +
                        "2023-01-06," +
                        "2023-01-07," +
                        "2023-01-08," +
                        "2023-01-09," +
                        "2023-01-10," +
                        "2023-01-11," +
                        "2023-01-12," +
                        "2023-01-13," +
                        "2023-01-14," +
                        "2023-01-15," +
                        "2023-01-16," +
                        "2023-01-17," +
                        "2023-01-18," +
                        "2023-01-19," +
                        "2023-01-20," +
                        "2023-01-21," +
                        "2023-01-22," +
                        "2023-01-23," +
                        "2023-01-24," +
                        "2023-01-25," +
                        "2023-01-26," +
                        "2023-01-27," +
                        "2023-01-28," +
                        "2023-01-29," +
                        "2023-01-30");
        assertEquals(75085.32, result);
    }
}
