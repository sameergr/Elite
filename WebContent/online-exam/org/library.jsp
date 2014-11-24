<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:include page="include/header1.jsp"></jsp:include>
	<div class="container" id="main-content content">
		<section>
			<div class="library-wrp">
				<h1>Library</h1>
				<div class="group">
					<div class="col spn_1_3">
						<div class="white_bg styledwidth">
                            <select name="styled-dropdown" class="styled">
                                <option value="">All Categories</option>
                                <option value="">Leadership Training</option>
                                <option value="">Safety</option>
                                <option value="">New Employees</option>
                                <option value="">Software</option>
                            </select>
						</div>

						<div class="white_bg">
							<div class="ttl">Question <a hre=""><img src="../images/add-icon.png" class="right"/></a></div>
                            <ul class="wht-menu">
    						    <li>Etiam scelerisque dui.</li>
                                <li>Multiple Correct Question</li>
                                <li>True & False Question</li>
                                <li>Yes or No Question</li>
                                <li>Open Text Question</li>
                                <li>Multiple Choice Question</li>
                                <li>Multiple Correct Question</li>
                                <li>True & False Question</li>
                                <li>Yes or No Question</li>
                                <li>Open Text Question</li>
                                <li>Multiple Choice Question</li>
                                <li>Multiple Correct Question</li>
                                <li>True & False Question</li>
                                <li>Yes or No Question</li>
                                <li>Open Text Question</li>
                            </ul>
                            <div class="ftr align-right"> <a hre=""><img src="../images/delete-icon.png" /></a></div>
						</div>
					</div>
					<div class="col spn_2_3">
						<div class="white_bg">
                            <div class="ttl">Add Your Question</div>
                            <div class="group">
                                <div class="col spn_1_4">
                                    Question
                                </div>
                                <div class="col spn_3_4">
                                    <textarea rows="2" title="This is a test Assessment with course for getting started."></textarea>
                                </div>
                            </div>

                             <div class="group">
                                <div class="col spn_1_4">
                                    Help/ Reference
                                </div>
                                <div class="col spn_3_4">
                                    <textarea rows="2" title="This is a test Assessment with course for getting started."></textarea>
                                </div>
                            </div>
                            <div class="group">
                                <div class="col spn_1_3">
                                    <div class="form-item">
                                        <div class="form-label">Points</div>
                                        <div class="form-value">
                                            <input type="text" value=""/>
                                        </div>
                                    </div>
                                </div>
                                <div class="col spn_1_3">
                                    <div class="form-item">
                                        <div class="form-label">Tries</div>
                                        <div class="form-value">
                                            <select name="styled-dropdown" class="style">
                                                <option value="">All Categories</option>
                                                <option value="">Leadership Training</option>
                                                <option value="">Safety</option>
                                                <option value="">New Employees</option>
                                                <option value="">Software</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="col spn_1_3">
                                    <div class="form-item">
                                        <div class="form-label">Level</div>
                                        <div class="form-value">
                                            <select name="styled-dropdown" class="styled">
                                                <option value="">Easy</option>
                                                <option value="">Medium</option>
                                                <option value="">Hard</option>
                                                <option value="">Advance</option>
                                                <option value="">Expert</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <%-- <jsp:include page="include/question-type.jsp"></jsp:include> --%>
                        </div>
                    </div>


					</div>
				</div>
			</div>
		</section>
	</div>

    <script type="text/javascript">
        tinyMCE.init({
        // General options
        selector: "textarea#sessioninfo",
        theme: "modern", 
        width: 585,
        height: 100,
        mode : "textareas",
        theme : "advanced",
        plugins : "pagebreak,style,layer,table,save,advhr,advimage,advlink,emotions,iespell,inlinepopups,insertdatetime,preview,media,searchreplace,print,contextmenu,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras,template,wordcount,advlist,autosave",

        // Theme options
        theme_advanced_buttons1 : "save,newdocument,|,bold,italic,underline,strikethrough ,|,forecolor,backcolor,|,justifyleft,justifycenter,justifyright,justifyfull,",
        theme_advanced_buttons2 : "styleselect,formatselect,fontselect,fontsizeselect",
        theme_advanced_buttons3 : "",
        theme_advanced_buttons4 : "",
        theme_advanced_toolbar_location : "top",
        theme_advanced_toolbar_align : "left",
        theme_advanced_statusbar_location : "bottom",
        theme_advanced_resizing : true,

        // Example content CSS (should be your site CSS)
        content_css : "css/content.css",

        // Drop lists for link/image/media/template dialogs
        template_external_list_url : "lists/template_list.js",
        external_link_list_url : "lists/link_list.js",
        external_image_list_url : "lists/image_list.js",
        media_external_list_url : "lists/media_list.js",

        // Style formats
        style_formats : [
            {title : 'Bold text', inline : 'b'},
            {title : 'Red text', inline : 'span', styles : {color : '#ff0000'}},
            {title : 'Red header', block : 'h1', styles : {color : '#ff0000'}},
            {title : 'Example 1', inline : 'span', classes : 'example1'},
            {title : 'Example 2', inline : 'span', classes : 'example2'},
            {title : 'Table styles'},
            {title : 'Table row 1', selector : 'tr', classes : 'tablerow1'}
        ],

        formats : {
            alignleft : {selector : 'p,h1,h2,h3,h4,h5,h6,td,th,div,ul,ol,li,table,img', classes : 'left'},
            aligncenter : {selector : 'p,h1,h2,h3,h4,h5,h6,td,th,div,ul,ol,li,table,img', classes : 'center'},
            alignright : {selector : 'p,h1,h2,h3,h4,h5,h6,td,th,div,ul,ol,li,table,img', classes : 'right'},
            alignfull : {selector : 'p,h1,h2,h3,h4,h5,h6,td,th,div,ul,ol,li,table,img', classes : 'full'},
            bold : {inline : 'span', 'classes' : 'bold'},
            italic : {inline : 'span', 'classes' : 'italic'},
            underline : {inline : 'span', 'classes' : 'underline', exact : true},
            strikethrough : {inline : 'del'}
        },

        // Replace values for the template plugin
        template_replace_values : {
            username : "Some User",
            staffid : "991234"
        }
        });
    </script>
	

<jsp:include page="include/footer.jsp"></jsp:include>