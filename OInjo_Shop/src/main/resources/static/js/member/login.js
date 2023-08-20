const emailInput = document.querySelector(".email-input");
const emailIcon = document.querySelector(".email-icon");
const passwordInput = document.querySelector(".password-input");
const passwordIcon = document.querySelector(".password-icon");
const loginFailText = document.querySelector(".login-fail-text");
const loginButton = document.querySelector(".login-button");
const emailDeleteButton = document.querySelector(".email-delete-button");
const passwordDeleteButton = document.querySelector(".password-delete-button");

// 입력 필드에 포커스될 시 아이콘 스타일 변경
function addInputFocusIcon(icon) {
  icon.classList.add("input-focus");
}

function removeInputFocusIcon(icon) {
  icon.classList.remove("input-focus");
}

emailInput.addEventListener("focus", () => {
  addInputFocusIcon(emailIcon);
});

emailInput.addEventListener("blur", () => {
  removeInputFocusIcon(emailIcon);
});

passwordInput.addEventListener("focus", () => {
  addInputFocusIcon(passwordIcon);
});

passwordInput.addEventListener("blur", () => {
  removeInputFocusIcon(passwordIcon);
});

// 이메일 및 비밀번호가 입력될 시 로그인 버튼 활성화
function updateLoginButtonState() {
  const isInputValid = emailInput.value.length > 0 && passwordInput.value.length > 0;
  loginButton.classList.toggle("validation-pass", isInputValid);
}

emailInput.addEventListener("input", updateLoginButtonState);
passwordInput.addEventListener("input", updateLoginButtonState);

// 로그인 버튼 클릭 시 로그인 실패 메시지 관리
loginButton.addEventListener("click", (event) => {
  if (emailInput.value.length === 0 || passwordInput.value.length === 0) {
    loginFailText.classList.remove("hidden");
    event.preventDefault();
  } else {
    loginFailText.classList.add("hidden");
  }
});

// X 버튼 클릭 시 입력값 삭제
emailDeleteButton.addEventListener("click", () => {
  emailInput.value = "";
  updateLoginButtonState();
});

passwordDeleteButton.addEventListener("click", () => {
  passwordInput.value = "";
  updateLoginButtonState();
});
