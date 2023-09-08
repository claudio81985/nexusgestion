function guardarCategoria() {
    // Obtén el valor del campo de entrada
    let nombreCategoria = document.getElementById("nuevaCat").value;
  
    // Crea un objeto con los datos de la nueva categoría
    let categoriaData = {
      nombre: nombreCategoria,
      // Otros campos de la categoría si los tienes
    };
  
    // Realiza la solicitud AJAX para guardar la categoría
    $.ajax({
      type: "POST",
      url: "/categorias/guardar", // Reemplaza con la ruta de tu controlador
      data: JSON.stringify(categoriaData),
      contentType: "application/json; charset=utf-8",
      dataType: "json",
      success: function (response) {
        // La categoría se ha guardado con éxito
        alert("Categoría guardada con éxito");
      },
      error: function (xhr, status, error) {
        // Ocurrió un error al guardar la categoría
        alert("Error al guardar la categoría: " + error);
      },
    });
  }
  
  // Agrega un evento click al botón para llamar a la función guardarCategoria
//   document.getElementById("btnGuardarCategoria").addEventListener("click", guardarCategoria);
  