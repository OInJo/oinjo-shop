const $topBtn = document.querySelector(".move-top-btn");
$topBtn.onclick = () => {
  window.scrollTo({ top: 0, behavior: "smooth" });
};
const $bottomBtn = document.querySelector(".move-bottom-btn");
$bottomBtn.onclick = () => {
  window.scrollTo({ top: document.body.scrollHeight, behavior: "smooth" });
};

const colorSelect = document.querySelector(".color-select");
const sizeSelect = document.querySelector(".size-select");
const selectProductWrapper = document.querySelector(".select-product-wrapper"); //flex로
const selectProductTotalPriceWrapper = document.querySelector(".select-product-total-price-wrapper"); //flex로
let sizeSelectValue = "";
let colorSelectValue = "";
colorSelect.addEventListener("change", (e) => {
  sizeSelectValue = e.target.value;
  if(sizeSelectValue != "" && colorSelectValue != "") {
    selectProductWrapper.style.display = "flex";
    selectProductTotalPriceWrapper.style.display = "flex";
  }
})
sizeSelect.addEventListener("change", (e) => {
  colorSelectValue = e.target.value;
  if(sizeSelectValue != "" && colorSelectValue != "") {
    selectProductWrapper.style.display = "flex";
    selectProductTotalPriceWrapper.style.display = "flex";
  }
})