$(function() {
	$("#submitUserForm").submit(function(e) {
		e.preventDefault();
		var frm = $("#submitUserForm");
		var data = {};
		$.each(this, function(i, v){
			var input = $(v);
			data[input.attr("name")] = input.val();
			delete data["undefined"];
		});
		saveRequestedData(frm, data, "user");
	});
	
	$("#submitAddressForm").submit(function(e) {
		e.preventDefault();
		var frm = $("#submitAddressForm");
		var data = {};
		$.each(this, function(i, v){
			var input = $(v);
			data[input.attr("name")] = input.val();
			delete data["undefined"];
		});
		saveRequestedData(frm, data, "address");
	});
	
	$("#uploadImage").on("submit", function(e) {
		e.preventDefault();
		var formData = new FormData(this);
		$.ajax({
			type:"POST",
			url:"/mightyjava/user/upload",
			data:formData,
			cache:false,
			dataType:"json",
			contentType:false,
			processData:false,
			success:function(data) {
				var title="Upload Confirmation";
				if(data.status == "success") {
					toastr.success(data.message, title, {
						closeButton:true
					});
					fetchList("user");
					$("#uploadModal").modal("hide");
					$("body").removeClass("modal-open");
					$(".modal-backdrop").remove();
				} else {
					toastr.error(data.message, title, {
						allowHtml:true,
						closeButton:true
					});
				}
			}
		});
	});
});

function saveRequestedData(frm, data, type) {
	$.ajax({
		contentType:"application/json; charset=utf-8",
		type:frm.attr("method"),
		url:frm.attr("action"),
		dataType:'json',
		data:JSON.stringify(data),
		success:function(data) {
			if(data.status == "success") {
				toastr.success(data.message, data.title, {
					closeButton:true
				});
				fetchList(type);
			} else {
				toastr.error(data.message, data.title, {
					allowHtml:true,
					closeButton:true
				});
			}
		}
	});
}