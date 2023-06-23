const uploadButton = document.querySelector(".upload-button");
const uploadForm = document.querySelector(".upload-form");

uploadButton.addEventListener("click", () => {
  uploadForm.submit();
});

const modal = document.querySelector(".modal");
const modalInput = document.querySelector(".modal-input");

const openModal = () => {
  modal.classList.remove("hidden");
  modalInput.focus();
};

const closeModal = () => {
  modal.classList.add("hidden");
};

const modalSaveButton = document.querySelector(".modal-save-button");
modalSaveButton.addEventListener("click", closeModal);

const detailButton = document.querySelector(".detail-button");
detailButton.addEventListener("click", openModal);

const modalResetButton = document.querySelector(".modal-reset-button");
modalResetButton.addEventListener("click", () => {
  modalInput.value = "";
  detailLimit.textContent = `${modalInput.value.length}/300`;
});

const detailLimit = document.querySelector(".detail-limit");
modalInput.addEventListener("input", () => {
  detailLimit.textContent = `${modalInput.value.length}/300`;
});
