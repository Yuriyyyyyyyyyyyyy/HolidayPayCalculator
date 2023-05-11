/*
Метод рассчитывает отпускные следующим образом:

1. Вычисляется цена одного дня работы как зарплата, деленная на 29.3.

2. Если даты отпуска не указаны, то отпускные вычисляются как
цена одного дня, умноженная на количество дней отпуска (days).

3. Если даты отпуска указаны, то выполняются следующие действия:
- Создается список праздничных дней.
-Даты отпуска разбиваются по запятым и преобразуются в массив.
Для каждой даты отпуска проверяется, является ли она праздничным днем.
Если нет, то увеличивается счетчик рабочих дней отпуска.
Если да, то увеличивается счетчик праздничных дней.

4. Отпускные вычисляются как цена одного дня,
умноженная на количество рабочих дней отпуска.
*/

package ru.butuzov.holidaypaycalculator.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class HolidayPayService {
    public double calculateHolidayPay(int salary, int days, String vacationDates) {
        double dayPrice = salary / 29.3;
        double vacation = 0;
        if (vacationDates == null) {
            vacation = dayPrice * days;
        } else {
            int vacationDays = 0;
            int holidayDays = 0;
            String[] dates = vacationDates.split(",");
            LocalDate[] localDates = new LocalDate[dates.length];
            for (int i = 0; i < dates.length; i++) {
                localDates[i] = LocalDate.parse(dates[i]);
            }
            List<LocalDate> holidays = new ArrayList<>();// Список праздников
// Новогодние каникулы 2023
            holidays.add(LocalDate.of(2023, 1, 1));
            holidays.add(LocalDate.of(2023, 1, 2));
            holidays.add(LocalDate.of(2023, 1, 3));
            holidays.add(LocalDate.of(2023, 1, 4));
            holidays.add(LocalDate.of(2023, 1, 5));
            holidays.add(LocalDate.of(2023, 1, 6));
            holidays.add(LocalDate.of(2023, 1, 7));
            holidays.add(LocalDate.of(2023, 1, 8));
// День защитника Отечества
            holidays.add(LocalDate.of(2023, 2, 23));
// Международный женский день
            holidays.add(LocalDate.of(2023, 3, 8));
// День Труда
            holidays.add(LocalDate.of(2023, 5, 1));
// День Победы
            holidays.add(LocalDate.of(2023, 5, 9));
// День России
            holidays.add(LocalDate.of(2023, 6, 12));
// День народного единства
            holidays.add(LocalDate.of(2023, 11, 4));
            for (LocalDate date : localDates) {
                if (!holidays.contains(date)) {
                    vacationDays++;
                } else {
                    holidayDays++;
                }
            }
            vacation = dayPrice * vacationDays;
        }
        return Math.round(vacation * 100.0) / 100.0;
    }
}