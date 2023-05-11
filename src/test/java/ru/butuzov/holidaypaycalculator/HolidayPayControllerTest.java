package ru.butuzov.holidaypaycalculator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class HolidayPayControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /* Тесты контроллера HolidayPayController.
    Они используют MockMvc для имитации HTTP-запросов
     к контроллеру и проверки корректности его ответов*/

    /* 1. Проверяет расчет отпускных с зарплатой 100000
     и 10 днями отпуска без указания дат отпуска*/
    @Test
    public void testCalculateVacationWithoutDates() throws Exception {
        mockMvc.perform(get("/calculate")
                        .param("salary", "100000")
                        .param("days", "10"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"result\":34129.69}"));
    }

    /*2. Проверяет расчет отпускных с зарплатой 100000
     и 0 днями отпуска с указанием дат отпуска*/
    @Test
    public void testCalculateVacationWithDates() throws Exception {
        mockMvc.perform(get("/calculate")
                        .param("salary", "100000")
                        .param("days", "0")
                        .param("vacationDates",
                                "2023-02-01," +
                                        "2023-02-02," +
                                        "2023-02-03," +
                                        "2023-02-04," +
                                        "2023-02-05," +
                                        "2023-02-06," +
                                        "2023-02-07," +
                                        "2023-02-08," +
                                        "2023-02-09," +
                                        "2023-02-10," +
                                        "2023-02-11," +
                                        "2023-02-12," +
                                        "2023-02-13," +
                                        "2023-02-14," +
                                        "2023-02-15"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"result\":51194.54}"));
    }

    /*3. Проверяет расчет отпускных с зарплатой 300000
     и 0 днями отпуска с указанием дат отпуска в мае,
     которые включают праздничные дни*/
    @Test
    public void testCalculateVacationWithHolidays() throws Exception {
        mockMvc.perform(get("/calculate")
                        .param("salary", "300000")
                        .param("days", "0")
                        .param("vacationDates",
                                "2023-05-01," +
                                        "2023-05-02," +
                                        "2023-05-03," +
                                        "2023-05-04," +
                                        "2023-05-05," +
                                        "2023-05-06," +
                                        "2023-05-07," +
                                        "2023-05-08," +
                                        "2023-05-09," +
                                        "2023-05-10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(81911.26));
    }
}
