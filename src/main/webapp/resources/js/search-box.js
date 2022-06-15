const searchInput = document.querySelector('.search-box > input');
const iconSearch = document.querySelector('.search__icon');
const searchBox = document.querySelector('.search-box');

if(searchInput) {
    searchInput.onfocus = () => {
        iconSearch.style.color = 'var(--primary)';
        searchBox.style.borderColor = 'var(--primary)';
    };
    searchInput.onblur = () => {
        iconSearch.style.color = 'var(--grey)';
        searchBox.style.borderColor = 'var(--grey)';
    }
}
