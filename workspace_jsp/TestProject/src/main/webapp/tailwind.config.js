/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/main/webapp/**/*.html", "./src/main/webapp/**/*.css"],
  theme: {
    extend: {},
  },
  plugins: [
    tailwindcss: {},
    autoprefixer: {}],
};
