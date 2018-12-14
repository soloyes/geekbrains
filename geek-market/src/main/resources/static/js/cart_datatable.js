// https://datatables.net/

$(document).ready(function () {
    var table = $('.table').DataTable({
        "ajax": {
            "url": "/geekmarket/api/cart_items",
            "dataSrc": "" //,
        },
        "columns": [
            {"data": "product.vendorCode"},
            {"data": "product.category.title"},
            {"data": "product.title"},
            {"data": "quantity"},
            {
                "data": null, //data is null since we want to access ALL data
                //for the sake of our calculation below
                "render": function (data, type, row) {
                    return (data.product.price * data.quantity)
                }
            },
            {
                "data": "product.id",
                "render": function (data, type, row) {
                    return (
                        '<a class="btn btn-outline-success" link="' + data + '">+</a>' +
                        '<a class="btn btn-outline-danger" link="' + data + '">-</a>' +
                        '<button type="button" class="btn btn-primary add-item-btn" link="' + data + '">Удалить</button>'
                    )
                }
            }
        ],
        "initComplete": function (settings, json) {
            $('.btn-outline-success').on('click', function (event) {
                var btnLink = $(this).attr('link');
                $.ajax({
                    url: "/geekmarket/api/cart_items",
                    type: "POST",
                    data: {
                        "id": btnLink
                    }
                }).done(function () {
                    location.reload();
                });
            });

            $('.btn-outline-danger').on('click', function (event) {
                var btnLink = $(this).attr('link');
                $.ajax({
                    url: "/geekmarket/api/cart_items/decrease",
                    type: "DELETE",
                    data: {
                        "id": btnLink
                    }
                }).done(function () {
                    location.reload();
                });
            });

            $('.btn-primary').on('click', function (event) {
                var btnLink = $(this).attr('link');
                $.ajax({
                    url: "/geekmarket/api/cart_items",
                    type: "DELETE",
                    data: {
                        "id": btnLink
                    }
                }).done(function () {
                    location.reload();
                });
            });
        }
    })
});