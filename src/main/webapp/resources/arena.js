
//execute when DOM is ready
$(document).ready(function() {

	function tmp() {
		console.log('tmp');
	}

	function removeElements(toRemove) {
		
		for (var i in toRemove) {
			var index = rows.toRemove(i);
			if (index > -1) {
			    toRemove.splice(index, 1);
			}
		}

		console.log($(rows));
		console.log(toRemove);
	}
	
	function appendToLink(value) {
		
		var aceId = /^[0-9]{1,2}$/;		// one or two digits. 			Ex: 1,23
		var sopId = /^S[0-9]{1,2}$/;	// an S with one or two digits. Ex: S3,S10
		
		if ( aceId.exec(value) ) {
			return $('<a target="_blank" href="#" class="acelink" value="' + value + '">' + value + '</a>');
		}
		
		if ( sopId.exec(value) ) {
			return $('<a target="_blank" href="#" class="soplink" value="' + value.slice(1) + '">' + value + '</a>');
		}
		
		return value;
	};
	
	function processTimeCell(cells) {
		gCells = cells;
	};
	
	function processLastCell(last) {
		
		var text = last.html();
		
		if ( text == null ) text = "";
		
		text = text.replace("<br>"," <br> ");
		var links = text.split(/-| /);
		last.text("");

		$.each(links,function(key,val) {
			last.append(appendToLink(val));
			last.append(" ");
		})
	};
	
	rows = $('table').find('tr');
	
	rows.each(function(key,val) {

		if ( key != 0 ) {
			
			var cells = $(val).find('td');

			if ( key == 1 ) processTimeCell(cells);

			processLastCell(cells.last());
		}
	});		
	
	$('.acelink').click(function(event) {

		event.preventDefault();
		link = $(this);
		var paramValue = "av" + link.attr("value");

		$.ajax({
			url: "home", 
			type: "GET",
			data: {ace: paramValue },
			success: function(result) {
				link.attr("href",result);
				window.open(result,'_blank');
			}
		});
	});
	
	$('.soplink').click(function(event) {

		event.preventDefault();
		link = $(this);
		var paramValue = "avs" + link.attr("value");

		$.ajax({
			url: "home", 
			type: "GET",
			data: {sop: paramValue },
			success: function(result) {
				link.attr("href",result);
			}
		});
	});
	
	handleClick = function(event,arg,param,callback) {
		
		event.preventDefault();
		link = $(this);
		var paramValue = arg + link.attr("value");
		
		$.ajax({
			url: "home", 
			type: "GET",
			data: {sop: paramValue },
			success: callback		
		});
	}
});