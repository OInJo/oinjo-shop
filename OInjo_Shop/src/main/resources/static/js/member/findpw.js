//이메일 인증 구현
const certificationStartButton = document.querySelector(
  ".certification-start-button"
);
const emailInput = document.querySelector(".email-input");
//인증번호 받기 버튼을 클릭 시, 서버에 email이라는 이름으로 emailInput의 값을 넘겨줌, 사용자가 입력한 이메일값이 들어가있음.
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
//인증하기 버튼을 클릭 시 사용자가 입력한 이메일값과 인증번호값을 서버로 보내줌, 인증번호값을 보내주는 이름은 certificationNumber
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
      //서버는 이메일값과 인증번호값을 이용해, 인증번호가 일치하는지 검사함. 인증번호가 일치할 시 response.ok가 반환되고, 인증이 성공됨. 인증이 성공되면 랜덤한 새로운 비밀번호(임시 비밀번호)를 반환함
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
      //이메일 인증에 실패할 시 인증 실패라는 문구를 출력함
    .catch((error) => {
      console.log("error:", error);
      document.querySelector(".result-message span").textContent = "인증 실패";
    });
    document.querySelector(".find-button").classList.remove("hidden");
});

//이메일입력란이나 인증번호란에 focus가 될 시 테두리선을 추가하여 사용자에게 클릭됨을 알려줌
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


//인증번호 전송 버튼을 클릭 시 이메일값에 아무값이라도 입력된경우 전송이 완료되었다는 문구를 출력
certificationStartButton.addEventListener("click", () => {
  if(emailInput.value !== ""){
    certificationStartButton.textContent = "전송완료"
  }
})

