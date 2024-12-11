<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <% String ctxPath = request.getContextPath(); %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />

    <!-- Required meta tags -->
    <meta charset="utf-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />

    <!-- Bootstrap CSS -->
    <link
      rel="stylesheet"
      type="text/css"
      href="<%= ctxPath%>/bootstrap-4.6.2-dist/css/bootstrap.min.css"
    />

    <!-- Font Awesome 6 Icons -->
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.1/css/all.min.css"
    />

    <!-- Optional JavaScript -->
    <script
      type="text/javascript"
      src="<%= ctxPath%>/js/jquery-3.7.1.min.js"
    ></script>
    <script
      type="text/javascript"
      src="<%= ctxPath%>/bootstrap-4.6.2-dist/js/bootstrap.bundle.min.js"
    ></script>

    <%-- jQueryUI CSS 및 JS --%>
    <link
      rel="stylesheet"
      type="text/css"
      href="<%= ctxPath%>/jquery-ui-1.13.1.custom/jquery-ui.min.css"
    />
    <script
      type="text/javascript"
      src="<%= ctxPath%>/jquery-ui-1.13.1.custom/jquery-ui.min.js"
    ></script>

    <title>메인페이지</title>
  </head>
  <body>
    <span>안녕하세요</span>
    <div class="container main_container">
      <div
        id="carouselExampleControls"
        class="carousel slide"
        data-bs-ride="carousel"
      >
        <div class="carousel-inner">
          <div class="carousel-item active">
            <img
              src="main\webapp\images\image-landscape-64243cff-b87b-4267-81c5-cfea3f1f7455-default_0.jpg"
              class="d-block w-100"
              alt="..."
            />
          </div>
          <div class="carousel-item">
            <img
              src="main\webapp\images\image-landscape-fill-015914f5-1fc2-49d1-82b1-923e1e0e5764-default_0.jpg"
              class="d-block w-100"
              alt="..."
            />
          </div>
          <div class="carousel-item">
            <img
              src="main\webapp\images\image-landscape-64243cff-b87b-4267-81c5-cfea3f1f7455-default_0.jpg"
              class="d-block w-100"
              alt="..."
            />
          </div>
        </div>
        <button
          class="carousel-control-prev"
          type="button"
          data-bs-target="#carouselExampleControls"
          data-bs-slide="prev"
        >
          <span class="carousel-control-prev-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Previous</span>
        </button>
        <button
          class="carousel-control-next"
          type="button"
          data-bs-target="#carouselExampleControls"
          data-bs-slide="next"
        >
          <span class="carousel-control-next-icon" aria-hidden="true"></span>
          <span class="visually-hidden">Next</span>
        </button>
      </div>
    </div>
  </body>
</html>
