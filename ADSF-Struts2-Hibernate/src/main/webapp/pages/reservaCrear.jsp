<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>

<html>
<head>
    <title>Reservas</title>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"   integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8=" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <style>.label{
        color: #000;
    }</style>
    <s:head />
    <sx:head />
</head>
<body>
<nav class="navbar navbar-default" role="navigation">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="/index">Hotel</a>
    </div>
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
        <ul class="nav navbar-nav">
            <li class="active"><a href="/reservaCrear">Crear Reserva</a></li>
        </ul>
        <div class="col-lg-3 pull-right" style="margin-top: 9px;">
            <div class="input-group">
                <input type="text" class="form-control" placeholder="Buscar tu reserva por ID">
                <span class="input-group-btn">
                    <button class="btn btn-default" type="button">Busca!</button>
                </span>
            </div><!-- /input-group -->
        </div><!-- /.col-lg-6 -->
    </div>
</nav>
<div class="container-fluid">
    <h2>Crear una Reserva</h2>
    <div class="col-md-6">
        <s:form action="reservaCrearResponse" method="POST" theme="simple">
            <div class="form-group">
                <label>Nombre</label>
                <s:textfield name="reserva.nombre" cssClass="form-control"/>
                <label>Apellidos</label>
                <s:textfield  name="reserva.apellidos" label="Apellidos" cssClass="form-control"/>
            </div>
            <div class="form-group">
                <label>Telefono</label>
                <s:textfield name="reserva.telefono" label="Telefono" cssClass="form-control"/>
            </div>
            <div class="form-group">
                <label>Numero de Personas: </label>
                <s:textfield name="reserva.numeroPersonas" label="Numero de personas" cssClass="form-control" />
            </div>
            <div class="form-group">
                <label>Fecha de Inicio</label>
                <sx:datetimepicker name="reserva.startTime" displayFormat="dd-MMM-yyyy" value="%{'today'}"/>
                <label>Fecha de Fin</label>
                <sx:datetimepicker name="reserva.endTime" displayFormat="dd-MMM-yyyy" value="%{'today'}"/>
            </div>
            <div class="form-group">
                <label>Tipo de Servicio</label>
                <s:combobox  label="Tipo de Servicio" name="reserva.tipoReserva" list="{'Normal','Premium','Deluxe'}" headerValue="Elige una opción" emptyOption="false" cssClass="form-control"/>
            </div>
            <hr/>
            <s:submit cssClass="btn btn-default"/>
        </s:form>
    </div>
</div>
<script>
    $("#reservaCrearResponse_reserva_tipoReserva").hide();
    $("br").remove();
    $("select").addClass("form-control")
</script>
</body>
</html>
