$(document).ready(function () {
    $('#header').load('/resources/header.html');
    $('#footer').load('/resources/footer.html');

    $.ajax({
        url: '/boardList',
        type: 'GET',
        data: { pageNum: 'index', link: '/page/movie', keyword: '' },
        success: function (result) {
            for (item of result) {
                $.ajax({
                    url: '/document',
                    type: 'GET',
                    data: { seq: item.DOCUMENT_SEQ },
                    success: function (result) {
                        if (!result.TITLE_IMG_PATH || result.TITLE_IMG_PATH == 'null') {
                            result.TITLE_IMG_PATH = '/img/imgnull.jpg';
                        }
                        $('.content-section').append('<div class="content-box" id="content' + result.DOCUMENT_SEQ + '"></div>');
                        $('#content' + result.DOCUMENT_SEQ)
                            .append('<img src="' + result.TITLE_IMG_PATH + '" alt="content img" />')
                            .append('<dl><dt>' + result.TITLE + '</dt><dd>' + result.CONTENT + '</dd></dl>')
                            .append('<button type="button" class="move-btn" seq="' + result.DOCUMENT_SEQ + '"><span>read more</span></button>');
                    },
                    error: function (e) {
                        console.log(e);
                    },
                });
            }
        },
        error: function (e) {
            console.log(e);
        },
    });

    $(document).on('click', '.move-btn', function (e) {
        location.href = '/page/document/' + $(this).attr('seq');
    });
});
