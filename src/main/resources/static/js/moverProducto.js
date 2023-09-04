let permisoUsuario;
let stock = {};
let listaProductos;
const lineasUtil = {
  incrementarCantidad: function (id) {
    let cantidad = parseInt($(`#cantidad_${id}`).val());
    console.log(`Cantidad = ${cantidad}`);
    $(`#cantidad_${id}`).val(++cantidad);
  },

  esRepetido: function (id) {
    let result = false;
    $('input[name="item_id[]"]').each(function () {
      if (parseInt(id) === parseInt($(this).val())) {
        result = true;
      }
    });
    return result;
  },

  calcularTotal: function () {
    let total = 0;
    $(`span[id^="subtotal_"]`).each(function () {
      total += parseFloat($(this).html());
    });
    console.log("Total = ", total);
    $("#total").html("$" + parseFloat(total).toFixed(2));
  },

  borrar: function (id) {
    $(`#fila_${id}`).remove();
    this.calcularTotal();
  },
};

$(document).ready(function () {
  // Función para obtener el rol del usuario
  function obtenerRolUsuario() {
    $.ajax({
      url: "/ventas/obtener-rol-usuario",
      dataType: "json",
      success: function (data) {
        permisoUsuario = data.rol;
        console.log("usuario:", data);

        $("#buscar_productos").autocomplete({
          minLength: 3,
          source: (request, response) => {
            $.ajax({
              url: `/ventas/buscar-productos/${request.term}`,
              dataType: "json",
              data: {
                term: request.term,
              },
              success: (data) => {
                console.log("Datos recibidos:", data);
                listaProductos = data;
                response(
                  $.map(data, (item) => {
                    if (permisoUsuario === "ROLE_SUCURSALUNO") {
                      stock = item.stockSucursalUno;
                    } else if (permisoUsuario === "ROLE_SUCURSALDOS") {
                      stock = item.stockSucursalDos;
                    } else {
                      stock = item.stockGeneral;
                    }

                    return {
                      value: item.id,
                      label: `[${item.codigoIdentificacion}] ${item.nombreComun} ${item.nombreTecnico} ${item.descripcion}`,
                    };
                  })
                );
              },
            });
          },
          select: (event, ui) => {
            //Crear una línea
            let linea = $("#lineas").html();

            //Asignar valores a sus celdas
            let producto = ui.item.label;
            let descripcion = producto.split("]")[1].trim(); //let descripcion = producto.split('-')[0];
            descripcion = descripcion.split("-")[0];
            // let precio = producto.split("-")[1];
            // precio = precio.split("$")[1];
            let id = ui.item.value;
            let codigoIdentificacion = producto.split("[")[1].split("]")[0];

            // console.log(`Producto seleccionado: ${producto}`);

            //Verificar si es repetido el producto...
            if (lineasUtil.esRepetido(id)) {
              lineasUtil.incrementarCantidad(id);
              return false;
            }

            //Reemplazar los valores de la linea auxiliar por los buscados...
            linea = linea.replace(/{ID}/g, id);
            linea = linea.replace(/{CODIGO}/g, codigoIdentificacion);
            linea = linea.replace(/{DESCRIPCION}/g, descripcion);

            $("#tabla_productos tbody").append(linea);
          },
        });
      },
      error: function (error) {
        console.error("Error al obtener el rol del usuario: ", error);
      },
    });
  }

  // Llamar a la función para obtener el rol del usuario al cargar la página
  obtenerRolUsuario();
});






function enviarDatos() {
  console.log("Función enviarDatos");
  // Crear arreglos para almacenar los datos
  var codigoIdentificacion = [];
  var cantidad = [];
  var origen = [];

  $("table#tabla_productos tbody tr").each(function () {
    var codigo = $(this).find("th[name='codigo[]']").text();
    var cantidadInput = $(this).find("input[name='cantidad[]']");
    var origenSelect = $(this).find("select[name='origen[]']");

    codigoIdentificacion.push(codigo);
    cantidad.push(cantidadInput.val());
    origen.push(origenSelect.val());
  });

  console.log(codigoIdentificacion);
  console.log(cantidad);
  console.log(origen);

  // Asignar los arreglos a los campos ocultos
  $("#codigoIdentificacionInput").val(codigoIdentificacion.join(','));
  $("#cantidadInput").val(cantidad.join(','));
  $("#origenInput").val(origen.join(','));

  // Enviar el formulario oculto al servidor
  $("#formularioOculto").submit();
}

// function enviarDatos() {
//   var id = document.getElementById("productoID").value;
//   var codigoIdentificacion = document.getElementById("codigoIdentificacion").innerText;
//   var cantidad = document.getElementById("cantidad_" + id).value;
//   var origen = document.getElementById("origen").value;

//   console.log("ID producto = ", id);
//   console.log("codigoIdentificacion = ", codigoIdentificacion);
//   console.log("cantidad = ", cantidad);
//   console.log("origen = ", origen);
//   debugger;
//   document.getElementById("codigoIdentificacionInput").value = codigoIdentificacion;
//   document.getElementById("cantidadInput").value = cantidad;
//   document.getElementById("origenInput").value = origen;

//   document.getElementById("formularioOculto").submit();
// }
