$(function() {
	
	//===== File manager =====//	
	
	var elf = $('#fileManager').elfinder({
		url : 'php/connector.php',  // connector URL (REQUIRED)
		uiOptions : {
			// toolbar configuration
			toolbar : [
				['back', 'forward'],
				['info'],
				['quicklook'],
				['search']
			]
		},
		contextmenu : {
		  // Commands that can be executed for current directory
		  cwd : ['reload', 'delim', 'info'], 
		  // Commands for only one selected file
		  files : ['select', 'open']
		}
	}).elfinder('instance');	
	
	
	//===== ShowCode plugin for <pre> tag =====//

	$('.code').sourcerer('js html css php'); // Display all languages
	
	
	//===== Left navigation styling =====//
	
	$('.subNav li a.this').parent('li').addClass('activeli');


	//===== Login pic hover animation =====//
	
	$(".loginPic").hover(
		function() { 
		
		$('.logleft, .logback').animate({left:10, opacity:1},200); 
		$('.logright').animate({right:10, opacity:1},200); },
		
		function() { 
		$('.logleft, .logback').animate({left:0, opacity:0},200);
		$('.logright').animate({right:0, opacity:0},200); }
	);
	
	
	//===== Image gallery control buttons =====//
	
	$(".gallery ul li").hover(
		function() { $(this).children(".actions").show("fade", 200); },
		function() { $(this).children(".actions").hide("fade", 200); }
	);
	
	
	//===== Autocomplete =====//
	
	var availableTags = [ "ActionScript", "AppleScript", "Asp", "BASIC", "C", "C++", "Clojure", "COBOL", "ColdFusion", "Erlang", "Fortran", "Groovy", "Haskell", "Java", "JavaScript", "Lisp", "Perl", "PHP", "Python", "Ruby", "Scala", "Scheme" ];
	$( ".ac" ).autocomplete({
	source: availableTags
	});	


	//===== jQuery file tree =====//
	
	$('.filetree').fileTree({
        root: '../images/',
        script: 'php/jqueryFileTree.php',
        expandSpeed: 200,
        collapseSpeed: 200,
        multiFolder: true
    }, function(file) {
        alert(file);
    });
	
	
	//===== Sortable columns =====//
	
	$("table").tablesorter();
	
	
	//===== Resizable columns =====//
	
	$("#resize, #resize2").colResizable({
		liveDrag:true,
		draggingClass:"dragging" 
	});
	
	
	//===== Bootstrap functions =====//
	
	// Loading button
    $('#loading').click(function () {
        var btn = $(this)
        btn.button('loading')
        setTimeout(function () {
          btn.button('reset')
        }, 3000)
      });
	
	// Dropdown
	$('.dropdown-toggle').dropdown();
	
	
	//===== Animated progress bars (ID dependency) =====//
	
	var percent = $('#bar1').attr('title');
	$('#bar1').animate({width: percent},1000);
	
	var percent = $('#bar2').attr('title');
	$('#bar2').animate({width: percent},1000);

	var percent = $('#bar3').attr('title');
	$('#bar3').animate({width: percent},1000);

	var percent = $('#bar4').attr('title');
	$('#bar4').animate({width: percent},1000);

	var percent = $('#bar5').attr('title');
	$('#bar5').animate({width: percent},1000);

	var percent = $('#bar6').attr('title');
	$('#bar6').animate({width: percent},1000);
	
	var percent = $('#bar7').attr('title');
	$('#bar7').animate({width: percent},1000);
	
	var percent = $('#bar8').attr('title');
	$('#bar8').animate({width: percent},1000);
	
	var percent = $('#bar9').attr('title');
	$('#bar9').animate({width: percent},1000);

	var percent = $('#bar10').attr('title');
	$('#bar10').animate({width: percent},1000);


	//===== Fancybox =====//
	
	$(".lightbox").fancybox({
	'padding': 2
	});
	
	
	//===== Color picker =====//
	
	$('#cPicker').ColorPicker({
		color: '#e62e90',
		onShow: function (colpkr) {
			$(colpkr).fadeIn(500);
			return false;
		},
		onHide: function (colpkr) {
			$(colpkr).fadeOut(500);
			return false;
		},
		onChange: function (hsb, hex, rgb) {
			$('#cPicker div').css('backgroundColor', '#' + hex);
		}
	});
	
	$('#flatPicker').ColorPicker({flat: true});


	//===== Time picker =====//
	
	$('.timepicker').timeEntry({
		show24Hours: false, // 24 hours format
		showSeconds: false, // Show seconds?
		spinnerImage: 'images/elements/ui/spinner.png', // Arrows image
		spinnerSize: [19, 26, 0], // Image size
		spinnerIncDecOnly: true // Only up and down arrows
	});
	

	//===== Usual validation engine=====//

	$("#usualValidate").validate({
		rules: {
			firstname: "required",
			minChars: {
				required: true,
				minlength: 3
			},
			maxChars: {
				required: true,
				maxlength: 6
			},
			mini: {
				required: true,
				min: 3
			},
			maxi: {
				required: true,
				max: 6
			},
			range: {
				required: true,
				range: [6, 16]
			},
			emailField: {
				required: true,
				email: true
			},
			urlField: {
				required: true,
				url: true
			},
			dateField: {
				required: true,
				date: true
			},
			digitsOnly: {
				required: true,
				digits: true
			},
			enterPass: {
				required: true,
				minlength: 5
			},
			repeatPass: {
				required: true,
				minlength: 5,
				equalTo: "#enterPass"
			},
			customMessage: "required",
			topic: {
				required: "#newsletter:checked",
				minlength: 2
			},
			agree: "required"
		},
		messages: {
			customMessage: {
				required: "Bazinga! This message is editable",
			},
			agree: "Please accept our policy"
		}
	});
	
	
	//===== Validation engine =====//
	
	$("#validate").validationEngine();

	
	//===== iButtons =====//
	
	$('.on_off :checkbox, .on_off :radio').iButton({
		labelOn: "",
		labelOff: "",
		enableDrag: false 
	});
	
	$('.yes_no :checkbox, .yes_no :radio').iButton({
		labelOn: "On",
		labelOff: "Off",
		enableDrag: false
	});
	
	$('.enabled_disabled :checkbox, .enabled_disabled :radio').iButton({
		labelOn: "Enabled",
		labelOff: "Disabled",
		enableDrag: false
	});

	$('.all_target :checkbox, .all_target :radio').iButton({
		labelOn: "All Audiences",
		labelOff: "Target Audience",
		enableDrag: false
	});
	
	$('.published_unpublished :checkbox, .published_unpublished :radio').iButton({
		labelOn: "Published",
		labelOff: "Unpublished",
		enableDrag: false
	});
	
	
	
	//===== Notification boxes =====//
	
	$(".nNote").click(function() {
		$(this).fadeTo(200, 0.00, function(){ //fade
			$(this).slideUp(200, function() { //slide up
				$(this).remove(); //then remove from the DOM
			});
		});
	});	
	
	
	//===== Animate current li when closing button clicked =====//
	
	$(".remove").click(function() {
		$(this).parent('li').fadeTo(200, 0.00, function(){ //fade
			$(this).slideUp(200, function() { //slide up
				$(this).remove(); //then remove from the DOM
			});
		});
	});	
	
	
	
	//===== Check all checbboxes =====//
	
	$(".titleIcon input:checkbox").click(function() {
		var checkedStatus = this.checked;
		$("#checkAll tbody tr td:first-child input:checkbox").each(function() {
			this.checked = checkedStatus;
				if (checkedStatus == this.checked) {
					$(this).closest('.checker > span').removeClass('checked');
					$(this).closest('table tbody tr').removeClass('thisRow');
				}
				if (this.checked) {
					$(this).closest('.checker > span').addClass('checked');
					$(this).closest('table tbody tr').addClass('thisRow');
				}
		});
	});	
	
	$(function() {
    $('#checkAll tbody tr td:first-child input[type=checkbox]').change(function() {
        $(this).closest('tr').toggleClass("thisRow", this.checked);
		});
	});


	//===== File uploader =====//
	
	$("#uploader").pluploadQueue({
		runtimes : 'html5,html4',
		url : 'php/upload.php',
		max_file_size : '100kb',
		unique_names : true,
		filters : [
			{title : "Image files", extensions : "jpg,gif,png"}
		]
	});
	
	
	//===== Wizards =====//
	
	$("#wizard1").formwizard({
		formPluginEnabled: true, 
		validationEnabled: false,
		focusFirstInput : false,
		disableUIStyles : true,
	
		formOptions :{
			success: function(data){$("#status1").fadeTo(500,1,function(){ $(this).html("<span>Form was submitted!</span>").fadeTo(5000, 0); })},
			beforeSubmit: function(data){$("#w1").html("<span>Form was submitted with ajax. Data sent to the server: " + $.param(data) + "</span>");},
			resetForm: true
		}
	});
	
	$("#wizard2").formwizard({ 
		formPluginEnabled: true,
		validationEnabled: true,
		focusFirstInput : false,
		disableUIStyles : true,
	
		formOptions :{
			success: function(data){$("#status2").fadeTo(500,1,function(){ $(this).html("<span>Form was submitted!</span>").fadeTo(5000, 0); })},
			beforeSubmit: function(data){$("#w2").html("<span>Form was submitted with ajax. Data sent to the server: " + $.param(data) + "</span>");},
			dataType: 'json',
			resetForm: true
		},
		validationOptions : {
			rules: {
				bazinga: "required",
				email: { required: true, email: true }
			},
			messages: {
				bazinga: "Bazinga. This note is editable",
				email: { required: "Please specify your email", email: "Correct format is name@domain.com" }
			}
		}
	});
	
	$("#wizard3").formwizard({
		formPluginEnabled: false, 
		validationEnabled: false,
		focusFirstInput : false,
		disableUIStyles : true
	});
	
	$("#addnew_activity").formwizard({
	formPluginEnabled: false, 
	validationEnabled: false,
	focusFirstInput : false,
	disableUIStyles : true
	});
	
	
	//===== WYSIWYG editor =====//
	
	$("#editor").cleditor({
		width:"100%", 
		height:"250px",
		bodyStyle: "margin: 10px; font: 12px Arial,Verdana; cursor:text",
		useCSS:true
	});

	$("#editor2").cleditor({
		width:"100%", 
		height:"250px",
		bodyStyle: "margin: 10px; font: 12px Arial,Verdana; cursor:text",
		useCSS:true
	});

	$("#editor3").cleditor({
		width:"100%", 
		height:"250px",
		bodyStyle: "margin: 10px; font: 12px Arial,Verdana; cursor:text",
		useCSS:true
	});
	
	
	//===== Dual select boxes =====//
	
	$.configureBoxes();


	//===== Select2 dropdowns =====//

	$(".select").select2();
		
	$(".selectMultiple").select2();
		
	$("#loadingdata").select2({
		placeholder: "Enter at least 1 character",
        allowClear: true,
        minimumInputLength: 1,
        query: function (query) {
            var data = {results: []}, i, j, s;
            for (i = 1; i < 5; i++) {
                s = "";
                for (j = 0; j < i; j++) {s = s + query.term;}
                data.results.push({id: query.term + i, text: s});
            }
            query.callback(data);
        }
    });		
		
	$("#maxselect").select2({ maximumSelectionSize: 3 });
		
	$("#minselect").select2({
        minimumInputLength: 2,
		width: 'element'
    });
	
	$("#minselect2").select2({
        minimumInputLength: 2
    });
	
	$("#disableselect, #disableselect2").select2(
        "disable"
    );
	
	
	//===== Autotabs. Inline data rows =====//

	$('.onlyNums input').autotab_magic().autotab_filter('numeric');
	$('.onlyText input').autotab_magic().autotab_filter('text');
	$('.onlyAlpha input').autotab_magic().autotab_filter('alpha');
	$('.onlyRegex input').autotab_magic().autotab_filter({ format: 'custom', pattern: '[^0-9\.]' });
	$('.allUpper input').autotab_magic().autotab_filter({ format: 'alphanumeric', uppercase: true });
	
	
	//===== Masked input =====//
	
	$.mask.definitions['~'] = "[+-]";
	$(".maskDate").mask("99/99/9999",{completed:function(){alert("Callback when completed");}});
	$(".maskPhone").mask("(999) 999-9999");
	$(".maskPhoneExt").mask("(999) 999-9999? x99999");
	$(".maskIntPhone").mask("+33 999 999 999");
	$(".maskTin").mask("99-9999999");
	$(".maskSsn").mask("999-99-9999");
	$(".maskProd").mask("a*-999-a999", { placeholder: " " });
	$(".maskEye").mask("~9.99 ~9.99 999");
	$(".maskPo").mask("PO: aaa-999-***");
	$(".maskPct").mask("99%");
	
	
	//===== Tags =====//	
		
	$('.tags').tagsInput({width:'100%'});
	$('.tags1').tagsInput({width:'100%'});
	$('.tags2').tagsInput({width:'100%'});
	$('.tags3').tagsInput({width:'100%'});
	$('.tags4').tagsInput({width:'100%'});
	$('.tags5').tagsInput({width:'100%'});
	$('.tags6').tagsInput({width:'100%'});
	$('.tags7').tagsInput({width:'100%'});
	$('.tags8').tagsInput({width:'100%'});
	$('.tags9').tagsInput({width:'100%'});
	
	
	//===== Input limiter =====//
	
	$('.lim').inputlimiter({
		limit: 100,
		boxId: 'limitingtext',
		boxAttach: false
	});
	
	
	
	//===== Elastic textarea =====//
	
	$('.auto').autosize();


	//===== Full width sidebar dropdown =====//
	
	$('.fulldd li').click(function () {
		$(this).children("ul").slideToggle(150);
	});
	$(document).bind('click', function(e) {
		var $clicked = $(e.target);
		if (! $clicked.parents().hasClass("has"))
		$('.fulldd li').children("ul").slideUp(150);
	});
	
	
	//===== Top panel search field =====//
	
	$('.userNav a.search').click(function () {
		$('.topSearch').fadeToggle(150);
	});
	
	
	//===== 2 responsive buttons (320px - 480px) =====//
	
	$('.iTop').click(function () {
		$('#sidebar').slideToggle(100);
	});
	
	$('.iButton').click(function () {
		$('.altMenu').slideToggle(100);
	});

	
	//===== Animated dropdown for the right links group on breadcrumbs line =====//
	
	$('.breadLinks ul li').click(function () {
		$(this).children("ul").slideToggle(150);
	});
	$(document).bind('click', function(e) {
		var $clicked = $(e.target);
		if (! $clicked.parents().hasClass("has"))
		$('.breadLinks ul li').children("ul").slideUp(150);
	});
	
	
	//===== Easy tabs =====//
	
	$('#tab-container').easytabs({
		animationSpeed: 300,
		collapsible: false,
		tabActiveClass: "clicked"
	});
		
	
	//===== Tabs =====//
		
	$( ".tabs" ).tabs();

	var tabs = $( ".tabs-sortable" ).tabs();
    tabs.find( ".ui-tabs-nav" ).sortable({
        axis: "x",
        stop: function() {
        tabs.tabs( "refresh" );
        }
    });

	//===== Dynamic data table =====//
	
	oTable = $('.dTable').dataTable({
		"bJQueryUI": false,
		"bAutoWidth": false,
		"sPaginationType": "full_numbers",
		"sDom": '<"tablePars"fl>t<"tableFooter"ip>',
		"oLanguage": {
			"sLengthMenu": "<span class='showentries'>Show entries:</span> _MENU_"
		}
	});
	

	//===== Dynamic table toolbars =====//		
	
	$('#dyn .tOptions').click(function () {
		$('#dyn .tablePars').slideToggle(200);
	});	
	
	$('#dyn2 .tOptions').click(function () {
		$('#dyn2 .tablePars').slideToggle(200);
	});	
	
	
	$('.tOptions').click(function () {
		$(this).toggleClass("act");
	});
	

	//== Adding class to :last-child elements ==//
		
	$(".subNav li:last-child a, .formRow:last-child, .userList li:last-child, table tbody tr:last-child td, .breadLinks ul li ul li:last-child, .fulldd li ul li:last-child, .niceList li:last-child").addClass("noBorderB")

	
	//===== Add classes for sub sidebar detection =====//
	
	if ($('div').hasClass('secNav')) {
		$('#sidebar').addClass('with');
		//$('#content').addClass('withSide');
	}
	else {
		$('#sidebar').addClass('without');
		$('#content').css('margin-left','100px');//.addClass('withoutSide');
		$('#footer > .wrapper').addClass('fullOne');
		};


	//===== Collapsible elements management =====//
	
	$('.exp').collapsible({
		defaultOpen: 'current',
		cookieName: 'navAct',
		cssOpen: 'subOpened',
		cssClose: 'subClosed',
		speed: 200
	});

	$('.opened').collapsible({
		defaultOpen: 'opened,toggleOpened',
		cssOpen: 'inactive',
		cssClose: 'normal',
		speed: 200
	});
	
	$('.closed').collapsible({
		defaultOpen: '',
		cssOpen: 'inactive',
		cssClose: 'normal',
		speed: 200
	});
	
	
	//===== Accordion =====//		
	
	$('div.menu_body:eq(0)').show();
	$('.acc .whead:eq(0)').show().css({color:"#2B6893"});
	
	$(".acc .whead").click(function() {	
		$(this).css({color:"#2B6893"}).next("div.menu_body").slideToggle(200).siblings("div.menu_body").slideUp("slow");
		$(this).siblings().css({color:"#404040"});
	});



	//===== Breadcrumbs =====//
	
	$('#breadcrumbs').xBreadcrumbs();
	
	
		//===== Sparklines =====//
	
	$('.balBars').sparkline(
	'html', {type: 'bar', barColor: '#db6464', height: '18px'}
	 );
	 

	//===== User nav dropdown =====//		
	
	$('a.leftUserDrop').click(function () {
		$('.leftUser').slideToggle(200);
	});
	$(document).bind('click', function(e) {
		var $clicked = $(e.target);
		if (! $clicked.parents().hasClass("leftUserDrop"))
		$(".leftUser").slideUp(200);
	});
	
	
	//===== Tooltips =====//

	$('.tipN').tipsy({gravity: 'n',fade: true, html:true});
	$('.tipS').tipsy({gravity: 's',fade: true, html:true});
	$('.tipW').tipsy({gravity: 'w',fade: true, html:true});
	$('.tipE').tipsy({gravity: 'e',fade: true, html:true});
	
	
	//===== Calendar =====//
	
	var date = new Date();
	var d = date.getDate();
	var m = date.getMonth();
	var y = date.getFullYear();
	
	$('#calendar').fullCalendar({
		header: {
			left: 'prev,next',
			center: 'title',
			right: 'month,agendaWeek,agendaDay'
		},
		editable: true,
		events: [
			{
				title: 'All Day Event',
				start: new Date(y, m, 1)
			},
			{
				title: 'Long Event',
				start: new Date(y, m, d-5),
				end: new Date(y, m, d-2)
			},
			{
				id: 999,
				title: 'Repeating Event',
				start: new Date(y, m, d-3, 16, 0),
				allDay: false
			},
			{
				id: 999,
				title: 'Repeating Event',
				start: new Date(y, m, d+4, 16, 0),
				allDay: false
			},
			{
				title: 'Meeting',
				start: new Date(y, m, d, 10, 30),
				allDay: false
			},
			{
				title: 'Lunch',
				start: new Date(y, m, d, 12, 0),
				end: new Date(y, m, d, 14, 0),
				allDay: false
			},
			{
				title: 'Birthday Party',
				start: new Date(y, m, d+1, 19, 0),
				end: new Date(y, m, d+1, 22, 30),
				allDay: false
			},
			{
				title: 'Click for Google',
				start: new Date(y, m, 28),
				end: new Date(y, m, 29),
				url: 'http://google.com/'
			}
		]
	});



	//===== Spinner options =====//
	
	$( "#spinner-default" ).spinner();
	$( "#spinner-default2" ).spinner();
	$( "#spinner-default3" ).spinner();
	$( "#spinner-default4" ).spinner();
	$( "#spinner-default5" ).spinner();
	$( "#spinner-default6" ).spinner();
	$( "#spinner-default7" ).spinner();
	$( "#spinner-default8" ).spinner();
	$( "#spinner-default9" ).spinner();
	$( "#spinner-default10" ).spinner();

	
	$( "#spinner-decimal" ).spinner({
		step: 0.01,
		numberFormat: "n"
	});
	
	$( "#culture" ).change(function() {
		var current = $( "#spinner-decimal" ).spinner( "value" );
		Globalize.culture( $(this).val() );
		$( "#spinner-decimal" ).spinner( "value", current );
	});
	
	$( "#currency" ).change(function() {
		$( "#spinner-currency" ).spinner( "option", "culture", $( this ).val() );
	});
	
	$( "#spinner-currency" ).spinner({
		min: 5,
		max: 2500,
		step: 25,
		start: 1000,
		numberFormat: "C"
	});
		
	$( "#spinner-overflow" ).spinner({
		spin: function( event, ui ) {
			if ( ui.value > 10 ) {
				$( this ).spinner( "value", -10 );
				return false;
			} else if ( ui.value < -10 ) {
				$( this ).spinner( "value", 10 );
				return false;
			}
		}
	});
	
	$.widget( "ui.timespinner", $.ui.spinner, {
		options: {
			// seconds
			step: 60 * 1000,
			// hours
			page: 60
		},

		_parse: function( value ) {
			if ( typeof value === "string" ) {
				// already a timestamp
				if ( Number( value ) == value ) {
					return Number( value );
				}
				return +Globalize.parseDate( value );
			}
			return value;
		},

		_format: function( value ) {
			return Globalize.format( new Date(value), "t" );
		}
	});

	$( "#spinner-time" ).timespinner();
	$( "#culture-time" ).change(function() {
		var current = $( "#spinner-time" ).timespinner( "value" );
		Globalize.culture( $(this).val() );
		$( "#spinner-time" ).timespinner( "value", current );
	});
	


	//===== jQuery UI dialog =====//
	
    $('#dialog').dialog({
        autoOpen: false,
        width: 400,
        buttons: {
            "Gotcha": function () {
                $(this).dialog("close");
            },
            "Cancel": function () {
                $(this).dialog("close");
            }
        }
    });
	
    // Dialog Link
    $('#dialog_open').click(function () {
        $('#dialog').dialog('open');
        return false;
    });
	
	// Dialog
    $('#formDialog').dialog({
        autoOpen: false,
        width: 400,
        buttons: {
            "Nice stuff": function () {
                $(this).dialog("close");
            },
            "Cancel": function () {
                $(this).dialog("close");
            }
        }
    });
	
    // Dialog Link
    $('#formDialog_open').click(function () {
        $('#formDialog').dialog('open');
        return false;
    });
	
	// Dialog
    $('#customDialog').dialog({
        autoOpen: false,
        width: 650,
        buttons: {
            "Very cool!": function () {
                $(this).dialog("close");
            },
            "Cancel": function () {
                $(this).dialog("close");
            }
        }
    });
	
    // Dialog Link
    $('#customDialog_open').click(function () {
        $('#customDialog').dialog('open');
        return false;
    });

	
	
	//===== Vertical sliders =====//
	
	$( "#eq > span" ).each(function() {
		// read initial values from markup and remove that
		var value = parseInt( $( this ).text(), 10 );
		$( this ).empty().slider({
			value: value,
			range: "min",
			animate: true,
			orientation: "vertical"
		});
	});
	
	
	//===== Modal =====//
	
    $('#modal-activity').dialog({
		autoOpen: false, 
		width: 350,
		modal: true,
		});	
    $('#modal-activity-open').click(function () {
        $('#modal-activity').dialog('open');
        return false;
    });
    
    $('#modal-activity_handouts').dialog({
		autoOpen: false, 
		width: 600,
		modal: true,
		});
    $('#modal-activity_handouts-open').click(function () {
        $('#modal-activity_handouts').dialog('open');
        return false;
    });
    
    $('#modal-activity_manage_categories').dialog({
		autoOpen: false, 
		width: 600,
		modal: true,
		buttons: {"Save and Close": function() {$( this ).dialog( "close" );}}
	});
    $('#modal-activity_manage_categories-open').click(function () {
        $('#modal-activity_manage_categories').dialog('open');
        return false;
    });
    
    $('#modal-activities_print').dialog({
	autoOpen: false, 
	width: 600,
	modal: true,
	buttons: {"Print": function() {$( this ).dialog( "print" );},"Download PDF": function() {$( this ).dialog( "download" );}}
	});
    $('#modal-activities_print-open').click(function () {
        $('#modal-activities_print').dialog('open');
        return false;
    });
    
    $('#modal-activities_export').dialog({
	autoOpen: false, 
	width: 600,
	modal: true,
	buttons: {"Download/Export File": function() {$( this ).dialog( "download" );}}
	});
    $('#modal-activities_export-open').click(function () {
        $('#modal-activities_export').dialog('open');
        return false;
    });
    
    $('#modal-activities_import').dialog({
	autoOpen: false, 
	width: 600,
	modal: true,
	buttons: {"Upload File and Import": function() {$( this ).dialog( "close" );},"Upload File and Merge": function() {$( this ).dialog( "close" );}}
	});
    $('#modal-activities_import-open').click(function () {
        $('#modal-activities_import').dialog('open');
        return false;
    });
    
    $('#modal-addexisting').dialog({
	autoOpen: false, 
	width: 800,
	modal: true,
	buttons: {"Add Selected Activities": function() {$( this ).dialog( "close" );},"Cancel": function() {$( this ).dialog( "close" );}}
	});
    $('#modal-addexisting-open').click(function () {
        $('#modal-addexisting').dialog('open');
        return false;
    });
    
    $('#modal-add_singleclass').dialog({
	autoOpen: false, 
	width: 800,
	modal: true,
	buttons: {"Add Class": function() {$( this ).dialog( "close" );},"Cancel": function() {$( this ).dialog( "close" );}}
	});
    $('#modal-add_singleclass-open').click(function () {
        $('#modal-add_singleclass').dialog('open');
        return false;
    });
    
    $('#modal-add_multiclass').dialog({
	autoOpen: false, 
	width: 800,
	modal: true,
	buttons: {"Add Class": function() {$( this ).dialog( "close" );},"Cancel": function() {$( this ).dialog( "close" );}}
	});
    $('#modal-add_multiclass-open').click(function () {
        $('#modal-add_multiclass').dialog('open');
        return false;
    });
    
    $('#modal-add_classcreator').dialog({
	autoOpen: false, 
	width: 800,
	modal: true,
	buttons: {"Add Class Creator": function() {$( this ).dialog( "close" );},"Cancel": function() {$( this ).dialog( "close" );}}
	});
    $('#modal-add_classcreator-open').click(function () {
        $('#modal-add_classcreator').dialog('open');
        return false;
    });
    
    $('#modal-archive_restore').dialog({
	autoOpen: false, 
	width: 450,
	modal: true,
	buttons: {"Restore Activity": function() {$( this ).dialog( "close" );},"Cancel": function() {$( this ).dialog( "close" );}}
	});
    $('#modal-archive_restore-open').click(function () {
        $('#modal-archive_restore').dialog('open');
        return false;
    });
    
    $('#modal-user_filter').dialog({
	autoOpen: false, 
	width: 700,
	height: 500,
	modal: true,
	buttons: {"Display Results": function() {$( this ).dialog( "close" );},"Clear": function() {$( this ).dialog( "close" );},"Cancel": function() {$( this ).dialog( "close" );}}
	});
    $('#modal-user_filter-open').click(function () {
        $('#modal-user_filter').dialog('open');
        return false;
    });
    
    $('#modal-user_profile').dialog({
	autoOpen: false, 
	width: 900,
	modal: true,
	buttons: {"Save and Close": function() {$( this ).dialog( "close" );},"Cancel": function() {$( this ).dialog( "close" );}}
	});
    $('#modal-user_profile-open').click(function () {
        $('#modal-user_profile').dialog('open');
        return false;
    });	
    
    $('#modal-new_user').dialog({
	autoOpen: false, 
	width: 900,
	modal: true,
	buttons: {"Save and Close": function() {$( this ).dialog( "close" );},"Cancel": function() {$( this ).dialog( "close" );}}
	});
    $('#modal-new_user-open').click(function () {
        $('#modal-new_user').dialog('open');
        return false;
    });	
    
    $('#modal-edit_user').dialog({
	autoOpen: false, 
	width: 900,
	modal: true,
	buttons: {"Save and Close": function() {$( this ).dialog( "close" );},"Cancel": function() {$( this ).dialog( "close" );}}
	});
    $('#modal-edit_user-open').click(function () {
        $('#modal-edit_user').dialog('open');
        return false;
    });	

    $('#modal-requirements_manage_categories').dialog({
		autoOpen: false, 
		width: 600,
		modal: true,
		buttons: {"Save and Close": function() {$( this ).dialog( "close" );}}
	});
    $('#modal-requirements_manage_categories-open').click(function () {
        $('#modal-requirements_manage_categories').dialog('open');
        return false;
    });

     $('#modal-roster_table').dialog({
		autoOpen: false, 
		width: 900,
		modal: true,
		buttons: {"Save and Close": function() {$( this ).dialog( "close" );},"Save and Move Class to Completed": function() {$( this ).dialog( "close" );}}
	});
    $('#modal-roster_table-open').click(function () {
        $('#modal-roster_table').dialog('open');
        return false;
    });
	
    $('#modal-asset_schedule').dialog({
		autoOpen: false, 
		width: 900,
		modal: true,
		buttons: {"Close": function() {$( this ).dialog( "close" );}}
	});
    $('#modal-asset_schedule-open').click(function () {
        $('#modal-asset_schedule').dialog('open');
        return false;
    });

    $('#modal-appSettings-trainingc').dialog({
		autoOpen: false, 
		width: 900,
		modal: true,
		buttons: {"Save and Close": function() {$( this ).dialog( "close" );}}
	});
    $('#modal-appSettings-trainingc-open').click(function () {
        $('#modal-appSettings-trainingc').dialog('open');
        return false;
    });

    $('#modal-appSettings-requirements').dialog({
		autoOpen: false, 
		width: 900,
		modal: true,
		buttons: {"Save and Close": function() {$( this ).dialog( "close" );}}
	});
    $('#modal-appSettings-requirements-open').click(function () {
        $('#modal-appSettings-requirements').dialog('open');
        return false;
    });

    $('#modal-appSettings-inprogress').dialog({
		autoOpen: false, 
		width: 900,
		modal: true,
		buttons: {"Save and Close": function() {$( this ).dialog( "close" );}}
	});
    $('#modal-appSettings-inprogress-open').click(function () {
        $('#modal-appSettings-inprogress').dialog('open');
        return false;
    });

     $('#modal-appSettings-trainingh').dialog({
		autoOpen: false, 
		width: 900,
		modal: true,
		buttons: {"Save and Close": function() {$( this ).dialog( "close" );}}
	});
    $('#modal-appSettings-trainingh-open').click(function () {
        $('#modal-appSettings-trainingh').dialog('open');
        return false;
    });

 	$('#modal-appSettings-supervisort').dialog({
	autoOpen: false, 
	width: 900,
	modal: true,
	buttons: {"Save and Close": function() {$( this ).dialog( "close" );}}
	});
    $('#modal-appSettings-supervisort-open').click(function () {
        $('#modal-appSettings-supervisort').dialog('open');
        return false;
    });

    $('#modal-appSettings-social').dialog({
	autoOpen: false, 
	width: 900,
	modal: true,
	buttons: {"Save and Close": function() {$( this ).dialog( "close" );}}
	});
    $('#modal-appSettings-social-open').click(function () {
        $('#modal-appSettings-social').dialog('open');
        return false;
    });

    $('#modal-appSettings-expertd').dialog({
	autoOpen: false, 
	width: 900,
	modal: true,
	buttons: {"Save and Close": function() {$( this ).dialog( "close" );}}
	});
    $('#modal-appSettings-expertd-open').click(function () {
        $('#modal-appSettings-expertd').dialog('open');
        return false;
    });

    $('#modal-appSettings-announcements').dialog({
	autoOpen: false, 
	width: 900,
	modal: true,
	buttons: {"Save and Close": function() {$( this ).dialog( "close" );}}
	});
    $('#modal-appSettings-announcements-open').click(function () {
        $('#modal-appSettings-announcements').dialog('open');
        return false;
    });

    $('#modal-mark_complete').dialog({
	autoOpen: false, 
	width: 400,
	modal: true,
	buttons: {"View Class Roster": function() {$( this ).dialog( "close" );},"Close": function() {$( this ).dialog( "close" );}}
	});
    $('#modal-mark_complete-open').click(function () {
        $('#modal-mark_complete').dialog('open');
        return false;
    });
    $('#modal-training_history').dialog({
	autoOpen: false, 
	width: 600,
	modal: true,
	buttons: {"Import Training History": function() {$( this ).dialog( "close" );},"Import Training History & Merge": function() {$( this ).dialog( "close" );},"Close": function() {$( this ).dialog( "close" );}}
	});
    $('#modal-training_history-open').click(function () {
        $('#modal-training_history').dialog('open');
        return false;
    });
     $('#modal-schedule_reports').dialog({
	autoOpen: false, 
	width: 600,
	modal: true,
	buttons: {"Schedule Report": function() {$( this ).dialog( "close" );},"Close": function() {$( this ).dialog( "close" );}}
	});
    $('#modal-schedule_reports-open').click(function () {
        $('#modal-schedule_reports').dialog('open');
        return false;
    });
	
	//===== jQuery UI stuff =====//
	
	// default mode
	$('#progress1').anim_progressbar();
	
	// from second #5 till 15
	var iNow = new Date().setTime(new Date().getTime() + 5 * 1000); // now plus 5 secs
	var iEnd = new Date().setTime(new Date().getTime() + 15 * 1000); // now plus 15 secs
	$('#progress2').anim_progressbar({start: iNow, finish: iEnd, interval: 1});
	
	// Progressbar
    $("#progress").progressbar({
        value: 80
    });
	
    // Modal Link
    $('#modal_link').click(function () {
        $('#dialog-message').dialog('open');
        return false;
    });
	
	// Datepicker
    $('.inlinedate').datepicker({
        inline: true,
		showOtherMonths:true
    });
	
	$( ".datepicker" ).datepicker({ 
		defaultDate: +7,
		showOtherMonths:true,
		autoSize: true,
		appendText: '(dd-mm-yyyy)',
		dateFormat: 'dd-mm-yy'
	});	
	
	$(function() {
			var dates = $( "#fromDate, #toDate" ).datepicker({
				defaultDate: "+1w",
				changeMonth: false,
				showOtherMonths:true,
				numberOfMonths: 3,
				onSelect: function( selectedDate ) {
					var option = this.id == "fromDate" ? "minDate" : "maxDate",
						instance = $( this ).data( "datepicker" ),
						date = $.datepicker.parseDate(
							instance.settings.dateFormat ||
							$.datepicker._defaults.dateFormat,
							selectedDate, instance.settings );
					dates.not( this ).datepicker( "option", option, date );
				}
			});
		});
	
	
	$( ".uSlider" ).slider(); /* Usual slider */
	
	
	$( ".uRange" ).slider({ /* Range slider */
		range: true,
		min: 0,
		max: 500,
		values: [ 75, 300 ],
		slide: function( event, ui ) {
			$( "#rangeAmount" ).val( "$" + ui.values[ 0 ] + " - $" + ui.values[ 1 ] );
		}
	});
	$( "#rangeAmount" ).val( "$" + $( ".uRange" ).slider( "values", 0 ) +" - $" + $( ".uRange" ).slider( "values", 1 ));
	

	$( ".uMin" ).slider({ /* Slider with minimum */
		range: "min",
		value: 37,
		min: 1,
		max: 700,
		slide: function( event, ui ) {
			$( "#minRangeAmount" ).val( "$" + ui.value );
		}
	});
	$( "#minRangeAmount" ).val( "$" + $( ".uMin" ).slider( "value" ) );


	$( ".uMax" ).slider({ /* Slider with maximum */
		range: "max",
		min: 1,
		max: 100,
		value: 20,
		slide: function( event, ui ) {
			$( "#maxRangeAmount" ).val( ui.value );
		}
	});
	$( "#maxRangeAmount" ).val( $( ".uMax" ).slider( "value" ) );	




	//===== Add class on #content resize. Needed for responsive grid =====//
	
	$(window).resize(function () {
	  var width = $("#content").width();
		if (width < 769) {
			$("#content").addClass('under');
		}
		else { $("#content").removeClass('under') }
	}).resize(); // Run resize on window load
	
	
	//===== Button for showing up sidebar on iPad portrait mode. Appears on right top =====//
				
	$("ul.userNav li a.sidebar").click(function() { 
		$(".secNav").toggleClass('display');
	});


	//===== Form elements styling =====//
	
	$(".styled, input:radio, input:checkbox, .dataTables_length select").uniform();

	//===== Sortable Tables =====//
	$( ".sortable").sortable({
            revert:true
    })

	
});

	
