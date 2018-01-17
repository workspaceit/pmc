<!-- <div id="wrapper">

<!-- Navigation -->
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" style="color: #f4e6fd;font-family: 'Exo', sans-serif;fview allont-size: 31px;font-weight: 500;" href="index2.html">
                <img style="display:inline;" width="140" src="<s:url value="/resources/images/logo2.png"/>" class="small-logo">
            </a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right" style="margin-top: 15px;background-color: #222">
                <li>
                    <a href="<c:url value="/admin/dashboard"/>">
                        <img src="<s:url value="/resources/images/dashboard.svg"/>" class="img-responsive w-25px">
                        <span class="visible-xs">Dashboard</span>
                    </a>
                </li>
                <li>
                    <a href="<c:url value="/admin/advertiser/add" />">
                        <img src="<s:url value="/resources/images/fa-buysellads.png"/>" class="img-responsive w-25px">
                        <span class="visible-xs">Advertisement</span>
                    </a>
                </li>
                <li>
                    <a href="<c:url value="/admin/event/add"/>">
                        <img src="<s:url value="/resources/images/add-event.svg"/>" class="img-responsive w-25px">
                        <span class="visible-xs">Create Event</span>
                    </a>
                </li>
                <li>
                    <a href="<c:url value="/admin/advertisement-prices"/>">
                        <img src="<s:url value="/resources/images/price.png"/>" class="img-responsive w-25px">
                        <span class="visible-xs">Pricing</span>
                    </a>
                </li>
                <li>
                    <a href="<c:url value="/admin/watermark/add" />">
                        <img src="<s:url value="/resources/images/watermark.png"/>" class="img-responsive w-25px">
                        <span class="visible-xs">Watermark Settings</span>
                    </a>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"> <i class="fa fa-plus-circle fa-2x f-size"></i><span class="visible-xs" style="margin-left:15px;">Dashboard</span> <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="<c:url value="/admin/user/add" />">Add a new Admin</a></li>
                        <li><a href="<c:url value="/admin/location/add" />">Add New Location</a></li>
                        <li><a href="<c:url value="/admin/venue/add" />"> Add New Venue</a></li>
                        <li><a href="<c:url value="/admin/photographer/add" />">Add New Photographer</a></li>
                        <li><a href="<c:url value="/admin/photographer/all" />">Photographers</a></li>
                        <li><a href="<c:url value="/admin/location/all" />">Locations</a></li>
                        <li><a href="<c:url value="/admin/user/all" />">Admins</a></li>
                        <li><a href="<c:url value="/admin/advertiser/all" />">Advertisers</a></li>
                        <li><a href="<c:url value="/admin/watermark/all" />">Watermarks</a></li>
                        <li><a href="<c:url value="/admin/venue/all" />">Venues</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" style="font-size:16px;color:#fff;" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><i class="fa fa-user"></i><span style="margin-left:15px;"> John Smith </span><span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="<c:url value="/admin/user/profile" />"><i class="fa fa-fw fa-user"></i>Profile</a></li>
                        <li><a href="<c:url value="/logout" />"><i class="fa fa-fw fa-power-off"></i>Log Out</a></li>
                    </ul>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>