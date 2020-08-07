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
                ],
                "order": [
                    [
                        0,
                        "desc"
                    ]]
            })
        }
    );
});

function filter() {
    $.get(context.ajaxUrl +"filter", $('#filterForm').serialize(), function (data) {
        context.datatableApi.clear().rows.add(data).draw();
    });
}


function clearFilter() {
    $('#filterForm')[0].reset();
    $.get(context.ajaxUrl +"filter", updateTable())
}
