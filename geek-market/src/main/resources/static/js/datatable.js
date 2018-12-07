$(document).ready( function () {
	 var table = $('.table').DataTable({
			"sAjaxSource": "/geekmarket/api/products",
			"sAjaxDataProp": "",
			"order": [[ 0, "asc" ]],
			"aoColumns": [
			      { "mData": "id" },
		          { "mData": "title" },
				  { "mData": "price" }
			]
	 })
});