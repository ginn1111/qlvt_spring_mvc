$(function() {
    const formControl = window.localStorage.getItem('showDialogEdit');

    if (formControl) {
        showForm(formControl);
        window.localStorage.removeItem('showDialogEdit');
    }

    const now = new Date();
    const year = now.getFullYear();
    const month = ('0' + (now.getMonth() + 1)).slice(-2);
    const date = ('0' + now.getDate()).slice(-2);
    const hourStart = ('0' + now.getHours()).slice(-2);
    const hourEnd = ('0' + (now.getHours() + 6) % 24).slice(-2);
    const minute = ('0' + now.getMinutes()).slice(-2);

    let formSubmit;

    const formInputTimeStart = $('.form__input--time-start');
    const birthdayInputDate = $('#birthday');

    if(birthdayInputDate.val() === '') {
        birthdayInputDate.val(`${year-18}-${month}-${date}`);
    }

    if (formInputTimeStart.val() === '') {
        $('.form__input--time-start').val(`${hourStart}:${minute}`);
        $('.form__input--time-end').val(`${hourEnd}:${minute}`);
    }

    function showForm(suffix) {
        $('.modal')
            .find(`.form--${suffix}`)
            .css('display', 'flex');

        $('.modal').fadeIn();
        $('.backdrop').fadeIn();
    }

    function enableBtnRemove(suffix) {
        let check;
        $(`.table__item--${suffix} .table__item--delete`).click(function(event) {
            event.stopPropagation();
            const parent = $(this).parent();
            parent.toggleClass('hovered');
            check = parent.children('input[type="checkbox"]');
            // handle enable btnRemove
            if ($(`.table__item--${suffix}.hovered`).length > 0) {
                $(`.btn--remove-${suffix}`).attr('disabled', false);
            } else {
                $(`.btn--remove-${suffix}`).attr('disabled', true);
            }
            // handle checked for checkbox
            check.prop('checked', !check.is(':checked'))
        });
    }

    function handleShowDialogEdit() {
        window.localStorage.setItem('showDialogEdit', $(this).data('control'));
    }

    enableBtnRemove('employees');
    enableBtnRemove('workers');
    enableBtnRemove('suppliers');
    enableBtnRemove('warehouses');
    enableBtnRemove('constructions');
    enableBtnRemove('supplies');
    enableBtnRemove('categories');
    enableBtnRemove('sectors');
    enableBtnRemove('empaccounts');
    enableBtnRemove('inforaccounts');

    $('.btn--customize.btn--add:not(.btn--payed-coupon)').click(function() {
        handleShowDialogEdit.call(this);
    });

    $('.btn--confirm').click(function() { });

    $('.table__item--edit').click(function() {
        handleShowDialogEdit.call($(this).parents('.table__item'));
    });

    $('.table__item--accounts').click(function() {
        handleShowDialogEdit.call(this);
    });

    $('.table__item input[type="checkbox"]').click(function(e) {
        e.preventDefault();
    });
    $('.supplies--checked input[type="checkbox"]').click(function(e) {
        e.preventDefault();
    });

    $('.btn--remove').click(function() {
        $('.dialog').fadeIn();
        $('.backdrop').fadeIn();
        const classList = this.classList;
        const splitClassList = classList[classList.length - 1].split('-');
        formSubmit = $(`.form-${splitClassList[splitClassList.length - 1]}`)
    });

    $('.btn--warning').click(function() {
        if (formSubmit) {
            formSubmit.submit();
        }
    });

   $('.btn--payed-coupon').click(function() {
       showForm('pycoupons--tmp');
       $('.btn--confirm').click(() => {
           handleShowDialogEdit.call(this)
       })
   });

   $('.supplies__icon').click(function(event) {
       $(this).toggleClass('hovered');
       const supplyCheckbox = $(this).parents('.supplies--checked').find('input[type="checkbox"]');
       supplyCheckbox.prop('checked', !supplyCheckbox.is(':checked'))
   })
});
