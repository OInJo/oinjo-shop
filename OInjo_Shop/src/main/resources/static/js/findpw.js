//이메일 인증 구현 시작
const certificationStartButton = document.querySelector(
  ".certification-start-button"
);
const emailInput = document.querySelector(".email-input");
certificationStartButton.addEventListener("click", () => {
  fetch("/login/mailAuthentication?email=" + emailInput.value, {
    method: "POST",
    body: JSON.stringify({
      email: emailInput.value,
    }),
    headers: {
      "Content-Type": "application/json",
    },
  })
    .then((response) => {
      console.log("response:", response);
    })
    .catch((error) => console.log("error:", error));
});

const certificationButton = document.querySelector(".certification-button");
const certificationNumber = document.querySelector(
  ".certification-number-input"
);
certificationButton.addEventListener("click", () => {
  const url = `/member/findpw?email=${emailInput.value}&code=${certificationNumber.value}`;
  fetch(url, {
    method: "POST",
    body: JSON.stringify({
      email: emailInput.value,
      certificationNumber: certificationNumber.value,
    }),
    headers: {
      "Content-Type": "application/json",
    },
  })
    .then((response) => {
      if (response.ok) {
        return response.text();
      } else {
        throw new Error("인증 실패");
      }
    })
    .then((data) => {
      document.querySelector(".result-message span").textContent = `새로운 비밀번호는 ${data}입니다.`;
    })
    .catch((error) => {
      console.log("error:", error);
      document.querySelector(".result-message span").textContent = "인증 실패";
    });
    document.querySelector(".find-button").classList.remove("hidden");
});

const emailInputBoxWrapper = document.querySelector(".email-input-box-wrapper");
const certificationNumberInputBoxWrapper = document.querySelector(".certification-number-input-box-wrapper");

emailInputBoxWrapper.addEventListener("focus", () => {
  emailInputBoxWrapper.classList.add("focus-border");
});

emailInputBoxWrapper.addEventListener("blur", () => {
  emailInputBoxWrapper.classList.remove("focus-border");
});

certificationNumberInputBoxWrapper.addEventListener("focus", () => {
  certificationNumberInputBoxWrapper.classList.add("focus-border");
});

certificationNumberInputBoxWrapper.addEventListener("blur", () => {
  certificationNumberInputBoxWrapper.classList.remove("focus-border");
});


certificationStartButton.addEventListener("click", () => {
  if(emailInput.value !== ""){
    certificationStartButton.textContent = "전송완료"
  }
})

