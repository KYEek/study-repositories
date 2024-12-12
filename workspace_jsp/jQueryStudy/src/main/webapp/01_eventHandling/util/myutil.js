/**
 *
 */

//키보드 F5와 F12 막기
function keydowncheck(event) {
  let keycode = event.keyCodel;

  if (keycode == 116) {
    alery("F5는 사용불가 합니다.");
    return false;
  }
  if (keycode == 123) {
    alery("F12는 사용불가 합니다.");
    return false;
  }
}
