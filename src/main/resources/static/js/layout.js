
// Tooltip
    var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
    var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
    return new bootstrap.Tooltip(tooltipTriggerEl)
    });

    // Delay de los mensaje en pantalla
    setInterval("cerrar()", 2000);

    const cerrar = () => {
        $(".alert").delay(500).slideUp(500, () => {
        $(this).hide();
        });
    }


// Formato moneda a pesos argentinos
$(document).ready(function() {
    $('.money').each(function() {
        const valor = $(this).text();
        console.log("contenido de 'valor' =", valor);
        console.log("tipo dato de 'valor' =", typeof(valor));
        const formattedValor = parseFloat(valor).toLocaleString('es-AR', {
            style: 'currency',
            currency: 'ARS',
            minimumFractionDigits: 2,
            maximumFractionDigits: 2
        });
        console.log("contenido de 'formattedValor' =", formattedValor);
        console.log("tipo dato de 'formattedValor' =", typeof(formattedValor));
        $(this).text(formattedValor);
    });
});







