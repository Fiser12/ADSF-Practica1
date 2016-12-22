<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
    <div class="alert alert-info">
        <strong>Atención!</strong> Guarde el Identificador de la reserva para poder acceder a ella en otras ocasiones
    </div>
    <h2>Comprobante de la Reserva y Desglose</h2>
    <div class="col-md-6">
            <div class="row form-group pull-right">
                <label>Id de la Reserva: </label>
                <s:property value="reserva.reservaId" />
            </div>
        </br>
        </br>
        </br>
            <div class="row form-group">
                <div class="col-md-6">
                    <label>Nombre: </label>
                    <s:property value="reserva.nombre" /> <s:property value="reserva.apellidos" />
                </div>
                <div class="col-md-6">
                    <label>Teléfono: </label>
                    <s:property value="reserva.telefono" />
                </div>
            </div>
            <div class="row form-group">
                <div class="col-md-6">
                    <label>Numero de Personas: </label>
                    <s:property value="reserva.numeroPersonas" />
                </div>
                <div class="col-md-6">
                    <label>Tipo de Servicio: </label>
                    <s:property value="reserva.tipoReserva" />
                </div>
            </div>
            <div class="row form-group">
                <div class="col-md-6">
                    <label>Fecha de Inicio: </label>
                    <s:date name="reserva.startTime" format="dd/MM/yyyy" />
                </div>
                <div class="col-md-6">
                    <label>Fecha de Fin: </label>
                    <s:date name="reserva.endTime" format="dd/MM/yyyy" />
                </div>
            </div>
            <hr/>
            <div class="row form-group pull-right">
                <label>Precio final: </label>
                <s:property value="reserva.precio" />
            </div>
    </div>
</div>

</body>
</html>
