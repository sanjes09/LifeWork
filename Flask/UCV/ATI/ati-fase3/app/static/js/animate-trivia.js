// Get the modal
var modal = document.getElementById("emoji");

// Get the <span> element that closes the modal
//var span = document.getElementsByClassName("close")[0];

// When the user clicks the button, open the modal 
$("#true").click(function() {
    $("#emoji-img").attr('src',"static/img/win.gif");
    $("#text-emoji").html("<b>¡Acertaste!</b>");
    $("#text-emoji").attr('class','text-center text-success');
    $("#emoji-img").css('height','20rem');
    $("#emoji-img").css('width','25rem');
    $("#emoji-img").css('margin-left','35rem');
    modal.style.display = "block";
    setTimeout(function(){ redirect(); }, 5000);
});

$(".false").click(function() {
    $("#emoji-img").attr('src',"static/img/llorar2.gif");
    $("#text-emoji").html("<b>¡Fallaste!</b>");
    $("#text-emoji").attr('class','text-center text-danger');
    $("#emoji-img").css('height','20rem');
    $("#emoji-img").css('width','25rem');
    $("#emoji-img").css('margin-left','38rem');
    modal.style.display = "block";
    setTimeout(function(){ redirect(); }, 5000);
  });

  $("#finish").click(function() {
    $("#emoji-img").attr('src',"static/img/winner.gif");
    $("#text-emoji").html("<b>Total: 400 puntos</b>");
    $("#text-emoji").attr('class','text-center text-success');
    $("#emoji-img").css('height','30rem');
    $("#emoji-img").css('width','35rem');
    $("#emoji-img").css('margin-left','30rem');
    modal.style.display = "block";
    setTimeout(function(){ redirect(); }, 5000);
  });

// When the user clicks on <span> (x), close the modal
/*span.onclick = function() {
  modal.style.display = "none";
}*/

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}