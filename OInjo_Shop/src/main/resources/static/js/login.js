const emailInput = document.querySelector(".email-input");
const emailIcon = document.querySelector(".email-icon")
const passwordInput = document.querySelector(".password-input");
const passwordIcon = document.querySelector(".password-icon")
const loginFailText = document.querySelector(".login-fail-text");
const loginButton = document.querySelector(".login-button");

emailInput.addEventListener("focus", () => {
  emailIcon.classList.add("input-focus")
})
emailInput.addEventListener("blur", () => {
  emailIcon.classList.remove("input-focus")
})
passwordInput.addEventListener("focus", () => {
  passwordIcon.classList.add("input-focus")
})
passwordInput.addEventListener("blur", () => {
  passwordIcon.classList.remove("input-focus")
})

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
  if(emailInput.value.length == 0 || passwordInput.value.length == 0) {
    loginFailText.classList.remove("hidden");
    event.preventDefault();
  }
  else {
    loginFailText.classList.add("hidden");
  }
})