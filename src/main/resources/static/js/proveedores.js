
// BUSQUEDA DE PROVEEDORES
document.addEventListener("DOMContentLoaded", function () {
    const searchBar = document.getElementById("search-bar");
    const searchInput = searchBar.querySelector("input");
    const tableRows = document.querySelectorAll("tbody tr");
  
    searchInput.addEventListener("input", function () {
      const searchTerm = this.value.trim().toLowerCase();
  
      tableRows.forEach(function (row) {
        const providerName = row.cells[2].innerText.toLowerCase();
        const providerCUIT = row.cells[1].innerText.toLowerCase();
        const showRow = providerName.includes(searchTerm) || providerCUIT.includes(searchTerm);
        row.style.display = showRow ? "" : "none";
      });
    });
  });
  