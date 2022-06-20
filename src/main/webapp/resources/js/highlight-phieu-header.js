$(function() {
    const couponHeaders = document.querySelectorAll("#coupons > .manage__header > .header__title");
    const windowHref = window.location.pathname;
    const splitHref = windowHref.split('/');
    const suffix = splitHref[splitHref.length - 1].split('.')[0];

    if(couponHeaders) {
        let hoveredItem;

        for(let i of couponHeaders) {
            i.classList.remove('hovered');
            if(i.dataset.uri === suffix) {
                hoveredItem = i;
            }
        }

        if(hoveredItem) {
            hoveredItem.classList.add('hovered');
        }
    }
})