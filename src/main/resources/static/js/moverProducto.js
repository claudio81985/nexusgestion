let sucursalUsuario;
let stock = 0;
let listaProductos;
const lineasUtil = {
  incrementarCantidad: function (id, precio) {
    let cantidad = parseInt($(`#cantidad_${id}`).val());
    $(`#cantidad_${id}`).val(++cantidad);
    this.calcularSubtotal(id, precio, cantidad);
  },

  sucursalOrigen: function (id) {
    let sucOrigen = document.getElementById(`sucOrigen_${id}`);
    // console.log(`Sucursal Origen (${id}) = `, sucOrigen.value);
    return sucOrigen.value;
  },

  actualizarStock: function(id, cantidadId) {
    let stk = listaProductos.find((i) => i.id === id);
    let sucActual = this.sucursalOrigen(id);
    let cantidad = document.getElementById(cantidadId);

    if (sucActual === "sucursalUno") {
      cantidad.value = Math.min(cantidad.value, stk.stockSucursalUno);
    } else if (sucActual === "sucursalDos") {
      cantidad.value = Math.min(cantidad.value, stk.stockSucursalDos);
    } else {
      cantidad.value = 0;
    }
  },

  validarStockDisponible: function (id, cantidad, inputId) {
    let stk = listaProductos.find((i) => i.id === id);
    let sucOrigen = this.sucursalOrigen(id);
    let input = document.getElementById(inputId);

    if (sucOrigen === "sucursalUno" && cantidad > stk.stockSucursalUno) {
        Swal.fire({
            icon: "error",
            title: "Oops...",
            text: "No hay stock suficiente en Sucursal Sauzalito!",
        });
        input.value = stk.stockSucursalUno;
    } else if (sucOrigen === "sucursalDos" && cantidad > stk.stockSucursalDos) {
        Swal.fire({
            icon: "error",
            title: "Oops...",
            text: "No hay stock suficiente en Sucursal Fontana!",
        });
        input.value = stk.stockSucursalDos;
    }
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

  borrar: function (id) {
    $(`#fila_${id}`).remove();
    this.calcularTotal();
  },
};

$(document).ready(function () {
  function obtenerSucursalUsuario() {
    $.ajax({
      url: "/ventas/obtener-sucursal-usuario",
      dataType: "json",
      success: function (data) {
        sucursalUsuario = data;

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
                listaProductos = data;
                response(
                  $.map(data, (item) => {
                    if (sucursalUsuario !== null) {
                      if (sucursalUsuario === 0) {
                        Swal.fire({
                          icon: "error",
                          title: "Error al obtener stock",
                          text: "El usuario actual no pertenece a ninguna sucursal",
                        });
                      }
                      if (sucursalUsuario === 1) {
                        stock = item.stockSucursalUno;
                      } 
                      if (sucursalUsuario === 2) {
                        stock = item.stockSucursalDos;
                      } 
                    } else {
                      Swal.fire({
                        icon: "error",
                        title: "ERROR",
                        text: "Algo salió mal al obtener el stock de la sucursal.",
                      });
                    }

                    return {
                      value: item.id,
                      label: `[${item.codigoIdentificacion}] ${item.nombreComun} ${item.nombreTecnico} ${item.descripcion} - $${item.precio}`,
                    };
                  })
                );
              },
            });
          },
          select: (event, ui) => {
            let linea = $("#lineas").html();
            let producto = ui.item.label;
            let descripcion = producto.split("]")[1].trim();
            descripcion = descripcion.split("-")[0];
            let precio = producto.split("-")[1];
            precio = precio.split("$")[1];
            let id = ui.item.value;
            let codigoIdentificacion = producto.split("[")[1].split("]")[0];

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
            lineasUtil.validarStockDisponible(id, 1);
          },
        });
      },
      error: function (error) {
        console.error("Error en la petición de datos al servidor: ", error);
      },
    });
  }

  obtenerSucursalUsuario();
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




// Obtén todos los elementos select con clase 'sucOrigen'
// let listaSucOrigen = document.querySelectorAll('.sucOrigen');
// console.log(`Lista sucOrigen: ${listaSucOrigen}`);
// for (let i = 0; i < listaSucOrigen.length; i++) {
//   let sucOrigen = listaSucOrigen[i]
//   console.log(`sucOrigen: ${sucOrigen}`);
//   let sucDestino = document.querySelector(".sucDestino");
//   if (sucOrigen.value === 'sucursalUno') {
//     sucDestino.textContent = 'sucursalDos';
//   } else {
//     sucDestino.textContent = 'sucursalUno';
//   }
// }

