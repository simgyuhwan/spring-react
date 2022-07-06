$(document).ready(function(){
    // 목록 조회
    $("#noticeListBtn").on("click", function(){
        $.get("/notices", function(data){
            console.log(data);

            alert(JSON.stringify(data));
        });
    });

    // 상세 조회
    $("#noticeReadBtn").on("click", function(){
     $.ajax({
                type:"GET",
                url : "/notices/" + $("#noticeNo").val(),
                contentType: "application/json; charset=UTF-8",
                success: function(){
                    console.log(data);

                    alert(JSON.stringify(data));

                    $("#noticeTitle").val(data.title);
                    $("#noticeContent").val(data.content);
                },
                error: function(xhr, status, error){
                    alert("code: " + xhr.status + "\n" + "message: " + xhr.responseText+ "\n" + "error:" + error);
                }
            });
    });

    // 등록
    $("#noticeRegisterBtn").on("click", function(){
        var noticeObject = {
            title : $("#noticeTitle").val(),
            content : $("#noticeContent").val()
        };

        alert(JSON.stringify(noticeObject));

        $.ajax({
            type:"POST",
            url : "/notices",
            data: JSON.stringify(noticeObject),
            contentType: "application/json; charset=UTF-8",
            headers : {
                "Authorization" : "Bearer " + ACCESS_TOKEN
            },
            success: function(){
                alert("Created");
            },
            error: function(xhr, status, error){
                alert("code: " + xhr.status + "\n" + "message: " + xhr.responseText+ "\n" + "error:" + error);
            }
        });
    });

    // 삭제
    $("#noticeDeleteBtn").on("click", function(){
        $.ajax({
            type:"DELETE",
            url : "/notices/" + $("#noticeNo").val(),
            contentType: "application/json; charset=UTF-8",
            headers : {
                "Authorization" : "Bearer " + ACCESS_TOKEN
            },
            success: function(){
                alert("Deleted");
            },
            error: function(xhr, status, error){
                alert("code: " + xhr.status + "\n" + "message: " + xhr.responseText+ "\n" + "error:" + error);
            }
        });
    });

    // 수정
    $("#noticeModifyBtn").on("click", function(){
        var noticeNoVal = $("#noticeNo").val();

        var noticeObject = {
            noticeNo : noticeNoVal,
            title : $("#noticeTitle").val(),
            content : $("noticeContent").val()
        };;

        $.ajax({
            type:"PUT",
            url : "/notices/" + noticeNoVal,
            data: JSON.stringify(noticeObject),
            contentType: "application/json; charset=UTF-8",
            headers : {
                "Authorization" : "Bearer " + ACCESS_TOKEN
            },
            success: function(){
                alert("Modified");
            },
            error: function(xhr, status, error){
                alert("code: " + xhr.status + "\n" + "message: " + xhr.responseText+ "\n" + "error:" + error);
            }
        });
    });

    // 입력값 리셋
    $("#noticeResetBtn").on("click", function(){
               $("#noticeNo").val("");
               $("#noticeTitle").val("");
               $("#noticeContent").val("");
    });

});