(function($) {
  $.scrollToElement = function($element, speed) {

    speed = speed || 750;

    $("html, body").animate({
      scrollTop: $element.offset().top,
      scrollLeft: $element.offset().left
    }, speed);
    return $element;
  };

  $.fn.scrollTo = function(speed) {
    speed = speed || "normal";
    return $.scrollToElement(this, speed);
  };
})(jQuery);

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

$('#start').dblclick(function() {
   $('html, body').animate({
    scrollTop: $('#intro').offset().top - 150
  }, 2000);
})

$.each([["#intro", "#idea"], ["#idea", "#demo"], ["#demo", '#design'], ['#design', '#testing'], ['#testing', '#web'], ['#web', '#asmah'], ['#asmah', '#mohammad'], ["#mohammad", '#anji'], ['#anji', '#brad'], ['#brad', '#oskar'], ['#oskar', '#goals']], function(index, val) {

  $(val[0]).click(function() {
   $('html, body').animate({
    scrollTop: $(val[1]).offset().top - 150
  }, 2000);
   if (val[0] === '#intro') {
    $('tspan').each(function(index){
      $(this).stop(true, true).delay(500* index).animate({
        'stroke-dashoffset':0
      }, 800);
    })
    setTimeout(function() {        
      $('#babbage').fadeIn(2000);
    }, 7000);
  } else if (val[0] === "#oskar") {
    console.log("changing body")
    setTimeout(function() {        
      $('html').css('background', '#00b4ff');
      $('#background-wrap').css('display', 'block');
    }, 2500)
  }
  return false;
})
})

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

var canvas = $('canvas')[0];
var context = canvas.getContext('2d');

canvas.width = window.innerWidth;
canvas.height = window.innerHeight;

var Dots = [];
var colors = ['#FF9900', '#424242', '#BCBCBC', '#3299BB'];
var maximum = 70;

function Initialize() {
  GenerateDots();

  Update();
}

function Dot() {
  this.active = true;

  this.diameter = Math.random() * 7;

  this.x = Math.round(Math.random() * canvas.width);
  this.y = Math.round(Math.random() * canvas.height);

  this.velocity = {
    x: (Math.random() < 0.5 ? -1 : 1) * Math.random() * 0.7,
    y: (Math.random() < 0.5 ? -1 : 1) * Math.random() * 0.7
  };

  this.alpha = 0.1;
  this.hex = colors[Math.round(Math.random() * 3)];
  this.color = HexToRGBA(this.hex, this.alpha);
}

Dot.prototype = {
  Update: function() {
    if(this.alpha < 0.8) {
      this.alpha += 0.01;
      this.color = HexToRGBA(this.hex, this.alpha);
    }

    this.x += this.velocity.x;
    this.y += this.velocity.y;

    if(this.x > canvas.width + 5 || this.x < 0 - 5 || this.y > canvas.height + 5 || this.y < 0 - 5) {
      this.active = false;
    }
  },

  Draw: function() {
    context.fillStyle = this.color;
    context.beginPath();
    context.arc(this.x, this.y, this.diameter, 0, Math.PI * 2, false);
    context.fill();
  }
}

function Update() {
  GenerateDots();

  Dots.forEach(function(Dot) {
    Dot.Update();
  });

  Dots = Dots.filter(function(Dot) {
    return Dot.active;
  });

  Render();
  requestAnimationFrame(Update);
}

function Render() {
  context.clearRect(0, 0, canvas.width, canvas.height);
  Dots.forEach(function(Dot) {
    Dot.Draw();
  });
}

function GenerateDots() {
  if(Dots.length < maximum) {
    for(var i = Dots.length; i < maximum; i++) {
      Dots.push(new Dot());
    }
  }

  return false;
}

function HexToRGBA(hex, alpha) {
  var red = parseInt((TrimHex(hex)).substring(0, 2), 16);
  var green = parseInt((TrimHex(hex)).substring(2, 4), 16);
  var blue = parseInt((TrimHex(hex)).substring(4, 6), 16);

  return 'rgba(' + red + ', ' + green + ', ' + blue + ', ' + alpha + ')';
}

function TrimHex(hex) {
  return (hex.charAt(0) == "#") ? hex.substring(1, 7) : h;
}

$(window).resize(function() {
  Dots = [];
  canvas.width = window.innerWidth;
  canvas.height = window.innerHeight;
});

Initialize();

var s=Snap('svg');
var text='Are We Different?'
var len=text.length;
var array=[];
for(var x=0;x < len;x++){
  var t=text[x]
  array.push(t);
}
var txt=s.text(50,50,array)
$('tspan').css({
  'font-size':50,
  fill:'none',
  stroke:'black',
  'stroke-dasharray':230,
  'stroke-dashoffset':230
})