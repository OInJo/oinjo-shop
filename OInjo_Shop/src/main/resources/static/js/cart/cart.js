// 장바구니에 아무 상품이 없으면 장바구니에 상품이 없다는 메시지 출력
document.addEventListener("DOMContentLoaded", function () {
  const cartContent = document.querySelector(".cart-content");
  const cartWrapper = document.querySelector(".cart-wrapper");
  const noCartMessage = document.querySelector(".no-cart");

  if (!cartContent || cartContent.children.length === 0) {
    noCartMessage.classList.remove("hidden");
  } else {
    cartWrapper.classList.remove("hidden");
  }
});

// 장바구니의 상품 수량개수를 수정하는 소스
// +버튼 클릭 시 이전 수량에서 1을 더하고, -버튼 클릭 시 이전 수량에서 1을 뺌. 수량의 최소값은 1
// 서버에 requestUrl에 PUT 메소드로 id, itemId, count 3개의 값을 보내줌
const memberId = parseInt(document.querySelector(".member-id").value);
const cartItemId = document.querySelectorAll(".cart-item-id");
const faMinus = document.querySelectorAll(".fa-minus");
const faPlus = document.querySelectorAll(".fa-plus");
let cartContentCounting = document.querySelectorAll(".cart-content-counting");
for (let i = 0; i < faMinus.length; i++) {
  const requestUrl = `/member/${memberId}/cart/${cartItemId[i].value}/update`;

  const cartContentPriceWon = document.querySelectorAll(
    ".cart-content-price-won"
  );
  const cartContentTotalPriceWon = document.querySelectorAll(
    ".cart-content-total-price-won"
  );

  const itemPrice = document.querySelectorAll(".item-price");
  //마이너스 클릭 시, 수량이 1이 아니라면 1씩 빼줌, 1보다 더 작아질 수는 없음
  faMinus[i].addEventListener("click", () => {
    if (parseInt(cartContentCounting[i].textContent) > 1) {
      cartContentCounting[i].textContent =
        parseInt(cartContentCounting[i].textContent) - 1;

      // PUT 메서드를 사용하여 요청을 보냄
      fetch(
        `${requestUrl}?id=${memberId}&itemId=${cartItemId[i].value}&count=${cartContentCounting[i].textContent}`,
        {
          method: "PUT",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            id: memberId,
            itemId: cartItemId[i].value,
            count: cartContentCounting[i].textContent,
          }),
        }
      )
        .then((response) => {
          console.log(response);
          const cartContentCountingElement = document.querySelectorAll(
            ".cart-content-counting"
          );
          cartContentCountingElement[i].textContent =
            cartContentCounting[i].textContent;
          cartContentTotalPriceWon[i].textContent = (
            parseInt(itemPrice[i].value) * cartContentCounting[i].textContent
          ).toLocaleString();
          let totalPrice = 0;
          for (let j = 0; j < cartContentTotalPriceWon.length; j++) {
            const priceText = cartContentTotalPriceWon[j].textContent;
            const price = parseInt(priceText.replace(/[^0-9]/g, "")); // 숫자 아닌 문자 제거 후 숫자로 변환
            totalPrice += price;
          }
          document.querySelector(".product-price").textContent =
            totalPrice.toLocaleString();
          document.querySelector(".total-price").textContent =
            totalPrice.toLocaleString();
        })
        .catch((error) => {
          console.error("오류 발생:", error);
        });
    }
  });

  //플러스 버튼 클릭 시 수량 1개 더함
  faPlus[i].addEventListener("click", () => {
    cartContentCounting[i].textContent =
      parseInt(cartContentCounting[i].textContent) + 1;

    // PUT 메서드를 사용하여 요청을 보냄
    fetch(
      `${requestUrl}?id=${memberId}&itemId=${cartItemId[i].value}&count=${cartContentCounting[i].textContent}`,
      {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          id: memberId,
          itemId: cartItemId[i].value,
          count: cartContentCounting[i].textContent,
        }),
      }
    )
      .then((response) => {
        console.log(response);
        const cartContentCountingElement = document.querySelectorAll(
          ".cart-content-counting"
        );
        cartContentCountingElement[i].textContent =
          cartContentCounting[i].textContent;
        cartContentTotalPriceWon[i].textContent = (
          parseInt(itemPrice[i].value) * cartContentCounting[i].textContent
        ).toLocaleString();
        let totalPrice = 0;
        for (let j = 0; j < cartContentTotalPriceWon.length; j++) {
          const priceText = cartContentTotalPriceWon[j].textContent;
          const price = parseInt(priceText.replace(/[^0-9]/g, "")); // 숫자 아닌 문자 제거 후 숫자로 변환
          totalPrice += price;
        }
        document.querySelector(".product-price").textContent =
          totalPrice.toLocaleString();
        document.querySelector(".total-price").textContent =
          totalPrice.toLocaleString();
      })
      .catch((error) => {
        console.error("오류 발생:", error);
      });
  });
}

// 원에 , 찍는 소스
const cartContentPriceWon = document.querySelectorAll(
  ".cart-content-price-won"
);
const cartContentTotalPriceWon = document.querySelectorAll(
  ".cart-content-total-price-won"
);
const itemPrice = document.querySelectorAll(".item-price");
for (let i = 0; i < cartContentPriceWon.length; i++) {
  cartContentPriceWon[i].textContent = parseInt(
    itemPrice[i].value
  ).toLocaleString();
  cartContentTotalPriceWon[i].textContent = (
    parseInt(itemPrice[i].value) * parseInt(cartContentCounting[i].textContent)
  ).toLocaleString();
}
const totalPriceWon = parseInt(
  document.querySelector(".total-price-won").value
).toLocaleString();
document.querySelector(".product-price").textContent = totalPriceWon;
document.querySelector(".total-price").textContent = totalPriceWon;
