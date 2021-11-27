<!doctype html>
<html lang="en">

    <head>
        <meta charset="utf-8" />
        <title>Error 500 | Admiria - Responsive Bootstrap 4 Admin Dashboard</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta content="Premium Multipurpose Admin & Dashboard Template" name="description" />
        <meta content="Themesbrand" name="author" />
        <!-- App favicon -->
        <link rel="shortcut icon" href="assets/images/favicon.ico">

        <!-- Bootstrap Css -->
        <link href="assets/css/bootstrap.min.css" id="bootstrap-style" rel="stylesheet" type="text/css" />
        <!-- Icons Css -->
        <link href="assets/css/icons.min.css" rel="stylesheet" type="text/css" />
        <!-- App Css-->
        <link href="assets/css/app.min.css" id="app-style" rel="stylesheet" type="text/css" />

    </head>

    <body data-topbar="dark" data-layout="horizontal">

        <!-- Loader -->
        <div id="preloader"><div id="status"><div class="spinner"></div></div></div>

        <!-- Begin page -->
        <div id="layout-wrapper">

            <header id="page-topbar">
                <div class="navbar-header">
                    <div class="d-flex">
                        <!-- LOGO -->
                        <div class="navbar-brand-box">
                            <a href="index.html" class="logo logo-dark">
                                <span class="logo-sm">
                                    <img src="assets/images/logo.png" alt="" height="22">
                                </span>
                                <span class="logo-lg">
                                    <img src="assets/images/logo-dark.png" alt="" height="30">
                                </span>
                            </a>

                            <a href="index.html" class="logo logo-light">
                                <span class="logo-sm">
                                    <img src="assets/images/logo-light.png" alt="" height="22">
                                </span>
                                <span class="logo-lg">
                                    <img src="assets/images/logo-light.png" alt="" height="30">
                                </span>
                            </a>
                        </div>

                        <button type="button" class="btn btn-sm mr-2 font-size-24 d-lg-none header-item waves-effect waves-light" data-toggle="collapse" data-target="#topnav-menu-content">
                            <i class="mdi mdi-menu"></i>
                        </button>
           
                    </div>

                     <!-- Search input -->
                     <div class="search-wrap" id="search-wrap">
                        <div class="search-bar">
                            <input class="search-input form-control" placeholder="Search" />
                            <a href="#" class="close-search toggle-search" data-target="#search-wrap">
                                <i class="mdi mdi-close-circle"></i>
                            </a>
                        </div>
                    </div>

                    <div class="d-flex">

                        <div class="dropdown d-none d-lg-inline-block mr-2">
                            <button type="button" class="btn header-item toggle-search noti-icon waves-effect" data-target="#search-wrap">
                                <i class="mdi mdi-magnify"></i>
                            </button>
                        </div>

                        <div class="dropdown d-none d-lg-inline-block mr-2">
                            <button type="button" class="btn header-item noti-icon waves-effect" data-toggle="fullscreen">
                                <i class="mdi mdi-fullscreen"></i>
                            </button>
                        </div>

                        <div class="dropdown d-none d-md-block mr-2">
                            <button type="button" class="btn header-item waves-effect" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="font-size-16"> English </span> <img class="ml-2" src="assets/images/flags/us_flag.jpg" alt="Header Language" height="16">
                            </button>
                            <div class="dropdown-menu dropdown-menu-right">

                                <!-- item-->
                                <a href="javascript:void(0);" class="dropdown-item notify-item">
                                    <img src="assets/images/flags/germany_flag.jpg" alt="user-image" height="12"> <span class="align-middle"> German </span>
                                </a>

                                <!-- item-->
                                <a href="javascript:void(0);" class="dropdown-item notify-item">
                                    <img src="assets/images/flags/italy_flag.jpg" alt="user-image" height="12"> <span class="align-middle"> Italian </span>
                                </a>

                                <!-- item-->
                                <a href="javascript:void(0);" class="dropdown-item notify-item">
                                    <img src="assets/images/flags/french_flag.jpg" alt="user-image" height="12"> <span class="align-middle"> French </span>
                                </a>

                                <!-- item-->
                                <a href="javascript:void(0);" class="dropdown-item notify-item">
                                    <img src="assets/images/flags/spain_flag.jpg" alt="user-image" height="12"> <span class="align-middle"> Spanish </span>
                                </a>

                                <!-- item-->
                                <a href="javascript:void(0);" class="dropdown-item notify-item">
                                    <img src="assets/images/flags/russia_flag.jpg" alt="user-image" height="12"> <span class="align-middle"> Russian </span>
                                </a>
                            </div>
                        </div>

                        <div class="dropdown d-inline-block mr-2">
                            <button type="button" class="btn header-item noti-icon waves-effect" id="page-header-notifications-dropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="ion ion-md-notifications"></i>
                                <span class="badge badge-danger badge-pill">3</span>
                            </button>
                            <div class="dropdown-menu dropdown-menu-lg dropdown-menu-right p-0" aria-labelledby="page-header-notifications-dropdown">
                                <div class="p-3">
                                    <div class="row align-items-center">
                                        <div class="col">
                                            <h5 class="m-0 font-size-16"> Notification (3) </h5>
                                        </div>
                                    </div>
                                </div>
                                <div data-simplebar style="max-height: 230px;">
                                    <a href="" class="text-reset notification-item">
                                        <div class="media">
                                            <div class="avatar-xs mr-3">
                                                <span class="avatar-title bg-success rounded-circle font-size-16">
                                                    <i class="mdi mdi-cart-outline"></i>
                                                </span>
                                            </div>
                                            <div class="media-body">
                                                <h6 class="mt-0 font-size-15 mb-1">Your order is placed</h6>
                                                <div class="font-size-12 text-muted">
                                                    <p class="mb-1">Dummy text of the printing and typesetting industry.</p>
                                                </div>
                                            </div>
                                        </div>
                                    </a>

                                    <a href="" class="text-reset notification-item">
                                        <div class="media">
                                            <div class="avatar-xs mr-3">
                                                <span class="avatar-title bg-warning rounded-circle font-size-16">
                                                    <i class="mdi mdi-message-text-outline"></i>
                                                </span>
                                            </div>
                                            <div class="media-body">
                                                <h6 class="mt-0 font-size-15 mb-1">New Message received</h6>
                                                <div class="font-size-12 text-muted">
                                                    <p class="mb-1">You have 87 unread messages</p>
                                                </div>
                                            </div>
                                        </div>
                                    </a>

                                    <a href="" class="text-reset notification-item">
                                        <div class="media">
                                            <div class="avatar-xs mr-3">
                                                <span class="avatar-title bg-info rounded-circle font-size-16">
                                                    <i class="mdi mdi-glass-cocktail"></i>
                                                </span>
                                            </div>
                                            <div class="media-body">
                                                <h6 class="mt-0 font-size-15 mb-1">Your item is shipped</h6>
                                                <div class="font-size-12 text-muted">
                                                    <p class="mb-1">It is a long established fact that a reader will</p>
                                                </div>
                                            </div>
                                        </div>
                                    </a>

                                </div>
                                <div class="p-2 border-top">
                                    <a class="btn btn-sm btn-link font-size-14 btn-block text-center" href="javascript:void(0)">
                                        View all
                                    </a>
                                </div>
                            </div>
                        </div>

            
                        <div class="dropdown d-inline-block">
                            <button type="button" class="btn header-item waves-effect" id="page-header-user-dropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <img class="rounded-circle header-profile-user" src="assets/images/users/avatar-1.jpg" alt="Header Avatar">
                            </button>
                            <div class="dropdown-menu dropdown-menu-right">
                                <!-- item-->
                                <a class="dropdown-item" href="#"><i class="bx bx-user font-size-16 align-middle mr-1"></i> Profile</a>
                                <a class="dropdown-item" href="#"><i class="bx bx-wallet font-size-16 align-middle mr-1"></i> My Wallet</a>
                                <a class="dropdown-item d-block" href="#"><span class="badge badge-success float-right">11</span><i class="bx bx-wrench font-size-16 align-middle mr-1"></i> Settings</a>
                                <a class="dropdown-item" href="#"><i class="bx bx-lock-open font-size-16 align-middle mr-1"></i> Lock screen</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item text-danger" href="#"><i class="bx bx-power-off font-size-16 align-middle mr-1 text-danger"></i> Logout</a>
                            </div>
                        </div>

                        <div class="dropdown d-inline-block">
                            <button type="button" class="btn header-item noti-icon right-bar-toggle waves-effect">
                                <i class="mdi mdi-spin mdi-settings"></i>
                            </button>
                        </div>
            
                    </div>
                </div>
    
            </header>
    
                <div class="topnav">
                    <div class="container-fluid">
                        <nav class="navbar navbar-light navbar-expand-lg topnav-menu">
    
                            <div class="collapse navbar-collapse" id="topnav-menu-content">
                                <ul class="navbar-nav">

                                    <li class="nav-item dropdown">
                                        <a class="nav-link dropdown-toggle arrow-none" href="#" id="topnav-dashboard" role="button"
                                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                            <i class="mdi mdi-view-dashboard mr-2"></i>Dashboard <div class="arrow-down"></div>
                                        </a>
                                        <div class="dropdown-menu dropdown-menu-left" aria-labelledby="topnav-dashboard">
                                            <a href="index.html" class="dropdown-item">Dashboard One</a>
                                            <a href="dashboard-2.html" class="dropdown-item">Dashboard Two</a>
                                        </div>
                                    </li>

    
                                    <li class="nav-item dropdown">
                                        <a class="nav-link dropdown-toggle arrow-none" href="#" id="topnav-ui kit" role="button"
                                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                            <i class="mdi mdi-buffer mr-2"></i>UI Kit <div class="arrow-down"></div>
                                        </a>

                                        <div class="dropdown-menu mega-dropdown-menu px-2 dropdown-mega-menu-xl dropdown-menu-left"
                                            aria-labelledby="topnav-ui kit">
                                            <div class="row">
                                                <div class="col-lg-4">
                                                    <div>
                                                       <a href="ui-buttons.html" class="dropdown-item">Buttons</a>
                                                       <a href="ui-colors.html" class="dropdown-item">Colors</a>
                                                       <a href="ui-cards.html" class="dropdown-item">Cards</a>
                                                       <a href="ui-tabs-accordions.html" class="dropdown-item">Tabs &amp; Accordions</a>
                                                       <a href="ui-modals.html" class="dropdown-item">Modals</a>
                                                       <a href="ui-images.html" class="dropdown-item">Images</a>
                                                       <a href="ui-alerts.html" class="dropdown-item">Alerts</a>
                                                       <a href="ui-progressbars.html" class="dropdown-item">Progress Bars</a>
                                                       <a href="ui-dropdowns.html" class="dropdown-item">Dropdowns</a>
                                                    </div>
                                                </div>
                                                <div class="col-lg-4">
                                                    <div>
                                                       <a href="ui-lightbox.html" class="dropdown-item">Lightbox</a>
                                                       <a href="ui-navs.html" class="dropdown-item">Navs</a>
                                                       <a href="ui-pagination.html" class="dropdown-item">Pagination</a>
                                                       <a href="ui-popover-tooltips.html" class="dropdown-item">Popover &amp; Tooltips</a>
                                                       <a href="ui-badge.html" class="dropdown-item">Badge</a>
                                                       <a href="ui-carousel.html" class="dropdown-item">Carousel</a>
                                                       <a href="ui-video.html" class="dropdown-item">Video</a>
                                                       <a href="ui-typography.html" class="dropdown-item">Typography</a>
                                                       <a href="ui-sweet-alert.html" class="dropdown-item">Sweet-Alert</a>
                                                    </div>
                                                </div>
                                                <div class="col-lg-4">
                                                    <div>
                                                       <a href="ui-grid.html" class="dropdown-item">Grid</a>
                                                       <a href="ui-animation.html" class="dropdown-item">Animation</a>
                                                       <a href="ui-highlight.html" class="dropdown-item">Highlight</a>
                                                       <a href="ui-rating.html" class="dropdown-item">Rating</a>
                                                       <a href="ui-nestable.html" class="dropdown-item">Nestable</a>
                                                       <a href="ui-alertify.html" class="dropdown-item">Alertify</a>
                                                       <a href="ui-rangeslider.html" class="dropdown-item">Range Slider</a>
                                                       <a href="ui-sessiontimeout.html" class="dropdown-item">Session Timeout</a>
                                                    </div>
                                                </div>
                                            </div>

                                        </div>
                                    </li>
    
                                    <li class="nav-item dropdown">
                                        <a class="nav-link dropdown-toggle arrow-none" href="#" id="topnav-components" role="button"
                                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                            <i class="mdi mdi-cube-outline mr-2"></i>Components <div class="arrow-down"></div>
                                        </a>
                                        <div class="dropdown-menu" aria-labelledby="topnav-components">

                                            <div class="dropdown">
                                                <a class="dropdown-item dropdown-toggle arrow-none" href="#" id="topnav-email"
                                                    role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                    Email <div class="arrow-down"></div>
                                                </a>
                                                <div class="dropdown-menu" aria-labelledby="topnav-email">
                                                   <a href="email-inbox.html" class="dropdown-item">Inbox</a>
                                                   <a href="email-read.html" class="dropdown-item">Email Read</a>
                                                   <a href="email-compose.html" class="dropdown-item">Email Compose</a>
                                                </div>
                                            </div>

                                            <a href="widgets.html" class="dropdown-item">Widgets</a>


                                            <a href="calendar.html" class="dropdown-item">Calendar</a>


                               
                                            <div class="dropdown">
                                                <a class="dropdown-item dropdown-toggle arrow-none" href="#" id="topnav-forms"
                                                    role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                    Forms <div class="arrow-down"></div>
                                                </a>
                                                <div class="dropdown-menu" aria-labelledby="topnav-forms">
                                                   <a href="form-elements.html" class="dropdown-item">Form Elements</a>
                                                   <a href="form-validation.html" class="dropdown-item">Form Validation</a>
                                                   <a href="form-advanced.html" class="dropdown-item">Form Advanced</a>
                                                   <a href="form-wizard.html" class="dropdown-item">Form Wizard</a>
                                                   <a href="form-editors.html" class="dropdown-item">Form Editors</a>
                                                   <a href="form-uploads.html" class="dropdown-item">Form File Upload</a>
                                                   <a href="form-mask.html" class="dropdown-item">Form Mask</a>
                                                   <a href="form-summernote.html" class="dropdown-item">Summernote</a>
                                                   <a href="form-xeditable.html" class="dropdown-item">Form Xeditable</a>
                                                </div>
                                            </div>
                                
                                            <div class="dropdown">
                                                <a class="dropdown-item dropdown-toggle arrow-none" href="#" id="topnav-chart"
                                                    role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                    Chart <div class="arrow-down"></div>
                                                </a>
                                                <div class="dropdown-menu" aria-labelledby="topnav-chart">
                                                    <a href="charts-morris.html" class="dropdown-item">Morris Chart</a>
                                                    <a href="charts-chartist.html" class="dropdown-item">Chartist Chart</a>
                                                    <a href="charts-chartjs.html" class="dropdown-item">Chartjs Chart</a>
                                                    <a href="charts-flot.html" class="dropdown-item">Flot Chart</a>
                                                    <a href="charts-c3.html" class="dropdown-item">C3 Chart</a>
                                                    <a href="charts-sparkline.html" class="dropdown-item">Sparkline Chart</a>
                                                    <a href="charts-other.html" class="dropdown-item">Jquery Knob Chart</a>
                                                    <a href="charts-peity.html" class="dropdown-item">Peity Chart</a>
                                                </div>
                                            </div>
                                            <div class="dropdown">
                                                <a class="dropdown-item dropdown-toggle arrow-none" href="#" id="topnav-tables"
                                                    role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                    Tables <div class="arrow-down"></div>
                                                </a>
                                                <div class="dropdown-menu" aria-labelledby="topnav-tables">
                                                    <a href="tables-basic.html" class="dropdown-item">Basic Tables</a>
                                                    <a href="tables-datatable.html" class="dropdown-item">Data Table</a>
                                                    <a href="tables-responsive.html" class="dropdown-item">Responsive Table</a>
                                                    <a href="tables-editable.html" class="dropdown-item">Editable Table</a>
                                                </div>
                                            </div>
                                            <div class="dropdown">
                                                <a class="dropdown-item dropdown-toggle arrow-none" href="#" id="topnav-icons"
                                                    role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                    Icons <div class="arrow-down"></div>
                                                </a>
                                                <div class="dropdown-menu" aria-labelledby="topnav-icons">
                                                    <a href="icons-material.html" class="dropdown-item">Material Design</a>
                                                    <a href="icons-ion.html" class="dropdown-item">Ion Icons</a>
                                                    <a href="icons-fontawesome.html" class="dropdown-item">Font Awesome</a>
                                                    <a href="icons-themify.html" class="dropdown-item">Themify Icons</a>
                                                    <a href="icons-dripicons.html" class="dropdown-item">Dripicons</a>
                                                    <a href="icons-typicons.html" class="dropdown-item">Typicons Icons</a>
                                                    <a href="icons-weather.html" class="dropdown-item">Weather Icons</a>
                                                    <a href="icons-mobirise.html" class="dropdown-item">Mobirise Icons</a>
                                                </div>
                                            </div>

                                            <div class="dropdown">
                                                <a class="dropdown-item dropdown-toggle arrow-none" href="#" id="topnav-maps"
                                                    role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                    Maps <div class="arrow-down"></div>
                                                </a>
                                                <div class="dropdown-menu" aria-labelledby="topnav-maps">
                                                   <a href="maps-google.html" class="dropdown-item"> Google Map</a>
                                                    <a href="maps-vector.html" class="dropdown-item"> Vector Map</a>
                                                </div>
                                            </div>

                                            <div class="dropdown">
                                                <a class="dropdown-item dropdown-toggle arrow-none" href="#" id="topnav-email-templates"
                                                    role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                    Email Templates <div class="arrow-down"></div>
                                                </a>
                                                <div class="dropdown-menu" aria-labelledby="topnav-email-templates">
                                                    <a href="email-templates-basic.html" class="dropdown-item">Basic Action Email</a>
                                                    <a href="email-templates-alert.html" class="dropdown-item">Alert Email</a>
                                                    <a href="email-templates-billing.html" class="dropdown-item">Billing Email</a>
                                                </div>
                                            </div>
                                        </div>
                                    </li>

                                    <li class="nav-item dropdown">
                                        <a class="nav-link dropdown-toggle arrow-none" href="#" id="topnav-pages" role="button"
                                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                            <i class="mdi mdi-google-pages mr-2"></i>Pages <div class="arrow-down"></div>
                                        </a>

                                        <div class="dropdown-menu mega-dropdown-menu px-2 dropdown-mega-menu-xl dropdown-menu-left"
                                            aria-labelledby="topnav-pages">
                                            <div class="row">
                                                <div class="col-lg-4">
                                                    <div>
                                                       <a href="pages-login.html" class="dropdown-item">Login</a>
                                                       <a href="pages-register.html" class="dropdown-item">Register</a>
                                                       <a href="pages-recoverpw.html" class="dropdown-item">Recover Password</a>
                                                       <a href="pages-lock-screen.html" class="dropdown-item">Lock Screen</a>
                                                       <a href="pages-login-2.html" class="dropdown-item">Login 2</a>
                                                       <a href="pages-register-2.html" class="dropdown-item">Register 2</a>
                                                       <a href="pages-recoverpw-2.html" class="dropdown-item">Recover Password 2</a>
                                                    </div>
                                                </div>
                                                <div class="col-lg-4">
                                                    <div>
                                                        <a href="pages-lock-screen-2.html" class="dropdown-item">Lock Screen 2</a>
                                                        <a href="pages-timeline.html" class="dropdown-item">Timeline</a>
                                                        <a href="pages-invoice.html" class="dropdown-item">Invoice</a>
                                                        <a href="pages-directory.html" class="dropdown-item">Directory</a>
                                                        <a href="pages-blank.html" class="dropdown-item">Blank Page</a>
                                                        <a href="pages-404.ftl" class="dropdown-item">Error 404</a>
                                                        <a href="pages-500.ftl" class="dropdown-item">Error 500</a>
                                                    </div>
                                                </div>
                                                <div class="col-lg-4">
                                                    <div>
                                                        <a href="pages-pricing.html" class="dropdown-item">Pricing</a>
                                                        <a href="pages-gallery.html" class="dropdown-item">Gallery</a>
                                                        <a href="pages-maintenance.html" class="dropdown-item">Maintenance</a>
                                                        <a href="pages-coming-soon.html" class="dropdown-item">Coming Soon</a>
                                                        <a href="faq.html" class="dropdown-item">FAQ</a>
                                                        <a href="contact.html" class="dropdown-item">Contact</a>
                                                    </div>
                                                </div>
                                            </div>

                                        </div>
                                    </li>

                                    <li class="nav-item dropdown">
                                        <a class="nav-link dropdown-toggle arrow-none" href="#" id="topnav-ecommerce" role="button"
                                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                            <i class="mdi mdi-cart-outline mr-2"></i>Ecommerce <div class="arrow-down"></div>
                                        </a>
                                        <div class="dropdown-menu dropdown-menu-left" aria-labelledby="topnav-ecommerce">
                                            <a href="ecommerce-product-list.html" class="dropdown-item">Product List</a>
                                            <a href="ecommerce-product-grid.html" class="dropdown-item">Product Grid</a>
                                            <a href="ecommerce-order-history.html" class="dropdown-item">Order History</a>
                                            <a href="ecommerce-customers.html" class="dropdown-item">Customers</a>
                                            <a href="ecommerce-product-edit.html" class="dropdown-item">Product Edit</a>
                                        </div>
                                    </li>
                        
    
                        
                                    <li class="nav-item dropdown">
                                        <a class="nav-link dropdown-toggle arrow-none" href="#" id="topnav-layout" role="button"
                                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                            <i class="mdi mdi-movie-filter-outline mr-2"></i>Layouts <div class="arrow-down"></div>
                                        </a>
                                        <div class="dropdown-menu dropdown-menu-right" aria-labelledby="topnav-layout">
                                            <a href="layouts-vertical.html" class="dropdown-item">Vertical</a>
                                            <a href="layouts-boxed-width.html" class="dropdown-item">Boxed width</a>
                                        </div>
                                    </li>
    
                                </ul>
                            </div>
                        </nav>
                    </div>
                </div>

 



            <!-- ============================================================== -->
            <!-- Start right Content here -->
            <!-- ============================================================== -->
            <div class="main-content">

                <div class="page-content">
                    <div class="container-fluid">

                        <!-- start page title -->
                        <div class="row">
                            <div class="col-12">
                                <div class="page-title-box d-flex align-items-center justify-content-between">
                                    <h4 class="mb-0 font-size-18">Error 500 </h4>

                                    <div class="page-title-right">
                                        <ol class="breadcrumb m-0">
                                            <li class="breadcrumb-item"><a href="javascript: void(0);">Pages</a></li>
                                            <li class="breadcrumb-item active">Error 500</li>
                                        </ol>
                                    </div>
                                    
                                </div>
                            </div>
                        </div>     
                        <!-- end page title -->

                        <div class="row">
                            <div class="col-md-6 offset-md-3 mx-auto">
                                <div class="card">
                                    <div class="card-body">
                                        <div class="ex-page-content text-center">
                                            <h1 class="">500</h1>
                                            <h3 class="">Internal Server Error</h3><br>
                                            <a class="btn btn-primary mb-5 waves-effect waves-light" href="index.html">Back to Dashboard</a>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>

                        

                    </div> <!-- container-fluid -->
                </div>
                <!-- End Page-content -->

                
                <footer class="footer">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-sm-6">
                                2017 - 2020 © Admiria.
                            </div>
                            <div class="col-sm-6">
                                <div class="text-sm-right d-none d-sm-block">
                                    Crafted with <i class="mdi mdi-heart text-danger"></i> by Themesbrand
                                </div>
                            </div>
                        </div>
                    </div>
                </footer>
            </div>
            <!-- end main content-->

        </div>
        <!-- END layout-wrapper -->

        <!-- Right Sidebar -->
        <div class="right-bar">
            <div data-simplebar class="h-100">
                <div class="rightbar-title px-3 py-4">
                    <a href="javascript:void(0);" class="right-bar-toggle float-right">
                        <i class="mdi mdi-close noti-icon"></i>
                    </a>
                    <h5 class="m-0">Settings</h5>
                </div>

                <!-- Settings -->
                <hr class="mt-0" />
                <h6 class="text-center mb-0">Choose Layouts</h6>

                <div class="p-4">
                    <div class="mb-2">
                        <img src="assets/images/layouts/layout-1.jpg" class="img-fluid img-thumbnail" alt="">
                    </div>
                    <div class="custom-control custom-switch mb-3">
                        <input type="checkbox" class="custom-control-input theme-choice" id="light-mode-switch" checked />
                        <label class="custom-control-label" for="light-mode-switch">Light Mode</label>
                    </div>
    
                    <div class="mb-2">
                        <img src="assets/images/layouts/layout-2.jpg" class="img-fluid img-thumbnail" alt="">
                    </div>
                    <div class="custom-control custom-switch mb-3">
                        <input type="checkbox" class="custom-control-input theme-choice" id="dark-mode-switch" data-bsStyle="assets/css/bootstrap-dark.min.css" 
                            data-appStyle="assets/css/app-dark.min.css" />
                        <label class="custom-control-label" for="dark-mode-switch">Dark Mode</label>
                    </div>
    
                    <div class="mb-2">
                        <img src="assets/images/layouts/layout-3.jpg" class="img-fluid img-thumbnail" alt="">
                    </div>
                    <div class="custom-control custom-switch mb-5">
                        <input type="checkbox" class="custom-control-input theme-choice" id="rtl-mode-switch" data-appStyle="assets/css/app-rtl.min.css" />
                        <label class="custom-control-label" for="rtl-mode-switch">RTL Mode</label>
                    </div>

            
                </div>

            </div> <!-- end slimscroll-menu-->
        </div>
        <!-- /Right-bar -->

        <!-- Right bar overlay-->
        <div class="rightbar-overlay"></div>

        <!-- JAVASCRIPT -->
        <script src="assets/libs/jquery/jquery.min.js"></script>
        <script src="assets/libs/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="assets/libs/metismenu/metisMenu.min.js"></script>
        <script src="assets/libs/simplebar/simplebar.min.js"></script>
        <script src="assets/libs/node-waves/waves.min.js"></script>

        <script src="assets/js/app.js"></script>

    </body>
</html>
