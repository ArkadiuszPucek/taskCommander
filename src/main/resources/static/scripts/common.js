// Aktualizacja czasu co sekundę
function updateTime() {
    var currentTime = new Date();
    var hours = currentTime.getHours();
    var minutes = currentTime.getMinutes();
    var formattedTime = (hours < 10 ? "0" : "") + hours + ":" + (minutes < 10 ? "0" : "") + minutes;
    document.getElementById("current-time").innerText = formattedTime;
}

setInterval(updateTime, 1000);

// Pokazywanie i ukrywanie dropdownu po kliknięciu przycisku
document.addEventListener("DOMContentLoaded", function () {
    var button = document.getElementById("user-dropdown-button");
    var dropdownList = document.getElementById("user-dropdown-list");

    button.addEventListener("click", function () {
        dropdownList.classList.toggle("show");
    });

    document.addEventListener("click", function (event) {
        if (!button.contains(event.target)) {
            dropdownList.classList.remove("show");
        }
    });
});

// Generowanie kalendarza na podstawie aktualnej daty
function generateCalendar() {
    const currentDate = new Date();
    const currentMonth = currentDate.getMonth();
    const currentYear = currentDate.getFullYear();
    const currentDay = currentDate.getDate();
    const daysInMonth = new Date(currentYear, currentMonth + 1, 0).getDate();
    const firstDayIndex = new Date(currentYear, currentMonth, 1).getDay();

    const calendarBody = document.getElementById("calendar-body");
    const currentMonthName = document.getElementById("current-month");

    if (calendarBody && currentMonthName) {
        currentMonthName.textContent = new Date(currentYear, currentMonth).toLocaleString('default', { month: 'long' });

        let days = "";
        let dayIndex = 0;

        for (let i = 0; i < 6; i++) { // 6 wierszy dla każdego możliwego układu kalendarza
            days += "<tr>";

            for (let j = 0; j < 7; j++) { // 7 dni tygodnia
                if (i === 0 && j < (firstDayIndex === 0 ? 6 : firstDayIndex - 1)) { // Zaczynamy od poniedziałku
                    days += `<td></td>`;
                } else if (dayIndex < daysInMonth) {
                    dayIndex++;
                    const isCurrentDay = dayIndex === currentDay ? " current-day" : "";
                    const isWeekend = (j === 5 || j === 6) ? " weekend" : "";
                    days += `<td class="calendar-day${isCurrentDay}${isWeekend}">${dayIndex}</td>`;
                } else {
                    days += `<td></td>`;
                }
            }

            days += "</tr>";
        }

        calendarBody.innerHTML = days;
    } else {
        console.error("Nie można odnaleźć elementu 'calendar-body' lub 'current-month'.");
    }
}

// Wywołanie funkcji generującej kalendarz po załadowaniu strony
document.addEventListener("DOMContentLoaded", function () {
    generateCalendar();
});
