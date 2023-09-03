let sucursalUsuario;
let stock = 0;
let listaProductos;
const lineasUtil = {
  incrementarCantidad: function (id, precio) {
    let cantidad = parseInt($(`#cantidad_${id}`).val());
    console.log(`Cantidad = ${cantidad}`);
    $(`#cantidad_${id}`).val(++cantidad);
    this.calcularSubtotal(id, precio, cantidad);
  },

  calcularSubtotal: function (id, precio, cantidad) {
    console.log(`Contenido de 'id'=${id}`);
    let stk = listaProductos.find((i) => i.id === id); // Busca el stock que coincide con el id del producto
    console.log(`Contenido de 'stk'=${stk}`);
    console.log(`stk.stockSucursalUno = ${stk.stockSucursalUno}`);
    console.log(`stk.stockSucursalDos = ${stk.stockSucursalDos}`);
    
    if (sucursalUsuario === 1 && cantidad > stk.stockSucursalUno) {
      Swal.fire({
        icon: "error",
        title: "Oops...",
        text: "No hay stock suficiente en Sucursal Sauzalito!",
      });
      $(`#cantidad_${id}`).val(stk.stockSucursalUno);
    } else if (sucursalUsuario === 2 && cantidad > stk.stockSucursalDos) {
      Swal.fire({
        icon: "error",
        title: "Oops...",
        text: "No hay stock suficiente en Sucursal Fontana!",
      });
      $(`#cantidad_${id}`).val(stk.stockSucursalDos);
    } else {
      console.log(`Cantidad = ${cantidad}`);
      $(`#subtotal_${id}`).html(
        (parseFloat(precio) * parseInt(cantidad)).toFixed(2)
      );
      this.calcularTotal();
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
  function obtenerSucursalUsuario() {
    $.ajax({
      url: "/ventas/obtener-sucursal-usuario",
      dataType: "json",
      success: function (data) {
        sucursalUsuario = data;
        console.log("usuario:", data);
        if (data = 2) {
          console.log("Sucursal usuario = Sucursal Fontana");
        }

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
            //Crear una línea
            let linea = $("#lineas").html();

            //Asignar valores a sus celdas
            let producto = ui.item.label;
            let descripcion = producto.split("]")[1].trim(); //let descripcion = producto.split('-')[0];
            descripcion = descripcion.split("-")[0];
            let precio = producto.split("-")[1];
            precio = precio.split("$")[1];
            let id = ui.item.value;
            let codigoIdentificacion = producto.split("[")[1].split("]")[0];

            // console.log(`Producto seleccionado: ${producto}`);

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

            // console.log(`id del producto antes de calcularSubtotal ${id}`);
            // console.log(`precio del producto antes de calcularSubtotal ${precio}`);
            lineasUtil.calcularSubtotal(id, precio, 1);
          },
        });
      },
      error: function (error) {
        console.error("Error en la petición de datos al servidor: ", error);
      },
    });
  }

  // Llamar a la función para obtener el rol del usuario al cargar la página
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

  debugger;

  // Asignar los arreglos a los campos ocultos
  $("#codigoIdentificacionInput").val(JSON.stringify(codigoIdentificacion));
  $("#cantidadInput").val(JSON.stringify(cantidad));
  $("#origenInput").val(JSON.stringify(origen));

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
