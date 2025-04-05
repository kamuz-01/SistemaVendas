function togglePassword() {
    const passwordField = document.getElementById('password');

    // Verifica o tipo atual e alterna
    if (passwordField.type === 'password') {
        passwordField.type = 'text';
    } else {
        passwordField.type = 'password';
    }

    // Solução para forçar redesenho do campo de senha em dispositivos móveis
    passwordField.style.display = 'none';   // Oculta
    passwordField.offsetHeight;             // Força o reflow
    passwordField.style.display = 'block';  // Exibe novamente
}