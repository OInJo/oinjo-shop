const emailInput = document.querySelector(".email-input");
const emailIcon = document.querySelector(".email-icon");
const passwordInput = document.querySelector(".password-input");
const passwordIcon = document.querySelector(".password-icon");
const loginFailText = document.querySelector(".login-fail-text");
const loginButton = document.querySelector(".login-button");
const emailDeleteButton = document.querySelector(".email-delete-button");
const passwordDeleteButton = document.querySelector(".password-delete-button");
//이메일, 비밀번호에 focus될 시 border를 추가하여 사용자에게 클릭됨을 안내
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

//이메일과 비밀번호 둘 다에 값이 1개라도 존재할 시 loginButton에 클래스명을 추가하여 사용자에게 로그인 시도가 가능함을 표시함
emailInput.addEventListener("input", () => {
  if (emailInput.value.length > 0 && passwordInput.value.length > 0) {
    loginButton.classList.add("validation-pass");
  } else {
    loginButton.classList.remove("validation-pass");
  }
});

//패스워드도 동일하게 이메일, 비밀번호에 둘 다 값이 있을 경우 로그인 시도가 가능함을 표시
passwordInput.addEventListener("input", () => {
  if (emailInput.value.length > 0 && passwordInput.value.length > 0) {
    loginButton.classList.add("validation-pass");
  } else {
    loginButton.classList.remove("validation-pass");
  }
});

//로그인 버튼이 클릭되면, 로그인 실패 메시지를 없애줌
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