function check_password() {
  if (
    document.getElementById("user-password").value != "" &&
    document.getElementById("user-password2").value != ""
  ) {
    if (
      document.getElementById("user-password").value ==
      document.getElementById("user-password2").value
    ) {
      document.getElementById("check").innerHTML = "일치";
      document.getElementById("check").style.color = "blue";
    } else {
      document.getElementById("check").innerHTML = "불일치";
      document.getElementById("check").style.color = "red";
    }
  }
}

// 지우기
const passwordClear = document.querySelector(".password-clear");
const userPassword = document.querySelector("#user-password");

function passwordClearFunction() {
  userPassword.value = "";
}
passwordClear.addEventListener("click", passwordClearFunction);
