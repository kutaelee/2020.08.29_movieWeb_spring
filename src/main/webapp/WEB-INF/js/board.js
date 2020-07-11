$(document).ready(function () {
    $('#header').load('/resources/header.html?ver=3');
    $('#footer').load('/resources/footer.html');

    $('.t-content').click(function () {
        console.log($(this).attr('seq'));
        document.location.href = '/document?seq=' + $(this).attr('seq');
    });
});
