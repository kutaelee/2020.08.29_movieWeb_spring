var path=window.location.pathname.split('/');
$(document).ready(function () {
    $('#header').load('/resources/header.html');
    $('#footer').load('/resources/footer.html');
   
    $.ajax({
    	url:'/document',
    	type:'GET',
    	data:{'seq':path[3]},
    	success:function(result){
    		console.log(result)
    		document.title=result.TITLE+' - 무비조아';
    		$('#document-title').text(result.TITLE);
    		$('#document-img').attr('src',result.TITLE_IMG_PATH);
    		$('#document-img').attr('alt',result.TITLE+' 포스터');
    		$('#document-regDate').text('작성일자 : '+result.REG_DATE);
    		$('#story').html(result.CONTENT);
    		$('#people').html(result.PEOPLE);
    	},
    	error:function(e){
    		console.log(e);
    	}
    });
});
