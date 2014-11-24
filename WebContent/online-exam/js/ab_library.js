/*------------------- 

Index: 
1. Window Width height variables
2. Global Overlay declarations
3. Console log shortcut
4. Placeholder html5 fallback
5. Browser.js
6. AB Gallery Plugin for image gallery. 
7. Real Width Plugin for getting natural width of any image in the DOM.
8. AB Popup
9. AB Message
10. AB Validate
11. AB FormFill
12. AB Slider

--------------------*/

//----------------------------- Global Variables
var winWidth = jQuery(window).width();
var winHeight = jQuery(window).height();

var abOverlay, abLoader, defaultMessage;

//----------------------------- Global Overlay Controls
// showOverlay({showLoading:true, overlayClick: false});
function showOverlay(options){
	// Default settings available
	var settings = jQuery.extend( {
	  showLoading	: false,
	  overlayClick	: true
	}, options);

	if(settings.overlayClick == false) abOverlay.addClass('disabled');

	if(abOverlay.is(':hidden')){
		abOverlay.fadeIn();
		jQuery('body').addClass('ab_overlay_shown');		
		if(settings.showLoading == true){
			abLoader.fadeIn();
		}
	}	
}
function hideOverlay(){
	abOverlay.removeClass('disabled').trigger('click');
}
function hideLoading(){
	abLoader.fadeOut();
}

//----------------------------- Console Log
function lg(obj){
	console.log(obj);
}

jQuery(document).ready(function($) {
    //----------------------------- Global Overlay	
	$('body').prepend('<div class="ab_overlay"></div><div class="ab_loading"></div><div id="ab_auto_message"></div>');
	defaultMessage = $('#ab_auto_message').abMessage();	
	abOverlay = $('.ab_overlay');
	abLoader = $('.ab_loading');

	abOverlay.on('click', function(){
		if(!abOverlay.hasClass('disabled')){
			abOverlay.add(abLoader).add('.ab_overlayTop:visible').fadeOut(function(){
				$('.ab_overlayDelete').remove();
				$('body').removeClass('ab_overlay_shown');
				$('.ab_overlayTop:visible').animate({'width': '0',
					'height'  : '0',
					'opacity' : '0',
					marginTop : '0',
					marginLeft: '0'
				}, 300);
			});	
		}		
	});
	
	//----------------------------- Window Resize
	$(window).resize(function(e) {
        winWidth = $(window).width();
		winHeight = $(window).height();
    });	

    //----------------------------- Placeholder HTML5 fallback
	if (! ("placeholder" in document.createElement("input"))) {
        $('*[placeholder]').each(function() {
            $this = $(this);
            var placeholder = $(this).attr('placeholder');
            if ($(this).val() === '') {
                $this.val(placeholder);
            }
            $this.bind('focus',
            function() {
                if ($(this).val() === placeholder) {
                    this.plchldr = placeholder;
                    $(this).val('');
                }
            });
            $this.bind('blur',
            function() {
                if ($(this).val() === '' && $(this).val() !== this.plchldr) {
                    $(this).val(this.plchldr);
                }
            });
        });
        $('form').bind('submit',
        function() {
            $(this).find('*[placeholder]').each(function() {
                if ($(this).val() === $(this).attr('placeholder')) {
                    $(this).val('');
                }
            });
        });
    }

    //----------------------------- Browser.js
	(function(){
		var val = navigator.userAgent.toLowerCase();
		if(val.indexOf("firefox") > -1){
			$('body').addClass('firefox');
		}
		else if(val.indexOf("opera") > -1){
			$('body').addClass('opera');
		}
		else if(val.indexOf("msie") > -1){
			$('body').addClass('ie');		
			// get ie version
			version = parseFloat(navigator.appVersion.split("MSIE")[1]);
			$('body').addClass('ie'+version);
		}	
		else if(val.match('chrome') != null){
			$('body').addClass('chrome');
		}
		else if(val.indexOf("safari") > -1){
			$('body').addClass('safari');
		}
	})();

});

