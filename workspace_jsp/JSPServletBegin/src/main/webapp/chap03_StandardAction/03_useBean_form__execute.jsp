<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <% String ctxPath = request.getContextPath(); %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>GET/POST방식으로 데이터 전송하기</title>
    <link
      rel="stylesheet"
      type="text/css"
      href="<%= ctxPath%>/chap03_StandardAction/css/03.css"
    />
    <script
      type="text/javascript"
      src="<%= ctxPath%>/js/jquery-3.7.1.min.js"
    ></script>
    <script
      type="text/javascript"
      src="<%= ctxPath%>/chap03_StandardAction/js/03.js"
    ></script>
  </head>
  <body>
    <div id="container">
      <form
        name="registerFrm"
        action="<%= ctxPath%>/registerPerson.do"
        method="post"
      >
        <fieldset>
          <legend>개인성향 테스트 03(GET/POST method)</legend>
          <ul>
            <li>
              <label for="name">성명</label>
              <input
                type="text"
                name="name"
                id="name"
                placeholder="성명입력"
                required
              />
            </li>
            <li>
              <label>학력</label>
              <select name="school">
                <option>선택하세요</option>
                <option>고졸</option>
                <option>초대졸</option>
                <option>대졸</option>
                <option>대학원졸</option>
              </select>
            </li>
            <li>
              <label>좋아하는 색상</label>
              <div>
                <label for="red">빨강</label>
                <input type="radio" name="color" id="red" value="red" />
                <label for="blue">파랑</label>
                <input type="radio" name="color" id="blue" value="blue" />
                <label for="green">초록</label>
                <input type="radio" name="color" id="green" value="green" />
                <label for="yellow">노랑</label>
                <input type="radio" name="color" id="yellow" value="yellow" />
              </div>
            </li>
            <li>
              <label>좋아하는 음식(다중선택)</label>
              <div>
                <label for="food1">짜장면</label>
                <input type="checkbox" name="food" id="food1" value="jjm.png" />
                <label for="food2">짬뽕</label>
                <input type="checkbox" name="food" id="food2" value="jjbong.png" />
                <label for="food3">탕수육</label>
                <input type="checkbox" name="food" id="food3" value="tangsy.png" />
                <label for="food4">양장피</label>
                <input type="checkbox" name="food" id="food4" value="yang.png" />
                <label for="food5">팔보채</label>
                <input type="checkbox" name="food" id="food5" value="palbc.png" />
              </div>
            </li>
            <li>
              <input type="submit" value="전송" />
              <input type="reset" value="취소" />
            </li>
          </ul>
        </fieldset>
      </form>
    </div>
  </body>
</html>
