const uploadButton = document.querySelector(".upload-button");
const uploadForm = document.querySelector(".upload-form");

uploadButton.addEventListener("click", () => {
  uploadForm.submit();
});
