$("#btn-toggle-comment").click(function(e){
    var x = document.getElementById("comentario"); 
    if(x.style.display === "none"){ 
        x.style.display = "block";
    }else{
        x.style.display = "none";
    }
});

$(".btn-like").click(function(e){
    e.preventDefault();
    let id = $(this).data("id");

    $.post("/images/"+id+"/like")
        .done(data =>{
            $("#likes-count-"+id).text(data.likes);
        });
});

$(".btn-unlike").click(function(e){
    e.preventDefault();
    let id = $(this).data("id");

    $.post("/images/"+id+"/unlike")
        .done(data =>{
            $("#likes-count-"+id).text(data.likes);
        });
});

$(".btn-remove").click(function(e){
    e.preventDefault();
    let $this = $(this);
    const response = confirm("Are you sure you want to delete this image?");
    let img;
    if(response){
        img = $this.data("id");
        console.log(img);
    }
    $.ajax({
        url: "/images/"+img,
        type: "DELETE",
    }).done(function(result){
        $this.removeClass("btn-danger").addClass("btn-success");
        $this.html("&#10003 Removed");
        $this.attr("disabled", true);
    })
});