(function($){	
	//---------------------------------------------------------- AB Gallery
	var galleryCounter = 0;		
	
	var methods = {
		init : function( options ) { 
			var gallery = this;
			var count = gallery.size();	
			galleryCounter++;					
			var clicker;			
				
			var ab_gallery_data = '<div id="ab_gallery_view_'+galleryCounter+'" class="ab_gallery_view ab_overlayTop"><a onclick="javascript: hideOverlay();" class="ab_gallery_close"></a><div class="ab_gallery_title"></div><div class="ab_gallery_count"><span class="ab_gallery_cur">1</span> of '+count+'</div>';
			
			ab_gallery_data += (count>1) ? '<a class="ab_gallery_prev ab_gallery_control"></a><a class="ab_gallery_next ab_gallery_control"></a></div>' : '</div>';		
				
			$('body').append(ab_gallery_data);	
			
			return gallery.each(function(){
				var $this = $(this);
				$this.addClass('ab_gallery_data_'+galleryCounter);
				$this.on('click', {glCt : galleryCounter, galleryObj: gallery} ,ab_gallery_click);
			});
		},
		destroy : function( ) {
			return this.each(function(){
				$(this).off('click', ab_gallery_click);				// destroy abGallery
			});
		}
	  };
	
	//----------------------------- main calling abGallery
	$.fn.abGallery = function( method ) {		
		// Method calling logic
		if ( methods[method] ) {
			return methods[ method ].apply( this, Array.prototype.slice.call( arguments, 1 ));
		} else if ( typeof method === 'object' || ! method ) {
			return methods.init.apply( this, arguments );
		} else {
			$.error( 'Method ' +  method + ' does not exist on jQuery.abGallery' );
		}    	  
	};

	//----------------------------- Click handler
	function ab_gallery_click(e){
		e.preventDefault();			
		showOverlay(1);			
		
		clicker = $(this);	
		var clickerImg = clicker.children('img');			
		var galleryView = $('#ab_gallery_view_'+e.data.glCt);	
		var gallery = e.data.galleryObj;
		galleryView.stop().css({'overflow': 'visible'});					
		
		gallery.removeClass('active_abGallery');
		clicker.addClass('active_abGallery');		
		
		var gallery_cur = gallery.index(clicker);										
		galleryView.find('.ab_gallery_cur').text(gallery_cur+1);						//-- current number element							
		galleryView.children('.ab_gallery_title').text(clickerImg.attr('title'));		//-- title of current image		
		
		var galleryImg = new Image();
		galleryImg.src = clicker.attr('href');							
				
		galleryImg.onload = function(){
			imageLoaded(galleryImg, galleryView);
		}					
		galleryImg.onerror = function(){		
			galleryImg = new Image();	
			galleryImg.src = clickerImg.attr('src');	
			imageLoaded(galleryImg, galleryView);
		}									
	}	
	
	//----------------------------- Image Handler
	function imageLoaded(galleryImg, galleryView){
		var galleryImgWidth = galleryImg.width;		
		var galleryImgHeight = galleryImg.height;	
		var ratio = galleryImgWidth / galleryImgHeight;
		if(ratio>1){
			galleryImgWidth = (galleryImgWidth>winWidth-100) ? winWidth-100 : galleryImgWidth;	
			galleryImgHeight = galleryImgWidth / ratio;
			galleryImgHeight = (galleryImgHeight>winHeight-100) ? winHeight-100 : galleryImgHeight;	
			galleryImgWidth = ratio*galleryImgHeight;
		}
		else{
			galleryImgHeight = (galleryImgHeight>winHeight-100) ? winHeight-100 : galleryImgHeight;	
			galleryImgWidth = ratio*galleryImgHeight;
			galleryImgWidth = (galleryImgWidth>winWidth-100) ? winWidth-100 : galleryImgWidth;	
			galleryImgHeight = galleryImgWidth / ratio;
		}						
		
		galleryImg.width = galleryImgWidth;		
							
		galleryView.animate({marginTop: -galleryImgHeight/2,
			marginLeft : -galleryImgWidth/2-17,
			'height' : galleryImgHeight,
			'width' : galleryImgWidth,
			'opacity' : '1'
		});
									
		galleryView.append(galleryImg);
		galleryView.children('img').addClass('ab_overlayDelete').fadeIn().animate({
			'height' : galleryImgHeight,
			'width' : galleryImgWidth
		});				
		
		hideLoading();			
		galleryView.fadeIn();			
	}
	
	//----------------------------- Navigation controls
	$('.ab_gallery_control').live('click', function(){	
		var galleryView = $(this).parent('.ab_gallery_view');
		var galleryId = galleryView.attr('id').substr(16);
		galleryId = '.ab_gallery_data_'+galleryId;
		
		var count = $(galleryId).size();
		var galleryCurIndex = $(galleryId+'.active_abGallery').index(galleryId);
		
		galleryView.children('img').fadeOut(function(){
			$(this).remove();
		});
					
		var newGallery;
				
		if($(this).hasClass('ab_gallery_prev')){
			if(galleryCurIndex==0){
				newGallery = $(galleryId).last();						
			}
			else{					
				newGallery = $($(galleryId)[galleryCurIndex-1]);
			}
		}
		else{				
			if(galleryCurIndex>=count-1){
				newGallery = $(galleryId).first();	
			}
			else{
				newGallery = $($(galleryId)[galleryCurIndex+1]);
			}	
		}
		
		newGallery.trigger('click');
	});

	// AB Gallery ends
//---------------------------------------------------------------------------------------------------------------------//
	
	//---------------------------------------------------------- Real Width
	$.fn.realWidth = function(){		
		var theImage = new Image();
		theImage.src = this.attr("src");
		var imageWidth = theImage.width;
		return imageWidth;
	};
	
//---------------------------------------------------------------------------------------------------------------------//

	//---------------------------------------------------------- AB Popup
	
	var popupCounter = 0;
	
	var abPopupMethods = {
		init : function() { 
			this.hide();
		},
		show : function( options ) {	
			var popup = this;	

			if(!popup.hasClass('ab_popup')){
				popup.addClass('ab_overlayTop ab_popup');
				popup.wrapInner('<div class="ab_popup_wrp group" />');
				var closeButton = '<a onclick="javascript: jQuery(this).closest(\'.ab_popup\').abPopup(\'hide\');" class="ab_popup_close"></a>';
				popup.append(closeButton);	
			}					

			// Default settings available
			var settings = $.extend( {
			  'width'		: -1,
			  'height'		: -1,
			  'speed'		: 300,
			  'content'		: null,
			  'overlayClick': true
			}, options);

			//--------- add content on run
			if(settings.content){
				this.children('.ab_popup_wrp').html(settings.content);
			}

			//--------- prevent overlay close
			if(settings.overlayClick==false) abOverlay.addClass('disabled');

			//--------- calculate popup width
			if(settings.width<0 || settings.height<0){
				var clone = this.clone().addClass('ab_popup_clone').insertAfter(this).show();

				// set the width to auto
				if(clone.width()<=0) clone.css('width', 'auto'); 
				if(clone.height()<=0) clone.css('height', 'auto');

				// if user has given height width then use them instead
				if(settings.width>0) clone.css('width', settings.width);
				if(settings.height>0) clone.css('height', settings.height);

				// if not that calculate the auto value
				if(settings.width<0) settings.width = clone.width();	
				if(settings.height<0) settings.height = clone.height();	

				clone.remove(); //---- bye bye clone
			}

			//-------- prevent the popup from being bigger then the window
			if(settings.width>winWidth-100) settings.width = winWidth-100;
			if(settings.height>winHeight-100) settings.height = winHeight-100;
			
			showOverlay();					
			hideLoading();		
				
			this.fadeIn(settings.speed).animate({'width' : settings.width+'px', 
				'height' 	: settings.height+'px',
				marginLeft	: -settings.width/2+'px',
				marginTop	: -settings.height/2+'px',
				'opacity'	: '1'
			}, settings.speed);			

			this.children('.ab_popup_wrp').find('input, a').first().trigger('focus');			
			return this;
		},
		wait: function(){
			showOverlay(1);
		},
		hide : function(){
			abLoader.add(abOverlay).fadeOut(function(){
				$('.ab_overlayDelete').remove();
				$('body').removeClass('ab_overlay_shown');
			});
			this.animate({'width': '0',
				'height'  : '0',
				'opacity' : '0',
				marginTop : '0',
				marginLeft: '0'
			}, 300);
			abOverlay.removeClass('disabled');
			return this;
		}
	  };
	
	$.fn.abPopup = function( method ) {		
		// Method calling logic
		if ( abPopupMethods[method] ) {
			return abPopupMethods[ method ].apply( this, Array.prototype.slice.call( arguments, 1 ));
		} else if ( typeof method === 'object' || ! method ) {
			return abPopupMethods.init.apply( this, arguments );
		} else {
			$.error( 'Method ' +  method + ' does not exist on jQuery.abPopup' );
		}    	  
	};
	
	
//---------------------------------------------------------------------------------------------------------------------//

	//---------------------------------------------------------- AB Message
	
	var messageCounter = 0;
	
	var abMessageMethods = {
		init : function() { 
			var message = this;
			messageCounter++;		
			message.addClass('ab_message ab_message_'+messageCounter);			
			return this;
		},
		show : function( options ) {						
			// Default settings available
			var settings = $.extend( {
			  'message'		: 'Please pass your message',
			  'location'	: 'top',
			  'type'		: 'info',
			  'stay'		: '6000'
			}, options);
			
			var message = this;			
			message.removeClass('error success').addClass(settings.type);			
			var messageContent = '<div class="ab_message_wrp">'+settings.message+'</div>';						
			message.append(messageContent);			
			message.filter(':hidden').fadeIn().animate({'top':'0px'}, 500);			
			setTimeout(function(){
				message.abMessage('hide');
			}, settings.stay);						
			return this;
		},
		hide: function(){
			this.stop().fadeOut(function(){
				$(this).children().remove();
			}).animate({'top':'-200px'}, 500);		
		}
	  };
	
	$.fn.abMessage = function( method ) {		
		// Method calling logic
		if ( abMessageMethods[method] ) {
			return abMessageMethods[ method ].apply( this, Array.prototype.slice.call( arguments, 1 ));
		} else if ( typeof method === 'object' || ! method ) {
			return abMessageMethods.init.apply( this, arguments );
		} else {
			$.error( 'Method ' +  method + ' does not exist on jQuery.abMessage' );
		}    	  
	};	
		
	
//---------------------------------------------------------------------------------------------------------------------//

	//---------------------------------------------------------- AB Validate
		
	var abValidateMethods = {
		init: function(){ 									
			return this.each(function(){
				var $this = $(this); // each form
				var inputs = $this.find('input, select, textarea').not(':button, :submit, :reset'); // all the user inputs
				$this.attr('novalidate', 'novalidate');		
						
				inputs.each(function(){
					var $this = $(this); // each input that needs validation
					$('<div></div>', {
						class: 'errorbox',
					}).insertAfter($this);
					
					if($this.is('select')){
						$this.on('change', function(){
							$(this).abValidate('validate');
						});
					}
					else{
						$this.on('keyup', function(e){
							if(e.keyCode!=9){
								$(this).abValidate('validate');
							}						
						});
					}										
					$this.on('blur', function(){
						$(this).abValidate('validate');
					});	
				});				
				
				$this.on('submit', function(){
					return $this.abValidate('validateForm');
				});	
			});
		},
		validate: function(){
			var $this = this, // currently changed input			
				errorbox = $this.next('.errorbox'),
				value = $this.val(),
				valueLength = value.length,
				type = $this.attr('type'),
				minVal = parseInt($this.attr('min')),	
				maxVal = parseInt($this.attr('max'));
				if(!maxVal){
					maxVal = parseInt($this.attr('maxlength'))
				}
							
			if($this.attr('required') && valueLength == 0){				
				errorbox.text('This field is required!').slideDown();
			}
			else if(type=='number' && isNaN(value) && valueLength!=0){
				errorbox.text('This field needs to be a number!').slideDown();
			}
			else if($this.data('text')==true && !value.match(/^[A-z\s]*$/) && valueLength!=0){
				errorbox.text('This field needs to be all text!').slideDown();
			}
			else if(type=='email' && !value.match(/^[\w\-\.\+]+\@[a-zA-Z0-9\.\-]+\.[a-zA-z0-9]{2,4}$/) && valueLength!=0){
				errorbox.text('Please enter a valid email address!').slideDown();
			}
			else if(!isNaN(minVal) && valueLength < minVal && valueLength!=0){
				errorbox.text('The minimum value for this field is '+ minVal).slideDown();
			}
			else if(!isNaN(maxVal) && valueLength > maxVal && valueLength!=0){
				errorbox.text('The maximum value for this field is '+ maxVal).slideDown();
			}	
			else if($this.find('option:selected').data('default')==true){
				errorbox.text('Please select a value for this field!').slideDown();
			}					
			else{
				$this.removeClass('error');
				errorbox.addClass('hiding').slideUp(function(){
					errorbox.removeClass('hiding');
				});
			}			
			if(errorbox.is(':visible') && !errorbox.hasClass('hiding')){
				$this.addClass('error');				
			}		
		},
		validateForm: function(){
			var $this = $(this); //--- the ab form being targeted
			$this.find('input, select, textarea').not(':button, :submit, :reset').trigger('blur');
			if($this.find('.error').length > 0){
				$('#ab_auto_message').abMessage('show', {'message':'The form could not be submitted, Please solve the errors first!','type':'error'});						
				return false;									
			}
			else{
				return true;
			}
		}
	  };
	
	$.fn.abValidate = function( method ) {		
		// Method calling logic
		if ( abValidateMethods[method] ) {
			return abValidateMethods[ method ].apply( this, Array.prototype.slice.call( arguments, 1 ));
		} else if ( typeof method === 'object' || ! method ) {
			return abValidateMethods.init.apply( this, arguments );
		} else {
			$.error( 'Method ' +  method + ' does not exist on jQuery.abValidate' );
		}    	  
	};	
	
//---------------------------------------------------------------------------------------------------------------------//

	//---------------------------------------------------------- AB Form Fill
		
	var abFormFillMethods = {
		init: function(){ 		
			var form = this,
				randomText = ['Lorem ipsum', 'Etiam tortor lacus', 'Vestibulum sagittis', 'Quisque ut', 'Proin scelerisque', 'Vestibulum', 'Aliquam eros', 'Sed porttitor', 'Phasellus quis felis', 'Scelerisque'],
				radioNames = [];
			
			//-------- input texts and passwords
			form.find('input[type="text"], input[type="password"]').each(function(){
				var i = Math.floor((Math.random()*10)); 
				$(this).val(randomText[i]);
			});
			
			//-------- select elements
			form.find('select').each(function(){
				var $this = $(this),
					options = $this.children('option'),
					max = options.size(),		
					i = Math.floor(Math.random() * (max - 0) + 0);				
				options.eq(i).attr('selected', 'selected');
			});
			
			//-------- radios and checkboxes
			form.find('input[type="radio"], input[type="checkbox"]').each(function(){
				var thisName = $(this).attr('name');				
				(radioNames.indexOf(thisName) < 0) && radioNames.push(thisName);
			});
			
			$.each(radioNames, function(index, value){
				var options = $('[name="'+value+'"]'),
					max = options.size(),		
					i = Math.floor(Math.random() * (max - 0) + 0);				
				options.eq(i).attr('checked', 'checked');
			});
			
			return form;
		}
	  };
	
	$.fn.abFormFill = function( method ) {		
		// Method calling logic
		if ( abFormFillMethods[method] ) {
			return abFormFillMethods[ method ].apply( this, Array.prototype.slice.call( arguments, 1 ));
		} else if ( typeof method === 'object' || ! method ) {
			return abFormFillMethods.init.apply( this, arguments );
		} else {
			$.error( 'Method ' +  method + ' does not exist on jQuery.abFormFill' );
		}    	  
	};	

//---------------------------------------------------------------------------------------------------------------------//

	//---------------------------------------------------------- AB Slider

	var absVar = {
			slider: '',
			slide: '',
			slideWidth: 0,
			slideCount: 0,
			sliderWidth: 0,
			sliderCounter: 0,
			slideInterval: null,
			navigation: null,
			control: null
		};
	
	var abSliderMethods = {
		init: function(options){
			// Default settings available
			var settings = $.extend( {
			  autoStart	: true,
			}, options);

			absVar.sliderCounter++; 		
			absVar.slider = this.addClass('ab-slider group'),
			absVar.slide = absVar.slider.children().addClass('ab-slider-slide'),
			absVar.slideWidth = absVar.slide.width(),
			absVar.slideCount = absVar.slide.length,
			absVar.sliderWidth = absVar.slideWidth*absVar.slideCount;

			absVar.slider.css('width', absVar.sliderWidth);
			absVar.slider.wrap('<div id="ab-slider-viewport-'+absVar.sliderCounter+'" class="ab-slider-viewport" style="width: '+absVar.slideWidth+'px;" />');
			absVar.slide.first().addClass('active');

			//------------ auto start
			if(settings.autoStart==true) absVar.slider.abSlider('createInterval');

			//------------ mouse over conditions
			absVar.slider.on('mouseenter', function(){
				if(settings.autoStart==true) clearInterval(absVar.slideInterval);
				absVar.slider.navigation.add(absVar.control).fadeIn();
			});
			absVar.slider.on('mouseleave', function(){
				if(settings.autoStart==true) absVar.slider.abSlider('createInterval');
				absVar.slider.navigation.add(absVar.control).fadeOut();
			});

			//------------ navigation controls
			absVar.slider.append('<div class="ab-slider-navigation"><a class="prev"></a><a class="next"></a></div>');
			absVar.slider.navigation = absVar.slider.find('.ab-slider-navigation');

			absVar.slider.navigation.on('click', 'a', function(){
				if($(this).hasClass('prev')){
					absVar.slider.abSlider('slide', { direction: -1 });
				}
				else{
					absVar.slider.abSlider('slide', { direction: 1 });
				}				
			});

			//------------ numbering controls
			var j = 0;
			absVar.slide.each(function(){
				$(this).data('nav', j);
				j++;
			});
			absVar.slider.append('<div class="ab-slider-controls"></div>');
			absVar.control = absVar.slider.children('.ab-slider-controls');
			for(var i=0; i<absVar.slideCount; i++){
				absVar.control.append('<a data-nav="'+i+'"></a>');
			}
			absVar.control.find('a:first').addClass('active');

			absVar.control.on('click', 'a', function(){
				var $this = $(this).addClass('active'),
					navId = $this.data('nav'),
					target = absVar.slide.eq(navId),
					current = absVar.slider.find('.ab-slider-slide.active'),
					currentId = current.data('nav');

				$this.siblings().removeClass('active');
				if(navId!=currentId){
					if(navId>currentId){
						target.insertAfter(current);
						absVar.slider.abSlider('slide', { direction: 1 });
					}
					else{
						target.insertBefore(current);
						absVar.slider.css('margin-left', -absVar.slideWidth).abSlider('slide', { direction: -1, inserted: true });
					}
				}
			});

			return this;
		},
		createInterval: function(){
			absVar.slideInterval = setInterval(function(){
				absVar.slider.abSlider('slide', { direction: 1 });
			}, 6000);
		},
		slide: function(options){
			if(options.direction==-1){
				if(options.inserted!=true){
					absVar.slider.css('margin-left', -absVar.slideWidth).find('.ab-slider-slide').last().prependTo(absVar.slider);
				}				
				absVar.slider.stop(true, false).animate({
					marginLeft : 0
				}, 1000, function(){	
					absVar.slide.removeClass('active');				
					var navId = absVar.slider.children('.ab-slider-slide').first().addClass('active').data('nav');
					absVar.control.children().removeClass('active').eq(navId).addClass('active');
				});	
			}
			else{
				absVar.slider.stop(true, false).animate({
					marginLeft : -absVar.slideWidth
				}, 1000, function(){
					absVar.slider.css('margin-left', 0).find('.ab-slider-slide.active').removeClass('active').appendTo(absVar.slider);
					var navId = absVar.slider.children('.ab-slider-slide').first().addClass('active').data('nav');
					absVar.control.children().removeClass('active').eq(navId).addClass('active');
				});
			}			
		}
	  };
	
	$.fn.abSlider = function( method ) {		
		// Method calling logic
		if ( abSliderMethods[method] ) {
			return abSliderMethods[ method ].apply( this, Array.prototype.slice.call( arguments, 1 ));
		} else if ( typeof method === 'object' || ! method ) {
			return abSliderMethods.init.apply( this, arguments );
		} else {
			$.error( 'Method ' +  method + ' does not exist on jQuery.abSlider' );
		}    	  
	};
})(jQuery);







