// Tooltip
var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
  return new bootstrap.Tooltip(tooltipTriggerEl)
})


let stock = {};
const stocks = [];

$(document).ready(function() {

    $("#buscar_productos").autocomplete({
        minLength: 3,
        source: (request, response) => {
            $.ajax({
                url: `/ventas/buscar-productos/${request.term}`,
                dataType: "json",
                data: {
                    term: request.term
                },
                success: (data) => {
                    response($.map(data, (item) => {

                        stock ={id: item.id, stock: item.stockSucursalUno};
                        stocks.push(stock);

                        return {
                            value: item.id,
                            label: `[${item.codigoIdentificacion}] ${item.nombreComun} ${item.nombreTecnico} ${item.descripcion} - $${item.precio}`
                        }
                    }));
                }
            });
        },
        select: (event, ui) => {

            //Crear una línea
            let linea = $("#lineas").html();

            //Asignar valores a sus celdas
            let producto = ui.item.label;
            let descripcion = producto.split(']')[1].trim(); //let descripcion = producto.split('-')[0];
            descripcion = descripcion.split('-')[0];
            let precio = producto.split('-')[1];
            precio = precio.split('$')[1];
            let id = ui.item.value;
            let codigoIdentificacion = producto.split('[')[1].split(']')[0];

            console.log(`Producto seleccionado: ${producto}`);

            //Verificar si es repetido el producto...
            if (lineasUtil.esRepetido(id)) {
                lineasUtil.incrementarCantidad(id, precio);
                return false;
            }

            //Reemplazar los valores de la linea auxiliar por los buscados...
            linea = linea.replace(/{ID}/g, id);
            linea = linea.replace(/{CODIGO}/g, codigoIdentificacion);
            linea = linea.replace(/{DESCRIPCION}/g, descripcion);
            linea = linea.replace(/{PRECIO}/g, precio);

            $("#tabla_productos tbody").append(linea);

            lineasUtil.calcularSubtotal(id, precio, 1);
        }
    });
});

//Clase de utilidades de Lineas de Ventas
const lineasUtil = {

    incrementarCantidad: function (id, precio) {
        let cantidad = parseInt($(`#cantidad_${id}`).val());
        $(`#cantidad_${id}`).val(++cantidad);
        this.calcularSubtotal(id, precio, cantidad);
    } ,
    calcularSubtotal : function (id, precio, cantidad) {
        //$("#subtotal_" + id): forma antigua...
        let stk = stocks.find(i => i.id === id);// busca el stock que coincide con el id del producto
        if(cantidad > stk.stock){
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'No hay stock suficiente!'
            });
            $(`#cantidad_${id}`).val(stock);
        }else{
            $(`#subtotal_${id}`).html((parseFloat(precio) * parseInt(cantidad)).toFixed(2));
            this.calcularTotal();
        }
       
    } ,
    esRepetido: function (id) {
        let result = false;
        $('input[name="item_id[]"]').each(function() {
            if(parseInt(id) === parseInt($(this).val())) {
                result = true;
            }
        });
        return result;
    },
    calcularTotal: function() {
        let total = 0;
        $(`span[id^="subtotal_"]`).each(function() {
            total += parseFloat($(this).html());
        });
        $("#total").html("$" + parseFloat(total).toFixed(2));
    },
    borrar : function (id) {
        $(`#fila_${id}`).remove();
        this.calcularTotal();
    }
    
    
}
