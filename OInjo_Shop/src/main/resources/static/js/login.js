const emailInput = document.querySelector(".email-input");
const emailIcon = document.querySelector(".email-icon");
const passwordInput = document.querySelector(".password-input");
const passwordIcon = document.querySelector(".password-icon");
const loginFailText = document.querySelector(".login-fail-text");
const loginButton = document.querySelector(".login-button");
const emailDeleteButton = document.querySelector(".email-delete-button");
const passwordDeleteButton = document.querySelector(".password-delete-button");
emailInput.addEventListener("focus", () => {
  emailIcon.classList.add("input-focus");
});
emailInput.addEventListener("blur", () => {
  emailIcon.classList.remove("input-focus");
});
passwordInput.addEventListener("focus", () => {
  passwordIcon.classList.add("input-focus");
});
passwordInput.addEventListener("blur", () => {
  passwordIcon.classList.remove("input-focus");
});

emailInput.addEventListener("input", () => {
  if (emailInput.value.length > 0 && passwordInput.value.length > 0) {
    loginButton.classList.add("validation-pass");
  } else {
    loginButton.classList.remove("validation-pass");
  }
});

passwordInput.addEventListener("input", () => {
  if (emailInput.value.length > 0 && passwordInput.value.length > 0) {
    loginButton.classList.add("validation-pass");
  } else {
    loginButton.classList.remove("validation-pass");
  }
});

loginButton.addEventListener("click", (event) => {
  if (emailInput.value.length == 0 || passwordInput.value.length == 0) {
    loginFailText.classList.remove("hidden");
    event.preventDefault();
  } else {
    loginFailText.classList.add("hidden");
  }
});

// X 버튼 클릭 시 input value 삭제 로직
emailDeleteButton.addEventListener("click", () => {
  emailInput.value = "";
});

passwordDeleteButton.addEventListener("click", () => {
  passwordInput.value = "";
});

// Caps Lock 감지하여 사용자에게 표시해주는 부분, input의 X버튼이 input value가 뭐라도 입력됐을때만 하도록 구현해주는 부분 추가 구현 필요
