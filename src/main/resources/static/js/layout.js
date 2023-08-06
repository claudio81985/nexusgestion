// Delay de los mensaje en pantalla
setInterval(cerrar, 5000);

const cerrar = () => {
    $(".alert").delay(500).slideUp(500, () => {
        $(this).hide();
    });
}
