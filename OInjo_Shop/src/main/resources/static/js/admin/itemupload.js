//브랜드의 name을 클릭 시 hidden 되어있는 input에 클릭한 브랜드의 id값을 넣어주는 로직, 함수 변경 리팩토링 필요
function attachChangeListener(selectElement, inputElement) {
  selectElement.addEventListener("change", (e) => {
    inputElement.value = e.target.value;
  });
}

const brandIdInput = document.querySelector(".brand-id-input");
const categoryIdInput = document.querySelector(".category-id-input");
const sizeIdInput = document.querySelector(".size-id-input");
const colorIdInput = document.querySelector(".color-id-input");

const brandSelect = document.querySelector(".brand-select");
const categorySelect = document.querySelector(".category-select");
const sizeSelect = document.querySelector(".size-select");
const colorSelect = document.querySelector(".color-select");

attachChangeListener(brandSelect, brandIdInput);
attachChangeListener(categorySelect, categoryIdInput);
attachChangeListener(sizeSelect, sizeIdInput);
attachChangeListener(colorSelect, colorIdInput);