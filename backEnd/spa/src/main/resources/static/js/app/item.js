$(document).ready(function(){
    // 목록 조회
    $("#itemListBtn").on("click", function(){

    });

    // 상세 조회
    $("#itemReadBtn").on("click", function(){

    });

    // 등록
    $("#itemRegisterBtn").on("click",function(){
        console.log("register");

        var itemObject = {
            itemName : $("#itemName").val(),
            price : $("#itemPrice").val(),
            description : $("#itemDescription").val()
        };

        var file = $("input[name=picture]")[0].files[0];
        var file2 = $("input[name=picture]")[1].files[0];

        var formData = new FormData();

        formData.append("file", file);
        formData.append("file2", file2);
        formData.append("item", JSON.stringify(itemObject));

        $.ajax({
            url: "/items",
            data: formData,
            dataType: "text",
            headers : {
                "Authorization" : "Bearer " + ACCESS_TOKEN
            },
            processData: false,
            contentType: false,
            type: "POST",
            success: function(){
                alert("Created");
            },
            error: function(xhr, status, error){
                alert("code: " + xhr.status + "\n" + "message: " + xhr.responseText + "\n" + "error: "+ error);
            }
        });
    });

    // 삭제
    $("#itemDeleteBtn").on("click", function(){

    });

    // 수정
    $("#itemModifyBtn").on("click", function(){

    });

    // 입력값 리셋
    $("#itemResetBtn").on("click", function(){

    });

    // 원본 이미지 다운로드
    $("#itemDownloadBtn").on("click", function(){

    });
});