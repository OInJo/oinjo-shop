const nameInput = document.querySelector(".name-input");
const phoneNumberInput = document.querySelector(".phone-number-input");
const findidErrorMessage = document.querySelector(".findid-error-message");
const noFindMessage = document.querySelector("no-find-message")
const findButton = document.querySelector(".find-button");
const findidValue = document.querySelector(".findid-value");
const resultMessage = document.querySelector(".result-message");
const nameIcon = document.querySelector(".name-icon")
const phoneIcon = document.querySelector(".phone-icon")
nameInput.addEventListener("focus", () => {
  nameInput.classList.add("focus-design-border");
  nameIcon.classList.add("focus-design-color");
})
nameInput.addEventListener("blur", () => {
  nameInput.classList.remove("focus-design-border")
  nameIcon.classList.remove("focus-design-color")
})

phoneNumberInput.addEventListener("focus", () => {
  phoneNumberInput.classList.add("focus-design-border");
  phoneIcon.classList.add("focus-design-color");

})
phoneNumberInput.addEventListener("blur", () => {
  phoneNumberInput.classList.remove("focus-design-border")
  phoneIcon.classList.remove("focus-design-color")
})
nameInput.addEventListener("input", () => {
  if (nameInput.value.length > 0 && phoneNumberInput.value.length > 0) {
    findButton.classList.add("validation-pass");
  } else {
    findButton.classList.remove("validation-pass");
  }
});
phoneNumberInput.addEventListener("input", () => {
  if (nameInput.value.length > 0 && phoneNumberInput.value.length > 0) {
    findButton.classList.add("validation-pass");
  } else {
    findButton.classList.remove("validation-pass");
  }
});

findButton.addEventListener("click", (event) => {
  if (nameInput.value.length === 0 || phoneNumberInput.value.length === 0) {
    event.preventDefault();
    findidErrorMessage.classList.remove("hidden");
  } else {
    if (findidValue.textContent !== "") {
      resultMessage.classList.remove("hidden")
      findidErrorMessage.classList.add("hidden")
    } else {
      resultMessage.classList.add("hidden")

    }
  }
});
