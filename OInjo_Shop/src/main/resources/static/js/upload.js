const uploadButton = document.querySelector(".upload-button");
const uploadForm = document.querySelector(".upload-form");

uploadButton.addEventListener("click", () => {
  uploadForm.submit();
});

const brandIdInput = document.querySelector(".brand-id-input");
const categoryIdInput = document.querySelector(".category-id-input");
const sizeIdInput = document.querySelector(".size-id-input");
const colorIdInput = document.querySelector(".color-id-input");

document.querySelector(".brand-select").addEventListener("change", (e) => {
  brandIdInput.value = e.target.value;
});
document.querySelector(".category-select").addEventListener("change", (e) => {
  categoryIdInput.value = e.target.value;
});
document.querySelector(".size-select").addEventListener("change", (e) => {
  sizeIdInput.value = e.target.value;
});
document.querySelector(".color-select").addEventListener("change", (e) => {
  colorIdInput.value = e.target.value;
});
