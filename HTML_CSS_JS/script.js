jQuery(document).ready(function(){
    $(".jobsearch-sugges-search").find("input").attr("placeholder", "Title, Keywords, or Phrase");
    $("#menu-main-menu").after('<a href="http://" class="careerfy-simple-btn careerfy-bgcolor"><span></span></a>');
    $("#menu-main-menu").after('<a href="http://" class="careerfy-simple-btn careerfy-bgcolor"><span></span></a>');
    $(".jobsearch-employer-profile-submit").val("Post");
    function walkText(node) {
        if (node.nodeType == 3) {
            node.data = node.data.replace(/Job/g, "Scholarship");
            node.data = node.data.replace(/job/g, "scholarship");
            node.data = node.data.replace(/JOB/g, "SCHOLARSHIP");
            node.data = node.data.replace(/employer/g, "organization");
            node.data = node.data.replace(/Employer/g, "Organization");
            node.data = node.data.replace(/EMPLOYER/g, "ORGANIZATION");
        }
        if (node.nodeType == 1 && node.nodeName != "SCRIPT") {
            for (var i = 0; i < node.childNodes.length; i++) {
            walkText(node.childNodes[i]);
            }
        }
    }
    walkText(document.body);
});