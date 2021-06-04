/*
 * ads rotator
 * Author: Lucas Forchino
 * WebSite: http://www.jqueryload.com
 */
$(document).ready(function(){
    $('.carrousel_right').click(function(){
        var elementsCount=$('carrousel_inner ul li').size();
        var position =$('.carrousel').attr('data-pos');
        position=parseInt(position, 10);
        var elementsCount=$('.carrousel ul li').size();
        elementsCount=elementsCount -3;
        if (position<elementsCount)
        {
            position=position+1;
            $('.carrousel_left').removeClass('left_inactive');
            if (position==elementsCount){
                $(this).addClass('right_inactive');
            }
            var pos=position*200;
            $('.carrousel').attr('data-pos',position);
            $('.carrousel_inner').animate({'scrollLeft' : pos });
        }
    });

    $('.carrousel_left').click(function(){
        var position =$('.carrousel').attr('data-pos');
        var elementsCount=$('carrousel_inner ul li').size();
        position=parseInt(position, 10);
        if (position>0)
        {
            $('.carrousel_right').removeClass('right_inactive');
            position=position-1;
            if(position==0)
                {
                    $(this).addClass('left_inactive');
                }
            var pos=position*200;
            $('.carrousel').attr('data-pos',position);
            $('.carrousel_inner').animate({'scrollLeft' : pos });
        }
    });
});