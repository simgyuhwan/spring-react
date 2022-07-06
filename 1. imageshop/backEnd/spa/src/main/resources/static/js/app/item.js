$(document).ready(function(){
    // 목록 조회
    $("#itemListBtn").on("click", function(){
        $.get("/items", function(data){
            console.log(data);

            alert(JSON.stringify(data));
        });
    });

    // 상세 조회
    $("#itemReadBtn").on("click", function(){
        $.ajax({
            type: "GET",
            url: "/items/" + $("#itemId").val(),
            contentType: "application/json; charset=UTF-8",
            success: function(data){
                console.log("data "  + data);

                alert(JSON.stringify(data));

                console.log("data: " + data.itemId);

                $("#itemName").val(data.itemName);
                $("#itemPrice").val(data.price);
                $("#itemDescription").val(data.description);

                $("#preview").empty();

                var str = "<img src='items/display?itemId="+ data.itemId + "&timestamp=" + new Date().getTime() + "width='210' height='240'>";

                $("#preview").append(str);
                $("#preview2").empty();

                var str2 = "<img src='items/preview?itemId=" + data.itemId + "&timestamp=" + new Date().getTime() + "width='210' height='240'>";

                $("#preview2").append(str2);

            },
            error: function(xhr, status, error){0
                alert("code: " + xhr.status + "\n" + "message : "+ xhr.responseText + "\n" +"error: " + error);
           }
        });
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
        $.ajax({
            type: "DELETE",
            url: "/items/" + $("#itemId").val(),
            contentType: "application/json; charset=UTF-8",
            header: {
                "Authorization" : "Bearer " + ACCESS_TOKEN
            },
            success: function(){
                alert("Deleted");
            },
            error: function(xhr, status, error){
                alert("code: " + xhr.status + "\n" + "message: " + xhr.responseText + "\n" + "error: "+ error);
            }
        });
    });

    // 수정
    $("#itemModifyBtn").on("click", function(){
        console.log("modify");

        var itemIdVal = $("#itemId").val();

        var itemObject = {
            itemId : itemIdVal,
            itemName : $("#itemName").val(),
            price : $("#itemPrice").val(),
            description: $("itemDescription").val()
        };

        var file = $("input[name=picture")[0].file[0];
        var file2 = $("input[name=picture")[1].file[0];

        console.log(file);

        var formData = new FormData();

        formData.append("file", file);
        formData.append("file2", file2);
        formData.append("item", JSON.stringify(itemObject));

        $.ajax({
            url: "/items/" + itemVal,
            data: formData,
            dataType: "text",
            header: {
                "Authorization" : "Bearer" + ACCESS_TOKEN
            },
            processData: false,
            contentType: false,
            type: "PUT",
            success: function(){
                alert("Modified");
            },
            error: function(xhr, status, error){
                alert("code: " + xhr.status + "\n" + "message: " + xhr.responseText + "\n" + "error: "+ error);
            }
        });
    });

    // 입력값 리셋
    $("#itemResetBtn").on("click", function(){
        $("#itemId").val("");
        $("#itemName").val("");
        $("#itemPrice").val("");
        $("#itemDescription").val("");
        $("#preview").empty()
        $("#preview2").empty();
    });

    // 원본 이미지 다운로드
    $("#itemDownloadBtn").on("click", function(){

    });

    $("#itemBuyBtn").on("click", function(){
        $.ajax({
            type: "GET",
            url: "/item/buy/" + $("#itemId").val(),
            contentType : "application/json; charset=UTF-8",
            headers:{
                "Authorization" : "Bearer " + ACCESS_TOKEN
            },
            success : function(data, status, xhr){
                alert(data);
            },
            error: function(xhr, status, error){
                console.log("code: " + xhr.status + "\n" + "message: " + xhr.responseText + "\n" + "error: "+ error);

                var jsonObj = JSON.parse(xhr.responseText);

                alert(jsonObj.message);
            }
        });
    });
});