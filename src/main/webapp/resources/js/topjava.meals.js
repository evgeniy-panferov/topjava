$(function () {
    makeEditable({
            ajaxUrl: "user/meals/",
            datatableApi: $("#datatable").DataTable({
                "paging": false,
                "info": true,
                "columns": [
                    {
                        "data": "dateTime"
                    },
                    {
                        "data": "description"
                    },
                    {
                        "data": "calories"
                    },
                    {
                        "defaultContent": "Edit",
                        "orderable": false
                    },
                    {
                        "defaultContent": "Delete",
                        "orderable": false
                    }
                ]
            })
        }
    );
});


function filter() {
    var form = $('#filterForm');
    $.get("user/meals/filter", form.serialize());
}


function clearFilter() {
    $.ajax({
        url: "user/meals/filter",
        date: {
            startDate: $('#startDate').val(0),
            endDate: $('#endDate').val(0),
            startTime: $('#startTime').val(0),
            endTime: $('#endTime').val(0)
        }
    });
}
