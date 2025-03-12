$(document).ready(function () {
    // Manejo de clics en los enlaces del menú para cargar diferentes páginas
    $(".menu-link").click(function () {
        var pagina = $(this).data("pagina");
        $("#contenido").load("PageController?page=" + pagina, function () {
            if (pagina === "reservations") {
                fetchReservations(); // Cargar las reservas si la página es "reservations"
            }

            if (pagina === "createReservation") {
                let today = new Date().toISOString().split("T")[0];
                document.getElementById("reservation_date").setAttribute("min", today); // Establecer la fecha mínima para evitar reservas en el pasado
            }
        });
    });

    // Establecer la fecha mínima en el campo de selección de fecha
    let today = new Date().toISOString().split("T")[0];
    document.getElementById("reservation_date").setAttribute("min", today);

    // Función para obtener las reservas desde el servidor y mostrarlas en la tabla
    function fetchReservations() {
        $.ajax({
            type: "GET",
            url: "CoworkingReservationsService",
            cache: false,
            success: function (response) {
                if ($.fn.DataTable.isDataTable("#table")) {
                    let table = $("#table").DataTable();
                    table.clear(); // Limpiar la tabla antes de actualizarla

                    response.forEach((reservation, index) => {
                        table.row.add([
                            index + 1,
                            reservation.userName,
                            reservation.reservationDate,
                            reservation.workspace,
                            reservation.reservationDuration + ` horas`,
                            `<a class="delete" title="Eliminar" data-toggle="tooltip" data-id="` + reservation.id + `"><i class="material-icons">&#xE872;</i></a>`
                        ]);
                    });
                    table.draw(); // Renderizar la tabla con los nuevos datos
                } else {
                    // Inicializar la tabla con DataTables si aún no está creada
                    $("#table").DataTable({
                        data: response.map((reservation, index) => [
                            index + 1,
                            reservation.userName,
                            reservation.reservationDate,
                            reservation.workspace,
                            reservation.reservationDuration + ` horas`,
                            `<a class="delete" title="Eliminar" data-toggle="tooltip" data-id="` + reservation.id + `"><i class="material-icons">&#xE872;</i></a>`
                        ]),
                        pageLength: 5,
                        lengthMenu: [[5, 10, 25, 50], [5, 10, 25, 50]],
                        language: {
                            lengthMenu: "Mostrar _MENU_ registros por página",
                            zeroRecords: "No se encontraron registros",
                            info: "Mostrando página _PAGE_ de _PAGES_",
                            infoEmpty: "No hay registros disponibles",
                            infoFiltered: "(filtrado de _MAX_ registros en total)",
                            search: "Buscar:",
                            paginate: {
                                first: "Primero",
                                last: "Último",
                                next: "Siguiente",
                                previous: "Anterior"
                            }
                        }
                    });
                }
            },
            error: function () {
                showToast("Error al cargar los datos.", "error");
            }
        });
    }

    // Cargar las reservas al iniciar la página
    fetchReservations();

    // Manejar la eliminación de una reserva
    $(document).on("click", ".delete", function () {
        var id = $(this).attr("data-id");

        Swal.fire({
            title: "¿Estás seguro?",
            text: "Esta acción eliminará la reserva permanentemente.",
            icon: "warning",
            showCancelButton: true,
            confirmButtonColor: "#d33",
            cancelButtonColor: "#3085d6",
            confirmButtonText: "Sí, eliminar",
            cancelButtonText: "Cancelar"
        }).then((result) => {
            if (result.isConfirmed) {
                $.ajax({
                    type: "POST",
                    url: "CoworkingReservationsService",
                    data: {
                        action: "delete",
                        id: id
                    },
                    success: function () {
                        setTimeout(fetchReservations, 500); // Actualizar la lista después de eliminar

                        Toastify({
                            text: "Reserva eliminada con éxito.",
                            duration: 3000,
                            close: true,
                            gravity: "top",
                            position: "right",
                            backgroundColor: "#28a745",
                        }).showToast();
                    },
                    error: function () {
                        showToast("Error al eliminar la reserva.", "error");
                    }
                });
            }
        });
    });

    // Manejar el envío del formulario de reserva
    $(document).on("submit", "#reservationForm", function (event) {
        event.preventDefault(); // Evitar la recarga de la página al enviar el formulario

        let username = $("input[name='userName']").val();
        let reservationDate = $("input[name='reservationDate']").val();
        let workspace = $("select[name='workspace']").val();
        let duration = $("input[name='reservationDuration']").val();

        // Validar que los campos no estén vacíos
        if (!username || !reservationDate || !workspace || !duration) {
            showToast("Por favor, completa todos los campos.", "error");
            return;
        }

        // Enviar la solicitud de creación de reserva al servidor
        $.ajax({
            type: "POST",
            url: "CoworkingReservationsService",
            data: {
                action: "create",
                userName: username,
                reservationDate: reservationDate,
                workspace: workspace,
                reservationDuration: duration
            },
            success: function (response) {
                showToast("Reserva creada con éxito.", "success");
                $("#reservationForm")[0].reset(); // Limpiar el formulario tras el envío
                fetchReservations(); // Actualizar la lista de reservas
            },
            error: function () {
                showToast("Error al registrar la reserva. Inténtalo de nuevo.", "error");
            }
        });
    });

    // Función auxiliar para mostrar notificaciones
    function showToast(message, type) {
        Toastify({
            text: message,
            duration: 3000,
            close: true,
            gravity: "top",
            position: "right",
            backgroundColor: type === "success" ? "#28a745" : "#dc3545",
        }).showToast();
    }
});