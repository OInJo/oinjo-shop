const nameInput = document.querySelector(".name-input");
const phoneNumberInput = document.querySelector(".phone-number-input");
const findidErrorMessage = document.querySelector(".findid-error-message");
const noFindMessage = document.querySelector("no-find-message")
const findButton = document.querySelector(".find-button");
const findidValue = document.querySelector(".findid-value");
const resultMessage = document.querySelector(".result-message");
const nameIcon = document.querySelector(".name-icon")
const phoneIcon = document.querySelector(".phone-icon")

//이름에 포커스가 되면, 이름에 포커스가 된 걸 알려주기 위해 border를 넣어주는 class명을 넣는 로직
nameInput.addEventListener("focus", () => {
  nameInput.classList.add("focus-design-border");
  nameIcon.classList.add("focus-design-color");
})
//이름에 포커스가 풀리면(blur), 반대로 border를 넣어주는 class명을 삭제.
nameInput.addEventListener("blur", () => {
  nameInput.classList.remove("focus-design-border")
  nameIcon.classList.remove("focus-design-color")
})

//phoneNumber도 동일, 차후 함수로 리팩토링 필요
phoneNumberInput.addEventListener("focus", () => {
  phoneNumberInput.classList.add("focus-design-border");
  phoneIcon.classList.add("focus-design-color");

})
phoneNumberInput.addEventListener("blur", () => {
  phoneNumberInput.classList.remove("focus-design-border")
  phoneIcon.classList.remove("focus-design-color")
})

//name도 동일, 차후 함수로 리팩토링 필요
nameInput.addEventListener("input", () => {
  if (nameInput.value.length > 0 && phoneNumberInput.value.length > 0) {
    findButton.classList.add("validation-pass");
  } else {
    findButton.classList.remove("validation-pass");
  }
});

//휴대폰 번호에 아무런 값도 입력하지 않으면 아이디 찾기 버튼을 활성화하지않음.
//아무런 값이라도 입력했을 때만 아이디 찾기 버튼이 활성화됨
phoneNumberInput.addEventListener("input", () => {
  if (nameInput.value.length > 0 && phoneNumberInput.value.length > 0) {
    findButton.classList.add("validation-pass");
  } else {
    findButton.classList.remove("validation-pass");
  }
});

//아이디찾기 버튼을 클릭하면, 아이디찾기에 실패했는지, 성공했는지 여부에 따라 사용자에게 메시지를 표시
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
