//브랜드의 name을 클릭 시 hidden 되어있는 input에 클릭한 브랜드의 id값을 넣어주는 로직, 함수 변경 리팩토링 필요
const brandIdInput = document.querySelector(".brand-id-input");
const categoryIdInput = document.querySelector(".category-id-input");
const sizeIdInput = document.querySelector(".size-id-input");
const colorIdInput = document.querySelector(".color-id-input");

document.querySelector(".brand-select").addEventListener("change", (e) => {
  brandIdInput.value = e.target.value;
});


document.querySelector(".category-select").addEventListener("change", (e) => {
  categoryIdInput.value = e.target.value;
});
document.querySelector(".size-select").addEventListener("change", (e) => {
  sizeIdInput.value = e.target.value;
});
document.querySelector(".color-select").addEventListener("change", (e) => {
  colorIdInput.value = e.target.value;
});
