// BUSQUEDA DE ARTICULOS
document.addEventListener("DOMContentLoaded", function () {
  const searchBar = document.getElementById("search-bar");
  const searchInput = searchBar.querySelector("input");
  const tableRows = document.querySelectorAll("tbody tr");

  searchInput.addEventListener("input", function () {
    const searchTerm = this.value.trim().toLowerCase();

    tableRows.forEach(function (row) {
      const articleName = row.cells[3].innerText.toLowerCase();
      const articleCode = row.cells[1].innerText.toLowerCase();
      const showRow = articleName.includes(searchTerm) || articleCode.includes(searchTerm);
      row.style.display = showRow ? "" : "none";
    });
  });
});

