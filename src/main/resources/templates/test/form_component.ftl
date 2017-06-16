<!DOCTYPE html><!--[if IE 8]> <html lang="en" class="ie8"> <![endif]--><!--[if IE 9]> <html lang="en" class="ie9"> <![endif]--><!--[if !IE]><!--> <html lang="en"> <!--<![endif]--><!-- BEGIN HEAD --><head>	<meta charset="utf-8" />	<title>Metronic | Form Stuff - Form Components</title>	<meta content="width=device-width, initial-scale=1.0" name="viewport" />	<meta content="" name="description" />	<meta content="" name="author" />	<!-- BEGIN GLOBAL MANDATORY STYLES -->	<link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>	<link href="/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>	<link href="/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>	<link href="/css/style-metro.css" rel="stylesheet" type="text/css"/>	<link href="/css/style.css" rel="stylesheet" type="text/css"/>	<link href="/css/style-responsive.css" rel="stylesheet" type="text/css"/>	<link href="/css/default.css" rel="stylesheet" type="text/css" id="style_color"/>	<link href="/css/uniform.default.css" rel="stylesheet" type="text/css"/>	<!-- END GLOBAL MANDATORY STYLES -->	<!-- BEGIN PAGE LEVEL STYLES -->	<link rel="stylesheet" type="text/css" href="/css/bootstrap-fileupload.css" />	<link rel="stylesheet" type="text/css" href="/css/jquery.gritter.css" />	<link rel="stylesheet" type="text/css" href="/css/chosen.css" />	<link rel="stylesheet" type="text/css" href="/css/select2_metro.css" />	<link rel="stylesheet" type="text/css" href="/css/jquery.tagsinput.css" />	<link rel="stylesheet" type="text/css" href="/css/clockface.css" />	<link rel="stylesheet" type="text/css" href="/css/bootstrap-wysihtml5.css" />	<link rel="stylesheet" type="text/css" href="/css/datepicker.css" />	<link rel="stylesheet" type="text/css" href="/css/timepicker.css" />	<link rel="stylesheet" type="text/css" href="/css/colorpicker.css" />	<link rel="stylesheet" type="text/css" href="/css/bootstrap-toggle-buttons.css" />	<link rel="stylesheet" type="text/css" href="/css/daterangepicker.css" />	<link rel="stylesheet" type="text/css" href="/css/datetimepicker.css" />	<link rel="stylesheet" type="text/css" href="/css/multi-select-metro.css" />	<link href="/css/bootstrap-modal.css" rel="stylesheet" type="text/css"/>	<!-- END PAGE LEVEL STYLES -->	<link rel="shortcut icon" href="media/image/favicon.ico" /></head><!-- END HEAD --><!-- BEGIN BODY --><body class="page-header-fixed">	<!-- BEGIN CONTAINER -->	<div class="page-container row-fluid">		<!-- END SIDEBAR -->		<!-- BEGIN PAGE -->  		<div class="page-content">			<!-- BEGIN PAGE CONTAINER-->			<div class="container-fluid">                <div class="row-fluid">                    <div class="span12">                        <!-- BEGIN SAMPLE FORM PORTLET-->                        <div class="portlet box blue">                            <div class="portlet-title">                                <div class="caption"><i class="icon-reorder"></i>Sample Form</div>                                <div class="tools">                                    <a href="javascript:;" class="collapse"></a>                                    <a href="#portlet-config" data-toggle="modal" class="config"></a>                                    <a href="javascript:;" class="reload"></a>                                    <a href="javascript:;" class="remove"></a>                                </div>                            </div>                            <div class="portlet-body form">                                <!-- BEGIN FORM-->                                <form action="#" class="form-horizontal">                                    <div class="control-group">                                        <label class="control-label">Custom Dropdown</label>                                        <div class="controls">                                            <select class="span6 chosen" data-placeholder="Choose a Category" tabindex="1">                                                <option value=""></option>                                                <option value="Category 1">Category 1</option>                                                <option value="Category 2">Category 2</option>                                                <option value="Category 3">Category 5</option>                                                <option value="Category 4">Category 4</option>                                            </select>                                        </div>                                    </div>                                </form>                                <!-- END FORM-->                            </div>                        </div>                        <!-- END SAMPLE FORM PORTLET-->                    </div>                </div>			</div>			<!-- END PAGE CONTAINER-->		</div>		<!-- END PAGE -->  	</div>	<!-- END CONTAINER -->	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->	<!-- BEGIN CORE PLUGINS -->	<script src="/assect/jquery-1.10.1.min.js" type="text/javascript"></script>	<script src="/assect/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>	<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->	<script src="/assect/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>      	<script src="/assect/bootstrap.min.js" type="text/javascript"></script>	<!--[if lt IE 9]>	<script src="/assect/excanvas.min.js"></script>	<script src="/assect/respond.min.js"></script>  	<![endif]-->   	<script src="/assect/jquery.slimscroll.min.js" type="text/javascript"></script>	<script src="/assect/jquery.blockui.min.js" type="text/javascript"></script>  	<script src="/assect/jquery.cookie.min.js" type="text/javascript"></script>	<script src="/assect/jquery.uniform.min.js" type="text/javascript" ></script>	<!-- END CORE PLUGINS -->	<!-- BEGIN PAGE LEVEL PLUGINS -->	<script type="text/javascript" src="/assect/ckeditor.js"></script>  	<script type="text/javascript" src="/assect/bootstrap-fileupload.js"></script>	<script type="text/javascript" src="/assect/chosen.jquery.min.js"></script>	<script type="text/javascript" src="/assect/select2.min.js"></script>	<script type="text/javascript" src="/assect/wysihtml5-0.3.0.js"></script> 	<script type="text/javascript" src="/assect/bootstrap-wysihtml5.js"></script><#--	<script type="text/javascript" src="/assect/jquery.tagsinput.min.js"></script>	<script type="text/javascript" src="/assect/jquery.toggle.buttons.js"></script>	<script type="text/javascript" src="/assect/bootstrap-datepicker.js"></script>	<script type="text/javascript" src="/assect/bootstrap-datetimepicker.js"></script>	<script type="text/javascript" src="/assect/clockface.js"></script>	<script type="text/javascript" src="/assect/date.js"></script>--><#--	<script type="text/javascript" src="/assect/daterangepicker.js"></script>	<script type="text/javascript" src="/assect/bootstrap-colorpicker.js"></script>  	<script type="text/javascript" src="/assect/bootstrap-timepicker.js"></script>	<script type="text/javascript" src="/assect/jquery.inputmask.bundle.min.js"></script>   	<script type="text/javascript" src="/assect/jquery.input-ip-address-control-1.0.min.js"></script>	<script type="text/javascript" src="/assect/jquery.multi-select.js"></script>   -->	<#--<script src="/assect/bootstrap-modal.js" type="text/javascript" ></script>	<script src="/assect/bootstrap-modalmanager.js" type="text/javascript" ></script> -->	<!-- END PAGE LEVEL PLUGINS -->	<!-- BEGIN PAGE LEVEL SCRIPTS -->	<script src="/assect/app.js"></script>	<script src="/assect/form-components.js"></script>     	<!-- END PAGE LEVEL SCRIPTS -->	<script>		jQuery(document).ready(function() {       		   // initiate layout and plugins		   App.init();		  /* FormComponents.init();*/		});	</script>	<!-- END JAVASCRIPTS -->   <script type="text/javascript">  var _gaq = _gaq || [];  _gaq.push(['_setAccount', 'UA-37564768-1']);  _gaq.push(['_setDomainName', 'keenthemes.com']);  _gaq.push(['_setAllowLinker', true]);  _gaq.push(['_trackPageview']);  (function() {    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;    ga.src = ('https:' == document.location.protocol ? 'https://' : 'http://') + 'stats.g.doubleclick.net/dc.js';    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);  })();</script></body><!-- END BODY --></html>