
$(".loading-bar")
.on("animationend webkitAnimationEnd oAnimationEnd MSAnimationEnd",
 function(e){
    // do something here    
 });

$('.parent-nav li').hover(function() {
  var liText = $(this).text().toLowerCase();

  var thisChild = $('.children-nav ul.' + liText + '-tag');

  $('.children-nav ul').css('visibility', 'hidden');
  thisChild.css('visibility', 'hidden');
  thisChild.css('visibility', 'visible');
}, function() {
});

$('.smaller-nav-options').fadeOut();

$('.small-nav-links li').hover(function() {
  var liText = $(this).text().toLowerCase();

  var thisChild = $('.smaller-nav-options ul.' + liText + '-tag');

  $('.smaller-nav-options ul li').css('visibility', 'hidden');
  thisChild.find('li').css('visibility', 'visible');

  thisChild.fadeIn();

  $('.smaller-nav-options').fadeIn();
}, function() {
  // thisChild.removeClass('slideDown');
});

$('.post-overlay').hover(function(e) {
  e.preventDefault();  
  var parentHeight = $(this).parent().css('height');
  $(this).css('height', parentHeight).css('opacity', '0.5');
}, function() {  
  $(this).css('opacity', '0');
})

function setHandler(direction) {
  if (direction === "up") {
      $('.smaller-nav').removeClass('slideDown').addClass('slideUp');
      $('.smaller-nav-options').fadeOut();
      setTimeout(function() {
        $('.smaller-nav').toggleClass('visible');
      }, 750)
    } else {
      $('.smaller-nav-options').fadeOut();
      $('.smaller-nav').removeClass('slideUp').addClass('slideDown').toggleClass('visible')
    }
}

$('#blurrMe').on('click',function() {
  console.log('clicked')
  if ($(this).hasClass('blurred')) {
    $('#close').click();
  }
  
})