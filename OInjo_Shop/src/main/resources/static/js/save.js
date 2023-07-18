const firstArticle = document.querySelector(".first-article");
const secondArticle = document.querySelector(".second-article");
const thirdArticle = document.querySelector(".third-article");
const fourthArticle = document.querySelector(".fourth-article");

const firstArticleNextButton = document.querySelector(
  ".first-article-next-button"
);
const secondArticleNextButton = document.querySelector(
  ".second-article-next-button"
);
const thirdArticleNextButton = document.querySelector(
  ".third-article-next-button"
);
const submitButton = document.querySelector(".submit-button");

const emailCertificationWrong = document.querySelector(
  ".email-certification-wrong"
);
firstArticleNextButton.addEventListener("click", () => {
  firstArticle.classList.add("hidden");
  secondArticle.classList.remove("hidden");
});

secondArticleNextButton.addEventListener("click", () => {
  secondArticle.classList.add("hidden");
  thirdArticle.classList.remove("hidden");
});

thirdArticleNextButton.addEventListener("click", () => {
  thirdArticle.classList.add("hidden");
  fourthArticle.classList.remove("hidden");
});

// 유효성 검사 로직
// 이름 유효성 검사
const nameInput = document.querySelector(".name-input");
const nameWrong = document.querySelector(".name-wrong");
const nameIcon = document.querySelector(".name-icon");
let nameValidation = false;
nameInput.addEventListener("input", () => {
  if (nameInput.value.length != 0 && nameInput.value.length >= 2) {
    //nameInput 값이 0이 아니고, 2자 이상일 때
    nameInput.classList.remove("wrong");
    nameIcon.classList.remove("wrong");
    nameWrong.classList.add("hidden");
    nameValidation = true;
  } else {
    nameInput.classList.add("wrong");
    nameIcon.classList.add("wrong");
    nameWrong.classList.remove("hidden");
    nameValidation = false;
  }
  if (nameValidation && nicknameValidation && phoneValidation) {
    firstArticleNextButton.classList.add("validation-pass");
    firstArticleNextButton.disabled = false;
  } else {
    firstArticleNextButton.classList.remove("validation-pass");
    firstArticleNextButton.disabled = true;
  }
});

// 닉네임 유효성 검사

const nicknameInput = document.querySelector(".nickname-input");
const nicknameWrong = document.querySelector(".nickname-wrong");
const nicknameIcon = document.querySelector(".nickname-icon");
let nicknameValidation = false;
nicknameInput.addEventListener("input", () => {
  if (nicknameInput.value.length != 0 && nameInput.value.length >= 1) {
    nicknameInput.classList.remove("wrong");
    nicknameIcon.classList.remove("wrong");
    nicknameWrong.classList.add("hidden");
    nicknameValidation = true;
  } else {
    nicknameInput.classList.add("wrong");
    nicknameIcon.classList.add("wrong");
    nicknameWrong.classList.remove("hidden");
    nicknameValidation = false;
  }
  if (nameValidation && nicknameValidation && phoneValidation) {
    firstArticleNextButton.classList.add("validation-pass");
    firstArticleNextButton.disabled = false;
  } else {
    firstArticleNextButton.classList.remove("validation-pass");
    firstArticleNextButton.disabled = true;
  }
});

// 전화번호 유효성 검사
const phoneInput = document.querySelector(".phone-input");
const phoneWrong = document.querySelector(".phone-wrong");
const phoneIcon = document.querySelector(".phone-icon");
let phoneValidation = false;
phoneInput.addEventListener("input", () => {
  if (
    phoneInput.value.length != 0 &&
    /^(010|011|016|017|018|019)\d{3,4}\d{4}$/.test(phoneInput.value)
  ) {
    phoneInput.classList.remove("wrong");
    phoneIcon.classList.remove("wrong");
    phoneWrong.classList.add("hidden");
    phoneValidation = true;
  } else {
    phoneInput.classList.add("wrong");
    phoneIcon.classList.add("wrong");
    phoneWrong.classList.remove("hidden");
    phoneValidation = false;
  }
  if (nameValidation && nicknameValidation && phoneValidation) {
    firstArticleNextButton.classList.add("validation-pass");
    firstArticleNextButton.disabled = false;
  } else {
    firstArticleNextButton.classList.remove("validation-pass");
    firstArticleNextButton.disabled = true;
  }
});

