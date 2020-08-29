var getUrlParameter = function getUrlParameter(sParam) {
    var sPageURL = window.location.search.substring(1),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : decodeURIComponent(sParameterName[1]);
        }
    }
};

var getBoardCount = function (link) {
    var keyword = getUrlParameter('keyword');
    return new Promise(function (resolve, reject) {
        $.ajax({
            url: '/getBoardCount',
            type: 'GET',
            data: { link: link, keyword: keyword },
            success: function (result) {
                resolve(result);
            },
            error: function (e) {
                console.log(e);
                reject();
            },
        });
    });
};
var path = window.location.pathname.split('/');
var pageNum = parseInt(getUrlParameter('pagenum'));
var keyword = getUrlParameter('keyword');
if (!pageNum || pageNum === '') {
    pageNum = 1;
}
var link = '/' + path[1] + '/' + path[2];
$(document).ready(function () {
    $('#header').load('/resources/header.html');
    $('#footer').load('/resources/footer.html');

    $(document).on('click', '.t-content', function () {
        console.log($(this).attr('seq'));
        document.location.href = '/page/document/' + $(this).attr('seq');
    });

    /* 게시글 리스트 바인딩 */
    $.ajax({
        url: '/boardList',
        type: 'GET',
        data: { pageNum: pageNum, link: link, keyword: keyword },
        success: function (result) {
            $('.content-section>table').hide();
            $('.content-section>h2').show();

            for (let item of result) {
                $('.content-section>table>tbody').append('<tr seq="' + item.DOCUMENT_SEQ + '" class="t-content" id="list-' + item.DOCUMENT_SEQ + '"></tr>');
            }

            for (let item of result) {
                $('#list-' + item.DOCUMENT_SEQ)
                    .append('<td class="seq">' + item.DOCUMENT_SEQ + '</td>')
                    .append('<td class="title">' + item.TITLE + ' 다운로드 ' + '</td>')
                    .append('<td class="regdate">' + item.REG_DATE + '</td>')
                    .append('<td class="nicname">' + '관리자' + '</td></tr>');
            }

            $('.content-section>h2').hide();
            $('.content-section>table').show();
        },
        error: function (e) {
            console.log(e);
        },
    });

    /* 페이징 */
    var documentCount;

    getBoardCount(link).then(function (result) {
        var count = parseInt(result, 10);
        if (!keyword || keyword == '') {
            keyword = '';
        }
        var currentPageNum = parseInt(getUrlParameter('pagenum'));
        if (!currentPageNum || currentPageNum === '') {
            currentPageNum = 1;
        }

        var lastPageNum = Math.ceil(currentPageNum / 10.0) * 10;

        if (currentPageNum > Math.ceil(count / 10.0)) {
            currentPageNum = Math.ceil(count / 10.0);
            lastPageNum = Math.ceil(count / 10.0);
        }

        if (currentPageNum + 10 > Math.ceil(count / 10.0)) {
            lastPageNum = Math.ceil(count / 10.0);
        }

        $('.pageNumber-section').html('<a class="page-move-btn" id="prev"><</a>');

        if (count / 10.0 <= 10) {
            for (var i = 1; i <= Math.ceil(count / 10.0); i++) {
                if (keyword) {
                    if (currentPageNum === i) {
                        $('.pageNumber-section').append('<a id="current-page" class="pagenum" href="' + link + '?pagenum=' + i + '&keyword=' + keyword + '">' + i + '</a>');
                    } else {
                        $('.pageNumber-section').append('<a class="pagenum" href="' + link + '?pagenum=' + i + '&keyword=' + keyword + '">' + i + '</a>');
                    }
                } else {
                    if (currentPageNum === i) {
                        $('.pageNumber-section').append('<a id="current-page" class="pagenum" href="' + link + '?pagenum=' + i + '">' + i + '</a>');
                    } else {
                        $('.pageNumber-section').append('<a class="pagenum" href="' + link + '?pagenum=' + i + '">' + i + '</a>');
                    }
                }
            }
        } else {
            for (var i = lastPageNum - 9; i <= lastPageNum; i++) {
                if (keyword) {
                    if (currentPageNum === i) {
                        $('.pageNumber-section').append('<a id="current-page" class="pagenum" href="' + link + '?pagenum=' + i + '&keyword=' + keyword + '">' + i + '</a>');
                    } else {
                        $('.pageNumber-section').append('<a class="pagenum" href="' + link + '?pagenum=' + i + '&keyword=' + keyword + '">' + i + '</a>');
                    }
                } else {
                    if (currentPageNum === i) {
                        $('.pageNumber-section').append('<a id="current-page" class="pagenum" href="' + link + '?pagenum=' + i + '">' + i + '</a>');
                    } else {
                        $('.pageNumber-section').append('<a class="pagenum" href="' + link + '?pagenum=' + i + '">' + i + '</a>');
                    }
                }
            }
        }

        $('.pageNumber-section').append('<a class="page-move-btn" id="next">></a>');
        documentCount = result;
    });

    $(document).on('click', '.page-move-btn', function () {
        var id = this.id;
        var currentPageNum = pageNum;
        if (id === 'prev') {
            var num;
            if (currentPageNum % 10 === 0) {
                num = currentPageNum - 10;
            } else {
                num = currentPageNum - (currentPageNum % 10) - 9;
            }
            if (num < 0) {
                num = 1;
            }
            window.location.href = link + '?pagenum=' + num;
        } else if (id === 'next') {
            var num;
            var lastPage = Math.ceil(documentCount / 10.0);
            if (currentPageNum + 1 < lastPage) {
                if (currentPageNum % 10 === 0) {
                    num = currentPageNum + 1;
                } else {
                    num = currentPageNum - (currentPageNum % 10) + 11;
                }
            } else {
                num = lastPage;
            }

            if (!keyword || keyword == '') {
                window.location.href = link + '?pagenum=' + num;
            } else {
                window.location.href = link + '?pagenum=' + num + '&keyword=' + keyword;
            }
        }
    });

    $('#search-btn').click(function () {
        var keyword = $('#search-keyword').val();
        if (keyword) {
            document.location.href = link + '?pagenum=1&keyword=' + keyword;
        } else {
            document.location.href = link;
        }
    });

    $('#search-keyword').keydown(function (e) {
        if (e.keyCode == '13') {
            var keyword = $('#search-keyword').val();
            if (keyword) {
                document.location.href = link + '?pagenum=1&keyword=' + keyword;
            } else {
                document.location.href = link;
            }
        }
    });
});
