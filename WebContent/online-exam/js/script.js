$(document).ready(function() {
	$('#category-tabs li').click(function(){
    var id = $(this).attr('id');
    id+='-block';
    $('.category-block > div, #category-tabs li').removeClass('active');
    $('.category-block > div').hide();
    $('#'+id).addClass('active').show();
    $(this).addClass('active');   
  });
});