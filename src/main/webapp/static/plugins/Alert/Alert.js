window.Alert = function(messages, callback) {
	if ($(".ec_tip").length < 1) {
		$("body").append("<div class=\"ec_tip\">" + messages + "</div>");
	}
	// 定位居中显示
	leftW = (document.body.clientWidth - $(".ec_tip").width()) / 2;
	topH = (document.body.clientHeight - $(".ec_tip").height()) / 2;
	$(".ec_tip").css("top", topH + "px").css("left", leftW + "px").fadeIn(2000);
	// 谈出效果并执行回调
	$(".ec_tip").animate({
		top : "0px",
		opacity : 0
	}, 2000, function() {
		$(".ec_tip").remove();
		if (callback != undefined)
			callback();
	});
}