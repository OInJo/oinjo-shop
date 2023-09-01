//index sidebar에 있는 카테고리와, 브랜드를 클릭 시 사용자에게 클릭한 카테고리, 브랜드를 안내
document.addEventListener("DOMContentLoaded", function () {
  const selectName = document.querySelectorAll(".select-name");
  let selectedBrand = null; // 현재 선택된 브랜드를 저장하는 변수
  let selectedCategory = null; // 현재 선택된 카테고리를 저장하는 변수

  selectName.forEach((element) => {
    element.addEventListener("click", selectNameClickEvent);
  });

  function selectNameClickEvent(event) {
    const selectedValue = event.target.innerText.trim();

    // 같은 브랜드 또는 카테고리가 다시 클릭되면, 함수를 종료
    if (
        (event.target.classList.contains("select-brand") &&
            selectedBrand === selectedValue) ||
        (event.target.classList.contains("select-category") &&
            selectedCategory === selectedValue)
    ) {
      return;
    }

    // 이전에 선택한 카테고리를 제거
    const previousSelected = document.querySelector(".now-category");
    if (previousSelected) {
      previousSelected.remove();
    }

    // 브랜드 또는 카테고리가 클릭되었는지 판단하고 선택된 변수를 업데이트
    if (event.target.classList.contains("select-brand")) {
      selectedBrand = selectedValue;
      selectedCategory = null;
      displaySelectedBrand(selectedValue);
    } else if (event.target.classList.contains("select-category")) {
      selectedCategory = selectedValue;
      selectedBrand = null;
      displaySelectedCategory(selectedValue);
    }

    showSelectedProducts();
  }

  // 선택된 브랜드를 표시
  function displaySelectedBrand(selectedValue) {
    const newCategory = document.createElement("p");
    newCategory.classList.add("now-category");
    newCategory.textContent = selectedValue;

    const closeButton = document.createElement("span");
    closeButton.classList.add("close-button");
    closeButton.textContent = "X";
    closeButton.addEventListener("click", () => {
      newCategory.remove();
      selectedBrand = null;
      enableAllBrands();
      showAllProducts();
    });

    newCategory.appendChild(closeButton);
    document.querySelector(".brand-category-choose").appendChild(newCategory);

    const clickedItems = document.querySelectorAll(".select-name");
    clickedItems.forEach((item) => {
      if (item.textContent === selectedValue) {
        item.removeEventListener("click", selectNameClickEvent);
        item.classList.add("disabled");
      } else {
        item.addEventListener("click", selectNameClickEvent);
        item.classList.remove("disabled");
      }
    });
  }

  // 선택된 카테고리를 표시
  function displaySelectedCategory(selectedValue) {
    const newCategory = document.createElement("p");
    newCategory.classList.add("now-category");
    newCategory.textContent = selectedValue;

    const closeButton = document.createElement("span");
    closeButton.classList.add("close-button");
    closeButton.textContent = "X";
    closeButton.addEventListener("click", () => {
      newCategory.remove();
      selectedCategory = null;
      enableAllCategories();
      showAllProducts();
    });

    newCategory.appendChild(closeButton);
    document.querySelector(".brand-category-choose").appendChild(newCategory);

    const clickedItems = document.querySelectorAll(".select-name");
    clickedItems.forEach((item) => {
      if (item.textContent === selectedValue) {
        item.removeEventListener("click", selectNameClickEvent);
        item.classList.add("disabled");
      } else {
        item.addEventListener("click", selectNameClickEvent);
        item.classList.remove("disabled");
      }
    });
  }

  function enableAllBrands() {
    const clickedItems = document.querySelectorAll(".select-brand");
    clickedItems.forEach((item) => {
      item.addEventListener("click", selectNameClickEvent);
      item.classList.remove("disabled");
    });
  }

  function enableAllCategories() {
    const clickedItems = document.querySelectorAll(".select-category");
    clickedItems.forEach((item) => {
      item.addEventListener("click", selectNameClickEvent);
      item.classList.remove("disabled");
    });
  }

  // 모든 제품을 표시
  function showAllProducts() {
    const productItems = document.querySelectorAll(".article-product");
    productItems.forEach(function (productItem) {
      productItem.style.display = "block";
    });

    const newCategory = document.createElement("p");
    newCategory.classList.add("now-category");
    newCategory.textContent = "전체";

    document.querySelector(".brand-category-choose").appendChild(newCategory);
  }

  // 선택된 제품을 표시
  function showSelectedProducts() {
    const productItems = document.querySelectorAll(".article-product");
    productItems.forEach(function (productItem) {
      const brandName = productItem
          .querySelector(".article-product-brand")
          .textContent.trim();
      const categoryName = productItem
          .querySelector(".article-product-category")
          .textContent.trim();

      const isBrandMatched =
          selectedBrand === null ||
          selectedBrand === brandName ||
          selectedBrand === "전체";
      const isCategoryMatched =
          selectedCategory === null ||
          selectedCategory === categoryName ||
          selectedCategory === "전체";

      if (isBrandMatched && isCategoryMatched) {
        productItem.style.display = "block";
      } else {
        productItem.style.display = "none";
      }
    });
  }
});