// 주소 유효성 검사, 현재 상세주소만 유효성 검사 되있음, 주소 API 구현되면 zipcode, firstAddress에도 값이 있는지 검사해야 함
const zipcode = document.querySelector(".zipcode");
const firstAddress = document.querySelector(".first-address");
const lastAddress = document.querySelector(".last-address");
const addressWrong = document.querySelector(".address-wrong");
function checkInputs() {
  const zipcodeValue = zipcode.value.trim();
  const firstAddressValue = firstAddress.value.trim();
  const lastAddressValue = lastAddress.value.trim();

  if (zipcodeValue !== '' && firstAddressValue !== '' && lastAddressValue !== '') {
    addressWrong.classList.add("hidden");
    secondArticleNextButton.classList.add("validation-pass");
    secondArticleNextButton.disabled = false;
    lastAddress.classList.remove("wrong");
  } else {
    addressWrong.classList.remove("hidden");
    secondArticleNextButton.classList.remove("validation-pass");
    secondArticleNextButton.disabled = true;
    lastAddress.classList.add("wrong");
  }
}

zipcode.addEventListener("input", checkInputs);
firstAddress.addEventListener("input", checkInputs);
lastAddress.addEventListener("input", checkInputs);
// lastAddress.addEventListener("input", () => {
//   if (lastAddress.value.length != 0) {
//     //뭐든 입력됐다는 의미
//     addressWrong.classList.add("hidden");
//     secondArticleNextButton.classList.add("validation-pass");
//     secondArticleNextButton.disabled = false;
//     lastAddress.classList.remove("wrong");
//   } else {
//     addressWrong.classList.remove("hidden");
//     secondArticleNextButton.classList.remove("validation-pass");
//     secondArticleNextButton.disabled = true;
//     lastAddress.classList.add("wrong");
//   }
// });

// 이메일, 인증번호 유효성 검사 나중에 구현하기

// 비밀번호, 비밀번호재입력 유효성 검사
const passwordInput = document.querySelector(".password-input");
const repasswordInput = document.querySelector(".repassword-input");
const passwordIcon = document.querySelector(".password-icon");
const repasswordIcon = document.querySelector(".repassword-icon");
const passwordWrong = document.querySelector(".password-wrong");
const passwordMatchWrong = document.querySelector(".password-match-wrong");
passwordInput.addEventListener("input", () => {
  if (
    /^(?=.*[a-zA-Z])(?=.*\d).{6,}$/.test(passwordInput.value) &&
    passwordInput.value === repasswordInput.value
  ) {
    passwordWrong.classList.add("hidden");
    passwordMatchWrong.classList.add("hidden");
    passwordInput.classList.remove("wrong");
    repasswordInput.classList.remove("wrong");
    passwordIcon.classList.remove("wrong");
    repasswordIcon.classList.remove("wrong");
    submitButton.disabled = false;
    submitButton.classList.add("validation-pass");
  } else if (
    /^(?=.*[a-zA-Z])(?=.*\d).{6,}$/.test(passwordInput.value) &&
    passwordInput.value !== repasswordInput.value
  ) {
    passwordWrong.classList.add("hidden");
    passwordMatchWrong.classList.remove("hidden");
    passwordInput.classList.add("wrong");
    repasswordInput.classList.add("wrong");
    passwordIcon.classList.add("wrong");
    repasswordIcon.classList.add("wrong");
    submitButton.disabled = true;
    submitButton.classList.remove("validation-pass");
  } else {
    passwordWrong.classList.remove("hidden");
    passwordMatchWrong.classList.add("hidden");
    passwordInput.classList.add("wrong");
    repasswordInput.classList.remove("wrong");
    passwordIcon.classList.add("wrong");
    repasswordIcon.classList.remove("wrong");
    submitButton.disabled = true;
    submitButton.classList.remove("validation-pass");
  }
});

