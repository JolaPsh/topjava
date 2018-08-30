var ajaxUrl = "ajax/profile/meals/";
var datatableApi;

function filterTable() {
    $.ajax({
        type: "GET",
        url: ajaxUrl + "filter",
        /*dataType*/
        data: $("#filter").serialize()
    }).done(updateTableByData);
}

function resetFilter() {
    $("#filter")[0].reset();
    $.get(ajaxUrl, updateTableByData);
}

$(function () {
    datatableApi = $('#datatable').DataTable({
        "paging": false,
        "info": true,
        "columns": [{
            "data": "dateTime"
        }, {
            "data": "description"
        }, {
            "data": "calories"
        }, {
            "defaultContent": "Edit",
            "orderable": false
        },
            {
                "defaultContent": "Delete",
                "orderable": false
            }
        ],
        "order": [
            [0, "desc"]
        ]
    });
    makeEditable();
});
