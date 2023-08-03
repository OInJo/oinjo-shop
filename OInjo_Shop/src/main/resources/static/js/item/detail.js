//화살표 위 아래 버튼이 화면에 존재하고, 클릭 시 스크롤의 맨 위, 맨 아래로 이동하는 소스
const $topBtn = document.querySelector(".move-top-btn");
$topBtn.onclick = () => {
  window.scrollTo({ top: 0, behavior: "smooth" });
};
const $bottomBtn = document.querySelector(".move-bottom-btn");
$bottomBtn.onclick = () => {
  window.scrollTo({ top: document.body.scrollHeight, behavior: "smooth" });
};

//색상과 사이즈를 선택했을 때 선택한 색상과 사이즈를 받아와, 어떤 색상과 사이즈를 선택했는지 나타내주도록 구현
const colorSelect = document.querySelector(".color-select");
const sizeSelect = document.querySelector(".size-select");
const selectProductWrapper = document.querySelector(".select-product-wrapper"); //flex로
const selectProductTotalPriceWrapper = document.querySelector(
  ".select-product-total-price-wrapper"
); //flex로
let sizeSelectValue = "";
let colorSelectValue = "";
colorSelect.addEventListener("change", (e) => {
  colorSelectValue = e.target.value;
  if (sizeSelectValue != "" && colorSelectValue != "") {
    selectProductWrapper.style.display = "flex";
    selectProductTotalPriceWrapper.style.display = "flex";
  }
});
sizeSelect.addEventListener("change", (e) => {
  sizeSelectValue = e.target.value;
  if (sizeSelectValue != "" && colorSelectValue != "") {
    selectProductWrapper.style.display = "flex";
    selectProductTotalPriceWrapper.style.display = "flex";
  }
});

// // 동민이가 만든 구매하기 테스트 해보기
// function order() {
//   var url = "/order";
//   var paramData = {
//     itemId: $("#itemId").val(),
//     // count: $("#count").val(), 일단 count 없으니까 삭제했음
//   };

//   var param = JSON.stringify(paramData);

//   $.ajax({
//     url: url,
//     type: "POST",
//     contentType: "application/json",
//     data: param,
//     dataType: "json",
//     cache: false,
//     success: function (result, status) {
//       alert("주문이 완료 되었습니다.");
//       location.href = "/";
//     },
//     error: function (jqXHR, status, error) {
//       if (jqXHR.status == "401") {
//         alert("로그인 후 이용해주세요");
//         location.href = "/member/login";
//       } else {
//         alert(jqXHR.responseText);
//       }
//     },
//   });
// }

// document.querySelector(".now-buy-button").addEventListener("click", order);
// // 여기까지 구매하기 소스

// 동민 소스 바닐라 JS 버전
// function order() {
//   var url = "/order";
//   var paramData = {
//     itemId: document.querySelector("#itemId").value,
//     // count: document.querySelector("#count").value, 일단 count 없으니까 삭제
//   };
//
//   fetch(url, {
//     method: "POST",
//     headers: {
//       "Content-Type": "application/json",
//     },
//     body: JSON.stringify(paramData),
//   })
//     .then(function (response) {
//       if (response.ok) {
//         alert("주문이 완료되었습니다.");
//         location.href = "/";
//       } else if (response.status === 401) {
//         alert("로그인 후 이용해주세요");
//         location.href = "/member/login";
//       } else {
//         throw new Error(response.statusText);
//       }
//     })
//     .catch(function (error) {
//       alert(error);
//     });
// }
//
// document.querySelector(".now-buy-button").addEventListener("click", order);

// 상품의 수량을 변경하면 장바구니 수량이 변경되는 소스, 금액도 변함
const itemCountValue = document.querySelector(".item-count-value");
const selectProductCountInput = document.querySelector(
  ".select-product-count-input"
);
const selectProductCountInputInForm = document.querySelector(
  ".select-product-count-input-in-form"
);
const detailPrice = document.querySelector(".detail-price");
const selectProductTotalPrice = document.querySelector(
  ".select-product-total-price"
);
itemCountValue.addEventListener("input", (e) => {
  selectProductCountInput.value = e.target.value;
  selectProductCountInputInForm.value = e.target.value;
  selectProductTotalPrice.textContent =
    detailPrice.textContent * e.target.value;
});
selectProductCountInput.addEventListener("input", (e) => {
  itemCountValue.value = e.target.value;
  selectProductCountInputInForm.value = e.target.value;
});

//장바구니 추가하기 버튼을 클릭 시 어떠한 상품이 몇개 장바구니에 추가되었다고 사용자에게 안내하도록 구현
document.querySelector(".shopping-bag-button").addEventListener("click", () => {
  const itemName = document.querySelector(".item-name").textContent;
  const itemCount = document.querySelector(".item-count").value;
  alert(`장바구니에 ${itemName} 상품이 ${itemCount}개 추가되었습니다.`);
});
