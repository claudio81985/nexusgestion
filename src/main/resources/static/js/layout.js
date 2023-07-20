// Delay de los mensaje en pantalla
setInterval("cerrar()", 2000);

const cerrar = () => {
    $(".alert").delay(500).slideUp(500, () => {
        $(this).hide();
    });
}

