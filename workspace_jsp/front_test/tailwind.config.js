/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./**/*.{html,js,mustache}", "!./node_modules/**/*"], // mustache는 사용하지 않을 경우 삭제

  theme: {
    extend: {},
  },
  plugins: [],
};
