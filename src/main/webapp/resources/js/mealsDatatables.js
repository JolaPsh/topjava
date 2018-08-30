var ajaxUrl = 'ajax/profile/meals/';
var datatableApi;

function filterTable() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + "filter",
        /*dataType*/
        data: $("#filter").serialize(),
        success: function () {
            datatableApi.clear().rows.add(data).draw();
        }
    });
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
