$(document).ready(function()
{
	 $("#alertSuccess").hide();
 	 $("#alertError").hide();
});

$(document).on("click", "#btnSave", function(event)
{ 
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
// Form validation-------------------
var status = validateUserForm(); 
if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
// If valid------------------------
var type = ($("#hididSave").val() == "") ? "POST" : "PUT"; 
 $.ajax( 
 { 
 url : "UserServlet", 
 type : type, 
 data : $("#formUser").serialize(), 
 dataType : "text", 
 complete : function(response, status) 
 { 
 onUserSaveComplete(response.responseText, status); 
 } 
 }); 
});

function onUserSaveComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divUserGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while saving."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while saving.."); 
 $("#alertError").show(); 
 }
$("#hididSave").val(""); 
$("#formUser")[0].reset(); 
}


// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
		{ 
		$("#hididSave").val($(this).data("id")); 
		 $("#userName").val($(this).closest("tr").find('td:eq(0)').text()); 
		 $("#userAddress").val($(this).closest("tr").find('td:eq(1)').text()); 
		 $("#userAccount").val($(this).closest("tr").find('td:eq(2)').text()); 
		 $("#userContact").val($(this).closest("tr").find('td:eq(3)').text()); 
		 $("#userEmail").val($(this).closest("tr").find('td:eq(4)').text()); 
		});
		
		
		
$(document).on("click", ".btnRemove", function(event)
		{ 
		 $.ajax( 
		 { 
		 url : "UserServlet", 
		 type : "DELETE", 
		 data : "id=" + $(this).data("id"),
		 dataType : "text", 
		 complete : function(response, status) 
		 { 
		 onUserDeleteComplete(response.responseText, status); 
		 } 
		 }); 
		});

function onUserDeleteComplete(response, status)
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divUserGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while deleting."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while deleting.."); 
 $("#alertError").show(); 
 } 
}


// CLIENT-MODEL================================================================
function validateUserForm()
{
	// NAME
	if ($("#userName").val().trim() == "")
	{
	return "Insert User_Name.";
	}
	// ADDRESS
	if ($("#userAddress").val().trim() == "")
	{
	return "Insert User_Address.";
	}
	//ACCOUNT NO
	if ($("#userAccount").val().trim() == "")
	{
	return "Insert User_Account_No.";
	}
	//CONTACT NO
	if ($("#userContact").val().trim() == "")
	{
	return "Insert User_Contact_No.";
	}
	//EMAIL
	if ($("#userEmail").val().trim() == "")
	{
	return "Insert Email.";
	}
	return true;
}
