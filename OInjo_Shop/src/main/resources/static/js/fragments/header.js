//관리자가 아닌 일반 사용자 페이지 header부분 로그인 여부에 따라 각기 다른 nav바를 출력하도록 하는 로직
const loginCheck = document.querySelector(".login-check");
const updateLink = document.querySelector(".update-link");
const logoutLink = document.querySelector(".logout-link");
const loginLink = document.querySelector(".login-link");
const adminLink = document.querySelector(".admin-link");
const signUpLink = document.querySelector(".signup-link")

window.onload = () => {
  toggleElementDisplay(loginCheck, "none");
  if (loginCheck.textContent.includes("Admin@naver.com")) {
    toggleElementDisplay(adminLink, "flex");
  } else {
    toggleElementDisplay(adminLink, "none");
  }

  if (loginCheck.textContent === "") {
    toggleElementDisplay(updateLink, "none");
    toggleElementDisplay(logoutLink, "none");
    toggleElementDisplay(loginLink, "flex");
    toggleElementDisplay(signUpLink, "flex");
  } else {
    toggleElementDisplay(updateLink, "flex");
    toggleElementDisplay(logoutLink, "flex");
    toggleElementDisplay(loginLink, "none");
    toggleElementDisplay(signUpLink, "none");
  }
};

function toggleElementDisplay(element, displayValue) {
  element.style.display = displayValue;
}

