const firstArticle = document.querySelector(".first-article");
const secondArticle = document.querySelector(".second-article");
const thirdArticle = document.querySelector(".third-article");
const fourthArticle = document.querySelector(".fourth-article");

const firstArticleNextButton = document.querySelector(".first-article-next-button");
const secondArticleNextButton = document.querySelector(".second-article-next-button");
const thirdArticleNextButton = document.querySelector(".third-article-next-button");
const submitButton = document.querySelector(".submit-button")

firstArticleNextButton.addEventListener("click", () => {
  firstArticle.classList.add("hidden");
  secondArticle.classList.remove("hidden");
})

secondArticleNextButton.addEventListener("click", () => {
  secondArticle.classList.add("hidden");
  thirdArticle.classList.remove("hidden");
})

thirdArticleNextButton.addEventListener("click", () => {
  thirdArticle.classList.add("hidden");
  fourthArticle.classList.remove("hidden");
})

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
    })
    .catch((error) => console.log("error:", error));
});
