$(document).ready(function() {
	
	$('#option-tabs li').click(function(){
    var id = $(this).attr('id');
    id+='-block';
    $('.option-block > div, #option-tabs li').removeClass('active');
    $('.option-block > div').hide();
    $('#'+id).addClass('active').show();
    $(this).addClass('active');   
  });
	
    $("#showLeft").on("click",function(){
        $("#cbp-spmenu-s1").show().addClass('cbp-spmenu-open');
    })

    $(".search_icon").on("click",function(){
        $("#cbp-spmenu-s1").hide();
    })
    
    $(".second").pageslide({ direction: "left", modal: true });


    var addDiv= $('#addinput');
  var i = $('#addinput p').size() + 1;


  $('#addNew').live('click', function() {
    $('<p class="mb12"><input type="radio" id="radio1" name="question1"/><input type="text" value=""/><a href="#" id="remNew"><img class="valm" src="../images/crosslrg-icon.png"/></a></p>').appendTo(addDiv);
      i++;
    return false;
  });
  $('#remNew').live('click', function() {
    if( i > 2 ) {
      $(this).parents('p').remove();
      i--;
    }
  return false;
  });
  $(window).load(function() {
    $('window').width();

  });
  var addDiv= $('#add-input');
  var i = $('#add-input p').size() + 1;


  $('#add-New').live('click', function() {
    $('<p class="mb12"><input type="checkbox" id="radio1" name="question1"/><input type="text" value=""/><a href="#" id="remNew"><img class="valm" src="../images/crosslrg-icon.png"/></a></p>').appendTo(addDiv);
      i++;
    return false;
  });
  $('#remNew').live('click', function() {
    if( i > 2 ) {
      $(this).parents('p').remove();
      i--;
    }
  return false;
  });


});