//font를 적용하기 위한 로직.
const fontImport = new FontFace(
  "FontName",
  "url(https://cdn.rawgit.com/moonspam/NanumSquare/master/NanumSquareL.ttf)"
);
fontImport
  .load()
  .then((loadedFont) => {
    document.fonts.add(loadedFont);
    // 폰트가 로드되면 폰트를 적용할 스타일을 정의하고 적용할 수 있습니다.
    // 예: document.body.style.fontFamily = 'FontName';
  })
  .catch((error) => {
    alert("폰트 로드 중 오류가 발생했습니다:", error);
  });

fontImport
  .load()
  .then((loadedFont) => {
    document.fonts.add(loadedFont);
    document.body.style.fontFamily = "FontName";
  })
  .catch((error) => {
    console.error("폰트 로드 중 오류가 발생했습니다:", error);
  });
