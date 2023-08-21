const togglePasswordButton = document.getElementById('togglePassword');
  const passwordInput = document.getElementById('pass');

togglePasswordButton.addEventListener('click', function () {
const type = passwordInput.getAttribute('type') === 'password' ? 'text' : 'password';
passwordInput.setAttribute('type', type);
togglePasswordButton.querySelector('i').classList.toggle('bi-eye');
togglePasswordButton.querySelector('i').classList.toggle('bi-eye-slash');
});