$(document).ready(function () {
    $('#header').load('/resources/header.html');
    $('#footer').load('/resources/footer.html');

    $('.t-content').click(function () {
        console.log($(this).attr('seq'));
        document.location.href = '/page/document/' + $(this).attr('seq');
    });
});
