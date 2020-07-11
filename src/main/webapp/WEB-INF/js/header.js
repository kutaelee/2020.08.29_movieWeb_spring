window.onload = function () {
    console.log('a');
    // 모바일 네비게이션 토글
    $('#menu-toggle').click(function () {
        let nav = document.getElementsByClassName('nav');
        let menu = document.getElementById('nav-menu');
        if (this.className === 'menu-toggle') {
            this.className = 'change';
            nav[0].style.display = 'block';
        } else {
            this.className = 'menu-toggle';
            nav[0].style.display = 'none';
        }
    });

    window.addEventListener('resize', menuToggle);
    function menuToggle() {
        let nav = document.getElementsByClassName('nav');
        let toggleBtn = document.getElementById('menu-toggle');
        if (window.innerWidth < 600) {
            toggleBtn.className = 'menu-toggle';
            nav[0].style.display = 'none';
        } else {
            toggleBtn.className.className = 'change';
            nav[0].style.display = 'block';
        }
    }

    $.ajax({
        url: 'naviList',
        type: 'GET',
        success: function (result) {
            console.log(result);
        },
        error: function (e) {
            console.log(e);
        },
    });
};
