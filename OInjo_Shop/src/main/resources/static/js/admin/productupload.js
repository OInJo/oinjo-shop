
// brand-name 클릭하면 brand-id 값을 가져옴
// brand-id-input의 value를 brand-id로 바꿔줌
// 함수 변경 리팩토링 필요
const brandName = document.querySelectorAll(".brand-name");
const brandId = document.querySelectorAll(".brand-id");
const brandIdInput = document.querySelector(".brand-id-input");

for(let i=0; i<brandName.length; i++) {
    brandName[i].addEventListener("click", () => {
        brandIdInput.value = brandId[i].textContent
    });
}

const categoryName = document.querySelectorAll(".category-name");
const categoryId = document.querySelectorAll(".category-id");
const categoryIdInput = document.querySelector(".category-id-input");

for(let i=0; i<categoryName.length; i++) {
    categoryName[i].addEventListener("click", () => {
        categoryIdInput.value = categoryId[i].textContent
    });
}


const colorName = document.querySelectorAll(".color-name");
const colorId = document.querySelectorAll(".color-id");
const colorIdInput = document.querySelector(".color-id-input");

for(let i=0; i<colorName.length; i++) {
    colorName[i].addEventListener("click", () => {
        colorIdInput.value = colorId[i].textContent
    });
}

const sizeName = document.querySelectorAll(".size-name");
const sizeId = document.querySelectorAll(".size-id");
const sizeIdInput = document.querySelector(".size-id-input");

for(let i=0; i<sizeName.length; i++) {
    sizeName[i].addEventListener("click", () => {
        sizeIdInput.value = sizeId[i].textContent
    });
}