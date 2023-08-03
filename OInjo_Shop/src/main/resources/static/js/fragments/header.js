//관리자가 아닌 일반 사용자 페이지 header부분 로그인 여부에 따라 각기 다른 nav바를 출력하도록 하는 로직
const loginCheck = document.querySelector(".login-check");
const updateLink = document.querySelector(".update-link");
const logoutLink = document.querySelector(".logout-link");
const loginLink = document.querySelector(".login-link");
const adminLink = document.querySelector(".admin-link");
loginCheck.style.display = "none";
window.onload = () => {
  if(loginCheck.textContent.includes("Admin@naver.com")){
    adminLink.style.display = "flex";
  }
  else{
    adminLink.style.display = "none"
  }
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
