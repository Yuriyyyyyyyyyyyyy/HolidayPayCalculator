/* Обработчик события отправки формы с id “calculate-form”.
Этот обработчик отменяет стандартное поведение формы (отправка данных на сервер) и выполняет следующие действия:

1. Получает значение зарплаты из поля ввода с id “salary” и проверяет, что оно введено. Если значение не введено, отображается сообщение об ошибке.

2. Получает значения количества дней отпуска, даты начала и окончания отпуска из соответствующих полей ввода.

3. Проверяет, что если выбрана дата начала отпуска, то выбрана и дата окончания отпуска. Если дата окончания не выбрана, отображается сообщение об ошибке.

4. Формирует URL для запроса расчета отпускных с параметрами зарплаты и количества дней отпуска.
Если выбраны даты начала и окончания отпуска, вызывается функция getVacationDates для получения
списка дат отпуска и добавления параметра vacationDates к URL запроса.

5. Выполняет GET-запрос к серверу по сформированному URL и отображает результат расчета в элементе с id “result”.

Использует jQuery для управления элементами страницы и выполнения HTTP-запросов к серверу*/

$(document).ready(function() {
    $("#calculate-form").submit(function(event) {
        event.preventDefault();
        let salary = $("#salary").val();
        if (!salary) {
            $("#salary-error").text("Введите среднюю зарплату");
            return;
        } else {
            $("#salary-error").text("");
        }
        let days = $("#days").val();
        let startDate = $("#startDate").val();
        let endDate = $("#endDate").val();
        if (startDate && !endDate) {
            $("#endDate-error").text("Выберите дату окончания");
            return;
        } else {
            $("#endDate-error").text("");
        }
        let url = "/calculate?salary=" + salary + "&days=" + days;
        if (startDate && endDate) {
            let vacationDates = getVacationDates(startDate, endDate);
            url += "&vacationDates=" + vacationDates;
        }
        $.get(url, function(data) {
            $("#result").text(data.result);
        });
    });
});

/* Принимает даты начала и окончания отпуска и возвращает список всех дат между ними */
function getVacationDates(startDate, endDate) {
    let start = new Date(startDate);
    let end = new Date(endDate);
    let dates = [];
    for (let date = start; date <= end; date.setDate(date.getDate() + 1)) {
        dates.push(date.toISOString().slice(0, 10));
    }
    return dates.join(",");
}

/* Обновляет значение количества дней отпуска на основе выбранных дат начала и окончания отпуска.
 Если выбраны обе даты, то вычисляется количество дней между ними и устанавливается в поле ввода с id “days”.
В противном случае поле ввода становится доступным для редактирования.*/
function updateDays() {
    let startDate = $("#startDate").val();
    let endDate = $("#endDate").val();
    if (startDate && endDate) {
        let start = new Date(startDate);
        let end = new Date(endDate);
        let days = Math.round((end - start) / (1000 * 60 * 60 * 24)) + 1;
        $("#days").val(days);
        $("#days").prop("readonly", true);
    } else {
        $("#days").prop("readonly", false);
    }
}

/* Функция для обновления минимального значения даты
окончания отпуска на основе выбранной даты начала отпуска*/
function updateEndDateMin() {
    let startDate = $("#startDate").val();
    if (startDate) {
        let start = new Date(startDate);
        start.setDate(start.getDate() + 1);
        let minDate = start.toISOString().slice(0, 10);
        $("#endDate").attr("min", minDate);
    } else {
        $("#endDate").attr("min", "");
    }
}

/* Если дата начала отпуска не выбрана,
то поле ввода даты окончания отпуска
становится недоступным для выбора.*/
function updateEndDateDisabled() {
    let startDate = $("#startDate").val();
    if (startDate) {
        $("#endDate").prop("disabled", false);
    } else {
        $("#endDate").prop("disabled", true);
    }
}

