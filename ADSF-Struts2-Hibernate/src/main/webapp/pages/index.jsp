<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
    <title>Reservas</title>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"   integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8=" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    <s:head />
    <style>
        .fade-carousel {
            position: relative;
            height: 100vh;
        }
        .fade-carousel .carousel-inner .item {
            height: 100vh;
        }
        .fade-carousel .carousel-indicators > li {
            margin: 0 2px;
            background-color: #f39c12;
            border-color: #f39c12;
            opacity: .7;
        }
        .fade-carousel .carousel-indicators > li.active {
            width: 10px;
            height: 10px;
            opacity: 1;
        }

        /********************************/
        /*          Hero Headers        */
        /********************************/
        .hero {
            position: absolute;
            top: 50%;
            left: 50%;
            z-index: 3;
            color: #fff;
            text-align: center;
            text-transform: uppercase;
            text-shadow: 1px 1px 0 rgba(0,0,0,.75);
            -webkit-transform: translate3d(-50%,-50%,0);
            -moz-transform: translate3d(-50%,-50%,0);
            -ms-transform: translate3d(-50%,-50%,0);
            -o-transform: translate3d(-50%,-50%,0);
            transform: translate3d(-50%,-50%,0);
        }
        .hero h1 {
            font-size: 6em;
            font-weight: bold;
            margin: 0;
            padding: 0;
        }

        .fade-carousel .carousel-inner .item .hero {
            opacity: 0;
            -webkit-transition: 2s all ease-in-out .1s;
            -moz-transition: 2s all ease-in-out .1s;
            -ms-transition: 2s all ease-in-out .1s;
            -o-transition: 2s all ease-in-out .1s;
            transition: 2s all ease-in-out .1s;
        }
        .fade-carousel .carousel-inner .item.active .hero {
            opacity: 1;
            -webkit-transition: 2s all ease-in-out .1s;
            -moz-transition: 2s all ease-in-out .1s;
            -ms-transition: 2s all ease-in-out .1s;
            -o-transition: 2s all ease-in-out .1s;
            transition: 2s all ease-in-out .1s;
        }

        /********************************/
        /*            Overlay           */
        /********************************/
        .overlay {
            position: absolute;
            width: 100%;
            height: 100%;
            z-index: 2;
            background-color: #080d15;
            opacity: .7;
        }

        /********************************/
        /*          Custom Buttons      */
        /********************************/
        .btn.btn-lg {padding: 10px 40px;}
        .btn.btn-hero,
        .btn.btn-hero:hover,
        .btn.btn-hero:focus {
            color: #f5f5f5;
            background-color: #1abc9c;
            border-color: #1abc9c;
            outline: none;
            margin: 20px auto;
        }

        /********************************/
        /*       Slides backgrounds     */
        /********************************/
        .fade-carousel .slides .slide-1,
        .fade-carousel .slides .slide-2,
        .fade-carousel .slides .slide-3 {
            height: 100vh;
            background-size: cover;
            background-position: center center;
            background-repeat: no-repeat;
        }
        .fade-carousel .slides .slide-1 {
            background-image: url(http://es.hoteldominebilbao.com/images/header.jpg);
        }
        .fade-carousel .slides .slide-2 {
            background-image: url(http://www.petitpalacearanabilbaohotel.com/uploads/galeriahoteles/hotel-bilbao-petit-palace-hotel-arana-bilbao-hidromasaje.jpg);
        }
        .fade-carousel .slides .slide-3 {
            background-image: url(http://fotos.elcorreodigital.com/201111/400m-sobre-bilbao.jpg);
        }

        /********************************/
        /*          Media Queries       */
        /********************************/
        @media screen and (min-width: 980px){
            .hero { width: 980px; }
        }
        @media screen and (max-width: 640px){
            .hero h1 { font-size: 4em; }
        }
        .navbar {
            margin-bottom: 0;
        }
    </style>
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
<div class="carousel fade-carousel slide" data-ride="carousel" data-interval="4000" id="bs-carousel">
    <!-- Overlay -->
    <div class="overlay"></div>

    <!-- Indicators -->
    <ol class="carousel-indicators">
        <li data-target="#bs-carousel" data-slide-to="0" class="active"></li>
        <li data-target="#bs-carousel" data-slide-to="1"></li>
        <li data-target="#bs-carousel" data-slide-to="2"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner">
        <div class="item slides active">
            <div class="slide-1"></div>
            <div class="hero">
                <hgroup>
                    <h1>El mejor hotel al mejor precio</h1>
                    <h3>Con las instalaciones más modernas y galadonado con 3 estrellas</h3>
                </hgroup>
            </div>
        </div>
        <div class="item slides">
            <div class="slide-2"></div>
            <div class="hero">
                <hgroup>
                    <h1>3 tipos de habitaciones</h1>
                    <h3>Para adaptarse a todo tipo de clientes</h3>
                </hgroup>
            </div>
        </div>
        <div class="item slides">
            <div class="slide-3"></div>
            <div class="hero">
                <hgroup>
                    <h1>Realiza tu reserva online</h1>
                    <h3>Disfruta de una estancia en Bilbao como nunca antes</h3>
                </hgroup>
            </div>
        </div>
    </div>
</div>
</body>
</html>
