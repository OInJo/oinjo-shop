// 장바구니에 아무 상품이 없으면 장바구니에 상품이 없다는 메시지 출력
document.addEventListener("DOMContentLoaded", function () {
    const cartContent = document.querySelector(".cart-content");
    const cartWrapper = document.querySelector(".cart-wrapper");
    const noCartMessage = document.querySelector(".no-cart");

    if (!cartContent || cartContent.children.length === 0) {
        noCartMessage.classList.remove("hidden");
    }
    else {
        cartWrapper.classList.remove("hidden")
    }
});