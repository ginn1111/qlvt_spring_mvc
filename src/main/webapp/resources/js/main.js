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
})