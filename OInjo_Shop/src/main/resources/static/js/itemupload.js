const brandNames = document.querySelectorAll(".brand-name");
brandNames.forEach(function (brand) {
  brand.addEventListener("click", function () {
    brandNames.forEach(function (item) {
      item.classList.remove("click-accent");
    });
    this.classList.add("click-accent");
  });
});

const categoryNames = document.querySelectorAll(".category-name");
categoryNames.forEach(function (category) {
  category.addEventListener("click", function () {
    categoryNames.forEach(function (item) {
      item.classList.remove("click-accent");
    });
    this.classList.add("click-accent");
  });
});

const colorNames = document.querySelectorAll(".color-name");
colorNames.forEach(function (color) {
  color.addEventListener("click", function () {
    colorNames.forEach(function (item) {
      item.classList.remove("click-accent");
    });
    this.classList.add("click-accent");
  });
});

const sizeNames = document.querySelectorAll(".size-name");
sizeNames.forEach(function (size) {
  size.addEventListener("click", function () {
    sizeNames.forEach(function (item) {
      item.classList.remove("click-accent");
    });
    this.classList.add("click-accent");
  });
});
