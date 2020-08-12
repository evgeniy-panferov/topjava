var mealAjaxUrl = "profile/meals/";

function updateFilteredTable() {
    $.ajax({
        type: "GET",
        url: mealAjaxUrl + "filter",
        data: $("#filter").serialize()
    }).done(updateTableByData);
}

function clearFilter() {
    $("#filter")[0].reset();
    $.get(mealAjaxUrl, updateTableByData);
}

$(function () {
    makeEditable({
        ajaxUrl: mealAjaxUrl,
        datatableApi: $("#datatable").DataTable({
            "ajax": {
                "url": mealAjaxUrl,
                "dataSrc": ""
            },
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "dateTime",
                    "render": function (date, type, row) {
                        const d = new Date(date)
                        const dateTimeFormat = new Intl.DateTimeFormat('en', {
                            year: 'numeric',
                            month: '2-digit',
                            day: '2-digit',
                            hour: '2-digit',
                            minute: '2-digit'
                        })

                        const [{value: year}, , {value: month}, , {value: day}, , {value: hour}, , {value: minute}] = dateTimeFormat.formatToParts(d)
                        return `${year}-${month}-${day} ${hour}:${minute}`
                    }
                },
                {
                    "data": "description",
                },
                {
                    "data": "calories",

                },
                {
                    "orderable": false,
                    "defaultContent": "",
                    "render": renderEditBtn
                },
                {
                    "orderable": false,
                    "defaultContent": "",
                    "render": renderDeleteBtn
                }
            ],

            "order": [
                [
                    0,
                    "desc"
                ]
            ],
            "createdRow": function (row, data, dataIndex) {
                $(row).addClass('tr').attr("data-mealExcess", data['excess'])
            }
        }),
        updateTable: function () {
            $.get(mealAjaxUrl, updateTableByData);
        }
    });
});