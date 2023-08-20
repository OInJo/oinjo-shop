// 화살표 위 아래 버튼이 화면에 존재하고, 클릭 시 스크롤의 맨 위, 맨 아래로 이동하는 소스
const $topBtn = document.querySelector(".move-top-btn");
$topBtn.onclick = () => {
  window.scrollTo({ top: 0, behavior: "smooth" });
};

const $bottomBtn = document.querySelector(".move-bottom-btn");
$bottomBtn.onclick = () => {
  window.scrollTo({ top: document.body.scrollHeight, behavior: "smooth" });
};

// 색상과 사이즈를 선택했을 때 선택한 색상과 사이즈를 받아와, 어떤 색상과 사이즈를 선택했는지 나타내주도록 구현
const colorSelect = document.querySelector(".color-select");
const sizeSelect = document.querySelector(".size-select");
const selectProductWrapper = document.querySelector(".select-product-wrapper"); //flex로
const selectProductTotalPriceWrapper = document.querySelector(".select-product-total-price-wrapper"); //flex로
let sizeSelectValue = "";
let colorSelectValue = "";

colorSelect.addEventListener("change", (e) => {
  colorSelectValue = e.target.value;
  if (sizeSelectValue && colorSelectValue) {
    toggleElementDisplay(selectProductWrapper, "flex");
    toggleElementDisplay(selectProductTotalPriceWrapper, "flex");
  }
});

sizeSelect.addEventListener("change", (e) => {
  sizeSelectValue = e.target.value;
  if (sizeSelectValue && colorSelectValue) {
    toggleElementDisplay(selectProductWrapper, "flex");
    toggleElementDisplay(selectProductTotalPriceWrapper, "flex");
  }
});

// 상품의 수량을 변경하면 장바구니 수량이 변경되는 소스, 금액도 변함
const itemCountValue = document.querySelector(".item-count-value");
const selectProductCountInput = document.querySelector(".select-product-count-input");
const selectProductCountInputInForm = document.querySelector(".select-product-count-input-in-form");
const detailPrice = document.querySelector(".detail-price");
const selectProductTotalPrice = document.querySelector(".select-product-total-price");

itemCountValue.addEventListener("input", (e) => {
  const inputValue = e.target.value;
  selectProductCountInput.value = inputValue;
  selectProductCountInputInForm.value = inputValue;
  selectProductTotalPrice.textContent = (parseInt(document.querySelector(".item-price").value) * inputValue).toLocaleString();
});

selectProductCountInput.addEventListener("input", (e) => {
  const inputValue = e.target.value;
  itemCountValue.value = inputValue;
  selectProductCountInputInForm.value = inputValue;
});

// 장바구니 추가하기 버튼을 클릭 시 어떠한 상품이 몇개 장바구니에 추가되었다고 사용자에게 안내하도록 구현
document.querySelector(".shopping-bag-button").addEventListener("click", () => {
  const itemName = document.querySelector(".item-name").textContent;
  const itemCount = document.querySelector(".item-count").value;
  alert(`장바구니에 ${itemName} 상품이 ${itemCount}개 추가되었습니다.`);
});

// 상품가격에 , 찍는 소스
const itemPriceValue = parseInt(document.querySelector(".item-price").value).toLocaleString();
document.querySelector(".detail-price").textContent = itemPriceValue;
document.querySelector(".select-product-price").textContent = itemPriceValue;
document.querySelector(".select-product-total-price").textContent = (parseInt(document.querySelector(".item-price").value) * parseInt(document.querySelector(".select-product-count-input").value)).toLocaleString();

// Helper 함수 - 요소의 display 속성을 변경하는 함수
function toggleElementDisplay(element, displayValue) {
  element.style.display = displayValue;
}
