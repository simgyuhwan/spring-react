$(document).ready(function(){
    //목록 조회
    $("#boardListBtn").on("click", function(){
        $.ajax({
            type: "GET",
            url: "/boards",
            contentType : "application/json; charset=UTF-8",
            success: function(data){
                console.log(data);

                alert(JSON.stringify(data));
            },
            error: function(xhr, status, error){
                 alert("code: " + xhr.status + "\n" + "message : "+ xhr.responseText + "\n" +"error: " + error);
            }
        });
    });

    // 상세 조회
    $("#boardReadBtn").on("click", function(){
        $.ajax({
            type: "GET",
            url: "/boards/" + $("#boardNo").val(),
            contentType: "application/json, charset=UTF-8",
            success: function(data){
                console.log(data);

                alert(JSON.stringify(data));

                $("#boardTitle").val(data.title);
                $("#boardContent").val(data.content);
                $("#boardWriter").val(data.writer);
            },
            error: function(xhr, status, error){
                 alert("code: " + xhr.status + "\n" + "message : "+ xhr.responseText + "\n" +"error: " + error);
            }
        });
    });

    // 등록
    $("#boardRegisterBtn").on("click", function(){
        var boardObject = {
            title : $("#boardTitle").val(),
            content : $("#boardContent").val(),
            writer : $("#boardWriter").val()
        };

        $.ajax({
            type: "POST",
            url: "/boards",
            data: JSON.stringify(boardObject),
            contentType: "application/json; charset=UTF-8",
            headers :{
                "Authorization" : "Bearer " + ACCESS_TOKEN
            },
            success: function (){
                alert("Created");
            },
            error: function(xhr, status, error){
                 alert("code: " + xhr.status + "\n" + "message : "+ xhr.responseText + "\n" +"error: " + error);
            }
        });
    });

    // 삭제
    $("#boardDeleteBtn").on("click",function(){
        $.ajax({
            type: "DELETE",
            url: "/boards/" + $("#boardNo").val() + "?writer=" + $("#boardWriter").val(),
            contentType: "application/json; charset=UTF-8",
            headers : {
                "Authorization" : "Bearer " + ACCESS_TOKEN
            },
            success: function(){
                alert("Delete");
            },
            error: function(xhr, status, error){
                 alert("code: " + xhr.status + "\n" + "message : "+ xhr.responseText + "\n" +"error: " + error);
            }
        });
    });

    // 수정
    $("#boardModifyBtn").on("click",function(){
        var boardNoVal = $("#boardNo").val();

        var boardObject ={
            boardNo : boardNoVal,
            title : $("#boardTitle").val(),
            content : $("#boardContent").val(),
            writer : $("#boardWriter").val()
        };

        $.ajax({
            type: "PUT",
            url: "/boards/" + boardNoVal,
            data: JSON.stringify(boardObject),
            contentType: "application/json; charset=UTF-8",
            headers : {
                "Authorization" : "Bearer " + ACCESS_TOKEN
            },
            success : function(){
                alert("Modify");
            },
            error: function(xhr, status, error){
                 alert("code: " + xhr.status + "\n" + "message : "+ xhr.responseText + "\n" +"error: " + error);
            }
        });
    });

    // 입력값 리셋
    $("#boardResetBtn").on("click",function(){
        $("#boardNo").val("");
        $("#boardTitle").val("");
        $("#boardContent").val("");
        $("#boardWriter").val("");
    });
});
