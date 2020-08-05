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
var pageNum=getUrlParameter('pagenum');
$(document).ready(function () {
    $('#header').load('/resources/header.html');
    $('#footer').load('/resources/footer.html');

    $(document).on('click','.t-content',function () {
        console.log($(this).attr('seq'));
        document.location.href = '/page/document/' + $(this).attr('seq');
    });
    
    $.ajax({
    	url:'/boardList',
    	type:'GET',
    	data:{'pageNum':pageNum},
    	success:function(result){
    		
    		for (let item of result) {
    		$('.content-section>table>tbody').append('<tr seq="'+item.DOCUMENT_SEQ+'" class="t-content" id="list-'+item.DOCUMENT_SEQ+'"></tr>');
    		}
    		
    		for(let item of result){
    			$('#list-'+item.DOCUMENT_SEQ)
        		.append('<td class="seq">'+item.DOCUMENT_SEQ+'</td>')
        		.append('<td class="title">'+item.TITLE+'</td>')
        		.append('<td class="regdate">'+item.REG_DATE+'</td>')
        		.append('<td class="nicname">'+'관리자'+'</td></tr>');
    		}
    	},
    	error:function(e){
    		console.log(e);
    	}
    });
	
    
    
});
