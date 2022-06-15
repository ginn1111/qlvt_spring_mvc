$(function () {
    $('.toggle').click(function () {
        $('.navigation').toggleClass('active');
        $('.main').toggleClass('active');
    });
    $('.user__refesh').click(function() {
		location.reload();
	});
});
