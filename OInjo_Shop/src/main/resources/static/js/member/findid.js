const nameInput = document.querySelector(".name-input");
const phoneNumberInput = document.querySelector(".phone-number-input");
const findidErrorMessage = document.querySelector(".findid-error-message");
const noFindMessage = document.querySelector(".no-find-message");
const findButton = document.querySelector(".find-button");
const findidValue = document.querySelector(".findid-value");
const resultMessage = document.querySelector(".result-message");
const nameIcon = document.querySelector(".name-icon");
const phoneIcon = document.querySelector(".phone-icon");

function addFocusDesign(element, iconElement) {
  element.classList.add("focus-design-border");
  iconElement.classList.add("focus-design-color");
}

function removeFocusDesign(element, iconElement) {
  element.classList.remove("focus-design-border");
  iconElement.classList.remove("focus-design-color");
}

function checkValidationAndEnableButton() {
  if (nameInput.value.length > 0 && phoneNumberInput.value.length > 0) {
    findButton.classList.add("validation-pass");
  } else {
    findButton.classList.remove("validation-pass");
  }
}

function showMessageOnButtonClick(event) {
  if (nameInput.value.length === 0 || phoneNumberInput.value.length === 0) {
    event.preventDefault();
    findidErrorMessage.classList.remove("hidden");
  } else {
    if (findidValue.textContent !== "") {
      resultMessage.classList.remove("hidden");
      findidErrorMessage.classList.add("hidden");
    } else {
      resultMessage.classList.add("hidden");
    }
  }
}

// 이름에 포커스가 되면, 이름에 포커스가 된 걸 알려주기 위해 border를 넣어주는 로직
nameInput.addEventListener("focus", () => {
  addFocusDesign(nameInput, nameIcon);
});

// 이름에 포커스가 풀리면(blur), 반대로 border를 넣어주는 class명을 삭제.
nameInput.addEventListener("blur", () => {
  removeFocusDesign(nameInput, nameIcon);
});

// phoneNumber도 동일
phoneNumberInput.addEventListener("focus", () => {
  addFocusDesign(phoneNumberInput, phoneIcon);
});

phoneNumberInput.addEventListener("blur", () => {
  removeFocusDesign(phoneNumberInput, phoneIcon);
});

nameInput.addEventListener("input", checkValidationAndEnableButton);
phoneNumberInput.addEventListener("input", checkValidationAndEnableButton);
findButton.addEventListener("click", showMessageOnButtonClick);