repasswordInput.addEventListener("input", () => {
  if (
    /^(?=.*[a-zA-Z])(?=.*\d).{6,}$/.test(passwordInput.value) &&
    passwordInput.value === repasswordInput.value
  ) {
    passwordWrong.classList.add("hidden");
    passwordMatchWrong.classList.add("hidden");
    passwordInput.classList.remove("wrong");
    repasswordInput.classList.remove("wrong");
    passwordIcon.classList.remove("wrong");
    repasswordIcon.classList.remove("wrong");
    submitButton.disabled = false;
    submitButton.classList.add("validation-pass");
  } else if (
    /^(?=.*[a-zA-Z])(?=.*\d).{6,}$/.test(passwordInput.value) &&
    passwordInput.value !== repasswordInput.value
  ) {
    passwordWrong.classList.add("hidden");
    passwordMatchWrong.classList.remove("hidden");
    passwordInput.classList.add("wrong");
    repasswordInput.classList.add("wrong");
    passwordIcon.classList.add("wrong");
    repasswordIcon.classList.add("wrong");
    submitButton.disabled = true;
    submitButton.classList.remove("validation-pass");
  } else {
    passwordWrong.classList.remove("hidden");
    passwordMatchWrong.classList.add("hidden");
    passwordInput.classList.add("wrong");
    repasswordInput.classList.remove("wrong");
    passwordIcon.classList.add("wrong");
    repasswordIcon.classList.remove("wrong");
    submitButton.disabled = true;
    submitButton.classList.remove("validation-pass");
  }
});

const passwordToggleButton = document.querySelector(".password-toggle-button");
const repasswordToggleButton = document.querySelector(
  ".repassword-toggle-button"
);
passwordToggleButton.addEventListener("click", () => {
  if (passwordInput.type === "text") {
    passwordInput.type = "password";
    passwordToggleButton.classList.add("fa-eye-slash");
    passwordToggleButton.classList.remove("fa-eye");
  } else {
    passwordInput.type = "text";
    passwordToggleButton.classList.remove("fa-eye-slash");
    passwordToggleButton.classList.add("fa-eye");
  }
});

repasswordToggleButton.addEventListener("click", () => {
  if (repasswordInput.type === "text") {
    repasswordInput.type = "password";
    repasswordToggleButton.classList.add("fa-eye-slash");
    repasswordToggleButton.classList.remove("fa-eye");
  } else {
    repasswordInput.type = "text";
    repasswordToggleButton.classList.remove("fa-eye-slash");
    repasswordToggleButton.classList.add("fa-eye");
  }
});

//이메일 인증 로직
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
      alert(`인증번호가 전송되었습니다 ${emailInput.value}를 확인하세요.`);
    })
    .catch((error) => {
      console.log("error:", error);
      alert("인증번호 전송에 실패했습니다.");
    });
});

const certificationButton = document.querySelector(".certification-button");
const certificationNumber = document.querySelector(".certification-number");
certificationButton.addEventListener("click", () => {
  const url = `/login/mailConfirm?email=${emailInput.value}&code=${certificationNumber.value}`;
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
      console.log("response:", response);
      if (response.ok) {
        alert("인증 성공!");
        thirdArticleNextButton.disabled = false;
        thirdArticleNextButton.classList.add("validation-pass");
        emailCertificationWrong.classList.add("hidden");
      } else {
        alert("인증번호가 일치하지 않습니다.");
        thirdArticleNextButton.disabled = true;
        thirdArticleNextButton.classList.remove("validation-pass");
        emailCertificationWrong.classList.remove("hidden");
      }
    })
    .catch((error) => {
      console.log("error:", error);
      alert("인증을 처리하는 도중 오류가 발생하였습니다.");
    });
});

// 주소 api 부분
function DaumPostcode() {
  new daum.Postcode({
    oncomplete: function(data) {
      var postcode = data.zonecode; // 우편번호
      var address = data.address; // 기본주소

      document.getElementById('postcode').value = postcode; // 우편번호 입력란에 값 설정
      document.getElementById('address').value = address; // 기본주소 입력란에 값 설정
    }
  }).open();
}

// 상세주소 입력란이 변경될 때마다 memberAddress에 저장
document.getElementById('detailAddress').addEventListener('input', function() {
  var detailAddress = document.getElementById('detailAddress').value; // 상세주소
  var address = document.getElementById('address').value; // 기본주소
  var postcode = document.getElementById('postcode').value; // 우편주소

  var memberAddress = ' (' + postcode + ') ' + address + ' ' + detailAddress; // 주소를 합침

  document.getElementById('memberAddressHidden').value = memberAddress; // memberAddress에 주소를 함께 저장
});
