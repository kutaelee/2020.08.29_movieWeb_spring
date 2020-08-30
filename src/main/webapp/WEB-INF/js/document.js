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
                result.TITLE_IMG_PATH = '/img/coming_soon.jpg';
            }
            document.title = result.TITLE + ' - 파일콕';
            $('#document-title').text(result.TITLE);
            $('#document-tag').text(result.TITLE + ' 다시보기 ' + result.TITLE + ' 다운로드 ');
            $('#document-img').attr('src', result.TITLE_IMG_PATH);
            $('#document-img').attr('alt', result.TITLE + ' 포스터');
            $('#document-regDate').text('작성일자 : ' + result.REG_DATE);
            $('#story').html(result.CONTENT + '<br/>' + result.TITLE + ' 다시보기 ' + result.TITLE + ' 다운받기 <br/> <br/>출처:네이버 영화');
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

    $('#coupon-button').click(function () {
        var dummy = document.createElement('textarea');
        document.body.appendChild(dummy);
        dummy.value = 'AA0002414';
        dummy.select();
        document.execCommand('copy');
        document.body.removeChild(dummy);
        alert('쿠폰번호가 복사되었습니다! \n쿠폰번호 : AA0002414 ');
    });
});
