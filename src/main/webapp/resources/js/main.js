$(document).ready(function() {
	// form
	var feedbackFormDiv = $('#feedbackForm');
	$('#btnGetInvoice').click(function() {
		$.ajax({
			type : "GET",
			url : "api/invoices?username=" + $('#username').val(),
			timeout : 100000,
			success : function(data) {
				handleClassAfterCall(feedbackFormDiv, true);
				feedbackFormDiv.html("done");
			},
			error : function(e) {
				handleClassAfterCall(feedbackFormDiv, false);
			}
		});
	});

	// helper actions
	var feedbackDiv = $('#feedbackAction');
	$('#addMock').click(function() {
		feedbackDiv.removeClass();
		$.ajax({
			type : "GET",
			url : "api/mock",
			timeout : 100000,
			success : function(data) {
				handleClassAfterCall(feedbackDiv, true);
				feedbackDiv.html('Mock data added.');
			},
			error : function(e) {
				handleClassAfterCall(feedbackDiv, false);
				feedbackDiv.html('Mock data can be added only 1 time!');
			}
		});
	});
	$('#showUsers').click(function() {
		feedbackDiv.removeClass();
		$.ajax({
			type : "GET",
			url : "api/users",
			timeout : 100000,
			success : function(data) {
				handleClassAfterCall(feedbackDiv, true);
				var text = '';
				for ( var key in data) {
					text += '<p>Username: ' + data[key].username + '</p>';
				}
				feedbackDiv.html(text);
			},
			error : function(e) {
				handleClassAfterCall(feedbackDiv, false);
				feedbackDiv.html("Users can't be shown!");
			}
		});
	});

	function handleClassAfterCall(div, isSuccess) {
		if (isSuccess) {
			div.addClass('alert alert-success');
		} else {
			div.addClass('alert alert-danger');
		}
	}
});