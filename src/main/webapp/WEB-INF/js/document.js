var path = window.location.pathname.split('/');
$(document).ready(function () {
    $('#header').load('/resources/header.html');
    $('#footer').load('/resources/footer.html');

    $.ajax({
        url: '/document',
        type: 'GET',
        data: { seq: path[3] },
        success: function (result) {
            if (!result.TITLE_IMG_PATH || result.TITLE_IMG_PATH == 'null') {
                result.TITLE_IMG_PATH = '/img/imgnull.jpg';
            }
            document.title = result.TITLE + ' - 무비조아';
            $('#document-title').text(result.TITLE);
            $('#document-img').attr('src', result.TITLE_IMG_PATH);
            $('#document-img').attr('alt', result.TITLE + ' 포스터');
            $('#document-regDate').text('작성일자 : ' + result.REG_DATE);
            $('#story').html(result.CONTENT);
            $('#people').html(result.PEOPLE);
        },
        error: function (e) {
            console.log(e);
        },
    });

    $('#back-btn').click(function () {
        history.go(-1);
    });
    $('#scroll-btn').click(function () {
        var top = $('#download-button').offset().top;
        $('html, body').animate({ scrollTop: top }, 1000);
    });

    $('#download-button').click(function () {
        var title = $('#document-title').text();
        window.open('http://www.filekok.com/partner/?p_id=mmk&path=search&section=&search_keyword=title&search=' + title + '&pop=ci&utm_source=partner&utm_medium=cpa', '_blank');
    });
});
