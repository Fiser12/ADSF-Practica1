<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
    <title>Reservas</title>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"   integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8=" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <s:head />
</head>
<body>
<s:form action="addReserva">
    <s:textfield name="reserva.nombre" label="Nombre"/>
    <s:textfield name="reserva.apellidos" label="Apellidos"/>
    <s:textfield name="reserva.telefono" label="Telefono"/>
    <s:textfield name="reserva.numeroPersonas" label="Numero de personas"/>
    <s:textfield name="reserva.tipoReserva" label="Tipo de servicio"/>
    <s:submit/>
    <hr/>
</s:form>
<h2>All Reservas</h2>

<s:if test="reservaList.size() > 0">
    <table border="1px" cellpadding="8px">
        <tr>
            <th>Nombre</th>
            <th>Apellidos</th>
            <th>Telefono</th>
            <th>Numero de personas</th>
            <th>Tipo de reserva</th>
            <th>Fecha inicio</th>
            <th>Fecha fin</th>
            <th>Precio</th>
            <th>Pagado</th>
        </tr>
        <s:iterator value="reservaList" status="userStatus">
            <tr>
                <td><s:property value="nombre" /></td>
                <td><s:property value="apellidos" /></td>
                <td><s:property value="telefono" /></td>
                <td><s:property value="numeroPersonas" /></td>
                <td><s:property value="tipoReserva" /></td>
                <td><s:date name="startTime" format="dd/MM/yyyy" /></td>
                <td><s:date name="endTime" format="dd/MM/yyyy" /></td>
                <td><s:property value="precio" /></td>
                <td><s:property value="pagado" /></td>

            </tr>
        </s:iterator>
    </table>
</s:if>

</body>
<script>
    $( ".datepicker" ).datepicker();
</script>
</html>
