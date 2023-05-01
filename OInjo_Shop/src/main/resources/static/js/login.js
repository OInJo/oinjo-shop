const del = document.querySelector(".delete");
const inputText = document.querySelector("#input-text"); 

function testFunction() {
  inputText.value = "";
}
del.addEventListener("click", testFunction); 
// delete X

const loginText = document.querySelector(".login-text");
const idInput = document.querySelector(".id-input");
const passwordInput = document.querySelector(".password-input");
const loginButton = document.querySelector(".login-button");
const saveButton = document.querySelector(".save-button");
const idButton = document.querySelector(".id-button");
const pwButton = document.querySelector(".pw-button");
const selectBar = document.querySelector(".select-bar");
selectBar.addEventListener("change", function (e) {
  console.log(e.target.value);
  if (e.target.value == "ko") {
    loginText.textContent = "로그인";
    idInput.placeholder = "이메일";
    passwordInput.placeholder = "비밀번호";
    loginButton.value = "로그인";
    saveButton.textContent = "회원가입";
    idButton.textContent = "아이디 찾기";
    pwButton.textContent = "비밀번호 찾기";
  }
  if (e.target.value == "en") {
    loginText.textContent = "Login";
    idInput.placeholder = "Email";
    passwordInput.placeholder = "Password";
    loginButton.value = "Login";
    saveButton.textContent = "Sign Up";
    idButton.textContent = "Find ID";
    pwButton.textContent = "Find Password";
  }
})
// 번역


// const id= document.querySelector(".id-input");
// const pw= document.querySelector("#.input-text");

// id.addEventListener("submit", (event) =>{
//   event.preventDefault();
// })

const id = document.querySelector(".id-input");
const pw = document.querySelector("#input-text");
const btn = document.querySelector(".login-button");


btn.addEventListener("click", () => { // 한번 disabled 되면 addEventListener 작동 안함
  if(id.value.length > 0 && pw.value.length > 0){
    btn.disabled = false;
  }
  else {
    btn.disabled = true;
    alert("이메일, 비밀번호를 입력하시오.")
  }
});