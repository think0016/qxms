function menu_active(ids) {
	var ids_arr = ids.split(",");
	for (var i = ids_arr.length - 1; i >= 0; i--) {
		var domid = "#menu-"+ids_arr[i];
		$(domid).addClass("active");
	}

}