//위, 아래로 이동하는 버튼 클릭 시, 스크롤이 맨 아래, 맨 위로 이동하는 로직
const $topBtn = document.querySelector(".move-top-btn");
$topBtn.onclick = () => {
  window.scrollTo({ top: 0, behavior: "smooth" });
};
const $bottomBtn = document.querySelector(".move-bottom-btn");
$bottomBtn.onclick = () => {
  window.scrollTo({ top: document.body.scrollHeight, behavior: "smooth" });
};

const redColor = document.querySelectorAll(".red-color");
const articleProductImage = document.querySelectorAll(".article-product-image");
for (let i = 0; i < redColor.length; i++) {
  redColor[i].addEventListener("click", () => {
    articleProductImage[i].src = `images/kakao.png`;
  });
}

// 최근본상품 로직 구현, 상품 클릭 시 해당 상품 정보를 localStorage에 저장
// 모든 "article-product" 요소에 이벤트 리스너 추가
const articleProducts = document.querySelectorAll(".article-product");

// 이벤트 리스너 함수
function handleClick(event) {
  const clickedArticle = event.currentTarget;

  // article-product 내에서 요소들 찾기
  const id = clickedArticle.querySelector(".article-product-id").textContent;
  const image = clickedArticle
    .querySelector(".article-product-image")
    .getAttribute("src");
  const name = clickedArticle.querySelector(
    ".article-product-name"
  ).textContent;
  const brand = clickedArticle.querySelector(
    ".article-product-brand"
  ).textContent;
  const price = clickedArticle.querySelector(
    ".article-product-price-won"
  ).textContent;
  // localStorage에서 recent 배열 가져오기
  const recentItems = JSON.parse(localStorage.getItem("recent")) || [];

  // 새로운 아이템 객체 생성
  const newItem = {
    id,
    image,
    name,
    brand,
    price,
  };

  // recent 배열에 새로운 아이템 추가
  recentItems.push(newItem);

  // 최대 10개의 최근 아이템 유지
  if (recentItems.length > 10) {
    recentItems.shift();
  }

  // 변경된 recent 배열을 localStorage에 저장
  localStorage.setItem("recent", JSON.stringify(recentItems));
}

// 각 "article-product" 요소에 클릭 이벤트 리스너 할당
articleProducts.forEach(function (articleProduct) {
  articleProduct.addEventListener("click", handleClick);
});

const sortByPriceAscending = () => {
  //오름차순 정렬, 가격 낮은 순
  // 모든 상품 요소들을 가져옵니다.
  const productElements = document.querySelectorAll(".article-product");
  // NodeList를 배열로 변환하여 조작하기 쉽도록 합니다.
  const products = Array.from(productElements);
  // 가격을 기준으로 상품들을 오름차순으로 정렬합니다.
  products.sort((a, b) => {
    const priceA = parseInt(
      a.querySelector(".article-product-price-won").textContent
    );
    const priceB = parseInt(
      b.querySelector(".article-product-price-won").textContent
    );
    return priceA - priceB;
  });

  // 기존의 상품 리스트를 초기화합니다.
  const articleProductWrapper = document.querySelector(
    ".article-product-wrapper"
  );
  articleProductWrapper.innerHTML = "";

  // 정렬된 상품들을 다시 articleProductWrapper에 추가합니다.
  products.forEach((product) => {
    articleProductWrapper.appendChild(product);
  });
};

const sortByPriceDescending = () => {
  //내림차순 정렬, 가격 높은 순
  // 모든 상품 요소들을 가져옵니다.
  const productElements = document.querySelectorAll(".article-product");
  // NodeList를 배열로 변환하여 조작하기 쉽도록 합니다.
  const products = Array.from(productElements);
  // 가격을 기준으로 상품들을 내림차순으로 정렬합니다.
  products.sort((a, b) => {
    const priceA = parseInt(
      a.querySelector(".article-product-price-won").textContent
    );
    const priceB = parseInt(
      b.querySelector(".article-product-price-won").textContent
    );
    return priceB - priceA;
  });

  // 기존의 상품 리스트를 초기화합니다.
  const articleProductWrapper = document.querySelector(
    ".article-product-wrapper"
  );
  articleProductWrapper.innerHTML = "";

  // 정렬된 상품들을 다시 articleProductWrapper에 추가합니다.
  products.forEach((product) => {
    articleProductWrapper.appendChild(product);
  });
};
// 가격 높은 순 정렬
const priceHighSort = document.querySelector("#price-high-sort");
priceHighSort.addEventListener("click", sortByPriceDescending);
//가격 낮은 순 정렬
const priceLowSort = document.querySelector("#price-low-sort");
priceLowSort.addEventListener("click", sortByPriceAscending);

// , 찍는 소스
const articleProductPriceWon = document.querySelectorAll(".article-product-price-won")
const itemPrice = document.querySelectorAll(".item-price")
for(let j=0; j<articleProductPriceWon.length; j++){
  articleProductPriceWon[j].textContent = parseInt(itemPrice[j].value).toLocaleString();
}