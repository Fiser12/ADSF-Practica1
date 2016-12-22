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
            <s:form action="getReservaByIdParameter" method="POST" theme="simple">
                <div class="input-group">
                    <s:textfield name="buscar" cssClass="form-control"/>
                    <span class="input-group-btn">
                        <s:submit cssClass="btn btn-default"/>
                    </span>
                </div><!-- /input-group -->
            </s:form>
        </div><!-- /.col-lg-6 -->
    </div>
</nav>
<div class="container-fluid">
    <div class="alert alert-danger">
        <strong>Error!</strong> ID incorrecto, no corresponde a una reserva
    </div>
</div>

</body>
</html>
