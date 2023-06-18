const productName = document.querySelector("#product-name");
const lengthLimit = document.querySelector(".length-limit");

const productNameChangeFunction = () => {
  lengthLimit.textContent = `${productName.value.length}/30`;
};
productName.addEventListener("change", productNameChangeFunction);

const detailButton = document.querySelector(".detail-button");
const modal = document.querySelector(".modal");

detailButton.addEventListener("click", () => {
  modal.classList.toggle("hidden");
});

const modalSaveButton = document.querySelector(".modal-save-button");
modalSaveButton.addEventListener("click", () => {
  modal.classList.toggle("hidden");
});

const modalResetButton = document.querySelector(".modal-reset-button");
const modalInput = document.querySelector(".modal-input");
modalResetButton.addEventListener("click", () => {
  modalInput.value = "";
  detailLimit.textContent = `${modalInput.value.length}/300`;
});

const detailLimit = document.querySelector(".detail-limit");
modalInput.addEventListener("change", () => {
  detailLimit.textContent = `${modalInput.value.length}/300`;
});
