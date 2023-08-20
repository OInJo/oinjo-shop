// 최근 본 상품 로직 구현, recentItems라는 localStorage값을 가져와서 출력,
// 하나도 없을 시 최근 본 상품이 없다고 출력
const noRecentProduct = document.querySelector(".no-recentproduct");
const recentProductWrapper = document.querySelector(".recentproduct-wrapper");
const recentItems = JSON.parse(localStorage.getItem("recent"));

if (recentItems.length === 0) {
  noRecentProduct.classList.remove("hidden");
} else {
  noRecentProduct.classList.add("hidden");

  // new Set을 이용하여 최근본상품에는 중복된 상품이 오지못하도록 구현
  const uniqueItems = Array.from(
    new Set(recentItems.map((item) => item.id))
  ).map((id) => {
    return recentItems.find((item) => item.id === id);
  });

  // 최근본상품의 갯수만큼 article element를 추가하여 페이지를 구성하는 로직
  uniqueItems.forEach((item) => {
    const article = document.createElement("article");
    article.classList.add("recentproduct");
    const link = document.createElement("a");
    link.href = `/item/${item.id}`; // 상품 페이지 링크 설정

    const image = document.createElement("img");
    image.src = item.image;
    image.alt = "상품 사진";
    image.classList.add("recentproduct-image");
    link.appendChild(image);

    const name = document.createElement("div");
    name.textContent = item.name;
    name.classList.add("recentproduct-name");
    article.appendChild(name);

    const brand = document.createElement("div");
    brand.textContent = item.brand;
    brand.classList.add("recentproduct-brand");
    article.appendChild(brand);

    const price = document.createElement("div");
    price.classList.add("recentproduct-price");

    const priceWon = document.createElement("div");
    priceWon.textContent = item.price;
    priceWon.classList.add("recentproduct-price-won");
    price.appendChild(priceWon);

    const wonText = document.createTextNode("원");
    price.appendChild(wonText);

    article.appendChild(price);

    link.appendChild(article);
    recentProductWrapper.appendChild(link);
  });
}
