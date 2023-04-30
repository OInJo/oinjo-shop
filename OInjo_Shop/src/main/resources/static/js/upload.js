const productName = document.querySelector("#product-name");
const lengthLimit = document.querySelector(".length-limit");

const productNameChangeFunction = () => {
    lengthLimit.textContent = `${productName.value.length}/30`;
};
productName.addEventListener("change", productNameChangeFunction);

document.querySelector("#product-detail").addEventListener("click", () => {
    console.log("bobobo");
});
