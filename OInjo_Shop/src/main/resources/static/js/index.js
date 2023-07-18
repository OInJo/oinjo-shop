// 카테고리에서 후드 티셔츠를 클릭 시 상품 부분 맨 위에 후드 티셔츠로 표시되도록 하는 소스
const selectName = document.querySelectorAll(".select-name");

selectName.forEach((element) => {
  element.addEventListener("click", selectNameClickEvent);
});

function selectNameClickEvent(event) {
  const selectedValue = event.target.innerText.trim();
  const isAlreadySelected = Array.from(
    document.querySelectorAll(".now-category")
  ).some((category) => category.textContent === selectedValue);

  const allCategory = document.querySelector(".now-category");
  if (allCategory && allCategory.textContent === "전체") {
    allCategory.remove();
  }

    const newCategory = document.createElement("p");
    newCategory.classList.add("now-category");
    newCategory.textContent = selectedValue;

    const closeButton = document.createElement("span");
    closeButton.classList.add("close-button");
    closeButton.textContent = "X";
    closeButton.addEventListener("click", () => {
      newCategory.remove();

      if (!document.querySelector(".now-category")) {
        const allCategory = document.createElement("div");
        allCategory.classList.add("now-category");
        allCategory.textContent = "전체";
        document
          .querySelector(".brand-category-choose")
          .appendChild(allCategory);
      }

      // 클릭했던 것도 삭제하면 다시 클릭 가능
    const clickedItems = document.querySelectorAll(".select-name");
    clickedItems.forEach((item) => {
      if (item.textContent === selectedValue) {
        item.addEventListener("click", selectNameClickEvent);
        item.classList.remove("disabled");
      }
    });
  });

    newCategory.appendChild(closeButton);
    document.querySelector(".brand-category-choose").appendChild(newCategory);

    // 이미 선택한 브랜드, 카테고리 비활성화
  const clickedItems = document.querySelectorAll(".select-name");
  clickedItems.forEach((item) => {
    if (item.textContent === selectedValue) {
      item.removeEventListener("click", selectNameClickEvent);
      item.classList.add("disabled");
    }
  });
}

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
const articleProducts = document.querySelectorAll('.article-product');

// 이벤트 리스너 함수
function handleClick(event) {
  const clickedArticle = event.currentTarget;
  
  // article-product 내에서 요소들 찾기
  const id = clickedArticle.querySelector(".article-product-id").textContent;
  const image = clickedArticle.querySelector('.article-product-image').getAttribute('src');
  const name = clickedArticle.querySelector('.article-product-name').textContent;
  const brand = clickedArticle.querySelector('.article-product-brand').textContent;
  const price = clickedArticle.querySelector('.article-product-price-won').textContent;
  // localStorage에서 recent 배열 가져오기
  const recentItems = JSON.parse(localStorage.getItem('recent')) || [];

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
  localStorage.setItem('recent', JSON.stringify(recentItems));
}

// 각 "article-product" 요소에 클릭 이벤트 리스너 할당
articleProducts.forEach(function(articleProduct) {
  articleProduct.addEventListener('click', handleClick);
});
