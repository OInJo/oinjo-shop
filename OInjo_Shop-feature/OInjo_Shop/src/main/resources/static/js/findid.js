// jQuery(요즘은 잘 쓰지않음), jQuery cdn 받아와야함
// let id = $("#id");
// let btn = $("#btn");
// $(btn).on("click", function () {
//   if ($(id).val() == "") {
//     $(id).next("label").addClass("warning");
//   }
// });

const email = document.querySelector("#email");
const emailInputLabel = document.querySelector(".email-input-label");
const emailChange = () => {
  if (email.value != "") {
    emailInputLabel.style.fontSize = "15px";
    emailInputLabel.style.top = "480px";
  }
  else {
    emailInputLabel.style.fontSize = "22px";
    emailInputLabel.style.top = "";
  }
};
email.addEventListener("change", emailChange);
