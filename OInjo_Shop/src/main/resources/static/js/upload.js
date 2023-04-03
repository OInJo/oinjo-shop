const productName = document.querySelector("#product-name");
const lengthLimit = document.querySelector(".length-limit");

const productNameChangeFunction = () => {
    lengthLimit.textContent = `${productName.value.length}/30`;
};
productName.addEventListener("change", productNameChangeFunction);

// modal js
const detailButton = document.querySelector(".detail-button");
const modal = document.querySelector(".modal")
const modalInput = document.querySelector(".modal-input");
const modalResetButton = document.querySelector(".modal-reset-button");
const modalSaveButton = document.querySelector(".modal-save-button");
const detailLimit = document.querySelector(".detail-limit");

detailButton.addEventListener("click", () => {
    modal.style.display = "flex";
})

modalResetButton.addEventListener("click", () => {
    modalInput.value = ""
    detailLimit.innerText = `0/300`
})

modalSaveButton.addEventListener("click", () => {
    modal.style.display = "none";
})


modalInput.addEventListener("change", () => {
    if(modalInput.value.length > 300) {
        alert("허용된 글자수를 넘었습니다.")
    }
    else {
        detailLimit.innerText = `${modalInput.value.length}/300`
    }
})