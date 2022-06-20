$(function () {
    function hideModal() {
        $('.backdrop').fadeOut(function() {
            $(this).find('.form').css('display', 'none');
        });
        $('.modal').fadeOut();
        $('.dialog').fadeOut();
    }
    $('.modal').click(function(e) {
        e.stopPropagation();
    });

    $('.btn--cancel').click(function(e) {
        hideModal();
        e.preventDefault();
        $('.form').submit(function(e) {
            e.preventDefault();
            $('.form').unbind('submit');
        });
    });

    $('.backdrop').click(hideModal);

    const now = new Date();
    $('.tickets__title').html(`Số lượng các phiếu được lập trong tháng ${('0' + (now.getMonth() + 1)).slice(-2)}`)
})