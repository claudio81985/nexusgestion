let permisoUsuario;
let stock = {};
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
    
    if (
      (permisoUsuario === "ROLE_SUCURSALUNO" || permisoUsuario === "ROLE_EMPLEADO") &&
      cantidad > stk.stockSucursalUno
    ) {
      Swal.fire({
        icon: "error",
        title: "Oops...",
        text: "No hay stock suficiente en Sucursal Sauzalito!",
      });
      $(`#cantidad_${id}`).val(stk.stockSucursalUno);
    } else if (
      permisoUsuario === "ROLE_SUCURSALDOS" &&
      cantidad > stk.stockSucursalDos
    ) {
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
                    if (permisoUsuario === "ROLE_SUCURSALUNO" || permisoUsuario === "ROLE_EMPLEADO") {
                      stock = item.stockSucursalUno;
                    } else if (permisoUsuario === "ROLE_SUCURSALDOS") {
                      stock = item.stockSucursalDos;
                    } else {
                      stock = item.stockGeneral;
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
        console.error("Error al obtener el rol del usuario: ", error);
      },
    });
  }

  // Llamar a la función para obtener el rol del usuario al cargar la página
  obtenerRolUsuario();
});


$(document).ready(function () {
  // Encuentra el elemento span por su id
  var numeroVentaSpan = document.getElementById('numeroVentaSpan');

  // Realiza una petición AJAX para obtener el último número de venta
  var xhr = new XMLHttpRequest();
  xhr.open('GET', '/ventas/generar-numero-venta', true);
  xhr.onreadystatechange = function () {
    if (xhr.readyState === XMLHttpRequest.DONE) {
      if (xhr.status === 200) {
        var response = JSON.parse(xhr.responseText);
        // Actualiza el contenido del span con el nuevo número de venta
        numeroVentaSpan.textContent = response.numeroVenta;
      } else {
        console.error('Error al obtener el número de venta');
      }
    }
  };
  xhr.send();
});

$(document).ready(function () {
  function updateClock() {
    const now = new Date();
    const clockDateElement = document.getElementById('clockDate');
    const clockTimeElement = document.getElementById('clockTime');

    const formattedDate = now.toLocaleDateString('es-ES', {
      day: '2-digit',
      month: '2-digit',
      year: 'numeric'
    });

    const formattedTime = now.toLocaleTimeString('es-ES', {
      hour12: false,
      hour: '2-digit',
      minute: '2-digit',
      second: '2-digit'
    });

    clockDateElement.textContent = formattedDate;
    clockTimeElement.textContent = formattedTime;
  }

  // Llama a la función para actualizar el reloj inicialmente
  updateClock();

  // Actualiza el reloj cada segundo (1000 milisegundos)
  setInterval(updateClock, 1000);
});