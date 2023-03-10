const del = document.querySelector(".delete");
const inputText = document.querySelector("#input-text"); 

function testFunction() {
  inputText.value = "";
}
del.addEventListener("click", testFunction); 
// delete X

const loginText = document.querySelector(".login-text");
const tetest = document.querySelector(".tetest");
const selectBar = document.querySelector(".select-bar");
selectBar.addEventListener("change", function (e) {
  console.log(e.target.value);
  if (e.target.value == "ko") {
    loginText.textContent = "로그인";
    tetest.placeholder = "한국어";
  }
  if (e.target.value == "en") {
    loginText.textContent = "Login";
    tetest.placeholder = "영어";
  }
})