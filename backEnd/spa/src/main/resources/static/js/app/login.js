var ACCESS_TOKEN = "";

$(document).ready(function(){
    // 로그인
    $("#loginBtn").on("click", function(){
        $.ajax({
            type: "POST",
            url: "api/authenticate?username=" + $("#userId").val() + "&password=" + $("#password").val(),
            success: function(data, textStatus, request){
                var responseHeader = request.getResponseHeader('Authorization');
                alert(responseHeader);

                ACCESS_TOKEN = responseHeader.subst(7);

                console.log(ACCESS_TOKEN);
            },
            error: function(request, textStatus, errorThrown){
                alert(request.getResponseHeader('Authorization'));
            }
        });
    });

    // 로그인 사용자 정보
    $("#myInfoBtn").on("click", function(){
        $.ajax({
            type: "GET",
            url : "/users/myinfo/",
            contentType: "application/json; charset=UTF-8",
            headers: {
                "Authorization" : "Bearer " + ACCESS_TOKEN
            },
            success : function(data){
                console.log(data);

                alert(JSON.stringify(data));
            },
            error: function(xhr, status, error){0
                alert("code: " + xhr.status + "\n" + "message : "+ xhr.responseText + "\n" +"error: " + error);
           }
        });
    });
});

