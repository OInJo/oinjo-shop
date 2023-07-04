// login 여부에 따라 nav바 변경
const loginCheck = document.querySelector(".login-check");
const updateLink = document.querySelector(".update-link");
const logoutLink = document.querySelector(".logout-link");
const loginLink = document.querySelector(".login-link");
loginCheck.style.display = "none";
window.onload = () => {
  if(loginCheck.textContent == "") {
    updateLink.style.display = "none"
    logoutLink.style.display = "none"
    loginLink.style.display = "flex"
  }
  else if(loginCheck.textContent.length >= 1) {
    updateLink.style.display = "flex"
    logoutLink.style.display = "flex"
    loginLink.style.display = "none"
  }
}
