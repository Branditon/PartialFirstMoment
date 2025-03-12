<%-- 
    Document   : createReservation
    Created on : Mar 11, 2025, 7:05:45 PM
    Author     : brandonescudero
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="signup-form">	
    <form id="reservationForm">
        <h2>Crear reserva</h2>
        <div class="form-group">
            <div class="input-group">
                <span class="input-group-addon"><i class="fa fa-user"></i></span>
                <input type="text" class="form-control" name="userName" placeholder="Nombre de usuario">
            </div>
        </div>
        <div class="form-group">
            <div class="input-group">
                <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                <input type="date" class="form-control" name="reservationDate" id="reservation_date">
            </div>
        </div>
        <div class="form-group">
            <div class="input-group">
                <span class="input-group-addon"><i class="fa fa-briefcase"></i></span>
                <select class="form-control" name="workspace">
                    <option value="" disabled selected>Selecciona un tipo de espacio</option>
                    <option value="Escritorio">Escritorio</option>
                    <option value="Sala de reuniones">Sala de reuniones</option>
                    <option value="Oficina privada">Oficina privada</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <div class="input-group">
                <span class="input-group-addon"><i class="fa fa-clock-o"></i></span>
                <input type="number" class="form-control no-arrows" name="reservationDuration" placeholder="Duración de la reserva (horas)" min="1">
            </div>
        </div>          
        <div class="form-group">
            <button type="submit" class="btn btn-primary btn-block btn-lg">Crear</button>
        </div>
    </form>
</div>
