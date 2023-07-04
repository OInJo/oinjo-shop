// 카테고리에서 후드 티셔츠를 클릭 시 상품 부분 맨 위에 후드 티셔츠로 표시되도록 하는 소스
const selectName = document.querySelectorAll(".select-name");

selectName.forEach((element) => {
  element.addEventListener("click", selectNameClickEvent);
});

function selectNameClickEvent(event) {
  const selectedValue = event.target.innerText.trim();
  const isAlreadySelected = Array.from(document.querySelectorAll(".now-category")).some(category => category.textContent === selectedValue);

  const allCategory = document.querySelector(".now-category");
  if (allCategory && allCategory.textContent === "전체") {
    allCategory.remove();
  }

  if (!isAlreadySelected) {
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
        document.querySelector(".brand-category-choose").appendChild(allCategory);
      }
    });

    newCategory.appendChild(closeButton);
    document.querySelector(".brand-category-choose").appendChild(newCategory);
  }
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

