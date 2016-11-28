$(document).ready(function() {
	// form
	var feedbackFormDiv = $('#feedbackForm');
	$('#btnGetInvoice').click(function() {
		$('#responseForm').html('');
		feedbackFormDiv.html('');
		if ($('#username').val() == '') {
			feedbackFormDiv.html('Username is not set!');
			handleFeedbackClass(feedbackFormDiv, false);
		} else {
			$.ajax({
				type : "GET",
				url : "api/invoices?username=" + $('#username').val(),
				timeout : 100000,
				success : function(data) {
					$(feedbackFormDiv).removeClass();
					var text = '';
					var total = data.length;
					if (total == 0) {
						feedbackFormDiv.html('<p>No invoices found in system.</p>');
						feedbackFormDiv.addClass('alert alert-warning');
					} else {
						text += '<h3>' + data[0].user.customerType + ' customer parks ' + total;
						if(total == 1) {
							text +=' time';
						} else {
							text +=' times';
						}
						text +='</h3>';
						text += '<ul>';
						var totalSum = 0;
						var currency = '';
						var sums = [];
						$.each(data, function(i, invoice) {
							$.each(invoice.fees, function(k, fee) {
								var part = fee.parts[0];
								text += '<li>';
								text += fee.start + ' - ' + fee.end;
								text += ' ('+ part.count + ' * ' + money(part.cost);
								text += ' = ' + money(fee.totalCost) + ' ' + fee.currency + ')';
								text += '</li>';
								sums.push(money(fee.totalCost));
								totalSum += fee.totalCost;
								currency = fee.currency;
							});
						});
						text += '</ul>';
						text += '<h4>Total invoice: ' + sums.join(' + ') + " = " + money(totalSum) + ' ' + currency;
						text += '</h4>';
					}
					$('#responseForm').html(text);
				},
				error : function(xhr, status, error) {
					feedbackFormDiv.html(xhr.responseText);
					handleFeedbackClass(feedbackFormDiv, false);
				}
			});
		}
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
				handleFeedbackClass(feedbackDiv, true);
				feedbackDiv.html('Mock data added.');
			},
			error : function(e) {
				handleFeedbackClass(feedbackDiv, false);
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
				handleFeedbackClass(feedbackDiv, true);
				var text = '';
				for ( var key in data) {
					text += '<p>Username: ' + data[key].username + '</p>';
				}
				if (data.length == 0) {
					text += '<p>No users found in system.</p>';
					feedbackDiv.addClass('alert alert-warning');
				}
				feedbackDiv.html(text);
			},
			error : function(e) {
				handleFeedbackClass(feedbackDiv, false);
				feedbackDiv.html("Users can't be shown!");
			}
		});
	});

	function handleFeedbackClass(div, isSuccess) {
		$(div).removeClass();
		if (isSuccess) {
			div.addClass('alert alert-success');
		} else {
			div.addClass('alert alert-danger');
		}
	}

	function money(cents) {
		return (cents / 100).toFixed(2);
	}
});