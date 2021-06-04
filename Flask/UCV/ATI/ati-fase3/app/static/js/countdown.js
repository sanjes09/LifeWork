countdown_trivia();

function redirect(){
  location = "./";
}

function fallar(){
  $("#emoji-img").attr('src',"static/img/llorar2.gif");
  $("#text-emoji").html("<b>¡Fallaste!</b>");
  $("#text-emoji").attr('class','text-center text-danger');
  $("#emoji-img").css('height','20rem');
  $("#emoji-img").css('width','25rem');
  $("#emoji-img").css('margin-left','38rem');
  $("#emoji").css('display','block');
  setTimeout(function(){ redirect(); }, 5000);
}



function countdown_trivia (){
  var seconds = new Date().getTime() + 30000;
  $('#lazy').countdown(seconds, {elapse: true})
  .on('update.countdown', function(event) {
    var $this = $(this);
    if (event.elapsed) {
      $this.html('<span>0</span>');
      $this.stop();
      return fallar();
      //console.log("FALSE");
    } else {
      $this.html(event.strftime('<span>%S</span>'));
    }
  });
}

