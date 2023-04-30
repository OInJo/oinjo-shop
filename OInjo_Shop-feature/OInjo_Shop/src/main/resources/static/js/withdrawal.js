const pwCheckId = document.querySelector("#pw-check-id");
const pwCheckIcon = document.querySelector(".pw-check-icon");
const pwCheckFunction = (event) => {
  if (event.target.value == "password") {
    pwCheckIcon.innerHTML = `<i class="fa-solid fa-check"></i>`;
    pwCheckIcon.style.color = "green";
  } else {
    pwCheckIcon.innerHTML = `<i class="fa-solid fa-x"></i>`;
    pwCheckIcon.style.color = "tomato";
  }
  console.log(event.target.value);
};
pwCheckId.addEventListener("change", pwCheckFunction);
